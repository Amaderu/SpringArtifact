package com.amaderu.client.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue//(strategy = GenerationType.IDENTITY)

    /*@GeneratedValue(generator = "Long")
    @GenericGenerator(
            name = "Long",
            strategy = "org.hibernate.id.UUIDGenerator"BIGINT
    )
    @Column(name = "ID", updatable = false, nullable = false)*/
    private Long id;
    private String email;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled = false;
}
