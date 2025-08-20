INSERT INTO ingredients (
    -- Identificação
    display_name, search_tags, unit_of_measurement, group_name,
    -- Macros
    calories_per_100, protein_per_100, lipids_per_100, total_carbohydrate_per_100,
    available_carbohydrate_per_100, fiber_per_100,
    -- Gorduras
    cholesterol_per_100, total_saturated_fatty_acids_per_100, trans_fatty_acids_per_100,
    -- Minerais
    calcium_per_100, iron_per_100, sodium_per_100,
    -- Vitaminas
    vitamin_a_re_per_100, vitamin_a_rae_per_100, vitamin_d_per_100, vitamin_e_per_100,
    vitamin_b6_per_100, vitamin_b12_per_100, vitamin_c_per_100,
    -- Outros
    alcohol_per_100, added_salt_per_100, added_sugar_per_100
) VALUES (
    -- Valores para 'Arroz, integral, cozido...'
    'Arroz, integral, cozido, c/ óleo, cebola e alho, c/ sal',
    'Arroz integral cozido com óleo cebola, alho e sal',
    'GRAMS',
    'CEREAL_AND_DERIVATIVES',
    116.00, 2.46, 2.03, 23.00, 20.90, 2.14, -- Macros
    0.00, 0.43, 0.01, -- Gorduras
    2.85, 0.12, 188.00, -- Minerais
    NULL, NULL, NULL, 0.34, 0.08, NULL, 0.45, -- Vitaminas
    NULL, 0.47, NULL -- Outros
);

INSERT INTO ingredients (
    -- Identificação
        display_name, search_tags, unit_of_measurement, group_name,
        -- Macros
        calories_per_100, protein_per_100, lipids_per_100, total_carbohydrate_per_100,
        available_carbohydrate_per_100, fiber_per_100,
        -- Gorduras
        cholesterol_per_100, total_saturated_fatty_acids_per_100, trans_fatty_acids_per_100,
        -- Minerais
        calcium_per_100, iron_per_100, sodium_per_100,
        -- Vitaminas
        vitamin_a_re_per_100, vitamin_a_rae_per_100, vitamin_d_per_100, vitamin_e_per_100,
        vitamin_b6_per_100, vitamin_b12_per_100, vitamin_c_per_100,
        -- Outros
        alcohol_per_100, added_salt_per_100, added_sugar_per_100
) VALUES (
    -- Valores para 'Feijão, carioca, cozido...'
    'Feijão, carioca, cozido (50% grão e 50% caldo), s/ óleo, s/ sal',
    'Feijão carioca cozido grao caldo sem óleo e sal',
    'GRAMS',
    'LEGUMES_AND_DERIVATIVES',
    71, 4.77, 0.54, 15.30, 8.20, 7.06, -- Macros
    0.00, 0.10, 0.00, -- Gorduras
    28.70, 1.39, 1.90, -- Minerais
    NULL, NULL, NULL, 0.78, NULL, NULL, NULL, -- Vitaminas
    NULL, NULL, NULL -- Outros
);

INSERT INTO ingredients (
    -- Identificação
        display_name, search_tags, unit_of_measurement, group_name,
        -- Macros
        calories_per_100, protein_per_100, lipids_per_100, total_carbohydrate_per_100,
        available_carbohydrate_per_100, fiber_per_100,
        -- Gorduras
        cholesterol_per_100, total_saturated_fatty_acids_per_100, trans_fatty_acids_per_100,
        -- Minerais
        calcium_per_100, iron_per_100, sodium_per_100,
        -- Vitaminas
        vitamin_a_re_per_100, vitamin_a_rae_per_100, vitamin_d_per_100, vitamin_e_per_100,
        vitamin_b6_per_100, vitamin_b12_per_100, vitamin_c_per_100,
        -- Outros
        alcohol_per_100, added_salt_per_100, added_sugar_per_100
) VALUES (
    -- Valores para 'Carne, frango, peito, s/ pele, grelhado, c/ óleo de soja, alho e pimenta do reino, c/ sal'
    'Carne, frango, peito, s/ pele, grelhado, c/ óleo de soja, alho e pimenta do reino, c/ sal',
    'Peito de frango grelhado, sem pele, grelhado, com oleo de soja, alho e pimenta do reino e sal',
    'GRAMS',
    'MEAT_AND_DERIVATIVES',
    178, 30.40, 6.11, 0.43, 0.34, 0.10, -- Macros
    84.30, 1.45, 0.06, -- Gorduras
    6.41, 0.35, 227, -- Minerais
    NULL, NULL, NULL, 0.58, 0.01, 0.18, 0.21, -- Vitaminas
    NULL, NULL, 0.45 -- Outros
);

INSERT INTO ingredients (
    -- Identificação
        display_name, search_tags, unit_of_measurement, group_name,
        -- Macros
        calories_per_100, protein_per_100, lipids_per_100, total_carbohydrate_per_100,
        available_carbohydrate_per_100, fiber_per_100,
        -- Gorduras
        cholesterol_per_100, total_saturated_fatty_acids_per_100, trans_fatty_acids_per_100,
        -- Minerais
        calcium_per_100, iron_per_100, sodium_per_100,
        -- Vitaminas
        vitamin_a_re_per_100, vitamin_a_rae_per_100, vitamin_d_per_100, vitamin_e_per_100,
        vitamin_b6_per_100, vitamin_b12_per_100, vitamin_c_per_100,
        -- Outros
        alcohol_per_100, added_salt_per_100, added_sugar_per_100
) VALUES (
    -- Valores para 'Ovo, galinha, inteiro, cozido, s/ sal'
    'Ovo, galinha, inteiro, cozido, s/ sal',
    'Ovo de galinha cozido sem sal',
    'GRAMS',
    'EGGS_AND_DERIVATIVES',
    125, 10.40, 8.70, 1.38, 1.38, 0.00, -- Macros
    364, 2.66, 0.02, -- Gorduras
    43.2, 1.33, 128, -- Minerais
    113, 113, 1.80, 0.85, NULL, 0.93, NULL, -- Vitaminas
    NULL, NULL, NULL -- Outros
);

INSERT INTO ingredients (
    -- Identificação
        display_name, search_tags, unit_of_measurement, group_name,
        -- Macros
        calories_per_100, protein_per_100, lipids_per_100, total_carbohydrate_per_100,
        available_carbohydrate_per_100, fiber_per_100,
        -- Gorduras
        cholesterol_per_100, total_saturated_fatty_acids_per_100, trans_fatty_acids_per_100,
        -- Minerais
        calcium_per_100, iron_per_100, sodium_per_100,
        -- Vitaminas
        vitamin_a_re_per_100, vitamin_a_rae_per_100, vitamin_d_per_100, vitamin_e_per_100,
        vitamin_b6_per_100, vitamin_b12_per_100, vitamin_c_per_100,
        -- Outros
        alcohol_per_100, added_salt_per_100, added_sugar_per_100
) VALUES (
    -- Valores para 'Ovo, galinha, inteiro, cozido, c/ sal'
    'Ovo, galinha, inteiro, cozido, c/ sal',
    'Ovo de galinha cozido com sal',
    'GRAMS',
    'EGGS_AND_DERIVATIVES',
    125, 10.30, 8.66, 1.38, 1.38, 0.00, -- Macros
    362, 2.65, 0.02, -- Gorduras
    43.1, 1.33, 315, -- Minerais
    NULL, NULL, 1.80, 0.84, NULL, 0.93, NULL, -- Vitaminas
    NULL, 0.47, NULL -- Outros
);

