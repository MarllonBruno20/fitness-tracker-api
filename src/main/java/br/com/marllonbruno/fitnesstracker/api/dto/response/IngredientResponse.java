package br.com.marllonbruno.fitnesstracker.api.dto.response;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.RecipeIngredientMeasurementUnit;

public record IngredientResponse(
        String name,
        Double displayQuantity,
        RecipeIngredientMeasurementUnit displayUnit

) {
}
