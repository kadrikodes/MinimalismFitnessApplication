INSERT INTO user_data (id, name, height, weight, birthdate, gender) VALUES (1, 'Rais', 180, 85, '2000-01-01', 'MALE');
INSERT INTO user_data (id, name, height, weight, birthdate, gender) VALUES (2, 'Divin', 160, 68, '1999-01-01', 'MALE');

INSERT INTO nutrition_data (id, food_name, calories, protein, carbohydrates, fats, meal_type, user_data_id) VALUES (1000, 'Pizza', 500, 40, 30, 30, 'Lunch', 1);
INSERT INTO nutrition_data (id, food_name, calories, protein, carbohydrates, fats, meal_type, user_data_id) VALUES (2000, 'Burger', 600, 60, 20, 20, 'Dinner', 2);
