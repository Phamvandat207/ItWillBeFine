package com.ifi.entity;

import com.ifi.util.constants.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "worker")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Worker extends Employee {
    //Lương 1 công
    @Basic
    @Column(name = "hourly_rating", precision = 6, scale = 5)
    @NonNull
    private BigDecimal hourlyRating;

    public Worker(@NonNull String name,
                  @NonNull Gender gender,
                  @NonNull Date dateOfBirth,
                  @NonNull Date joinedDate,
                  @NonNull BigDecimal hourlyRating) {
        super(name, gender, dateOfBirth, joinedDate);
        this.hourlyRating = hourlyRating;
    }
}
