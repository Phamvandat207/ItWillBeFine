package com.ifi.entity;

import com.ifi.constants.Gender;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Basic
    @Column(name = "name", length = 50, nullable = false)
    @NonNull
    private String name;

    @Basic
    @Column(name = "gender", nullable = false)
    @NonNull
    private Gender gender;

    @Basic
    @Column(name = "dob")
    @NonNull
    private Date dateOfBirth;

    @Basic
    @Column(name = "joined_date", nullable = false)
    @NonNull
    private Date joinedDate;
}
