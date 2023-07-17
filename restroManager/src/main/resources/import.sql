-- ~~~~~~~~~~~~~~~~~~~USERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
-- User data
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (123456789, 'Jelena', 'Jankovic', 'janeSmith', 'jane@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'MANAGER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (234567890, 'Aleksandar', 'Aleksic', 'alexA92', 'alex@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'COOK', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (345678901, 'Jovan', 'Jovanovic', 'jovan85', 'john@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'ADMIN', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (456789012, 'Emilija', 'Emic', 'emmaE23', 'emma@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'WAITER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (567890123, 'Mihajlo', 'Mihajlovic', 'michaelM', 'michael@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'BARTENDER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (678901234, 'Sara', 'Savic', 'sara_s', 'sarah@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'BARTENDER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (789012345, 'David', 'Davidovic', 'david_22', 'david@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'MANAGER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (890123456, 'Emilija', 'Eric', 'emilyE45', 'emily@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'COOK', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (901234567, 'Rajko', 'Rajic', 'ryanR98', 'ryan@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'WAITER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (1234567890, 'Olivera', 'Obradovic', 'oliviaO', 'olivia@example.com', '$2a$10$iOzChqe0nYpBSxKeCFEeT.4JWz2ZGO4GA.2.lWvtGVQZn27.pRdfS', 'BARTENDER', false);

-- ~~~~~~~~~~~~~~~~~~~SALARIES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
-- Salary data for user with ID 123456789 (Jelena Jankovic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (1234567891, 2000, '2023-01-01', '2023-01-31', 123456789, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (2345678901, 2100, '2023-02-01', null, 123456789, true);

-- Salary data for user with ID 234567890 (Aleksandar Aleksic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (3456789011, 1800, '2023-01-01', '2023-01-31', 234567890, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (4567890121, 1900, '2023-02-01', null, 234567890, true);

-- Salary data for user with ID 345678901 (Jovan Jovanovic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (5678901231, 2500, '2023-01-01', '2023-01-31', 345678901, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (6789012341, 2600, '2023-02-01', null, 345678901, true);

-- Salary data for user with ID 456789012 (Emilija Emic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (7890123451, 1700, '2023-01-01', '2023-01-31', 456789012, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (8901234561, 1800, '2023-02-01', null, 456789012, true);

-- Salary data for user with ID 567890123 (Mihajlo Mihajlovic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (9012345671, 2200, '2023-01-01', '2023-01-31', 567890123, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (12345678901, 2300, '2023-02-01', null, 567890123, true);

-- Salary data for user with ID 678901234 (Sara Savic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (23456765011, 1600, '2023-01-01', '2023-01-31', 678901234, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (34567890121, 1700, '2023-02-01', null, 678901234, true);

-- Salary data for user with ID 789012345 (David Davidovic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (45678901231, 1900, '2023-01-01', '2023-01-31', 789012345, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (56789012341, 2000, '2023-02-01', null, 789012345, true);

-- Salary data for user with ID 890123456 (Emilija Eric)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (67890123451, 1500, '2023-01-01', '2023-01-31', 890123456, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (78901234561, 1600, '2023-02-01', null, 890123456, true);

-- Salary data for user with ID 901234567 (Rajko Rajic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (89012345671, 2000, '2023-01-01', '2023-01-31', 901234567, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (90123456781, 2100, '2023-02-01', null, 901234567, true);

-- Salary data for user with ID 1234567890 (Olivera Obradovic)
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (12345678904, 1400, '2023-01-01', '2023-01-31', 1234567890, false);
INSERT INTO salary (id, amount, start_date, end_date, user_id, active) VALUES (23456789011, 1500, '2023-02-01', null, 1234567890, true);

# -- ~~~~~~~~~~~~~~~~~~~ITEM CATEGORIES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#
INSERT INTO item_category (id, name, active) VALUES (3456789012345678, 'Burger', true);
INSERT INTO item_category (id, name, active) VALUES (1234567890123456, 'Koktel', true);
INSERT INTO item_category (id, name, active) VALUES (4567890123456789, 'Palacinka', true);
INSERT INTO item_category (id, name, active) VALUES (5678901234567890, 'Pivo', true);
INSERT INTO item_category (id, name, active) VALUES (6789012345678901, 'Sok', true);
INSERT INTO item_category (id, name, active) VALUES (7890123456789012, 'Kafa', true);
INSERT INTO item_category (id, name, active) VALUES (2345678901234567, 'Sendvič', true);

# -- ~~~~~~~~~~~~~~~~~~~ITEMS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#
# -- Food Items for Sendvic category
INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (123456789012346, 'Kulen sendvič', 'Sendvič sa kulenom', 900.0, 243.0, true, null, 2345678901234567, 'Gluten, soja', 9, 'food');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (234567890123457, 'Pileći sendvič', 'Sendvič sa piletinom', 800.0, 216.0, true, null, 2345678901234567, 'Gluten, soja', 10, 'food');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (345678901234568, 'Vegetarijanski sendvič', 'Sendvič sa povrćem', 700.0, 189.0, true, null, 2345678901234567, 'Gluten, soja', 9, 'food');

-- Food Items for Burger category
#
INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (567890123456780, 'Classic burger', 'Klasični burger', 750.0, 202.5, true, null, 3456789012345678, 'Gluten, jaja, soja', 22, 'food');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (678901234567891, 'Veggie burger', 'Burger sa povrćem', 650.0, 175.5, true, null, 3456789012345678, 'Gluten, soja', 18, 'food');

-- Drink Items for Pivo category
INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (789012345678902, 'Lager', 'Svetlo pivo', 350.0, 94.5, true, null, 5678901234567890, 'Ječam', 2, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (890123456789013, 'IPA', 'India Pale Ale', 450.0, 121.5, true, null, 5678901234567890, 'Ječam', 2, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (901234567890124, 'Crno pivo', 'Tamno pivo', 400.0, 108.0, true, null, 5678901234567890, 'Ječam', 2, 'drink');

-- Food Items for Koktel category
INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (123456789012347, 'Mojito', 'Osvežavajući koktel sa rumom, limetom i nanom', 550.0, 148.5, true, null, 1234567890123456, 'Nema', 9, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (234567890123458, 'Cosmopolitan', 'Klasični koktel sa vodkom, sokom od brusnice i limetom', 600.0, 162.0, true, null, 1234567890123456, 'Nema', 8, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (345678901234569, 'Margarita', 'Koktel sa tekilom, limetom i triple sec likerom', 600.0, 162.0, true, null, 1234567890123456, 'Nema', 7, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (456789012345680, 'Pina Colada', 'Tropski koktel sa rumom, kremom od kokosa i sokom od ananasa', 650.0, 175.5, true, null, 1234567890123456, 'Nema', 7, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (567890123456781, 'Daiquiri', 'Koktel sa rumom, limetom i šećerom', 600.0, 162.0, false, null, 1234567890123456, 'Nema', 6, 'drink');

# -- Drink Items for Sok category
INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (678901234567892, 'Narandža', 'Sveže ceđeni sok od narandže', 250.0, 67.5, true, null, 6789012345678901, 'Nema', 5, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (789012345678903, 'Limunada', 'Osvežavajuća limunada sa dodatkom mente', 200.0, 54.0, true, null, 6789012345678901, 'Nema', 4, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (890123456789014, 'Jabuka', 'Sveže ceđeni sok od jabuke', 220.0, 59.4, true, null, 6789012345678901, 'Nema', 8, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (901234567890125, 'Borovnica', 'Sveže ceđeni sok od borovnice', 280.0, 75.6, true, null, 6789012345678901, 'Nema', 3, 'drink');

INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (123456789012356, 'Ananas', 'Sveže ceđeni sok od ananasa', 240.0, 64.8, true, null, 6789012345678901, 'Nema', 3, 'drink');

# -- Drink Items for Kafa category
#
#
INSERT INTO item (id, name, description, price, cost, active, img_path, item_category_id, allergens, prep_time, item_type) VALUES (456789012345679, 'Latte', 'Kafa sa mlekom i mlečnom penu', 250.0, 67.5, true, null, 7890123456789012, 'Mleko', 4, 'drink');
#

