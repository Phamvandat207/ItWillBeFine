package com.ifi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "engineer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Engineer extends Employee {
    //Lương tháng
    @Basic
    @Column(name = "monthly_wage", precision = 10, scale = 9)
    private BigDecimal hourlyRating;

    //Phụ cấp
    @Basic
    @Column(name = "allowance", precision = 8, scale = 7)
    private BigDecimal allowance;
}
