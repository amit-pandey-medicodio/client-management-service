package com.example.client.repository;

import com.example.client.entity.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {
}
