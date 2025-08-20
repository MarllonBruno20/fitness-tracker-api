package br.com.marllonbruno.fitnesstracker.api.controller;

import br.com.marllonbruno.fitnesstracker.api.dto.request.RecipeCreateRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.RecipeDetailResponse;
import br.com.marllonbruno.fitnesstracker.api.dto.response.RecipeSummaryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @PostMapping("/new")
    public ResponseEntity<RecipeSummaryResponse> newRecipe(@RequestBody @Valid RecipeCreateRequest request) {
        return null;
    }

    @GetMapping("/list-all")
    public ResponseEntity<RecipeSummaryResponse> listAllRecipes() { return null; }

    @GetMapping("/details/{id}")
    public ResponseEntity<RecipeDetailResponse> getRecipeDetails(@PathVariable Long id) { return null; }
}
