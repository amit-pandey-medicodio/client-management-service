package com.example.client.service;

import com.example.client.dto.ClientRequestDTO;
import com.example.client.dto.ClientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ClientDetailsService {
    ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);
    ClientResponseDTO getClientById(Long clientId);
    List<ClientResponseDTO> getAllClients();
    ClientResponseDTO updateClient(Long clientId, ClientRequestDTO clientRequestDTO);
    void deleteClient(Long clientId);
}
