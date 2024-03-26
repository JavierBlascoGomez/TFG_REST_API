package models.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;

@ToString
public class LoginPost {
    @NotEmpty(message = "The username must not be empty")
    public String username;

    @NotEmpty(message = "The password must not be empty")
    public String password;
}