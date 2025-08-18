CREATE TABLE ingredients (

    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    unit_of_measurement VARCHAR(50) NOT NULL,
    group_name VARCHAR(50) NOT NULL,

    calories_per_100 NUMERIC(10,2) NOT NULL,
    total_carbohydrate_per_100 NUMERIC(10,2) NOT NULL,
    available_carbohydrate_per_100 NUMERIC(10,2) NOT NULL,
    protein_per_100 NUMERIC(10,2) NOT NULL,
    lipids_per_100 NUMERIC(10,2) NOT NULL,

    fiber_per_100 NUMERIC(10,2) NOT NULL,
    alcohol_per_100 NUMERIC(10,2),
    cholesterol_per_100 NUMERIC(10,2) NOT NULL,
    total_saturated_fatty_acids_per_100 NUMERIC(10,2) NOT NULL,
    trans_fatty_acids_per_100 NUMERIC(10,2) NOT NULL,
    calcium_per_100 NUMERIC(10,2) NOT NULL,
    iron_per_100 NUMERIC(10,2),
    sodium_per_100 NUMERIC(10,2),
    vitamin_a_re_per_100 NUMERIC(10,2),
    vitamin_a_rae_per_100 NUMERIC(10,2),
    vitamin_d_per_100 NUMERIC(10,2),
    vitamin_e_per_100 NUMERIC(10,2),
    vitamin_b6_per_100 NUMERIC(10,2),
    vitamin_b12_per_100 NUMERIC(10,2),
    vitamin_c_per_100 NUMERIC(10,2),
    added_salt_per_100 NUMERIC(10,2),
    added_sugar_per_100 NUMERIC(10,2)

);