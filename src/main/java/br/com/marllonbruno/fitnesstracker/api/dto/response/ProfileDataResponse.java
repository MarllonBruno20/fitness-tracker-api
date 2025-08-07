package br.com.marllonbruno.fitnesstracker.api.dto.response;

import br.com.marllonbruno.fitnesstracker.api.entity.User;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.ActivityLevel;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Gender;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Objective;

import java.time.LocalDate;

public record ProfileDataResponse(
        String name,
        LocalDate birthDate,
        Integer heightCm,
        Double currentWeightKg,
        Double goalWeightKg,
        Gender gender,
        ActivityLevel activityLevel,
        Objective objective,
        Integer dailyCaloriesGoal,
        Integer dailyProteinGoal,
        Integer dailyCarbsGoal,
        Integer dailyFatGoal,
        Double imc,
        Double tmb
) {

    public ProfileDataResponse(User user) {
        this(
                user.getName(),
                user.getBirthDate(),
                user.getHeightCm(),
                user.getCurrentWeightKg(),
                user.getGoalWeightKg(),
                user.getGender(),
                user.getActivityLevel(),
                user.getObjective(),
                user.getDailyCaloriesGoal(),
                user.getDailyProteinGoal(),
                user.getDailyCarbsGoal(),
                user.getDailyFatGoal(),
                user.getImc(),
                user.getTmb()
        );
    }

}
