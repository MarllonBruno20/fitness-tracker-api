package br.com.marllonbruno.fitnesstracker.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record RecipeCreateRequest(
        @NotBlank(message = "Por favor, informe o nome da receita")
        String name,

        @NotBlank(message = "Por favor, informe a descrição da receita")
        String description,

        @NotEmpty(message = "Por favor, informe as instruções da receita")
        List<String> instructions,

        @NotBlank(message = "Por favor, informe a imagem da receita")
        String image,

        @NotNull(message = "Por favor, informe o tempo de preparo da receita")
        @Positive(message = "Por favor, o tempo de preparo deve ser maior que zero")
        Integer prepTimeMinutes,

        @Positive(message = "Por favor, a quantidade de porções deve ser maior que zero")
        Integer servings,

        @NotEmpty(message = "Por favor, informe os ingredientes da receita")
        @Valid
        List<IngredientRequest> ingredients
) {

}
