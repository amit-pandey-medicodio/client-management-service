package com.example.client.service;

import com.example.client.dto.ClientRequestDTO;
import com.example.client.dto.ClientResponseDTO;
import com.example.client.entity.ClientDetails;
import com.example.client.repository.ClientDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        log.info("Creating client with data: {}", clientRequestDTO);
        ClientDetails client = new ClientDetails();
        client.setClientName(clientRequestDTO.getClientName());
        client.setContactPerson(clientRequestDTO.getContactPerson());
        client.setContactInfo(clientRequestDTO.getContactInfo());
        client.setStatus(clientRequestDTO.getStatus());
        client.setApiAccess(clientRequestDTO.isApiAccess());
        client.setCreatedDate(LocalDateTime.now());

        ClientDetails savedClient = clientDetailsRepository.save(client);
        ClientResponseDTO responseDTO = mapToClientResponseDTO(savedClient);
        log.info("Client created: {}", responseDTO);
        return responseDTO;
    }

    @Override
    public ClientResponseDTO getClientById(Long clientId) {
        log.info("Retrieving client with ID: {}", clientId);
        ClientDetails client = clientDetailsRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        ClientResponseDTO responseDTO = mapToClientResponseDTO(client);
        log.info("Client retrieved: {}", responseDTO);
        return responseDTO;
    }

    @Override
    public List<ClientResponseDTO> getAllClients() {
        log.info("Retrieving all clients");
        List<ClientDetails> clients = clientDetailsRepository.findAll();
        List<ClientResponseDTO> responseDTOs = clients.stream()
                .map(this::mapToClientResponseDTO)
                .collect(Collectors.toList());
        log.info("Clients retrieved: {}", responseDTOs);
        return responseDTOs;
    }

    @Override
    public ClientResponseDTO updateClient(Long clientId, ClientRequestDTO clientRequestDTO) {
        log.info("Updating client with ID: {} with data: {}", clientId, clientRequestDTO);
        ClientDetails client = clientDetailsRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setClientName(clientRequestDTO.getClientName());
        client.setContactPerson(clientRequestDTO.getContactPerson());
        client.setContactInfo(clientRequestDTO.getContactInfo());
        client.setStatus(clientRequestDTO.getStatus());
        client.setApiAccess(clientRequestDTO.isApiAccess());

        ClientDetails updatedClient = clientDetailsRepository.save(client);
        ClientResponseDTO responseDTO = mapToClientResponseDTO(updatedClient);
        log.info("Client updated: {}", responseDTO);
        return responseDTO;
    }

    @Override
    public void deleteClient(Long clientId) {
        log.info("Deleting client with ID: {}", clientId);
        clientDetailsRepository.deleteById(clientId);
        log.info("Client deleted with ID: {}", clientId);
    }

    private ClientResponseDTO mapToClientResponseDTO(ClientDetails clientDetails) {
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        responseDTO.setClientName(clientDetails.getClientName());
        responseDTO.setContactPerson(clientDetails.getContactPerson());
        responseDTO.setContactInfo(clientDetails.getContactInfo());
        responseDTO.setStatus(clientDetails.getStatus());
        responseDTO.setApiAccess(clientDetails.isApiAccess());
        return responseDTO;
    }
}
