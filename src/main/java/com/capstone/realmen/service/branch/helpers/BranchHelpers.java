package com.capstone.realmen.service.branch.helpers;

import java.util.List;

import com.capstone.realmen.common.enums.EProfessional;
import com.capstone.realmen.common.enums.EServiceAssignment;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.shop.service.ShopService;

public class BranchHelpers {
    protected Boolean verifyActive(List<ShopService> serviceList, 
                                   List<Account> staffList) {
        List<EServiceAssignment> assignments = EServiceAssignment.of(serviceList);
        List<EProfessional> professionals = EProfessional.of(staffList);
        List<String> professionalCodes = professionals.stream()
            .map(EProfessional::getCode)
            .toList();
        for (EServiceAssignment assignment : assignments) {
            if(!professionalCodes.contains(assignment.getCode())) {
                return false;
            }
        }
        return true;       
    }
}
