package br.com.marllonbruno.fitnesstracker.api.dto.response;

public record RecipeSummaryResponse(
        Long id,
        String name,
        String image,
        Integer prepTimeMinutes,
        Integer servings,
        Integer totalCalories
) {
}
