package com.example.client.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_details")
public class ClientDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", updatable = false, nullable = false)
    private Long clientId;

    @Column(nullable = false)	
    private String clientName;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private boolean apiAccess;
}
