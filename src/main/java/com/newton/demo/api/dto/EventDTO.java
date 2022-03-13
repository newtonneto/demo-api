package com.newton.demo.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class EventDTO {
    private Long id;
    private String description;
    private OffsetDateTime registerDate;
}
