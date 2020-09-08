package com.ifi.dto;

import com.ifi.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
@Builder
public class EngineerDTO {
    UUID id;

    @NotNull
    @Size(max = 50) String name;

    @NotNull
    @Basic
    Gender gender;

    @NotNull
    @Basic
    Date dateOfBirth;

    @NotNull
    @Temporal(TemporalType.DATE)
    Date joinedDate;

    @NotNull
    @Basic
    BigDecimal monthlyWage;

    @NotNull
    @Basic
    BigDecimal allowance;
}
