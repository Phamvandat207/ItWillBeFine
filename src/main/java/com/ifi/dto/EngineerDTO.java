package com.ifi.dto;

import com.ifi.constants.Gender;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class EngineerDTO extends EmployeeDTO {

    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer=3, fraction=2)
    BigDecimal engineerMonthlyWage;

    @Positive
    BigDecimal engineerAllowance;

    @Builder(builderMethodName = "engineerBuilder")
    public EngineerDTO(UUID id, @NotNull @Size(min = 2, max = 50) String name, @NotNull Gender gender, @NotNull Date dateOfBirth, @NotNull Date joinedDate, @NotNull BigDecimal engineerMonthlyWage, @NotNull BigDecimal engineerAllowance) {
        super(id, name, gender, dateOfBirth, joinedDate);
        this.engineerMonthlyWage = engineerMonthlyWage;
        this.engineerAllowance = engineerAllowance;
    }
}
