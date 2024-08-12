package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.data.dto.shop.service.ShopService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EServiceAssignment {
    STYLIST(EProfessional.STYLIST.getCode(), "Dịch vụ của thợ cắt tóc", EProfessional.STYLIST),
    MASSEUR(EProfessional.MASSEUR.getCode(), "Dịch vụ của thợ massage", EProfessional.MASSEUR);

    String code;
    String name;
    EProfessional professional;

    public static EServiceAssignment of(ShopService service) {
        return Arrays.stream(EServiceAssignment.values())
                .filter(assignment -> assignment.getCode().equals(service.serviceAssignmentCode()))
                .findAny()
                .orElse(null);
    }

    public static List<EServiceAssignment> of(List<ShopService> serviceList) {
        List<String> assignmentCodes = serviceList.stream()
                .map(ShopService::serviceAssignmentCode)
                .toList();
        return Arrays.stream(EServiceAssignment.values())
                .filter(assignment -> assignmentCodes.contains(assignment.getCode()))
                .toList();
    }

    public static EServiceAssignment findByCode(String code) {
        return Arrays.stream(EServiceAssignment.values())
                .filter(serviceClassify -> Objects.equals(serviceClassify.getCode(), code))
                .findAny()
                .orElse(null);
    }

    public Boolean isQualify(List<EProfessional> professionals) {
        return professionals.contains(this.professional);
    }
}
