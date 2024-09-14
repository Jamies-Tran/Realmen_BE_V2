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
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.repository.database.booking.BookingEntity;
import com.capstone.realmen.repository.database.booking.IBookingRepository;
import com.capstone.realmen.service.account.AccountCommandService;
import com.capstone.realmen.service.account.data.AccountCreateRequire;
import com.capstone.realmen.service.booking.data.BookingCreateRequire;
import com.capstone.realmen.service.booking.data.BookingDeleteRequire;
import com.capstone.realmen.service.booking.data.BookingSearchCriteria;
import com.capstone.realmen.service.booking.helpers.BookingHelper;
import com.capstone.realmen.service.booking.other.BookingServiceCommandService;
import com.capstone.realmen.service.booking.other.data.BookingServiceCreateRequire;
import com.capstone.realmen.service.booking.other.data.BookingServiceDeleteRequire;
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
        DailyPlanServiceQueryService dpQuery;

        @NonNull
        RequestContext requestContext;

        public void save(BookingCreateRequire createRequire) {
                DailyPlanServiceSearchCriteria dpsSearchCriteria = DailyPlanServiceSearchCriteria.of(
                                createRequire.booking().dailyPlanId(),
                                createRequire.booking().branchId(),
                                createRequire.serviceIds());
                List<DailyPlanService> searvices = dpQuery
                                .findAll(dpsSearchCriteria, PageRequestCustom.unPaged())
                                .getContent();
                if (searvices.isEmpty())
                        throw new NotFoundException("Không tìm thấy dịch vụ");

                Account requestAccount = requestContext.getAccount();
                AccountCreateRequire aCreateRequire = AccountCreateRequire.ofReceptionist(createRequire.customer());
                AccountCreated cusCreated = aCommand.createAccount(aCreateRequire);
                BookingEntity newBooking = mapper.toEntity(createRequire.booking());

                BookingEntity updatedBooking = createBookingByRole(newBooking, requestAccount.roleCode(),
                                cusCreated.accountId());
                repository.save(updatedBooking);

        }

        public void saveOrUpdate(BookingCreateRequire createRequire) {
                BookingSearchCriteria searchCriteria = BookingSearchCriteria.of(
                                createRequire.booking().branchId(),
                                List.of(EBookingStatus.DRAFT.getCode()),
                                createRequire.booking().dailyPlanId());
                List<Booking> bookings = bQuery
                                .findAll(searchCriteria, PageRequestCustom.unPaged())
                                .getContent();

                DailyPlanServiceSearchCriteria dpsSearchCriteria = DailyPlanServiceSearchCriteria.of(
                                createRequire.booking().dailyPlanId(),
                                createRequire.booking().branchId(),
                                createRequire.serviceIds());
                List<DailyPlanService> searvices = dpQuery
                                .findAll(dpsSearchCriteria, PageRequestCustom.unPaged())
                                .getContent();
                if (searvices.isEmpty())
                        throw new NotFoundException("Các dịch vụ này không có trong kế hoạch hoạt động của chi nhánh");

                Long bookingId;
                if (Objects.nonNull(bookings) && !bookings.isEmpty()) {
                        Booking draftBooking = bookings.stream().findFirst().get();
                        bookingId = draftBooking.bookingId();
                } else {
                        Account requestAccount = requestContext.getAccount();
                        BookingEntity newBooking = mapper.toEntity(createRequire.booking());
                        BookingEntity updateNewBooking = createBookingByRole(newBooking, requestAccount.roleCode(),
                                        requestAccount.accountId())
                                        .setAudit(requestContext.auditCreate());

                        BookingEntity savedBooking = repository.save(updateNewBooking);
                        bookingId = savedBooking.getBookingId();
                }

                BookingServiceCreateRequire bsCreateRequire = BookingServiceCreateRequire
                                .of(bookingId, createRequire.bookingServices(), searvices);

                bsCommand.saveAll(bsCreateRequire);
        }

        public void delete(BookingDeleteRequire deleteRequire) {
                BookingEntity foundBooking = repository.findById(deleteRequire.bookingId())
                                .orElseThrow(NotFoundException::new);

                repository.delete(foundBooking);

                BookingServiceDeleteRequire bsDeleteRequire = BookingServiceDeleteRequire.of(deleteRequire.bookingId());
                bsCommand.deleteAllByBookingId(bsDeleteRequire);
        }
}
