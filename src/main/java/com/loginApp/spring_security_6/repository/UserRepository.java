package com.loginApp.spring_security_6.repository;

import com.loginApp.spring_security_6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
