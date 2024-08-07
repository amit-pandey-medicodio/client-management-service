package com.example.client.controller;

import com.example.client.dto.ClientRequestDTO;
import com.example.client.dto.ClientResponseDTO;
import com.example.client.service.ClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/clients")
public class ClientDetailsController {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        log.info("Received request to create client: {}", clientRequestDTO);
        ClientResponseDTO createdClient = clientDetailsService.createClient(clientRequestDTO);
        log.info("Response for create client: {}", createdClient);
        return ResponseEntity.ok(createdClient);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long clientId) {
        log.info("Received request to get client by ID: {}", clientId);
        ClientResponseDTO client = clientDetailsService.getClientById(clientId);
        log.info("Response for get client by ID: {}", client);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        log.info("Received request to get all clients");
        List<ClientResponseDTO> clients = clientDetailsService.getAllClients();
        log.info("Response for get all clients: {}", clients);
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long clientId, @RequestBody ClientRequestDTO clientRequestDTO) {
        log.info("Received request to update client: {} with data: {}", clientId, clientRequestDTO);
        ClientResponseDTO updatedClient = clientDetailsService.updateClient(clientId, clientRequestDTO);
        log.info("Response for update client: {}", updatedClient);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        log.info("Received request to delete client: {}", clientId);
        clientDetailsService.deleteClient(clientId);
        log.info("Client deleted: {}", clientId);
        return ResponseEntity.noContent().build();
    }
}
