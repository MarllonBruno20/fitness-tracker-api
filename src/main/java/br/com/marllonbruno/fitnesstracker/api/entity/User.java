package br.com.marllonbruno.fitnesstracker.api.entity;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.ActivityLevel;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Gender;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Objective;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "height_cm")
    private Integer heightCm;

    @Column(name = "current_weight_kg", columnDefinition = "NUMERIC(5,2)")
    private Double currentWeightKg;

    @Column(name = "goal_weight_kg", columnDefinition = "NUMERIC(5,2)")
    private Double goalWeightKg;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "activity_level")
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Column(name = "daily_calories_goal")
    private Integer dailyCaloriesGoal;

    @Column(name = "daily_protein_goal")
    private Integer dailyProteinGoal;

    @Column(name = "daily_carbs_goal")
    private Integer dailyCarbsGoal;

    @Column(name = "daily_fat_goal")
    private Integer dailyFatGoal;

    @Column(name = "imc", columnDefinition = "NUMERIC(4,2)")
    private Double imc;

    @Column(name = "tmb", columnDefinition = "NUMERIC(10,2)")
    private Double tmb;

    @Enumerated(EnumType.STRING)
    private Objective objective;

    private boolean active = true;

    public boolean isProfileComplete() {
        return birthDate != null &&
                heightCm != null &&
                currentWeightKg != null &&
                gender != null &&
                activityLevel != null &&
                objective != null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
