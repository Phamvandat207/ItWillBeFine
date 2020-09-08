package com.ifi.dto;


import com.ifi.constants.Gender;
import lombok.*;

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

    @NotNull
    final Date dateOfBirth;

    @NotNull
    final Date joinedDate;

}
