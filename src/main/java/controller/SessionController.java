package controller;

import controller.utils.ControllerValidationErrors;
import jakarta.validation.Valid;
import models.dao.UserDAOImpl;
import models.dto.LoginPost;
import models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SessionController {

    @Autowired
    private UserDAOImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTAuthtenticationConfig authtenticationConfig;

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
