package com.ifi.entity;

import com.ifi.util.constants.Gender;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Employee {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "gender")
    private Gender gender;

    @Basic
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Basic
    @Column(name = "joined_date")
    @Temporal(TemporalType.DATE)
    private Date joinedDate;
}
