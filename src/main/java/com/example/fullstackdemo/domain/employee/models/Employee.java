package com.example.fullstackdemo.domain.employee.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    @NonNull
    @Column(name = "email_id")
    private String email;
}
