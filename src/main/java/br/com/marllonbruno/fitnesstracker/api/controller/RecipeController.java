package br.com.marllonbruno.fitnesstracker.api.controller;

import br.com.marllonbruno.fitnesstracker.api.dto.request.RecipeCreateRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.RecipeDetailResponse;
import br.com.marllonbruno.fitnesstracker.api.dto.response.RecipeSummaryResponse;
import br.com.marllonbruno.fitnesstracker.api.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/new")
    public ResponseEntity<RecipeDetailResponse> newRecipe(@RequestBody @Valid RecipeCreateRequest request) {

        System.out.println("Entrou no Controller newRecipe");

        RecipeDetailResponse recipeDetailResponse = recipeService.saveRecipe(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(recipeDetailResponse);
    }

    @GetMapping("/list-all")
    public ResponseEntity<RecipeSummaryResponse[]> listAllRecipes() {

        RecipeSummaryResponse[] recipeSummaryResponses = recipeService.listAllRecipes();
        return ResponseEntity.ok(recipeSummaryResponses);

    }

    @GetMapping("/details/{id}")
    public ResponseEntity<RecipeDetailResponse> getRecipeDetails(@PathVariable Long id) {
        RecipeDetailResponse recipeDetailResponse = recipeService.getRecipeDetails(id);
        return ResponseEntity.ok(recipeDetailResponse);
    }
}
