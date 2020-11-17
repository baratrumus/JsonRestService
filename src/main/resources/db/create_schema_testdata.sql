CREATE TABLE IF NOT EXISTS json_objects
(
  id SERIAL PRIMARY KEY,
  json_id character varying(255) NOT NULL,
  json_node jsonb
);

DELETE FROM json_objects;

/*
INSERT INTO json_objects(json_id, json_node) VALUES
('13', '{"id": "13", "name": "Иванов"}'),
('13', '{"id": "13", "name": "Петров"}'),
('55', '{"id": "55", "name": "Сидоров"}');
*/
