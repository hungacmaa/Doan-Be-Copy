package com.be_project.entity.dto;

import com.be_project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountToken {
    private long id;
    private String username;
    private String password;
    private String name;
    private String status;
    private String avatar;
    private String phone;
    private String address;
    private Role role;
    private String latitude;
    private String longitude;
    private String token;
}
