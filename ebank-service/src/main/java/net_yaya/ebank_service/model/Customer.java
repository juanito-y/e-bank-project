package net_yaya.ebank_service.model;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}