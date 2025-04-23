package com.ijse.orm.mentalhealthcenter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment implements SuperEntity {
    @Id
    private String id;

    @ManyToOne
    @MapsId("patientID")
    private Patient patient;

    @ManyToOne
    @MapsId("programID")
    private TherapyProgram therapyProgram;

}
