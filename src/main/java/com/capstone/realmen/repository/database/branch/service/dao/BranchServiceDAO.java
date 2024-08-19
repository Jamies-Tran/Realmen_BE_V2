package com.capstone.realmen.repository.database.branch.service.dao;

public interface BranchServiceDAO {
    void setBranchServiceId(Long branchServiceId);
    Long getBranchServiceId();

    void setBranchId(Long branchId);
    Long getBranchId();

    void setShopId(Long shopId);
    Long getShopId();

    void setBranchServiceStatusCode(String branchServiceStatusCode);
    String getBranchServiceStatusCode();

    void setBranchServiceStatusName(String branchServiceStatusName);
    String getBranchServiceStatusName();

    void setServiceAssignmentCode(String serviceAssignmentCode);
    String getServiceAssignmentCode();

    void setServiceAssignmentName(String serviceAssignmentName);
    String getServiceAssignmentName();
}
