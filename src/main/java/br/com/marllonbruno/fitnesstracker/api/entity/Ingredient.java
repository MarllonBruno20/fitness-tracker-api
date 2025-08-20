package br.com.marllonbruno.fitnesstracker.api.entity;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Group;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.IngredientMeasurementUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name", nullable = false, unique = true)
    private String displayName;

    @Column(name = "search_tags", nullable = false)
    private String searchTags;

    @Column(name = "unit_of_measurement", nullable = false)
    @Enumerated(EnumType.STRING)
    private IngredientMeasurementUnit ingredientMeasurementUnitOfMeasurement;

    @Enumerated(EnumType.STRING)
    @Column(name = "group_name", nullable = false)
    private Group group;

    @Column(name = "calories_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double caloriesPer100;

    @Column(name = "total_carbohydrate_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double totalCarbohydratePer100;

    @Column(name = "available_carbohydrate_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double availableCarbohydratePer100;

    @Column(name = "protein_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double proteinPer100;

    @Column(name = "lipids_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double lipidsPer100;

    @Column(name = "fiber_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double fiberPer100;

    @Column(name = "alcohol_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double alcoholPer100;

    @Column(name = "cholesterol_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double cholesterolPer100;

    @Column(name = "total_saturated_fatty_acids_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double totalSaturatedFattyAcidsPer100;

    @Column(name = "trans_fatty_acids_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double transFattyAcidsPer100;

    @Column(name = "calcium_per_100", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double calciumPer100;

    @Column(name = "iron_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double ironPer100;

    @Column(name = "sodium_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double sodiumPer100;

    @Column(name = "vitamin_a_re_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double vitaminARePer100;

    @Column(name = "vitamin_a_rae_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double vitaminARaePer100;

    @Column(name = "vitamin_d_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double vitaminDPer100;

    @Column(name = "vitamin_e_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double vitaminEPer100;

    @Column(name = "vitamin_b6_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double vitaminB6Per100;

    @Column(name = "vitamin_b12_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double vitaminB12Per100;

    @Column(name = "vitamin_c_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double vitaminCPer100;

    @Column(name = "added_salt_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double addedSaltPer100;

    @Column(name = "added_sugar_per_100", columnDefinition = "NUMERIC(10, 2)")
    private Double addedSugarPer100;

}
