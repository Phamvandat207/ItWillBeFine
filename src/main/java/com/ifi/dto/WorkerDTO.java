package com.ifi.dto;

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

@Value
@EqualsAndHashCode(callSuper = true)
public class WorkerDTO extends EmployeeDTO {

    @DecimalMin(value = "0.0", inclusive = false, message = "monthly wage must be positive")
    @Digits(integer = 1, fraction = 5)
    BigDecimal workerHourlyRating;

    @Builder(builderMethodName = "workerBuilder")
    public WorkerDTO(UUID id, @NotNull @Size(min = 2, max = 50) String name, @NotNull Gender gender, @NotNull LocalDate dateOfBirth, @NotNull LocalDate joinedDate, @NotNull BigDecimal workerHourlyRating) {
        super(id, name, gender, dateOfBirth, joinedDate);
        this.workerHourlyRating = workerHourlyRating;
    }
}
