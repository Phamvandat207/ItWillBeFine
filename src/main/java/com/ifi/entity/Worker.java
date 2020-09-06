package com.ifi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "worker")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Worker extends Employee {
    //Lương 1 công
    @Basic
    @Column(name = "hourly_rating", precision = 6, scale = 5)
    private BigDecimal hourlyRating;
}
