package br.com.marllonbruno.fitnesstracker.api.entity.enuns;

import lombok.Getter;

@Getter
public enum RecipeIngredientMeasurementUnit {

    GRAMS("g"),
    KILOGRAMS("kg"),

    MILILITERS("ml"),
    LITERS("l"),
    TEASPOON("colher de chá"),
    TABLESPOON("colher de sopa"),
    CUP("xícara"),

    // Unidades Genéricas
    UNIT("unidade"),
    PINCH("pitada");

    private final String description;

    RecipeIngredientMeasurementUnit(String description) { this.description = description; }

}
