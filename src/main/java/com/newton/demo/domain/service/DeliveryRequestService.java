package com.newton.demo.domain.service;

import com.newton.demo.domain.exception.RulesException;
import com.newton.demo.domain.model.Client;
import com.newton.demo.domain.model.Delivery;
import com.newton.demo.domain.model.DeliveryStatus;
import com.newton.demo.domain.repository.ClientRepository;
import com.newton.demo.domain.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DeliveryRequestService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private ClientCatalogService clientCatalogService;

    @Transactional
    public Delivery request(Delivery delivery) {
        Client client = clientCatalogService.search(delivery.getClient().getId());

        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(LocalDateTime.now());

        return deliveryRepository.save(delivery);
    }
}
