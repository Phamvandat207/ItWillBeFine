package com.ifi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "worker")
@Data
@EqualsAndHashCode(callSuper = true)
public class Worker extends Employee {
    //Lương 1 công
    @Basic
    @Column(name = "hourly_rating", precision = 6, scale = 5)
    private BigDecimal hourlyRating;
}
