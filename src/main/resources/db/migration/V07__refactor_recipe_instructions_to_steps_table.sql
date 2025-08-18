ALTER TABLE recipes DROP COLUMN instructions;

CREATE TABLE recipe_steps (

    recipe_id BIGINT NOT NULL,
    step_description TEXT NOT NULL,
    step_order INT NOT NULL,

    PRIMARY KEY (recipe_id, step_order),

    CONSTRAINT fk_recipe_steps_recipe FOREIGN KEY (recipe_id) REFERENCES recipes(id)

)