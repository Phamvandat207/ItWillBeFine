package com.ifi.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifi.constants.Gender;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class EmployeeDTO {
    @JsonProperty("employee_id")
    final UUID id;

    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 50, message = "name must between 2 and 50 characters")
    @JsonProperty("employee_name")
    final String name;

    @NotNull(message = "gender cannot be null")
    @JsonProperty("gender")
    final Gender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY")
    @JsonProperty("date_of_birth")
    final Date dateOfBirth;

    @NotNull(message = "joined date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY")
    @JsonProperty("joined_date")
    final Date joinedDate;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EmployeeDTO(@JsonProperty("employee_id") UUID id,
                       @JsonProperty("employee_name") @NotNull @Size(min = 2, max = 50) String name,
                       @JsonProperty("gender") @NotNull Gender gender,
                       @JsonProperty("date_of_birth") @NotNull Date dateOfBirth,
                       @JsonProperty("joined_date") @NotNull Date joinedDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.joinedDate = joinedDate;
    }
}
