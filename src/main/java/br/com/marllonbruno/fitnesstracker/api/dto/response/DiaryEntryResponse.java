package br.com.marllonbruno.fitnesstracker.api.dto.response;

public record DiaryEntryResponse(
        Long id,

        String recipeName,
        String recipeImage,
        Double servingsConsumed,

        Integer caloriesConsumed,
        Double proteinConsumed,
        Double carbsConsumed,
        Double fatConsumed
) {
}
