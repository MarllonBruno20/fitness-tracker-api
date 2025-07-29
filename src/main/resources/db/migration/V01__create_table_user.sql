-- V01__create-table_user.sql

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    birth_date DATE,
    height_cm INTEGER,
    current_weight_kg NUMERIC(5, 2),
    goal_weight_kg NUMERIC(5, 2),
    gender VARCHAR(50),
    activity_level VARCHAR(50),
    daily_calories_goal INTEGER,
    daily_protein_goal INTEGER,
    daily_carbs_goal INTEGER,
    daily_fat_goal INTEGER,
    imc NUMERIC(4, 2),
    active BOOLEAN NOT NULL DEFAULT TRUE

);