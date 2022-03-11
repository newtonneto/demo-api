package com.newton.demo.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Receiver {
    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_street")
    private String street;

    @Column(name = "receiver_number")
    private String number;

    @Column(name = "receiver_complement")
    private String complement;

    @Column(name = "receiver_district")
    private String district;
}
