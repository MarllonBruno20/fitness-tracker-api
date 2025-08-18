CREATE TABLE recipe_ingredients (

    id BIGINT PRIMARY KEY,

    recipe_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,

    quantity_in_grams NUMERIC(10, 2) NOT NULL,

    display_quantity NUMERIC(10, 2) NOT NULL,
    display_unit VARCHAR(50) NOT NULL,

    CONSTRAINT fk_recipe_ingredients_recipe FOREIGN KEY (recipe_id) REFERENCES recipes(id),
    CONSTRAINT fk_recipe_ingredients_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredients(id)

);