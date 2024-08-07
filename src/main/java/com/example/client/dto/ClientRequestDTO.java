package com.example.client.dto;

import lombok.Data;

@Data
public class ClientRequestDTO {
    private String clientName;
    private String contactPerson;
    private String contactInfo;
    private String status;
    private boolean apiAccess;
}

