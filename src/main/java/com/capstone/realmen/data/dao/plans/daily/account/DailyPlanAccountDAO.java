package com.capstone.realmen.data.dao.plans.daily.account;

import java.time.LocalDateTime;

public interface DailyPlanAccountDAO {
    void setDailyPlanAccountId(Long dailyPlanAccountId);

    Long getDailyPlanAccountId();

    void setDailyPlanId(Long dailyPlanId);

    Long getDailyPlanId();

    void setDate(LocalDateTime date);

    LocalDateTime getDate();

    void setAccountId(Long accountId);

    Long getAccountId();

    void setFullName(String fullName);

    String getFullName();

    void setPhone(String phone);

    String getPhone();

    void setGenderCode(String genderCode);

    String getGenderCode();

    void setGenderName(String genderName);

    String getGenderName();

    void setProfessionalTypeCode(String professionalTypeCode);

    String getProfessionalTypeCode();

    void setProfessionalTypeName(String professionalTypeName);

    String getProfessionalTypeName();

    void setThumbnail(String thumbnail);

    String getThumbnail();

    void setAccountStatusCode(String accountStatusCode);

    String getAccountStatusCode();

    void setAccountStatusName(String accountStatusName);

    String getAccountStatusName();

    void setShiftCode(String shiftCode);

    String getShiftCode();

    void setShiftName(String shiftName);

    String getShiftName();
}
