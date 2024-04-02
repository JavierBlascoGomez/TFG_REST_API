package com.example.tfg_rest.models.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;

import java.util.List;

@ToString
public class UserTFGRegisterCreateEntity {

    @NotEmpty(message = "The resgister must have at least one entrance")
    @Valid
    public List<TFGRegisterCreateEntity> registers;
}
