package com.newton.demo.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private Long id;
    private String name;
    private String email;
    private String phone;
}