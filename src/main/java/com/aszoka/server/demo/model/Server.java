package com.aszoka.server.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.*;

@Entity
@Data
@NoArgsConstructor
public class Server {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "IP address cannot be empty or null.")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;
}
