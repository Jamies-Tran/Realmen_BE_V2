package com.capstone.realmen.service.booking.other;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EBookingStatus;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.booking.service.IBookingServiceMapper;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.repository.database.booking.IBookingRepository;
import com.capstone.realmen.repository.database.booking.service.BookingServiceEntity;
import com.capstone.realmen.repository.database.booking.service.IBookingServiceRepository;
import com.capstone.realmen.service.booking.other.data.BookingServiceCreateRequire;
import com.capstone.realmen.service.booking.other.data.BookingServiceDeleteRequire;
import com.capstone.realmen.service.booking.other.helpers.BookingServiceHelper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceCommandService extends BookingServiceHelper {
        @NonNull
        IBookingServiceRepository repository;

        @NonNull
        IBookingRepository bRepository;

        @NonNull
        IBookingServiceMapper mapper;

        public void saveAll(BookingServiceCreateRequire createRequire) {
                Map<Long, DailyPlanService> services = createRequire.services()
                                .stream()
                                .collect(Collectors.toMap(DailyPlanService::shopServiceId, s -> s));

                List<BookingServiceEntity> newBookingServices = createRequire.bookingServices()
                                .stream()
                                .map(bookingService -> {
                                        DailyPlanService s = services.get(bookingService.serviceId());
                                        LocalTime finishAt = getEstimateFinishAt(bookingService.beginAt(),
                                                        s.estimateDuration(),
                                                        s.durationUnitCode());
                                        return mapper.toEntity(bookingService)
                                                        .withBookingId(createRequire.bookingId())
                                                        .withFinishAt(finishAt)
                                                        .withStatusCode(EBookingStatus.DRAFT.getCode())
                                                        .withStatusName(EBookingStatus.DRAFT.getName());
                                })
                                .toList();

                repository.saveAll(newBookingServices);
        }

        public void delete(BookingServiceDeleteRequire deleteRequire) {
                BookingServiceEntity foundBookingService = repository.findById(deleteRequire.bookingServiceId())
                                .orElseThrow(NotFoundException::new);
                List<BookingServiceEntity> foundBookingServiceList = repository
                                .findByBookingId(foundBookingService.getBookingId());
                if (foundBookingServiceList.size() == 1) {
                        bRepository.deleteById(foundBookingService.getBookingId());
                }

                repository.delete(foundBookingService);
        }

        public void deleteAllByBookingId(BookingServiceDeleteRequire deleteRequire) {
                List<BookingServiceEntity> foundBookingServices = repository.findByBookingId(deleteRequire.bookingId());

                repository.deleteAll(foundBookingServices);
        }

}
