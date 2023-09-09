package com.sowfte.rabbitMQ.dto;

import lombok.Data;

@Data
public class UserRecord {
    private Long id;
    private String fullName;
    private String email;
    private String password;
}
