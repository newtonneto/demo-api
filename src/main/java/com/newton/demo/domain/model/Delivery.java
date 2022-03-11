package com.newton.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.newton.demo.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Delivery {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
    @NotNull
    @ManyToOne
    private Client client;

    //receiver não vai ser uma tabela a parte no banco, a annotation Embedded separa em classes diferentes
    //elementos de uma mesma tabela
    @Embedded
    @Valid
    @NotNull
    private Receiver receiver;

    @NotNull
    private BigDecimal fare;

    //EnumType.STRING salva o texto do enum ao inves do seu valor numerico
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = Access.READ_ONLY)
    private DeliveryStatus status;

    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime orderDate;

    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime deliveryDate;
}
