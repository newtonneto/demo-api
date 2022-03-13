package com.newton.demo.api.assembler;

import com.newton.demo.api.dto.EventDTO;
import com.newton.demo.domain.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {
    @Autowired
    ModelMapper modelMapper;

    public EventDTO toDto(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    public List<EventDTO> toCollectionDto(List<Event> events) {
        return events.stream().map(this::toDto).collect(Collectors.toList());
    }
}
