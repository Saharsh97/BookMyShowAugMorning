package org.example.bookmyshowaugmorning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshowaugmorning.models.enums.PaymentMode;
import org.example.bookmyshowaugmorning.models.enums.PaymentProvider;
import org.example.bookmyshowaugmorning.models.enums.PaymentStatus;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private String referenceId;

    @Enumerated
    private PaymentMode paymentMode;

    private int amount;

    @Enumerated
    private PaymentProvider provider;

    @Enumerated
    private PaymentStatus paymentStatus;
}
