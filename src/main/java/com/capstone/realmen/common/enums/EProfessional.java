package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import com.capstone.realmen.data.dto.account.professional.Professional;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EProfessional {
    STYLIST("STYLIST", "Thợ cắt tóc"),
    MASSEUR("MASSEUR", "Thợ massage");

    String code;
    String name;

    public Professional toDto() {
        return Professional.builder()
            .code(this.getCode())
            .name(this.getName())
            .build();
    }

    public static List<Professional> findAll(String search) {
        return Arrays.stream(EProfessional.values())
                .filter(professional -> !StringUtils.hasText(search) || professional.getCode().equalsIgnoreCase(search))
                .map(professional -> Professional.builder()
                        .code(professional.getCode()).name(professional.getName()).build())
                .toList();
    }

    public static EProfessional findByCode(String code) {
        return Arrays.stream(EProfessional.values())
                .filter(professional -> Objects.equals(professional.getCode(), code))
                .findAny()
                .orElse(null);
    }
}
