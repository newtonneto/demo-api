package com.newton.demo.api.controller;

import com.newton.demo.domain.model.Client;
import com.newton.demo.domain.repository.ClientRepository;
import com.newton.demo.domain.service.ClientCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    /*@PersistenceContext
    private EntityManager entityManager;*/

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientCatalogService clientCatalogService;

    @GetMapping
    public List<Client> list() {
        //return entityManager.createQuery("from Client", Client.class).getResultList();
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> find(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@Valid @RequestBody Client client) {
        return clientCatalogService.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@Valid @PathVariable Long id, @RequestBody Client client) {
        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        //Seta o id do client no objeto para que ao salvar a entidade seja atualizada ao inves de criar uma nova
        client.setId(id);
        client = clientCatalogService.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clientCatalogService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
