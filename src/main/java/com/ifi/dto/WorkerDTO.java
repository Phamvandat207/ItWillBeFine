package com.ifi.dto;

import com.ifi.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
@Builder
public class WorkerDTO {

    UUID workerId;

    @NotNull
    @Size(min = 2, max = 50)
    String workerName;

    @NotNull
    Gender workerGender;

    @NotNull
    Date workerDateOfBirth;

    @NotNull
    Date workerJoinedDate;

    @NotNull
    BigDecimal workerHourlyRating;
}
