package com.dio.desafio.controller.dto;

import com.dio.desafio.domain.model.Pet;
import com.dio.desafio.domain.model.User;

public record PetDTO(
    Long id,
    String name,
    String species,
    String breed,
    int age,
    Long ownerId
) {
    public PetDTO(Pet model) {
        this(
            model.getId(),
            model.getName(),
            model.getSpecies(),
            model.getBreed(),
            model.getAge(),
            model.getOwner().getId()
        );
    }

    public Pet toModel(User owner) {
        Pet model = new Pet();
        model.setId(this.id);
        model.setName(this.name);
        model.setSpecies(this.species);
        model.setBreed(this.breed);
        model.setAge(this.age);
        model.setOwner(owner); // Agora recebemos um objeto User como parâmetro
        return model;
    }
}
