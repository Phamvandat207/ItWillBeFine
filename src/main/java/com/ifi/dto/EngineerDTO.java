package com.ifi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifi.constants.Gender;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class EngineerDTO extends EmployeeDTO {

    @DecimalMin(value = "0.0", inclusive = false, message = "monthly wage must be positive")
    @Digits(integer = 1, fraction = 5)
    @JsonProperty("monthly_wage")
    BigDecimal engineerMonthlyWage;

    @DecimalMin(value = "0.0", inclusive = false, message = "monthly wage must be positive")
    @Digits(integer = 1, fraction = 5)
    @JsonProperty("allowance")
    BigDecimal engineerAllowance;

    @Builder(builderMethodName = "engineerBuilder")
    public EngineerDTO(UUID id, @NotNull @Size(min = 2, max = 50) String name, @NotNull Gender gender, @NotNull LocalDate dateOfBirth, @NotNull LocalDate joinedDate, @NotNull BigDecimal engineerMonthlyWage, @NotNull BigDecimal engineerAllowance) {
        super(id, name, gender, dateOfBirth, joinedDate);
        this.engineerMonthlyWage = engineerMonthlyWage;
        this.engineerAllowance = engineerAllowance;
    }
}
