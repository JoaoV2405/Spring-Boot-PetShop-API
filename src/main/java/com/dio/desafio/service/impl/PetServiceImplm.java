package com.dio.desafio.service.impl;


import com.dio.desafio.domain.model.Pet;
import com.dio.desafio.domain.model.User;
import com.dio.desafio.domain.repository.PetRepository;
import com.dio.desafio.domain.repository.UserRepository;
import com.dio.desafio.service.PetService;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetServiceImplm implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetServiceImplm(PetRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        Pet pet = petRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pet not found"));
        return pet;
    }

    @Override
    public Pet createPet(Pet pet) {
        Optional<User> owner= userRepository.findById(pet.getOwner().getId());
        if (owner.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        return petRepository.save(pet);
    }

    @Override
    public Pet update(Long id, Pet pet) {
        Pet existingPet = petRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pet not found"));

        existingPet.setName(pet.getName());
        existingPet.setSpecies(pet.getSpecies());
        existingPet.setBreed(pet.getBreed());
        existingPet.setAge(pet.getAge());
        existingPet.setOwner(pet.getOwner());

        return petRepository.save(existingPet);
    }

    @Override
    public void delete(Long id) {
        Pet pet = petRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pet not found"));
    
    petRepository.delete(pet);
}


    

    

}