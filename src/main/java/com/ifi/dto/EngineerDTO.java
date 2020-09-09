package com.ifi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifi.constants.Gender;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
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
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EngineerDTO(@JsonProperty("employee_id") UUID id,
                       @JsonProperty("employee_name") String name,
                       @JsonProperty("gender") Gender gender,
                       @JsonProperty("date_of_birth") LocalDate dateOfBirth,
                       @JsonProperty("joined_date") LocalDate joinedDate,
                       @JsonProperty("monthly_wage") BigDecimal engineerMonthlyWage,
                       @JsonProperty("allowance") BigDecimal engineerAllowance) {
        super(id, name, gender, dateOfBirth, joinedDate);
        this.engineerMonthlyWage = engineerMonthlyWage;
        this.engineerAllowance = engineerAllowance;
    }
}
