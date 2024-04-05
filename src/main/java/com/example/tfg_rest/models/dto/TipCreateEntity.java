package com.example.tfg_rest.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.ToString;

@ToString
public class TipCreateEntity {

    @NotEmpty(message = "No tip text specified.")
    @Size(min = 3, message = "The text of the tip must be greater than 3 characters.")
    public String tipText;
}
