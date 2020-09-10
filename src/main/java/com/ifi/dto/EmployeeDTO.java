package com.ifi.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.ifi.constants.Gender;
import com.ifi.util.validator.joindate.JoinDate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JoinDate
@JsonSerialize
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date_of_birth")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    final LocalDate dateOfBirth;

    @NotNull(message = "joined date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("joined_date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    final LocalDate joinedDate;

    @JsonProperty("employee_type")
    String type;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    @Builder
    public EmployeeDTO(@JsonProperty("employee_id") UUID id,
                       @JsonProperty("employee_name") String name,
                       @JsonProperty("gender") Gender gender,
                       @JsonProperty("date_of_birth") LocalDate dateOfBirth,
                       @JsonProperty("joined_date") LocalDate joinedDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.joinedDate = joinedDate;
    }
}
