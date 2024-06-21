package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.data.dto.shop.category.assignment.ServiceAssignment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EServiceAssignment {
    STYLIST(EProfessional.STYLIST.getCode(), "Dịch vụ của thợ cắt tóc", EProfessional.STYLIST),
    MASSEUR(EProfessional.MASSEUR.getCode(), "Dịch vụ của thợ massage", EProfessional.STYLIST);

    String code;
    String name;
    EProfessional professional;

    public List<ServiceAssignment> findAll() {
        return Arrays.stream(EServiceAssignment.values())
                .map(serviceAssignment -> ServiceAssignment.builder()
                        .code(this.getCode())
                        .professional(this.getProfessional().toDto())
                        .build())
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
