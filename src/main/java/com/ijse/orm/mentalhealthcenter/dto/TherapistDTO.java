package com.ijse.orm.mentalhealthcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistDTO {
    private String id;
    private String name;
    private String availability;
    private String description;
}
