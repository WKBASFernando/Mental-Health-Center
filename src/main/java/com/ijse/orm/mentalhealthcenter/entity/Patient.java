package com.ijse.orm.mentalhealthcenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "patient")
public class Patient implements SuperEntity {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    @Column(length = 100)
    private String address;
    private String email;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<TherapySession> sessions;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payment> payments;
}