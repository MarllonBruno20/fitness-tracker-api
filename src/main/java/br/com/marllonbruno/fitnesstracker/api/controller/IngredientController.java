package br.com.marllonbruno.fitnesstracker.api.controller;

import br.com.marllonbruno.fitnesstracker.api.dto.request.IngredientCreationRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.IngredientCreationResponse;
import br.com.marllonbruno.fitnesstracker.api.dto.response.IngredientListResponse;
import br.com.marllonbruno.fitnesstracker.api.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/new")
    public ResponseEntity<IngredientCreationResponse> newIngredient(@Valid @RequestBody IngredientCreationRequest request) {

        System.out.println("Entrou no newIngredient");

        IngredientCreationResponse response = ingredientService.saveIngredient(request);

        // Retorne o status 201 Created com o objeto criado no corpo
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<IngredientListResponse>> listIngredients(@RequestParam(required = false) String search) {
        List<IngredientListResponse> response;
        if(search != null){
            response = ingredientService.searchIngredients(search);
        } else {
            response = ingredientService.listAllIngredients();
        }
        return ResponseEntity.ok(response);
    }
}
