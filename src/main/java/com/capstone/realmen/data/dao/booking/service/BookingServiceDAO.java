package com.capstone.realmen.data.dao.booking.service;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingServiceDAO {
    void setBookingServiceId(Long bookingServiceId);

    void setDailyPlanServiceId(Long dailyPlanServiceId);

    void setBookingId(Long bookingId);

    void setStaffId(Long staffId);

    void setCusId(Long cusId);

    void setShopServiceName(String shopServiceName);

    void setPrice(Long price);

    void setBookingCode(String bookingCode);

    void setBookedAt(LocalDate bookedAt);

    void setStaffCode(String staffCode);

    void setStaffName(String staffName);

    void setStaffPhone(String staffPhone);

    void setBeginAt(LocalTime beginAt);

    void setFinishAt(LocalTime finshAt);

    void setActualBeginAt(LocalTime actualBeginAt);

    void setActualFinishedAt(LocalTime actualFinishedAt);

    void setStatusCode(String statusCode);

    void setStatusName(String statusName);

    Long getBookingServiceId();

    Long getDailyPlanServiceId();

    Long getBookingId();

    Long getStaffId();

    Long getCusId();

    String getShopServiceName();

    Long getPrice();

    String getBookingCode();

    LocalDate getBookedAt();

    String getStaffCode();

    String getStaffName();

    String getStaffPhone();

    String getCusName();

    String getCusPhone();

    LocalTime getBeginAt();

    LocalTime getFinishAt();

    LocalTime getActualBeginAt();

    LocalTime getActualFinishedAt();

    String getStatusCode();

    String getStatusName();
}
