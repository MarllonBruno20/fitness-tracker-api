package br.com.marllonbruno.fitnesstracker.api.dto.response;

import java.util.List;

public record RecipeDetailResponse(

        String authorName,

        Long id,
        String name,
        String description,
        String instructions,
        String image,
        Integer prepTimeMinutes,
        Integer servings,

        Integer totalCalories,
        Integer totalProtein,
        Integer totalCarbohydrate,
        Integer totalLipids,

        List<IngredientResponse> ingredients
) {
}
