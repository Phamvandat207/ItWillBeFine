package com.ifi.dto;

import com.ifi.constants.Gender;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class WorkerDTO extends EmployeeDTO {

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 1, fraction = 5)
    BigDecimal workerHourlyRating;

    @Builder(builderMethodName = "workerBuilder")
    public WorkerDTO(UUID id, @NotNull @Size(min = 2, max = 50) String name, @NotNull Gender gender, @NotNull Date dateOfBirth, @NotNull Date joinedDate, @NotNull BigDecimal workerHourlyRating) {
        super(id, name, gender, dateOfBirth, joinedDate);
        this.workerHourlyRating = workerHourlyRating;
    }
}
