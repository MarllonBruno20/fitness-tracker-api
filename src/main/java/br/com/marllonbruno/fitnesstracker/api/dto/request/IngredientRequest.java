package br.com.marllonbruno.fitnesstracker.api.dto.request;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.RecipeIngredientMeasurementUnit;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IngredientRequest(
        @NotNull(message = "Por favor, informe o id do ingrediente")
        Long ingredientId,

        @NotNull(message = "Por favor, informe a quantidade em gramas do ingrediente")
        @Positive(message = "Por favor, a quantidade em gramas deve ser maior que zero")
        Double quantityInGrams,

        @NotNull(message = "Por favor, informe a unidade de medida do ingrediente")
        RecipeIngredientMeasurementUnit displayUnit
) {
}
