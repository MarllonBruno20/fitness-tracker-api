package br.com.marllonbruno.fitnesstracker.api.dto.request;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.ActivityLevel;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Gender;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Objective;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ProfileUpdateRequest(
        @NotNull(message = "Por favor, informe a sua data de nascimento")
        @Past(message = "Por favor, a data de nascimento deve ser no passado")
        LocalDate birthDate,

        @NotNull(message = "Por favor, informe a sua altura")
        @Positive(message = "Por favor, a altura deve ser maior que zero")
        Integer heightCm,

        @NotNull(message = "Por favor, informe o seu peso atual")
        @Positive(message = "Por favor, o peso atual deve ser maior que zero")
        Double currentWeightKg,

        @Positive(message = "Por favor, o peso alvo deve ser maior que zero")
        Double goalWeightKg,

        @NotNull(message = "Por favor, informe o seu sexo")
        Gender gender,

        @NotNull(message = "Por favor, informe o seu nivel de atividade fisica")
        ActivityLevel activityLevel,

        @NotNull(message = "Por favor, informe o seu objetivo de dieta")
        Objective objective
) {
}
