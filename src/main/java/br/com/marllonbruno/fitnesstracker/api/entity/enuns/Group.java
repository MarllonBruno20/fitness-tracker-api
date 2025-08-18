package br.com.marllonbruno.fitnesstracker.api.entity.enuns;

public enum Group {
    BEVERAGES("Bebidas"),
    CEREAL_AND_DERIVATIVES("Cereais e derivados"),
    EGGS_AND_DERIVATIVES("Ovos e derivados"),
    FAST_FOOD("Fast food"),
    FAT_AND_OILS("Gorduras e oleos"),
    FISH_AND_SEAFOOD("Pescados e frutos do mar"),
    FOOD_FOR_SPECIAL_PURPOSES("Alimentos para fins especiais"),
    FRUITS_AND_DERIVATIVES("Frutas e derivados"),
    LEGUMES_AND_DERIVATIVES("Legumes e derivados"),
    MEAT_AND_DERIVATIVES("Carnes e derivados"),
    MILK_AND_DERIVATIVES("Leite e derivados"),
    MISCELLANEOUS("Miscelâneas"),
    NUTS_AND_SEEDS("Nozes e sementes"),
    PROCESSED_FOODS_UNPREPARED("Alimentos industrializados"),
    SUGAR_AND_SWEETS("Açucares e doces"),
    VEGETABLES_AND_DERIVATIVES("Vegetais e derivados");

    private final String description;

    Group(String description) {
        this.description = description;
    }
}
