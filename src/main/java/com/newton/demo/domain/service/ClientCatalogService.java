package com.newton.demo.domain.service;

import com.newton.demo.domain.exception.RulesException;
import com.newton.demo.domain.model.Client;
import com.newton.demo.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientCatalogService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        boolean isUnique = clientRepository
                .findByEmail(client.getEmail())
                .stream()
                .anyMatch(clientRegistred -> !clientRegistred.equals(client));
        if (isUnique) {
            throw new RulesException("Email previamente cadastrado");
        }

        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public Client search(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RulesException("Cliente não encontrado"));
    }
}
