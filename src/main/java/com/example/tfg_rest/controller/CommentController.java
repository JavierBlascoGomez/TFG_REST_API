package com.example.tfg_rest.controller;

import com.example.tfg_rest.controller.utils.ControllerValidationErrors;
import com.example.tfg_rest.models.dao.CommentDAOImpl;
import com.example.tfg_rest.models.dao.TipDAOImpl;
import com.example.tfg_rest.models.dao.UserDAOImpl;
import com.example.tfg_rest.models.dto.CommentCreateEntity;
import com.example.tfg_rest.models.entity.Comment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentDAOImpl commentService;

    @Autowired
    private UserDAOImpl userService;

    @Autowired
    private TipDAOImpl tipService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> showAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> show(@PathVariable long id) {

        Comment comment = commentService.findById(id);

        if (comment == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(comment);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody @Valid CommentCreateEntity newComment, BindingResult result) {

        if (result.hasFieldErrors()) {
            return ControllerValidationErrors.generateFieldErrors(result);
        }

        Comment comment = new Comment();

        comment.setDate(new Date());

        comment.setUser(userService.findById(newComment.userId));

        comment.setCommentText(newComment.commentText);

        comment.setTip(tipService.findById(newComment.tipId));

        commentService.save(comment);

        return ResponseEntity.ok(newComment);
    }
}