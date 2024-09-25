package com.capstone.realmen.data.dao.booking.service;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IBookingServiceDAO {
    void setBookinServiceId(Long bookingServiceId);

    void setBookingId(Long bookingId);

    void setStaffId(Long staffId);

    void dailyPlanServiceId(Long dailyPlanServiceId);

    void setCusId(Long cusId);

    void setBeginAt(LocalTime beginAt);

    void setFinishAt(LocalTime finshAt);

    void setActualBeginAt(LocalTime actualBeginAt);

    void setActualFinishedAt(LocalTime actualFinishedAt);

    void setBookingCode(String bookingCode);

    void setBookedAt(LocalDate bookedAt);

    void setStaffCode(String staffCode);

    void setStaffName(String staffName);

    void setStaffPhone(String staffPhone);

    void setCusName(String cusName);

    void setCusPhone(String cusPhone);

    


}
