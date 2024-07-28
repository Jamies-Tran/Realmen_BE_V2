package com.capstone.realmen.data.dao.plans.daily.account;

public interface DailyPlanAccountDAO {
    void setDailyPlanAccountId(Long dailyPlanAccountId);

    Long getDailyPlanAccountId();

    void setDailyPlanId(Long dailyPlanId);

    Long getDailyPlanId();

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
}
