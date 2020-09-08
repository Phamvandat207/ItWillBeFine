package com.ifi.dto;


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

    final UUID id;

    @NotNull
    @Size(min = 2, max = 50)
    final String name;

    @NotNull
    final Gender gender;

    final Date dateOfBirth;

    @NotNull
    final Date joinedDate;

    public EmployeeDTO(UUID id, @NotNull @Size(min = 2, max = 50) String name, @NotNull Gender gender, @NotNull Date dateOfBirth, @NotNull Date joinedDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.joinedDate = joinedDate;
    }
}
