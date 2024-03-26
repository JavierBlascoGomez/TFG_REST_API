package models.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

import java.util.Date;

@ToString
public class TFGRegisterCreateEntity {

    public Date register_date;

    @DecimalMin(value = "0.0", inclusive = false, message = "The serum creatinine must be grater than 0")
    public double serum_creatinine;

    @DecimalMin(value = "0.0", inclusive = false, message = "The result must be grater than 0")
    public double result;

    @NotNull(message = "The user must be valid")
    @Min(value = 1, message = "The user id must be a valid id")
    public long userId;
}
