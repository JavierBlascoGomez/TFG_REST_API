package controller.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public class ControllerValidationErrors {
    public static ResponseEntity<List<String>> generateFieldErrors(BindingResult result) {
        List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> STR."The field '\{err.getField()}' contains errors. \{err.getDefaultMessage()}").toList();

        return ResponseEntity.badRequest().body(errors);
    }
}
