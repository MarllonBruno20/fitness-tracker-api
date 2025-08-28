package br.com.marllonbruno.fitnesstracker.api.service;

import br.com.marllonbruno.fitnesstracker.api.dto.request.IngredientRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.request.RecipeCreateRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.IngredientResponse;
import br.com.marllonbruno.fitnesstracker.api.dto.response.RecipeDetailResponse;
import br.com.marllonbruno.fitnesstracker.api.dto.response.RecipeSummaryResponse;
import br.com.marllonbruno.fitnesstracker.api.entity.Ingredient;
import br.com.marllonbruno.fitnesstracker.api.entity.Recipe;
import br.com.marllonbruno.fitnesstracker.api.entity.RecipeIngredient;
import br.com.marllonbruno.fitnesstracker.api.entity.User;
import br.com.marllonbruno.fitnesstracker.api.repository.IngredientRepository;
import br.com.marllonbruno.fitnesstracker.api.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final AutheticatedUserService autheticatedUserService;

    public RecipeService(RecipeRepository recipeRepository, AutheticatedUserService autheticatedUserService, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.autheticatedUserService = autheticatedUserService;
        this.ingredientRepository = ingredientRepository;
    }

    public RecipeDetailResponse saveRecipe(RecipeCreateRequest request){

        System.out.println("Entrou no saveRecipe");

        // Pegando o usuário autenticado
        User currentUser = autheticatedUserService.getAuthenticatedUser();
        System.out.println("Encontrei o usuário autenticado: " + currentUser.getEmail());

        // Criando a receita e populando com o que eu já tenho de dados
        Recipe recipe = new Recipe();

        recipe.setName(request.name());
        recipe.setDescription(request.description());
        recipe.setInstructions(request.instructions());
        recipe.setImage(request.image());
        recipe.setPrepTimeMinutes(request.prepTimeMinutes());
        recipe.setServings(request.servings());

        recipe.setUser(currentUser);
        recipe.setSuitableMeals(request.mealTypes());

        // Associando os ingredientes a receitas
        for (IngredientRequest ingredientRequest : request.ingredients()){
            Ingredient ingredient = ingredientRepository.findById(ingredientRequest.ingredientId())
                    .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

            System.out.println("Encontrei o ingrediente: " + ingredient.getDisplayName());

            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setQuantityInGrams(ingredientRequest.quantityInGrams());
            recipeIngredient.setDisplayQuantity(ingredientRequest.quantityInGrams());
            recipeIngredient.setDisplayUnit(ingredientRequest.displayUnit());

            recipe.getRecipeIngredients().add(recipeIngredient);
        }

        System.out.println("Ingredientes associados à receita: " + recipe.getRecipeIngredients().size());

        // Agora eu preciso calcular os valores nutricionais
        calculateNutritionalValues(recipe);

        System.out.println("Passou pelo método calculateNutritionalValues");

        Recipe savedRecipe = recipeRepository.save(recipe);

        List<IngredientResponse> ingredientResponses = savedRecipe.getRecipeIngredients().stream()
                .map(recipeIngredient -> new IngredientResponse(
                        recipeIngredient.getIngredient().getSearchTags(),
                        recipeIngredient.getDisplayQuantity(),
                        recipeIngredient.getDisplayUnit()
                ))
                .toList();

        return new RecipeDetailResponse(
                savedRecipe.getUser().getName(),
                savedRecipe.getId(),
                savedRecipe.getName(),
                savedRecipe.getDescription(),
                savedRecipe.getInstructions(),
                savedRecipe.getImage(),
                savedRecipe.getPrepTimeMinutes(),
                savedRecipe.getServings(),
                savedRecipe.getTotalCalories(),
                savedRecipe.getTotalProtein(),
                savedRecipe.getTotalCarbohydrate(),
                savedRecipe.getTotalLipids(),
                ingredientResponses
        );
    }

    /**
     * Método privado responsável por calcular os totais nutricionais de uma receita
     * e atribuí-los ao objeto Recipe.
     *
     * @param recipe O objeto Recipe com sua lista de recipeIngredients já preenchida.
     */
    private void calculateNutritionalValues(Recipe recipe){

        System.out.println("Entrou no calculateNutritionalValues");

        double totalCalories = 0.0;
        double totalCarbohydrate = 0.0;
        double totalProtein = 0.0;
        double totalLipids = 0.0;

        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
            Ingredient ingredient = recipeIngredient.getIngredient();
            Double quantityInGrams = recipeIngredient.getQuantityInGrams();

            // Pula para o próximo se algum dado essencial estiver faltando (medida de segurança)
            if(ingredient == null || quantityInGrams == null){
                continue;
            }

            // 3. Calcula a contribuição de cada nutriente para este ingrediente
            // A fórmula é: (quantidade / 100) * valor_nutricional_por_100g
            double factor = quantityInGrams / 100.0;

            if(ingredient.getCaloriesPer100() != null){
                totalCalories += ingredient.getCaloriesPer100() * factor;
            }

            if(ingredient.getProteinPer100() != null){
                totalProtein += ingredient.getProteinPer100() * factor;
            }

            if(ingredient.getAvailableCarbohydratePer100() != null){
                totalCarbohydrate += ingredient.getAvailableCarbohydratePer100() * factor;
            }

            if(ingredient.getLipidsPer100() != null){
                totalLipids += ingredient.getLipidsPer100() * factor;
            }
        }

        recipe.setTotalCalories((int) Math.round(totalCalories));
        recipe.setTotalProtein((int) Math.round(totalProtein));
        recipe.setTotalCarbohydrate((int) Math.round(totalCarbohydrate));
        recipe.setTotalLipids((int) Math.round(totalLipids));
    }

    public RecipeSummaryResponse[] listAllRecipes(){

        List<Recipe> recipes = recipeRepository.findAll();

        return recipes.stream()
                .map(recipe -> new RecipeSummaryResponse(
                        recipe.getId(),
                        recipe.getName(),
                        recipe.getImage(),
                        recipe.getPrepTimeMinutes(),
                        recipe.getServings(),
                        recipe.getTotalCalories()
                ))
                .toArray(RecipeSummaryResponse[]::new);

    }

    public RecipeDetailResponse getRecipeDetails(Long id){

        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        return new RecipeDetailResponse(
                recipe.getUser().getName(),
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getInstructions(),
                recipe.getImage(),
                recipe.getPrepTimeMinutes(),
                recipe.getServings(),
                recipe.getTotalCalories(),
                recipe.getTotalProtein(),
                recipe.getTotalCarbohydrate(),
                recipe.getTotalLipids(),
                recipe.getRecipeIngredients().stream()
                        .map(recipeIngredient -> new IngredientResponse(
                                recipeIngredient.getIngredient().getDisplayName(),
                                recipeIngredient.getDisplayQuantity(),
                                recipeIngredient.getDisplayUnit()
                        ))
                        .toList()
        );
    }

}
