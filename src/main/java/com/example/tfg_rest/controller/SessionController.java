package com.example.tfg_rest.controller;

import com.example.tfg_rest.models.dao.UserDAOImpl;
import com.example.tfg_rest.models.dto.LoginPost;
import com.example.tfg_rest.models.entity.User;
import com.example.tfg_rest.controller.utils.ControllerValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.tfg_rest.security.JWTAuthenticationConfig;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SessionController {

    @Autowired
    private UserDAOImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTAuthenticationConfig authtenticationConfig;

    @PostMapping("/login")
    @PreAuthorize("isAnonimous()")
    public ResponseEntity<?> login(@RequestBody @Valid LoginPost body, BindingResult result) {

        if (result.hasFieldErrors()) {
            return ControllerValidationErrors.generateFieldErrors(result);
        }

        User user = userService.findByUsername(body.username);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!passwordEncoder.matches(body.password, user.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong password");
        }

        String token = authtenticationConfig.getJWTToken(user);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
