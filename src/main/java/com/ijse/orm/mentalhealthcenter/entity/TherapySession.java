package com.ijse.orm.mentalhealthcenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TherapySession implements SuperEntity{
    @Id
    private String id;
    private String date;
    private String time;
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "therapistId")
    private Therapist therapist;
}
