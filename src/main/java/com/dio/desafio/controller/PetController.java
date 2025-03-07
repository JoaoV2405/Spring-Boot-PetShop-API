package com.dio.desafio.controller;
import com.dio.desafio.controller.dto.PetDTO;
import com.dio.desafio.domain.model.Pet;
import com.dio.desafio.domain.model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dio.desafio.service.PetService;
import com.dio.desafio.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final UserService userService;

    public PetController(PetService petService, UserService userService){
        this.petService = petService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
        var pet = petService.getPetById(id);
        return pet != null ? ResponseEntity.ok(new PetDTO(pet)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Pet created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid pet data provided")
    })
    public ResponseEntity<PetDTO> create(@RequestBody PetDTO petDto) {
        // Buscar o dono antes de criar o pet
        User owner = userService.FindbyID(petDto.ownerId());
        if (owner == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        var pet = petService.createPet(petDto.toModel(owner));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pet.getId())
                .toUri();

        return ResponseEntity.created(location).body(new PetDTO(pet));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> update(@PathVariable Long id, @RequestBody PetDTO petDto) {
    User owner = userService.FindbyID(petDto.ownerId());
    if (owner == null) {
        return ResponseEntity.unprocessableEntity().build();  
    }
    Object updatedPet = petService.update(id, petDto.toModel(owner));
        if (!(updatedPet instanceof Pet pet)) {
            return ResponseEntity.unprocessableEntity().build();  
        } 
        return ResponseEntity.ok(new PetDTO(pet));
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
