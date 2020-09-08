package com.ifi.entity;

import com.ifi.constants.Gender;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
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
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Basic
    @Column(name = "name")
    @NonNull
    private String name;

    @Basic
    @Column(name = "gender")
    @NonNull
    private Gender gender;

    @Basic
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    @NonNull
    private Date dateOfBirth;

    @Basic
    @Column(name = "joined_date")
    @Temporal(TemporalType.DATE)
    @NonNull
    private Date joinedDate;
}
