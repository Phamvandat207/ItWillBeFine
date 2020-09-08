package com.ifi.entity;

import com.ifi.constants.Gender;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "worker")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
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
