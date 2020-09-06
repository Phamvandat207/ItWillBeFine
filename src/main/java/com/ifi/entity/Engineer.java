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

@Entity(name = "engineer")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Engineer extends Employee {
    //Lương tháng
    @Basic
    @Column(name = "monthly_wage", precision = 10, scale = 9)
    @NonNull
    private BigDecimal monthlyWage;

    //Phụ cấp
    @Basic
    @Column(name = "allowance", precision = 8, scale = 7)
    @NonNull
    private BigDecimal allowance;

    public Engineer(@NonNull String name,
                    @NonNull Gender gender,
                    @NonNull Date dateOfBirth,
                    @NonNull Date joinedDate,
                    @NonNull BigDecimal monthlyWage,
                    @NonNull BigDecimal allowance) {
        super(name, gender, dateOfBirth, joinedDate);
        this.monthlyWage = monthlyWage;
        this.allowance = allowance;
    }
}
