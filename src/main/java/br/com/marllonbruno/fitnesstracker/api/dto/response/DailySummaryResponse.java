package br.com.marllonbruno.fitnesstracker.api.dto.response;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.MealType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record DailySummaryResponse(
        LocalDate date,

        Integer caloriesGoal,
        Integer caloriesConsumed,

        Double proteinGoal,
        Double proteinConsumed,

        Double fatGoal,
        Double fatConsumed,

        Double carbohydrateGoal,
        Double carbohydrateConsumed,

        Map<MealType, List<DiaryEntryResponse>> entriesByMealType
) {
}
