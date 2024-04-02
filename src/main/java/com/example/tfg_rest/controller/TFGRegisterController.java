package com.example.tfg_rest.controller;

import com.example.tfg_rest.controller.utils.ControllerValidationErrors;
import com.example.tfg_rest.models.dao.TFGRegisterDAOImpl;
import com.example.tfg_rest.models.dao.UserDAOImpl;
import com.example.tfg_rest.models.dto.TFGRegisterCreateEntity;
import com.example.tfg_rest.models.entity.TFGRegister;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/registers")
public class TFGRegisterController {

    @Autowired
    private TFGRegisterDAOImpl registerService;

    @Autowired
    private UserDAOImpl userService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> showAll() {
        return ResponseEntity.ok(registerService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> show(@PathVariable long id) {

        TFGRegister register = registerService.findById(id);

        if (register == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(register);
    }

    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> create(@RequestBody @Valid TFGRegisterCreateEntity newRegister, BindingResult result) {

        if (result.hasFieldErrors()) {
            return ControllerValidationErrors.generateFieldErrors(result);
        }

        TFGRegister register = new TFGRegister();

        register.setRegister_date(new Date());

        register.setUser(userService.findById(newRegister.userId));

        register.setResult(newRegister.result);

        register.setSerum_creatinine(newRegister.serum_creatinine);

        registerService.save(register);

        return ResponseEntity.ok(newRegister);
    }
}