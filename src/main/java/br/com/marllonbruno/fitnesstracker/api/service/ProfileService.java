package br.com.marllonbruno.fitnesstracker.api.service;

import br.com.marllonbruno.fitnesstracker.api.dto.request.ProfileUpdateRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.ProfileDataResponse;
import br.com.marllonbruno.fitnesstracker.api.entity.User;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.ActivityLevel;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Gender;
import br.com.marllonbruno.fitnesstracker.api.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ProfileService {
    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Nenhum usuário autenticado encontrado.");
        }

        String userEmail = authentication.getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("Nenhum usuário encontrado com o email " + userEmail));

    }

    public User updateUser(ProfileUpdateRequest request) {

        // Já pego o usuário autenticado, responsável pela requisição
        User currentUser = getAuthenticatedUser();

        updateUserDataFromRequest(currentUser, request);

        calculateAllMetrics(currentUser);

        return userRepository.save(currentUser);

    }

    private void updateUserDataFromRequest(User user, ProfileUpdateRequest request) {
        user.setBirthDate(request.birthDate());
        user.setHeightCm(request.heightCm());
        user.setCurrentWeightKg(request.currentWeightKg());
        user.setGoalWeightKg(request.goalWeightKg());
        user.setGender(request.gender());
        user.setActivityLevel(request.activityLevel());
    }

    private void calculateAllMetrics(User user) {
        double imc = calculateIMC(user);
        user.setImc(imc);

        double bmr = calculateBMR(user);
        user.setTmb(bmr);

        int tdee = calculateTDEE(bmr, user.getActivityLevel());
        user.setDailyCaloriesGoal(tdee);

        calculateMacros(user, tdee);
    }

    private double calculateIMC(User user) {
        double heightInMeters = user.getHeightCm() / 100.0;
        return user.getCurrentWeightKg() / (heightInMeters * heightInMeters);
    }

    private double calculateBMR(User user) {
        int age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();

        if (user.getGender() == Gender.MALE) {
            return 10 * user.getCurrentWeightKg() + 6.25 * user.getHeightCm() - 5 * age + 5;
        } else {
            return 10 * user.getCurrentWeightKg() + 6.25 * user.getHeightCm() - 5 * age - 161;
        }
    }

    private int calculateTDEE(double bmr, ActivityLevel activityLevel) {
        return (int) (bmr * activityLevel.getMultiplier());
    }

    private void calculateMacros(User user, int tdee) {
        user.setDailyCarbsGoal((int) ((tdee * 0.40) / 4));
        user.setDailyProteinGoal((int) ((tdee * 0.30) / 4));
        user.setDailyFatGoal((int) ((tdee * 0.30) / 9));
    }

    public ProfileDataResponse getProfileData() {
        User user = getAuthenticatedUser();
        return new ProfileDataResponse(
                user.getName(),
                user.getBirthDate(),
                user.getHeightCm(),
                user.getCurrentWeightKg(),
                user.getGoalWeightKg(),
                user.getGender(),
                user.getActivityLevel(),
                user.getDailyCaloriesGoal(),
                user.getDailyProteinGoal(),
                user.getDailyCarbsGoal(),
                user.getDailyFatGoal(),
                user.getImc(),
                user.getTmb()
        );
    }

}
