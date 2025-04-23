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
public class TherapyProgram implements SuperEntity{
    @Id
    private String  id;
    private String  name;
    private String  duration;
    private double fee;
    @OneToMany(mappedBy = "therapyProgram")
    private List<Patient> patients;
}
