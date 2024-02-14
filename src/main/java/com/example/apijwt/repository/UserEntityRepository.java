package com.example.apijwt.repository;

import com.example.apijwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    <Optional>UserEntity findByUsername(String username);
}
