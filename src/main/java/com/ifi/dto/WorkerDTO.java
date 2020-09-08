package com.ifi.dto;

import com.ifi.constants.Gender;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class WorkerDTO extends EmployeeDTO {

    @Positive
    BigDecimal workerHourlyRating;

    @Builder(builderMethodName = "workerBuilder")
    public WorkerDTO(UUID id, @NotNull @Size(min = 2, max = 50) String name, @NotNull Gender gender, @NotNull Date dateOfBirth, @NotNull Date joinedDate, @NotNull BigDecimal workerHourlyRating) {
        super(id, name, gender, dateOfBirth, joinedDate);
        this.workerHourlyRating = workerHourlyRating;
    }
}
