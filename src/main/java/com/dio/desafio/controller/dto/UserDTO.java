package com.dio.desafio.controller.dto;

import com.dio.desafio.domain.model.User;


public record UserDTO(
        Long id,
        String name,
        String Email,
        String Telephone
) {
    public UserDTO(User model) {
        this(
                model.getId(),
                model.getName(),
                model.getEmail(),
                model.getTelephone()
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setEmail(this.Email);
        model.setTelephone(this.Telephone);
        return model;
    }
}
