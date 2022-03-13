package com.newton.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.newton.demo.domain.ValidationGroups;
import com.newton.demo.domain.exception.RulesException;
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
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Delivery {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    private Client client;

    //receiver não vai ser uma tabela a parte no banco, a annotation Embedded separa em classes diferentes
    //elementos de uma mesma tabela
    @Embedded
    private Receiver receiver;

    private BigDecimal fare;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    //EnumType.STRING salva o texto do enum ao inves do seu valor numerico
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private OffsetDateTime orderDate;
    private OffsetDateTime deliveryDate;

    public Event addEvent(String description) {
        Event event = new Event();
        event.setDescription(description);
        event.setRegisterDate(OffsetDateTime.now());
        event.setDelivery(this);
        this.getEvents().add(event);

        return event;
    }

    public void conclude() {
        if (!DeliveryStatus.PENDING.equals(getStatus())) {
            throw new RulesException("Entrega não pode ser finalizada");
        }

        setStatus(DeliveryStatus.FINISHED);
        setDeliveryDate(OffsetDateTime.now());
    }


}
