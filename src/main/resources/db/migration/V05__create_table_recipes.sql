CREATE TABLE recipes (

    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    instructions TEXT NOT NULL,
    image VARCHAR(255) NOT NULL,
    prep_time_minutes INTEGER NOT NULL,
    servings INTEGER,

    total_calories INTEGER,
    total_protein INTEGER,
    total_carbohydrate INTEGER,
    total_lipids INTEGER,

    user_id BIGINT NOT NULL,

    CONSTRAINT fk_recipes_user FOREIGN KEY (user_id) REFERENCES users(id)
);