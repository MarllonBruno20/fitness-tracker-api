package br.com.marllonbruno.fitnesstracker.api.dto.response;

public record IngredientListResponse(
        Long id,
        String name,
        String ingredientMeasurementUnitOfMeasurement,
        String group,
        Double caloriesPer100,
        Double availableCarbohydratePer100,
        Double proteinPer100,
        Double lipidsPer100
) {
}
