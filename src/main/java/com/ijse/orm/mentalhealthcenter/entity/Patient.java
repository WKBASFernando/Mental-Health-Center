package com.ijse.orm.mentalhealthcenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "patient")
    private List<TherapySession> sessions;
    @ManyToMany(mappedBy = "patients")
    private List<TherapyProgram> programs;
}
