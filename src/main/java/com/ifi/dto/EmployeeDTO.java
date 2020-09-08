package com.ifi.dto;


import com.ifi.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
@Builder
public class EmployeeDTO implements Serializable {

    UUID id;

    @NotNull
    @Size(min = 2, max = 50)
    String name;

    @NotNull
    Gender gender;

    @NotNull
    Date dateOfBirth;

    @NotNull
    Date joinedDate;

}
