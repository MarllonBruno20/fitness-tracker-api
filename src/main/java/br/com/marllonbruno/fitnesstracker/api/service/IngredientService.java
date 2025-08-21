package br.com.marllonbruno.fitnesstracker.api.service;

import br.com.marllonbruno.fitnesstracker.api.dto.request.IngredientCreationRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.IngredientCreationResponse;
import br.com.marllonbruno.fitnesstracker.api.entity.Ingredient;
import br.com.marllonbruno.fitnesstracker.api.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientCreationResponse saveIngredient(IngredientCreationRequest request) {

        Optional<Ingredient> existingIngredient = ingredientRepository.findByDisplayName(request.displayName());
        if (existingIngredient.isPresent()) {
            throw new IllegalArgumentException("Já existe um ingrediente com o nome " + request.displayName() + " cadastrado.");
        }

        Ingredient newIngredient = new Ingredient();
        newIngredient.setDisplayName(request.displayName());
        newIngredient.setSearchTags(request.searchTags());
        newIngredient.setIngredientMeasurementUnitOfMeasurement(request.ingredientMeasurementUnitOfMeasurement());
        newIngredient.setGroup(request.group());
        newIngredient.setCaloriesPer100(request.caloriesPer100());
        newIngredient.setTotalCarbohydratePer100(request.totalCarbohydratePer100());
        newIngredient.setAvailableCarbohydratePer100(request.availableCarbohydratePer100());
        newIngredient.setProteinPer100(request.proteinPer100());
        newIngredient.setLipidsPer100(request.lipidsPer100());
        newIngredient.setFiberPer100(request.fiberPer100());
        newIngredient.setAlcoholPer100(request.alcoholPer100());
        newIngredient.setAddedSaltPer100(request.addedSaltPer100());
        newIngredient.setAddedSugarPer100(request.addedSugarPer100());
        newIngredient.setCholesterolPer100(request.cholesterolPer100());
        newIngredient.setTotalSaturatedFattyAcidsPer100(request.totalSaturatedFattyAcidsPer100());
        newIngredient.setTransFattyAcidsPer100(request.transFattyAcidsPer100());
        newIngredient.setCalciumPer100(request.calciumPer100());
        newIngredient.setIronPer100(request.ironPer100());
        newIngredient.setSodiumPer100(request.sodiumPer100());
        newIngredient.setVitaminARePer100(request.vitaminARePer100());
        newIngredient.setVitaminARaePer100(request.vitaminARaePer100());
        newIngredient.setVitaminDPer100(request.vitaminDPer100());
        newIngredient.setVitaminEPer100(request.vitaminEPer100());
        newIngredient.setVitaminB6Per100(request.vitaminB6Per100());
        newIngredient.setVitaminB12Per100(request.vitaminB12Per100());
        newIngredient.setVitaminCPer100(request.vitaminCPer100());
        newIngredient.setAddedSaltPer100(request.addedSaltPer100());

        Ingredient savedIngredient = ingredientRepository.save(newIngredient);

        System.out.println("Ingrediente salvo com sucesso: " + savedIngredient);

        return new IngredientCreationResponse(
                savedIngredient.getId(),
                savedIngredient.getDisplayName(),
                savedIngredient.getSearchTags(),
                savedIngredient.getIngredientMeasurementUnitOfMeasurement(),
                savedIngredient.getGroup(),
                savedIngredient.getCaloriesPer100(),
                savedIngredient.getTotalCarbohydratePer100(),
                savedIngredient.getAvailableCarbohydratePer100(),
                savedIngredient.getProteinPer100(),
                savedIngredient.getLipidsPer100(),
                savedIngredient.getFiberPer100(),
                savedIngredient.getCholesterolPer100(),
                savedIngredient.getTotalSaturatedFattyAcidsPer100(),
                savedIngredient.getTransFattyAcidsPer100(),
                savedIngredient.getCalciumPer100(),
                savedIngredient.getIronPer100(),
                savedIngredient.getSodiumPer100(),
                savedIngredient.getVitaminARePer100(),
                savedIngredient.getVitaminARaePer100(),
                savedIngredient.getVitaminDPer100(),
                savedIngredient.getVitaminEPer100(),
                savedIngredient.getVitaminB6Per100(),
                savedIngredient.getVitaminB12Per100(),
                savedIngredient.getVitaminCPer100(),
                savedIngredient.getAlcoholPer100(),
                savedIngredient.getAddedSaltPer100(),
                savedIngredient.getAddedSugarPer100()
                );
    }
}
