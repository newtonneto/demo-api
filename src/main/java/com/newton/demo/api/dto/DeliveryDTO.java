package com.newton.demo.api.dto;

import com.newton.demo.domain.model.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryDTO {
    private Long id;
    private String clientName;
    private ReceiverDTO receiver;
    private BigDecimal fare;
    private DeliveryStatus status;
    private OffsetDateTime orderDate;
    private OffsetDateTime deliveryDate;
}
