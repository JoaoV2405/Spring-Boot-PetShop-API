package com.dio.desafio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.desafio.domain.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

}
