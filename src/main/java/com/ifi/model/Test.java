package com.ifi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "test")
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
