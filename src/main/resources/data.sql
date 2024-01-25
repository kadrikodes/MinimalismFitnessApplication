INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1990-04-11', 167, 67, 15, 'MALE', 'User1');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1991-05-12', 170, 70, 16, 'MALE', 'User2');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1992-06-13', 172, 73, 17, 'FEMALE', 'User3');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1993-07-14', 175, 76, 18, 'FEMALE', 'User4');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1994-08-15', 178, 79, 19, 'MALE', 'User5');
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (50, '22:00', '07:00','22:30', '07:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (51, '22:00', '07:00','22:35', '07:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (52, '22:00', '07:00','23:30', '08:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (53, '22:30', '07:30','22:30', '07:00', 16);
INSERT INTO nutrition_data (id, food_name, calories, protein, carbohydrates, fats, meal_type, user_data_id) VALUES (1000, 'Pizza', 500, 40, 30, 30, 'Lunch', 15);
INSERT INTO nutrition_data (id, food_name, calories, protein, carbohydrates, fats, meal_type, user_data_id) VALUES (2000, 'Burger', 600, 60, 20, 20, 'Dinner', 16);
INSERT INTO push_up_data (number_of_push_ups, target, time_duration, calories_burnt, id, user_data_id) VALUES (5, 10, 1.5, 50, 4, 15);
INSERT INTO walking_data (id, steps, distance, calories_burned, duration, speed, date_time, user_data_id) VALUES (1000, 100, 10, 100, 60, 5, '2023-11-29 11:33', 15);
INSERT INTO walking_data (id, steps, distance, calories_burned, duration, speed, date_time, user_data_id) VALUES (2000, 150, 20, 200, 120, 6, '2023-11-29 11:33', 16);