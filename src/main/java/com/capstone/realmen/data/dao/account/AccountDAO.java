package com.capstone.realmen.data.dao.account;

import java.time.LocalDate;

public interface AccountDAO {
    void setAccountId(Long accountId);
    Long getAccountId();

    void setFirstName(String firstName);
    String getFirstName();

    void setLastName(String lastName);
    String getLastName();

    void setPhone(String phone);
    String getPhone();

    void setAddress(String address);
    String getAddress();

    void setStaffCode(String staffCode);
    String getStaffCode();

    void setProfessionalTypeCode(String professionalTypeCode);
    String getProfessionalTypeCode();

    void setProfessionalTypeName(String professionalTypeName);
    String getProfessionalTypeName();

    void setRoleCode(String roleCode);
    String getRoleCode();

    void setRoleName(String roleName);
    String getRoleName();

    void setThumbnail(String thumbnail);
    String getThumbnail();

    void setDob(LocalDate dob);
    LocalDate getDob();

    void setGenderCode(String genderCode);
    String getGenderCode();

    void setGenderName(String genderName);
    String getGenderName();

    void setAccountStatusCode(String accountStatusCode);
    String getAccountStatusCode();

    void setAccountStatusName(String accountStatusName);
    String getAccountStatusName();
}
