package com.ijse.orm.mentalhealthcenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientTM {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String address;
    private String email;
}
