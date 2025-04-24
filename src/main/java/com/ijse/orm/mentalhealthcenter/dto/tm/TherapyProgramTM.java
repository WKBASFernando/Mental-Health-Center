package com.ijse.orm.mentalhealthcenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapyProgramTM {
    private String id;
    private String name;
    private String duration;
    private double fee;
}
