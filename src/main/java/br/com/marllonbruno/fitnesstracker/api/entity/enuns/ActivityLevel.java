package br.com.marllonbruno.fitnesstracker.api.entity.enuns;

import lombok.Getter;

@Getter
public enum ActivityLevel {
    SEDENTARY(1.2),
    LIGHTLY_ACTIVE(1.375),
    MODERATELY_ACTIVE(1.55),
    ACTIVE(1.725),
    VERY_ACTIVE(1.9);

    private final double multiplier;

    ActivityLevel(double multiplier) {
        this.multiplier = multiplier;
    }

}
