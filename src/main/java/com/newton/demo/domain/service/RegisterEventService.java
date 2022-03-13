package com.newton.demo.domain.service;

import com.newton.demo.domain.exception.RulesException;
import com.newton.demo.domain.model.Delivery;
import com.newton.demo.domain.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterEventService {
    @Autowired
    private DeliverySearchService deliverySearchService;

    @Transactional
    public Event register(Long deliveryId, String description) {
        Delivery delivery = deliverySearchService.search(deliveryId);

        //Como o objeto já existe no banco, e estamos alterando ele, não é necessario chamar o metodo save
        return delivery.addEvent(description);
    }
}
