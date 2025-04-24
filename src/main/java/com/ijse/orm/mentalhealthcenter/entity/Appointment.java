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
    private String time;
    private String notes;
    private String date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    private String status;
    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "NO";
        }
    }
}