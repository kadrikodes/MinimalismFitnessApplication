INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1960-04-11', 167, 67, 15, 'MALE', 'ABC');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1990-01-01', 170, 70, 25, 'MALE', 'TestUser');
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (10, '22:00', '07:00','22:30', '07:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (11, '22:00', '07:00','22:35', '07:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (12, '22:00', '07:00','23:30', '08:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (13, '22:30', '07:30','22:30', '07:00', 25);