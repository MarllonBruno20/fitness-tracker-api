package br.com.marllonbruno.fitnesstracker.api.entity;

import br.com.marllonbruno.fitnesstracker.api.entity.enuns.RecipeIngredientMeasurementUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    // --- CAMPO PARA CÁLCULO (A FONTE DA VERDADE NUTRICIONAL) ---
    @Column(name = "quantity_in_grams", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double quantityInGrams;

    // --- CAMPOS PARA EXIBIÇÃO (O QUE O USUÁRIO INSERIU) ---
    @Column(name = "display_quantity", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double displayQuantity; // Armazenar o número que o usuário digitou, correspondente à displayUnit selecionada.

    @Column(name = "display_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    // Armazenar a unidade de medida que o usuário escolheu na interface do aplicativo. É um campo focado na experiência do usuário (UX).
    private RecipeIngredientMeasurementUnit displayUnit;

}
