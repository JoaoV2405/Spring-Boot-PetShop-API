package com.dio.desafio.service;

import com.dio.desafio.domain.model.Pet;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PetService {
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    Pet createPet(Pet Pet);
    void delete(Long id);
    Object update(Long id, Pet model);
    
}

