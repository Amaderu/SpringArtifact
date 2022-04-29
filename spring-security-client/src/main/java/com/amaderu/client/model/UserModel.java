package com.amaderu.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String email;
    private String password;
    private String confirmPassword;
}
