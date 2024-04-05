package com.example.tfg_rest.controller;

import com.example.tfg_rest.controller.utils.ControllerValidationErrors;
import com.example.tfg_rest.models.dao.CommentDAOImpl;
import com.example.tfg_rest.models.dao.TipDAOImpl;
import com.example.tfg_rest.models.dto.TipCreateEntity;
import com.example.tfg_rest.models.entity.Tip;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tips")
public class TipController {

    @Autowired
    private CommentDAOImpl commentService;

    @Autowired
    private TipDAOImpl tipService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> showAll() {
        return ResponseEntity.ok(tipService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> show(@PathVariable long id) {

        Tip tip = tipService.findById(id);

        if (tip == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tip);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody @Valid TipCreateEntity newTip, BindingResult result) {

        if (result.hasFieldErrors()) {
            return ControllerValidationErrors.generateFieldErrors(result);
        }

        Tip tip = new Tip();

        tip.setTipText(newTip.tipText);

        tip.setComments(commentService.findAll());

        tipService.save(tip);

        return ResponseEntity.ok(tip);
    }
}
