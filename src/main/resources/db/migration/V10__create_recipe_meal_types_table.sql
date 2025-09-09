CREATE TABLE recipe_meal_types (
    recipe_id BIGINT NOT NULL,
    meal_type VARCHAR(50) NOT NULL,

    PRIMARY KEY (recipe_id, meal_type),

    CONSTRAINT fk_recipe_meal_types_recipe FOREIGN KEY (recipe_id) REFERENCES recipes(id)
);