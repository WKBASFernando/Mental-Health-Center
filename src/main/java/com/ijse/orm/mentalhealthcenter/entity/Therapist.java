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
public class Therapist implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String availability;
    private String description;
    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL)
    private List<TherapySession> sessions;

}
