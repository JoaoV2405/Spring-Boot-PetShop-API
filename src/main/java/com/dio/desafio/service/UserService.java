package com.dio.desafio.service;

import org.springframework.stereotype.Service;

import com.dio.desafio.domain.model.User;


@Service
public interface UserService {
    User FindbyID(Long id);
    User Create(User user);
    User update(Long id, User userDTO);
    void delete(Long id);


}
