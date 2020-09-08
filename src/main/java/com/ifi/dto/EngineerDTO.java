package com.ifi.dto;

import com.ifi.constants.Gender;
import lombok.AllArgsConstructor;
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
public class EngineerDTO {
    UUID engineerId;

    @NotNull
    @Size(min = 2, max = 50)
    String engineerName;

    @NotNull
    Gender engineerGender;

    @NotNull
    Date engineerDateOfBirth;

    @NotNull
    Date engineerJoinedDate;

    @NotNull
    BigDecimal engineerAllowance;
}
