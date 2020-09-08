package com.ifi.dto;


import com.ifi.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Value;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

    UUID id;

    @NotNull
    @Size(max = 50)
    String name;

    @NotNull
    Gender gender;

    @NotNull
    Date dateOfBirth;

    @NotNull
    @Temporal(TemporalType.DATE)
    Date joinedDate;
}
