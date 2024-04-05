package com.example.tfg_rest.models.dto;

import com.example.tfg_rest.models.entity.Comment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;

import java.util.List;

@ToString
public class TipCommentCreateEntity {

    @NotEmpty(message = "The comment must have at least one entrance")
    @Valid
    public List<Comment> comments;
}
