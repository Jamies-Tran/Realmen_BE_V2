package com.capstone.realmen.service.booking;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EBookingStatus;
import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.data.dto.booking.Booking;
import com.capstone.realmen.data.dto.booking.IBookingMapper;
import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.repository.database.booking.BookingEntity;
import com.capstone.realmen.repository.database.booking.IBookingRepository;
import com.capstone.realmen.service.account.AccountCommandService;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.AccountCreateRequire;
import com.capstone.realmen.service.account.data.AccountSearchByField;
import com.capstone.realmen.service.booking.data.BookingCreateRequire;
import com.capstone.realmen.service.booking.data.BookingDeleteRequire;
import com.capstone.realmen.service.booking.data.BookingSearchCriteria;
import com.capstone.realmen.service.booking.helpers.BookingHelper;
import com.capstone.realmen.service.booking.other.BookingServiceCommandService;
import com.capstone.realmen.service.booking.other.data.BookingServiceCreateRequire;
import com.capstone.realmen.service.booking.other.data.BookingServiceDeleteRequire;
import com.capstone.realmen.service.branch.others.services.BranchServiceQueryService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchCriteria;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.DailyPlanServiceQueryService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingCommandService extends BookingHelper {
        @NonNull
        IBookingRepository repository;

        @NonNull
        IBookingMapper mapper;

        @NonNull
        BookingServiceCommandService bsCommand;

        @NonNull
        BookingQueryService bQuery;

        @NonNull
        AccountCommandService aCommand;

        @NonNull
        AccountQueryService aQuery;

        @NonNull
        DailyPlanServiceQueryService dpQuery;

        @NonNull
        BranchServiceQueryService brsQuery;

        @NonNull
        RequestContext requestContext;

        public void saveByRecept(BookingCreateRequire createRequire) {
                BranchServiceSearchCriteria brsSearchCriteria = BranchServiceSearchCriteria.searchByBrIdAndBrsIds(
                                createRequire.booking().branchId(),
                                createRequire.dailyPlanServiceIds());
                List<BranchService> searvices = brsQuery
                                .findAll(brsSearchCriteria, PageRequestCustom.unPaged())
                                .getContent();
                if (searvices.isEmpty())
                        throw new NotFoundException("Không tìm thấy dịch vụ");

                Account requestAccount = requestContext.getAccount();
                Long bookingId;
                if (createRequire.isAccountIdEmpty()) {
                        AccountCreateRequire aCreateRequire = AccountCreateRequire
                                        .ofReceptionist(createRequire.customer());
                        AccountCreated cusCreated = aCommand.createAccount(aCreateRequire);
                        BookingEntity newBooking = mapper.toEntity(createRequire.booking());
                        BookingEntity updatedBooking = createBookingByRole(newBooking, requestAccount.roleCode(),
                                        cusCreated.accountId());

                        BookingEntity savedBooking = repository.save(updatedBooking);
                        bookingId = savedBooking.getBookingId();
                } else {
                        AccountSearchByField aSearchByField = AccountSearchByField.of(createRequire.customerPhone());
                        Account cusAccount = aQuery.find(aSearchByField);
                        BookingSearchCriteria searchCriteria = BookingSearchCriteria.of(
                                        cusAccount.accountId(),
                                        createRequire.booking().branchId(),
                                        List.of(EBookingStatus.DRAFT.getCode()),
                                        createRequire.booking().bookedAt());
                        List<Booking> bookings = bQuery
                                        .findAll(searchCriteria, PageRequestCustom.unPaged())
                                        .getContent();

                        if (Objects.nonNull(bookings) && !bookings.isEmpty()) {
                                Booking draftBooking = bookings.stream().findFirst().get();
                                bookingId = draftBooking.bookingId();
                        } else {
                                BookingEntity newBooking = mapper.toEntity(createRequire.booking());
                                BookingEntity updateNewBooking = createBookingByRole(newBooking,
                                                requestAccount.roleCode(),
                                                cusAccount.accountId()).setAudit(requestContext.auditCreate());

                                BookingEntity savedBooking = repository.save(updateNewBooking);
                                bookingId = savedBooking.getBookingId();
                        }
                }

                BookingServiceCreateRequire bsCreateRequire = BookingServiceCreateRequire
                                .createByBranchService(bookingId, createRequire.bookingServices(), searvices);

                bsCommand.saveListByBranchService(bsCreateRequire);

        }

        public Booking saveByCus(BookingCreateRequire createRequire) {
                Account requestAccount = requestContext.getAccount();
                BookingSearchCriteria searchCriteria = BookingSearchCriteria.of(
                                requestAccount.accountId(),
                                createRequire.booking().branchId(),
                                List.of(EBookingStatus.DRAFT.getCode()),
                                createRequire.booking().dailyPlanId());
                List<Booking> bookings = bQuery
                                .findAll(searchCriteria, PageRequestCustom.unPaged())
                                .getContent();

                DailyPlanServiceSearchCriteria dpsSearchCriteria = DailyPlanServiceSearchCriteria.ofDailyPlanServiceIds(
                                createRequire.dailyPlanServiceIds(),
                                createRequire.booking().branchId(),
                                createRequire.booking().dailyPlanId());
                List<DailyPlanService> searvices = dpQuery
                                .findAll(dpsSearchCriteria, PageRequestCustom.unPaged())
                                .getContent();
                if (searvices.isEmpty())
                        throw new NotFoundException("Các dịch vụ này không có trong kế hoạch hoạt động của chi nhánh");

                if (Objects.nonNull(bookings) && !bookings.isEmpty()) {
                        Booking draftBooking = bookings.stream().findFirst().get();
                        BookingServiceCreateRequire bsCreateRequire = BookingServiceCreateRequire
                                        .createByDailyPlanService(draftBooking.bookingId(),
                                                        createRequire.bookingServices(), searvices);

                        bsCommand.saveListByDailyPlanService(bsCreateRequire);
                        return draftBooking;
                } else {

                        BookingEntity newBooking = mapper.toEntity(createRequire.booking());
                        BookingEntity updateNewBooking = createBookingByRole(newBooking, requestAccount.roleCode(),
                                        requestAccount.accountId())
                                        .setAudit(requestContext.auditCreate());

                        BookingEntity savedBooking = repository.save(updateNewBooking);
                        BookingServiceCreateRequire bsCreateRequire = BookingServiceCreateRequire
                                        .createByDailyPlanService(savedBooking.getBookingId(),
                                                        createRequire.bookingServices(), searvices);

                        bsCommand.saveListByDailyPlanService(bsCreateRequire);
                        return mapper.toDto(savedBooking);
                }

        }

        public void delete(BookingDeleteRequire deleteRequire) {
                BookingEntity foundBooking = repository.findById(deleteRequire.bookingId())
                                .orElseThrow(NotFoundException::new);

                repository.delete(foundBooking);

                BookingServiceDeleteRequire bsDeleteRequire = BookingServiceDeleteRequire.of(deleteRequire.bookingId());
                bsCommand.deleteAllByBookingId(bsDeleteRequire);
        }
}
