package com.example.tfg_rest.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.ToString;

import java.util.Date;

@ToString
public class CommentCreateEntity {

    @NotEmpty(message = "No tip text specified.")
    @Size(min = 3, message = "The text of the comment must be greater than 3 characters.")
    public String commentText;

    public Date date;

    @NotNull(message = "The user must be valid")
    @Min(value = 1, message = "The user id must be a valid id")
    public long userId;

    @NotNull(message = "The tip must be valid")
    @Min(value = 1, message = "The tip id must be a valid id")
    public long tipId;
}
