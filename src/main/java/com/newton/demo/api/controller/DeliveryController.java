package com.newton.demo.api.controller;

import com.newton.demo.api.assembler.DeliveryMapper;
import com.newton.demo.api.dto.DeliveryDTO;
import com.newton.demo.api.dto.ReceiverDTO;
import com.newton.demo.domain.model.Delivery;
import com.newton.demo.domain.repository.DeliveryRepository;
import com.newton.demo.domain.service.DeliveryRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    @Autowired
    private DeliveryRequestService deliveryRequestService;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliveryMapper deliveryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDTO request(@Valid @RequestBody Delivery delivery) {
        Delivery deliveryRequested = deliveryRequestService.request(delivery);

        return deliveryMapper.toDto(deliveryRequested);
    }

    @GetMapping
    public List<DeliveryDTO> list() {
        List<Delivery> deliveries = deliveryRepository.findAll();

        return deliveryMapper.toCollectionDto(deliveries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDTO> search(@PathVariable Long id) {
        /*Forma alternativa
        return deliveryRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());*/
        return deliveryRepository.findById(id).map(delivery -> {
                    //Utilizando  ModelMapper
                    DeliveryDTO deliveryDto = deliveryMapper.toDto(delivery);

                    //Forma tradicional
                    /*DeliveryDTO deliveryDto = new DeliveryDTO();
                    deliveryDto.setId(delivery.getId());
                    deliveryDto.setClientName(delivery.getClient().getName());
                    deliveryDto.setReceiver(new ReceiverDTO());
                    deliveryDto.getReceiver().setName(delivery.getReceiver().getName());
                    deliveryDto.getReceiver().setStreet(delivery.getReceiver().getStreet());
                    deliveryDto.getReceiver().setNumber(delivery.getReceiver().getNumber());
                    deliveryDto.getReceiver().setComplement(delivery.getReceiver().getComplement());
                    deliveryDto.getReceiver().setDistrict(delivery.getReceiver().getDistrict());
                    deliveryDto.setFare(delivery.getFare());
                    deliveryDto.setStatus(delivery.getStatus());
                    deliveryDto.setOrderDate(delivery.getOrderDate());
                    deliveryDto.setDeliveryDate(delivery.getDeliveryDate());*/

                    return ResponseEntity.ok(deliveryDto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
