package com.ijse.orm.mentalhealthcenter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "appointment")
public class Appointment implements SuperEntity {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;
}