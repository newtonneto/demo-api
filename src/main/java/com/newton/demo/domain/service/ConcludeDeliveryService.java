package com.newton.demo.domain.service;

import com.newton.demo.domain.model.Delivery;
import com.newton.demo.domain.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConcludeDeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliverySearchService deliverySearchService;

    @Transactional
    public void conclude(Long id) {
        Delivery delivery = deliverySearchService.search(id);
        delivery.conclude();

        deliveryRepository.save(delivery);
    }
}
