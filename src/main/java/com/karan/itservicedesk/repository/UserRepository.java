package com.karan.itservicedesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karan.itservicedesk.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
