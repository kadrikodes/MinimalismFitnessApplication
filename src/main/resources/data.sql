INSERT INTO USER_DATA (birthdate, gender, height, name, weight, id) VALUES ('1980-06-19','FEMALE',170.0,'Esra',60.0,1);
INSERT INTO USER_DATA (birthdate, gender, height, name, weight, id) VALUES ('1980-06-19','MALE',170.0,'Divin',60.0,2);
INSERT INTO USER_DATA (birthdate, gender, height, name, weight, id) VALUES ('1980-06-19','MALE',170.0,'Sam',60.0,3);
INSERT INTO USER_DATA (birthdate, gender, height, name, weight, id) VALUES ('1980-06-19','MALE',170.0,'Kadri',60.0,4);
INSERT INTO USER_DATA (birthdate, gender, height, name, weight, id) VALUES ('1980-06-19','MALE',170.0,'Rais',60.0,5);

INSERT INTO NUTRITION_DATA (id, food_name, calories, protein, carbohydrates, fats, meal_type, user_data_id) VALUES (1000, 'Pizza', 500, 40, 30, 30, 'Lunch', 1);
INSERT INTO NUTRITION_DATA (id, food_name, calories, protein, carbohydrates, fats, meal_type, user_data_id) VALUES (2000, 'Burger', 600, 60, 20, 20, 'Dinner', 2);

INSERT INTO PUSH_UP_DATA (number_of_push_ups, target, time_duration, calories_burnt, id, user_data_id) VALUES (5, 10, 1.5, 50, 4, 1);

INSERT INTO SLEEP_DATA (id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (10, '22:00', '07:00','22:30', '07:30', 1);
INSERT INTO SLEEP_DATA (id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (11, '22:00', '07:00','22:35', '07:30', 2);
INSERT INTO SLEEP_DATA (id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (12, '22:00', '07:00','23:30', '08:30', 3);

INSERT INTO WALKING_DATA (id, steps, distance, calories_burned, duration, speed, date_time, user_data_id) VALUES (1000, 100, 10, 100, 60, 5, '2023-11-29 11:33', 1);
INSERT INTO WALKING_DATA (id, steps, distance, calories_burned, duration, speed, date_time, user_data_id) VALUES (2000, 150, 20, 200, 120, 6, '2023-11-29 11:33', 2);