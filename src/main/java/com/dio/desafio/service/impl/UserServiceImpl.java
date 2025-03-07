package com.dio.desafio.service.impl;

import org.springframework.stereotype.Service;

import com.dio.desafio.domain.model.User;
import com.dio.desafio.domain.repository.UserRepository;
import com.dio.desafio.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User FindbyID(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user;

    }

    @Override
    public User Create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setTelephone(user.getTelephone());
        existingUser.setPets(user.getPets());
        existingUser.setAppointments(user.getAppointments());
    
            return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    
        userRepository.delete(user);
}

}
