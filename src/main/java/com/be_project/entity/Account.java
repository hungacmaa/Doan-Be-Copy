package com.be_project.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @Size(min = 6, message = "Password should have at least 6 characters")
    @NotBlank(message = "Password is required")
    private String password;

    private String name;
    private String status;
    private String avatar;
    private String phone;
    private String address;
    @ManyToOne
    private Role role;
    private String latitude = "0";
    private String longitude = "0";
}
