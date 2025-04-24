package com.ijse.orm.mentalhealthcenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTM {
    private String id;
    private Double amount;
    private String paymentDate;
    private String patient_id;
}
