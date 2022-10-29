INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO usuarios (email, nome, quatro_letras, senha) VALUES ('admin@gft.com', 'Admin', 'user', '$2a$10$98P8lNdVUsgaqsqo1S.0IeGJ7RqMpFlXMb8yGkSl.DVPfX935uX3i'),('josi@gft.com', 'João Silva', 'josi', '$2a$10$lggJvQhRZDyNwqkfmvjQUuoAVDnj6Z0fvZjMIcR4U5wOJr9yCaBi2');

INSERT INTO usuarios_roles  (usuario_id, roles_id) VALUES (1, 1), (2 ,2);

INSERT INTO eventos (nome, descricao, data_inicio, data_final) VALUES('MasterClass HumanSkills', 'Mussum Ipsum, cacilds vidis litro abertis. Per aumento de cachacis, eu reclamis.Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.Copo furadis é disculpa de bebadis, arcu quam euismod magna.Mé faiz elementum girarzis.', '2022-10-25', '2022-10-31' );

INSERT INTO atividades (nome, descricao, data_inicio, data_final, evento_id) VALUES ('Atividade 1 - AutoConhecimento', 'cacilds vidis litro abertis. Per aumento de cachacis, eu reclamis.Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.Copo furadis é disculpa de bebadis', '2022-10-25', '2022-10-26', 1), ('Atividade 2 - O que são Human Skills', 'cacilds vidis litro abertis. Per aumento de cachacis, eu reclamis.Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.Copo furadis é disculpa de bebadis', '2022-10-26', '2022-10-27', 1);

INSERT INTO grupos (nome, evento_id) VALUES ('Grupo Verde', 1), ('Grupo Azul', 1);

INSERT INTO participantes (nome, nivel, quatro_letras, email, grupo_id) VALUES ('João Silva', 'L4', 'josi', 'josi@gft.com', 1), ('Handryo Casali', 'L0', 'hoci', 'hoci@gft.com', 1), ('Cecília Pereira', 'L2', 'ceci', 'ceci@gft.com', 1), ('Esther Pires', 'L3', 'erps', 'erps@gft.com', 2), ('Murilo Souza', 'L1', 'mosa','mosa@gft.com', 2), ('Maria Julia Campos','L3', 'macs', 'macs@gft.com', 2);

INSERT INTO atividades_participantes (status_conclusao, participante_id, atividade_id) VALUES ('NAO_CONCLUIU', 1, 1), ('NAO_CONCLUIU', 1, 2), ('NAO_CONCLUIU', 2, 1), ('NAO_CONCLUIU', 2, 2), ('NAO_CONCLUIU', 3, 1), ('NAO_CONCLUIU', 3, 2), ('NAO_CONCLUIU', 4, 1), ('NAO_CONCLUIU', 4, 2), ('NAO_CONCLUIU', 5, 1), ('NAO_CONCLUIU', 5, 2), ('NAO_CONCLUIU', 6, 1), ('NAO_CONCLUIU', 6, 2);

INSERT INTO presencas_participantes (dia_evento, presenca, participante_id, evento_id) VALUES (1, 'NAO_CONCLUIU', 1, 1), (1, 'NAO_CONCLUIU', 2, 1), (1, 'NAO_CONCLUIU', 3, 1), (1, 'NAO_CONCLUIU', 4, 1),(2, 'NAO_CONCLUIU', 1, 1), (2, 'NAO_CONCLUIU', 2, 1), (2, 'NAO_CONCLUIU', 3, 1), (2, 'NAO_CONCLUIU', 4, 1),(3, 'NAO_CONCLUIU', 1, 1), (3, 'NAO_CONCLUIU', 2, 1), (3, 'NAO_CONCLUIU', 3, 1), (3, 'NAO_CONCLUIU', 4, 1),(4, 'NAO_CONCLUIU', 1, 1), (4, 'NAO_CONCLUIU', 2, 1), (4, 'NAO_CONCLUIU', 3, 1), (4, 'NAO_CONCLUIU', 4, 1),(5, 'NAO_CONCLUIU', 1, 1), (5, 'NAO_CONCLUIU', 2, 1), (5, 'NAO_CONCLUIU', 3, 1), (5, 'NAO_CONCLUIU', 4, 1),(6, 'NAO_CONCLUIU', 1, 1), (6, 'NAO_CONCLUIU', 2, 1), (6, 'NAO_CONCLUIU', 3, 1), (6, 'NAO_CONCLUIU', 4, 1),(7, 'NAO_CONCLUIU', 1, 1), (7, 'NAO_CONCLUIU', 2, 1), (7, 'NAO_CONCLUIU', 3, 1), (7, 'NAO_CONCLUIU', 4, 1),(1, 'NAO_CONCLUIU', 5, 1), (1, 'NAO_CONCLUIU', 6, 1),  (2, 'NAO_CONCLUIU', 5, 1), (2, 'NAO_CONCLUIU', 6, 1), (3, 'NAO_CONCLUIU', 5, 1), (3, 'NAO_CONCLUIU', 6, 1), (4, 'NAO_CONCLUIU', 5, 1), (4, 'NAO_CONCLUIU', 6, 1), (5, 'NAO_CONCLUIU', 5, 1), (5, 'NAO_CONCLUIU', 6, 1), (6, 'NAO_CONCLUIU', 5, 1), (6, 'NAO_CONCLUIU', 6, 1), (7, 'NAO_CONCLUIU', 5, 1), (7, 'NAO_CONCLUIU', 6, 1);





