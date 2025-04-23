package com.ijse.orm.mentalhealthcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String address;
    private String email;
}
