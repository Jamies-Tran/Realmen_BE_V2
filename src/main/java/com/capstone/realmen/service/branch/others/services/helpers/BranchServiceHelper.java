package com.capstone.realmen.service.branch.others.services.helpers;

import java.util.List;

import com.capstone.realmen.common.enums.EBranchServiceStatus;
import com.capstone.realmen.common.enums.EProfessional;
import com.capstone.realmen.common.enums.EServiceAssignment;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.shop.service.ShopService;

public class BranchServiceHelper {
    protected EBranchServiceStatus getBranchServiceStatus(
            ShopService service,
            List<Account> staffList) {
        EServiceAssignment assignment = EServiceAssignment.of(service);
        List<EProfessional> professionals = EProfessional.of(staffList);
        List<String> professionalCodes = professionals.stream()
                .map(EProfessional::getCode)
                .toList();
        return professionalCodes.contains(assignment.getCode()) ? EBranchServiceStatus.ACTIVE
                : EBranchServiceStatus.INACTIVE;
    }
}
