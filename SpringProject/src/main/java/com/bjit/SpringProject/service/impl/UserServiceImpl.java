package com.bjit.SpringProject.service.impl;

import com.bjit.SpringProject.entity.Role;
import com.bjit.SpringProject.entity.UserEntity;
import com.bjit.SpringProject.model.AuthenticationResponse;
import com.bjit.SpringProject.model.UserRequestModel;
import com.bjit.SpringProject.repository.UserRepository;
import com.bjit.SpringProject.service.UserService;
import com.bjit.SpringProject.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class  UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<Object> register(UserRequestModel requestModel) {
        UserEntity userEntity = UserEntity.builder()
                .email(requestModel.getEmail())
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .address(requestModel.getAddress())
                .password(requestModel.getPassword())
                .password(passwordEncoder.encode(requestModel.getPassword()))
                .role(Role.valueOf(requestModel.getRole()))
                .build();
        userRepository.save(userEntity);
        AuthenticationResponse authRes = AuthenticationResponse.builder()
                .token(jwtService.generateToken(userEntity))
                .build();
        return new ResponseEntity<>(authRes, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Object> getUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user==null){
            return new ResponseEntity<>("No user found with this email!", HttpStatus.NOT_FOUND);
        }
        else {
            UserEntity userEntity = UserEntity.builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .address(user.getAddress())
                    .build();
            return new ResponseEntity<>(userEntity, HttpStatus.FOUND);
        }
    }

    public ResponseEntity<Iterable<Object>> getAllUsers() {
        Iterable<Object> users = Collections.singleton(userRepository.findAll());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateUser(UserRequestModel requestUser) {
        UserEntity user = userRepository.findByEmail(requestUser.getEmail());
        if (user==null){
            return new ResponseEntity<>("No user found with this email!", HttpStatus.NOT_FOUND);
        }
        else {
            user.setEmail(requestUser.getEmail());
            user.setFirstName(requestUser.getFirstName());
            user.setLastName(requestUser.getLastName());
            user.setAddress(requestUser.getAddress());

            UserEntity savedUser = userRepository.save(user);

            UserEntity savedUserEntity = UserEntity.builder()
                    .email(savedUser.getEmail())
                    .firstName(savedUser.getFirstName())
                    .lastName(savedUser.getLastName())
                    .address(savedUser.getAddress())
                    .build();
            return new ResponseEntity<>(savedUserEntity, HttpStatus.OK);
        }
    }

}
