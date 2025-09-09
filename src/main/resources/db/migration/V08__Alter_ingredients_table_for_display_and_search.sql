ALTER TABLE ingredients RENAME COLUMN name TO display_name;

ALTER TABLE ingredients ADD COLUMN search_tags TEXT;

UPDATE ingredients SET search_tags = lower(display_name);

ALTER TABLE ingredients ALTER COLUMN search_tags SET NOT NULL;