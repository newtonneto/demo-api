package com.newton.demo.api.controller;

import com.newton.demo.api.assembler.EventMapper;
import com.newton.demo.api.dto.EventDTO;
import com.newton.demo.api.dto.input.EventInputDTO;
import com.newton.demo.domain.model.Delivery;
import com.newton.demo.domain.model.Event;
import com.newton.demo.domain.service.DeliverySearchService;
import com.newton.demo.domain.service.RegisterEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/deliveries/{deliveryId}/events")
public class EventController {
    @Autowired
    private DeliverySearchService deliverySearchService;
    @Autowired
    private RegisterEventService registerEventService;
    @Autowired
    private EventMapper eventMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO register(@PathVariable Long deliveryId, @Valid @RequestBody EventInputDTO eventInput) {
        Event event = registerEventService.register(deliveryId, eventInput.getDescription());

        return eventMapper.toDto(event);
    }

    @GetMapping
    public List<EventDTO> list(@PathVariable Long deliveryId) {
        Delivery delivery = deliverySearchService.search(deliveryId);

        return eventMapper.toCollectionDto(delivery.getEvents());
    }
}
