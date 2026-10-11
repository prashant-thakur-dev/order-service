package in.coderarmy.orderit.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record Product(Long id, @NotBlank String name, @Max(1000000) BigDecimal price) {
}
