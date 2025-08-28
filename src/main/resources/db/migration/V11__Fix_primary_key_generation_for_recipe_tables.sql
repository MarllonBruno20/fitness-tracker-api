-- V12__Fix_primary_key_generation_for_recipe_tables.sql
-- Corrige a geração de chave primária para as tabelas 'recipes' e 'recipe_ingredients'
-- para que funcionem com a estratégia GenerationType.IDENTITY do JPA.

-- --- Correção para a tabela 'recipes' ---

-- 1. Cria uma sequência de números para a tabela de receitas.
CREATE SEQUENCE IF NOT EXISTS recipes_id_seq;

-- 2. Define o valor padrão da coluna 'id' para usar a sequência recém-criada.
--    Agora, novos INSERTs sem 'id' receberão um valor automaticamente.
ALTER TABLE recipes
ALTER COLUMN id SET DEFAULT nextval('recipes_id_seq');

-- 3. (Opcional, mas seguro) Associa a sequência à coluna para que ela seja
--    removida caso a coluna ou a tabela sejam removidas no futuro.
ALTER SEQUENCE recipes_id_seq OWNED BY recipes.id;

-- 4. (CRUCIAL) Atualiza o valor inicial da sequência para ser o maior ID existente + 1.
--    Isso evita erros de "chave duplicada" se já existirem dados na tabela.
--    A função COALESCE garante que, se a tabela estiver vazia, o valor será 1.
SELECT setval('recipes_id_seq', COALESCE((SELECT MAX(id) FROM recipes), 1), COALESCE((SELECT MAX(id) FROM recipes), 1) IS NOT NULL);


-- --- Correção para a tabela 'recipe_ingredients' ---

-- Repete o mesmo processo para a tabela de junção.
CREATE SEQUENCE IF NOT EXISTS recipe_ingredients_id_seq;

ALTER TABLE recipe_ingredients
ALTER COLUMN id SET DEFAULT nextval('recipe_ingredients_id_seq');

ALTER SEQUENCE recipe_ingredients_id_seq OWNED BY recipe_ingredients.id;

SELECT setval('recipe_ingredients_id_seq', COALESCE((SELECT MAX(id) FROM recipe_ingredients), 1), COALESCE((SELECT MAX(id) FROM recipe_ingredients), 1) IS NOT NULL);