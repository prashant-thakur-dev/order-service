package in.coderarmy.orderit.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record Product(Long id, @NotBlank String name, @Positive @DecimalMax("1000000") BigDecimal price) {
}
