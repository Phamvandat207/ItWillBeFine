package com.ifi.dto;

import com.ifi.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
@Builder
public class EngineerDTO {
    UUID engineerId;

    @NotNull
    @Size(min = 2, max = 50)
    String engineerName;

    @NotNull
    Gender engineerGender;

    @NotNull
    Date engineerDateOfBirth;

    @NotNull
    Date engineerJoinedDate;

    @NotNull
    BigDecimal engineerMonthlyWage;

    @NotNull
    BigDecimal engineerAllowance;
}
