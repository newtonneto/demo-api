package com.newton.demo.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EventInputDTO {
    @NotBlank
    private String description;
}
