package com.ifi.dto;

import com.ifi.constants.Gender;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

//@Value
@EqualsAndHashCode(callSuper = true)
@Value
public class EngineerDTO extends EmployeeDTO {

    @NotNull
    BigDecimal engineerMonthlyWage;

    @NotNull
    BigDecimal engineerAllowance;

    @Builder(builderMethodName = "engineerBuilder")
    public EngineerDTO(UUID id, @NotNull @Size(min = 2, max = 50) String name, @NotNull Gender gender, @NotNull Date dateOfBirth, @NotNull Date joinedDate, @NotNull BigDecimal engineerMonthlyWage, @NotNull BigDecimal engineerAllowance) {
        super(id, name, gender, dateOfBirth, joinedDate);
        this.engineerMonthlyWage = engineerMonthlyWage;
        this.engineerAllowance = engineerAllowance;
    }
}
