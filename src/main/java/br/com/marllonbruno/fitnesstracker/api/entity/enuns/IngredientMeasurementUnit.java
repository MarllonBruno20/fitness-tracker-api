package br.com.marllonbruno.fitnesstracker.api.entity.enuns;

import lombok.Getter;

@Getter
public enum IngredientMeasurementUnit {
    GRAMS("g"),
    MILLILITERS("ml"),
    UNIT("un"); // Para coisas como "1 ovo", "1 fatia de pão"

    private final String abbreviation;

    IngredientMeasurementUnit(String abbreviation) { this.abbreviation = abbreviation; }

}
