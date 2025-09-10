package br.com.marllonbruno.fitnesstracker.api.dto.request;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.MealType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DiaryEntryRequest(
        @NotNull(message = "O ID do ingrediente deve ser informado")
        Long recipeId,
        @NotNull(message = "A data e hora da refeição deve ser informada")
        LocalDateTime consumptionTimestamp,
        @NotNull(message = "O tipo da refeição deve ser informado")
        MealType mealType,
        @NotNull(message = "O número de porções consumidas deve ser informado")
        Double servingsConsumed
) {
}
