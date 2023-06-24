package com.bjit.SpringProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestModel {

    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String role;

}
