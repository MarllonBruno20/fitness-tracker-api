package br.com.marllonbruno.fitnesstracker.api.dto.response;

public record IngredientResponse(
        String name,
        Double displayQuantity,
        String displayUnit

) {
}
