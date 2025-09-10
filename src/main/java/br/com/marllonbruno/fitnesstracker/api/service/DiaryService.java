package br.com.marllonbruno.fitnesstracker.api.service;

import br.com.marllonbruno.fitnesstracker.api.dto.request.DiaryEntryRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.DailySummaryResponse;
import br.com.marllonbruno.fitnesstracker.api.dto.response.DiaryEntryResponse;
import br.com.marllonbruno.fitnesstracker.api.entity.DiaryEntry;
import br.com.marllonbruno.fitnesstracker.api.entity.Recipe;
import br.com.marllonbruno.fitnesstracker.api.entity.User;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.MealType;
import br.com.marllonbruno.fitnesstracker.api.repository.DiaryEntryRepository;
import br.com.marllonbruno.fitnesstracker.api.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    private final DiaryEntryRepository diaryEntryRepository;
    private final RecipeRepository recipeRepository;
    private final AutheticatedUserService autheticatedUserService;

    public DiaryService(DiaryEntryRepository diaryEntryRepository,
                        AutheticatedUserService autheticatedUserService,
                        RecipeRepository recipeRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.autheticatedUserService = autheticatedUserService;
        this.recipeRepository = recipeRepository;
    }

    public DiaryEntryResponse addEntry(DiaryEntryRequest request) {

        // ASSOCIAR O USUARIO AO DIARIO
        User currentUser = autheticatedUserService.getAuthenticatedUser();

        // BUSCAR A REFEICAO PELO ID
        Recipe recipe = recipeRepository.findById(request.recipeId())
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + request.recipeId()));
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setUser(currentUser);
        diaryEntry.setRecipe(recipe);
        diaryEntry.setConsumptionTimestamp(request.consumptionTimestamp());
        diaryEntry.setMealType(request.mealType());
        diaryEntry.setServingsConsumed(request.servingsConsumed());

        diaryEntryRepository.save(diaryEntry);

        NutrientDetails consumedNutrients = calculateNutrientsForPortion(recipe, request.servingsConsumed());

        return new DiaryEntryResponse(
                diaryEntry.getId(),
                diaryEntry.getRecipe().getName(),
                diaryEntry.getRecipe().getImage(),
                diaryEntry.getServingsConsumed(),
                consumedNutrients.calories,
                consumedNutrients.protein,
                consumedNutrients.carbohydrate,
                consumedNutrients.lipids
        );

    }

    private NutrientDetails calculateNutrientsForPortion(Recipe recipe, Double servingsConsumed) {

        if (recipe.getServings() == null || recipe.getServings() == 0) {
            return new NutrientDetails(0, 0.0, 0.0, 0.0);
        }

        // Calcula o valor nutricional por UMA porção
        double caloriesPerServing = (double) recipe.getTotalCalories() / recipe.getServings();
        double proteinPerServing = (double) recipe.getTotalProtein() / recipe.getServings();
        double carbohydratePerServing = (double) recipe.getTotalCarbohydrate() / recipe.getServings();
        double lipidsPerServing = (double) recipe.getTotalLipids() / recipe.getServings();

        // Multiplica pela quantidade de porções que o usuário consumiu
        int totalCalories = (int) Math.round(caloriesPerServing * servingsConsumed);
        double totalProtein = proteinPerServing * servingsConsumed;
        double totalCarbohydrate = carbohydratePerServing * servingsConsumed;
        double totalLipids = lipidsPerServing * servingsConsumed;

        return new NutrientDetails(totalCalories, totalProtein, totalCarbohydrate, totalLipids);

    }

    private record NutrientDetails (Integer calories, Double protein, Double carbohydrate, Double lipids){ }

    @Transactional(readOnly = true)
    public DailySummaryResponse getDailySummaryByUser(LocalDate date) {

        User currentUser = autheticatedUserService.getAuthenticatedUser();

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<DiaryEntry> diaryEntries = diaryEntryRepository.findByUserAndConsumptionTimestampBetween(currentUser, startOfDay, endOfDay);

        double totalCaloriesConsumed = 0.0;
        double totalProteinConsumed = 0.0;
        double totalCarbohydrateConsumed = 0.0;
        double totalLipidsConsumed = 0.0;

        List<DiaryEntryResponse> entryResponses = new ArrayList<>();

        for (DiaryEntry diaryEntry : diaryEntries) {
            Recipe recipe = diaryEntry.getRecipe();
            double servings = diaryEntry.getServingsConsumed();

            double calories = ((double) recipe.getTotalCalories() / recipe.getServings()) * servings;
            double protein = ((double) recipe.getTotalProtein() / recipe.getServings()) * servings;
            double carbohydrate = ((double) recipe.getTotalCarbohydrate() / recipe.getServings()) * servings;
            double lipids = ((double) recipe.getTotalLipids() / recipe.getServings()) * servings;

            totalCaloriesConsumed += calories;
            totalProteinConsumed += protein;
            totalCarbohydrateConsumed += carbohydrate;
            totalLipidsConsumed += lipids;

            entryResponses.add(new DiaryEntryResponse(
                            diaryEntry.getId(),
                            recipe.getName(),
                            recipe.getImage(),
                            servings,
                            (int) Math.round(calories),
                            protein,
                            carbohydrate,
                            lipids
            ));
        }

        Map<MealType, List<DiaryEntryResponse>> entriesByMealType = entryResponses.stream()
                .collect(Collectors.groupingBy(entry -> findMealTypeForResponse(diaryEntries, entry.id())));

        return new DailySummaryResponse(
                date,

                currentUser.getDailyCaloriesGoal(),
                (int) Math.round(totalCaloriesConsumed),

                currentUser.getDailyProteinGoal().doubleValue(),
                totalProteinConsumed,

                currentUser.getDailyFatGoal().doubleValue(),
                totalLipidsConsumed,

                currentUser.getDailyCarbsGoal().doubleValue(),
                totalCarbohydrateConsumed,

                entriesByMealType
        );
    }

    private MealType findMealTypeForResponse(List<DiaryEntry> entries, Long responseId) {

        return entries.stream()
                .filter(entry -> entry.getId().equals(responseId))
                .findFirst()
                .map(DiaryEntry::getMealType)
                .orElse(null);

    }

}


