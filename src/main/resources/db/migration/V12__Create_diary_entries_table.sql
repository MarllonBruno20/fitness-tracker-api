CREATE TABLE diary_entries (

    id BIGSERIAL PRIMARY KEY,
    consumption_timestamp TIMESTAMP NOT NULL,
    meal_type VARCHAR(50) NOT NULL,
    servings_consumed DECIMAL(10,2) NOT NULL,
    user_id BIGINT NOT NULL,
    recipe_id BIGINT NOT NULL,

    CONSTRAINT fk_diary_entries_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_diary_entries_recipes FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE

);

CREATE INDEX idx_diary_entries_user_id_consumption_timestamp ON diary_entries(user_id, consumption_timestamp);