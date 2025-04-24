package com.ijse.orm.mentalhealthcenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistTM {
    private String id;
    private String name;
    private String availability;
    private String description;
}
