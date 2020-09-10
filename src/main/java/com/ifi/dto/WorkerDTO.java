package com.ifi.dto;

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

@Value
@EqualsAndHashCode(callSuper = true)
public class WorkerDTO extends EmployeeDTO {

    @DecimalMin(value = "0", message = "hourly rating must be positive")
    @Digits(integer = 1, fraction = 5)
    @JsonProperty("hourly_rating")
    BigDecimal workerHourlyRating;

    @Builder(builderMethodName = "workerBuilder")
    public WorkerDTO(@JsonProperty("employee_id") UUID id,
                     @JsonProperty("employee_name") String name,
                     @JsonProperty("gender") Gender gender,
                     @JsonProperty("date_of_birth") LocalDate dateOfBirth,
                     @JsonProperty("joined_date") LocalDate joinedDate,
                     @JsonProperty("hourly_rating") BigDecimal workerHourlyRating) {
        super(id, name, gender, dateOfBirth, joinedDate);
        this.workerHourlyRating = workerHourlyRating;
    }
}
