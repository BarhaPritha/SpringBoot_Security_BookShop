package com.bjit.SpringProject.repository;

import com.bjit.SpringProject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public UserEntity findByEmail(String email);

}
