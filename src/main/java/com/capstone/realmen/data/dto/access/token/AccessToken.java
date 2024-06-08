package com.capstone.realmen.data.dto.access.token;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccessToken(
        Long accountId,
        Long branchId,
        String staffCode,
        String lastName,
        String firstName,
        String accessToken,
        LocalDateTime issueAt,
        String roleCode,
        String roleName) {

    public static AccessToken of(Long accountId, String lastName, String firstName, String accessToken,
            String roleCode, String roleName) {

                return init().withAccountId(accountId)
                    .withLastName(lastName).withFirstName(firstName)
                    .withLastName(lastName).withAccessToken(accessToken)
                    .withRoleCode(roleCode).withRoleName(roleName)
                    .withIssueAt(LocalDateTime.now());

    }

    public static AccessToken of(Long accountId, Long branchId, String staffCode, 
            String lastName, String firstName, String accessToken,String roleCode, String roleName) {

                return init().withAccountId(accountId)
                    .withBranchId(branchId).withStaffCode(staffCode)
                    .withLastName(lastName).withFirstName(firstName)
                    .withLastName(lastName).withAccessToken(accessToken)
                    .withRoleCode(roleCode).withRoleName(roleName)
                    .withIssueAt(LocalDateTime.now());

    }

    private static AccessToken init() {
        return AccessToken.builder().build();
    }
}
