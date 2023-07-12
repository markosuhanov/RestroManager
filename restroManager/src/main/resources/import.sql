#users
-- User data
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (123456789, 'Jelena', 'Jankovic', 'janeSmith', 'jane@example.com', 'password456', 'MANAGER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (234567890, 'Aleksandar', 'Aleksic', 'alexA92', 'alex@example.com', 'password789', 'COOK', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (345678901, 'Jovan', 'Jovanovic', 'jovan85', 'john@example.com', 'password123', 'ADMIN', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (456789012, 'Emilija', 'Emic', 'emmaE23', 'emma@example.com', 'password321', 'WAITER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (567890123, 'Mihajlo', 'Mihajlovic', 'michaelM', 'michael@example.com', 'password654', 'BARTENDER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (678901234, 'Sara', 'Savic', 'sara_s', 'sarah@example.com', 'password987', 'BARTENDER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (789012345, 'David', 'Davidovic', 'david_22', 'david@example.com', 'password123', 'MANAGER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (890123456, 'Emilija', 'Eric', 'emilyE45', 'emily@example.com', 'password456', 'COOK', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (901234567, 'Rajko', 'Rajic', 'ryanR98', 'ryan@example.com', 'password789', 'WAITER', true);
INSERT INTO user (id, first_name, last_name, username, email, password, role, active) VALUES (1234567890, 'Olivera', 'Obradovic', 'oliviaO', 'olivia@example.com', 'password321', 'BARTENDER', false);

#salaries
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
