package com.ijse.orm.mentalhealthcenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentTM {
    private String id;
    private String time;
    private String notes;
    private String date;
    private String program_id;
    private String patient_id;
    private String therapist_id;
}
