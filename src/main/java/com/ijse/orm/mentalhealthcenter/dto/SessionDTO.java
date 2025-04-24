package com.ijse.orm.mentalhealthcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionDTO {
    private long id;
    private String patient_id;
    private String therapist_id;
}
