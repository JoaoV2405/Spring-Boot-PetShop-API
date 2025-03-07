package com.dio.desafio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.desafio.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
