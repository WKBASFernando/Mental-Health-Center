package com.ijse.orm.mentalhealthcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String id;
    private Double amount;
    private String paymentDate;
    private String patient_id;
}
