package com.newton.demo.domain.service;

import com.newton.demo.domain.exception.EntityNotFoundException;
import com.newton.demo.domain.model.Delivery;
import com.newton.demo.domain.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverySearchService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    public Delivery search(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
    }
}
