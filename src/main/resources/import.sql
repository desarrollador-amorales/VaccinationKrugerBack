INSERT INTO roles (id, description, name, status) VALUES('27396f02-e915-11ec-8fea-0242ac120002', 'ADMIN', 'ROLE_ADMIN', true);
INSERT INTO roles (id, description, name, status) VALUES('30f5a970-e915-11ec-8fea-0242ac120002', 'USER', 'ROLE_USER', true);

INSERT INTO vaccination_type (id, description, name, status) VALUES('e64825f4-e920-11ec-8fea-0242ac120002', 'SPUTNIK', 'SPUTNIK', true);
INSERT INTO vaccination_type (id, description, name, status) VALUES('f04aa978-e920-11ec-8fea-0242ac120002', 'ASTRAZENECA', 'ASTRAZENECA', true);
INSERT INTO vaccination_type (id, description, name, status) VALUES('f7bf1d24-e920-11ec-8fea-0242ac120002', 'PFIZER', 'PFIZER', true);
INSERT INTO vaccination_type (id, description, name, status) VALUES('fe9c98ce-e920-11ec-8fea-0242ac120002', 'JHONSON&JHONSON', 'JHONSON&JHONSON', true);

INSERT INTO employee (id, created_by, created_on, updated_by, updated_on, birth_date, email, identification, names, second_surname, status, surname, vaccination_status) VALUES('1e459900-e921-11ec-8fea-0242ac120002', '', '2000-01-01 00:00:00.000', NULL, '2000-01-01 00:00:00.000', '2000-01-01', 'armj@gmail.com', '0930007539', 'ADMIN', '', true, 'ADMIN', 'Y');
INSERT INTO auth_employee (id, created_by, created_on, updated_by, updated_on, id_employee, "password", status, username) VALUES('1e459900-e921-11ec-8fea-0242ac120002', '', '2022-01-01 00:00:00.000', '', '2022-01-01 00:00:00.000', '1e459900-e921-11ec-8fea-0242ac120002', '$2a$10$6/Tt/iJQHnGslc86uYdWo.KlqHWWDMoCG88iyzk41xOd.tPZNa456', true, '0930007539');

INSERT INTO auth_role (auth_id, rol_id) VALUES('1e459900-e921-11ec-8fea-0242ac120002', '27396f02-e915-11ec-8fea-0242ac120002');