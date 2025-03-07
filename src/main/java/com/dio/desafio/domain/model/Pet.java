package com.dio.desafio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pets")
@Getter @Setter

public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String species;
    private String breed;
    private int age;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

}

