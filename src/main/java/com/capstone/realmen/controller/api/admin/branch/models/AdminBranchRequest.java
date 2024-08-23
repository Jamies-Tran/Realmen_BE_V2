package com.capstone.realmen.controller.api.admin.branch.models;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.capstone.realmen.controller.api.admin.branch.models.display.AdminBranchDisplayRequest;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.twilio.exception.InvalidRequestException;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminBranchRequest(
                Long branchId,
                @Pattern(regexp = "^[^!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$", message = "Tên chi nhánh không hợp lệ") 
                String branchName,
                String branchHotline,
                String branchThumbnail,
                String branchAddress,
                LocalDateTime open,
                LocalDateTime close,
                List<AdminBranchDisplayRequest> branchDisplays) {
        public AdminBranchRequest {
                long hoursBetween = ChronoUnit.HOURS.between(open.toLocalTime(), close.toLocalTime());
                boolean isAvailableOpenTime = open.toLocalDate().equals(close.toLocalDate());
                if (!isAvailableOpenTime || hoursBetween < 8) {
                        throw new InvalidRequest("Giờ mở cửa ít nhất 8 tiếng một ngày");
                }
        }

}
