package com.ijse.orm.mentalhealthcenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Patient {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String address;
    private String email;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<TherapySession> sessions;
    @ManyToMany(mappedBy = "patients", cascade = CascadeType.ALL)
    private List<TherapyProgram> programs;
}
