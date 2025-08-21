package br.com.marllonbruno.fitnesstracker.api.dto.request;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Group;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.IngredientMeasurementUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record IngredientCreationRequest(
        @NotBlank(message = "Por favor, informe o nome do ingrediente")
        String displayName,

        @NotBlank(message = "Por favor, informe as tags de busca do ingrediente")
        String searchTags,

        @NotNull(message = "Por favor, informe a unidade de medida do ingrediente")
        IngredientMeasurementUnit ingredientMeasurementUnitOfMeasurement,

        @NotNull(message = "Por favor, informe o grupo do ingrediente")
        Group group,

        @Positive(message = "Por favor, a quantidade de calorias por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de calorias por 100 gramas")
        Double caloriesPer100,

        @Positive(message = "Por favor, a quantidade de carboidratos totais por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de carboidratos totais por 100 gramas")
        Double totalCarbohydratePer100,

        @Positive(message = "Por favor, a quantidade de carboidratos disponíveis por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de carboidratos disponíveis por 100 gramas")
        Double availableCarbohydratePer100,

        @Positive(message = "Por favor, a quantidade de proteínas por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de proteínas por 100 gramas")
        Double proteinPer100,

        @Positive(message = "Por favor, a quantidade de lípidios por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de lípidios por 100 gramas")
        Double lipidsPer100,

        @PositiveOrZero(message = "Por favor, a quantidade de fibras por 100 gramas deve ser maior ou igual a zero")
        @NotNull(message = "Por favor, informe a quantidade de fibras por 100 gramas")
        Double fiberPer100,

        @Positive(message = "Por favor, a quantidade de colesterol por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de colesterol por 100 gramas")
        Double cholesterolPer100,

        @Positive(message = "Por favor, a quantidade de gorduras saturadas por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de gorduras saturadas por 100 gramas")
        Double totalSaturatedFattyAcidsPer100,

        @Positive(message = "Por favor, a quantidade de gorduras trans por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de gorduras trans por 100 gramas")
        Double transFattyAcidsPer100,

        @Positive(message = "Por favor, a quantidade de cálcio por 100 gramas deve ser maior que zero")
        @NotNull(message = "Por favor, informe a quantidade de cálcio por 100 gramas")
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
