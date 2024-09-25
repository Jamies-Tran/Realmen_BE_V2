package com.capstone.realmen.controller.api.app.booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.booking.service.models.BookingServiceResponse;
import com.capstone.realmen.controller.api.app.booking.service.models.IBookingServiceModelMapper;
import com.capstone.realmen.service.booking.other.BookingServiceUseCaseService;
import com.capstone.realmen.service.booking.other.data.BookingServiceSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceController implements IBookingServiceAPI {
    @NonNull
    BookingServiceUseCaseService useCaseService;

    @NonNull
    IBookingServiceModelMapper modelMapper;

    @Override
    public PageImplResponse<BookingServiceResponse> findAll(List<LocalDateTime> timeRange, List<String> statusCodes,
            Long bookingId, Long staffId, Integer current, Integer pageSize) {
        BookingServiceSearchCriteria searchCriteria = BookingServiceSearchCriteria
                .of(timeRange, statusCodes, staffId, bookingId);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<BookingServiceResponse> response = useCaseService.findAll(searchCriteria, pageRequestCustom)
                .map(modelMapper::toModel);

        return new PageImplResponse<>(
                response.getContent(),
                response.getTotalElements(),
                response.getTotalPages(),
                current,
                pageSize);
    }
}
