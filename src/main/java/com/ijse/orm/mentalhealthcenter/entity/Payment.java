package com.ijse.orm.mentalhealthcenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment implements SuperEntity {
    @Id
    private String id;
    private Double amount;
    private String paymentDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}