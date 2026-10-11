package in.coderarmy.orderit.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record Product(Long id, @NotBlank String name, @Positive @Max(1000000) double price) {
}
