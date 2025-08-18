package br.com.marllonbruno.fitnesstracker.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "step_description", nullable = false)
    @OrderColumn(name = "step_order")
    private List<String> instructions = new ArrayList<>();

    @Column(nullable = false)
    private String image;

    @Column(name = "prep_time_minutes", nullable = false)
    private Integer prepTimeMinutes;

    private Integer servings;

    @Column(name = "total_calories")
    private Integer totalCalories;

    @Column(name = "total_protein")
    private Integer totalProtein;

    @Column(name = "total_carbohydrate")
    private Integer totalCarbohydrate;

    @Column(name = "total_lipids")
    private Integer totalLipids;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

}
