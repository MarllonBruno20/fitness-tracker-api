package br.com.marllonbruno.fitnesstracker.api.entity.enuns;

import lombok.Getter;

@Getter
public enum MealType {
    BREAKFAST("Café da Manhã"),
    LUNCH("Almoço"),
    DINNER("Jantar"),
    SNACK("Lanche");

    private final String description;

    MealType(String description) {
        this.description = description;
    }

}
