package com.bjit.SpringProject.service;

import com.bjit.SpringProject.model.UserRequestModel;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> register(UserRequestModel requestModel);
    ResponseEntity<Object> getUser(String email);
    ResponseEntity<Iterable<Object>> getAllUsers();
    ResponseEntity<Object> updateUser(UserRequestModel requestModel);

}
