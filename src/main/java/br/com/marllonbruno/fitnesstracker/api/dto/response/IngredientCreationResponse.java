package br.com.marllonbruno.fitnesstracker.api.dto.response;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Group;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.IngredientMeasurementUnit;

public record IngredientCreationResponse(

        Long id,
        String displayName,
        String searchTags,
        IngredientMeasurementUnit ingredientMeasurementUnitOfMeasurement,
        Group group,
        Double caloriesPer100,
        Double totalCarbohydratePer100,
        Double availableCarbohydratePer100,
        Double proteinPer100,
        Double lipidsPer100,
        Double fiberPer100,
        Double cholesterolPer100,
        Double totalSaturatedFattyAcidsPer100,
        Double transFattyAcidsPer100,
        Double calciumPer100,
        Double ironPer100,
        Double sodiumPer100,
        Double vitaminARePer100,
        Double vitaminARaePer100,
        Double vitaminDPer100,
        Double vitaminEPer100,
        Double vitaminB6Per100,
        Double vitaminB12Per100,
        Double vitaminCPer100,
        Double alcoholPer100,
        Double addedSaltPer100,
        Double addedSugarPer100
) {
}
