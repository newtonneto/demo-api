package com.newton.demo.api.assembler;

import com.newton.demo.api.dto.DeliveryDTO;
import com.newton.demo.domain.model.Delivery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeliveryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public DeliveryDTO toDto(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryDTO.class);
    }

    public List<DeliveryDTO> toCollectionDto(List<Delivery> deliveries) {
        return deliveries.stream().map(this::toDto).collect(Collectors.toList());
    }
}
