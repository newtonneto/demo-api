package com.newton.demo.api.controller;

import com.newton.demo.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {
    @GetMapping("/clients")
    public List<Client> list() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Jaum");
        client1.setPhone("HAUHSUAH");
        client1.setEmail("ahushauhsa");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Jaum2");
        client2.setPhone("HAUHSUAH2");
        client2.setEmail("ahushauhsa2");

        return Arrays.asList(client1, client2);
    }
}
