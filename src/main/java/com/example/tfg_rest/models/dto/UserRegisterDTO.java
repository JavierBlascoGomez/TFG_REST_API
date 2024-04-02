package com.example.tfg_rest.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UserRegisterDTO {

    @NotEmpty(message = ("No username specified"))
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    public String username;

    @NotEmpty(message = ("No name specified"))
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    public String name;

    public String surnames;

    @NotEmpty(message = ("No email specified"))
    @Size(min = 3, message = "Email must be greater than 3 characters")
    public String email;

    @NotEmpty(message = ("No birth date specified"))
    public Date birth_date;

    @NotEmpty(message = ("No password specified"))
    public String password;
}
