INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1990-04-11', 167, 67, 15, 'MALE', 'User1');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1991-05-12', 170, 70, 16, 'MALE', 'User2');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1992-06-13', 172, 73, 17, 'FEMALE', 'User3');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1993-07-14', 175, 76, 18, 'FEMALE', 'User4');
INSERT INTO user_data(birthdate, height, weight, id, gender, name) VALUES('1994-08-15', 178, 79, 19, 'MALE', 'User5');
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (50, '22:00', '07:00','22:30', '07:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (51, '22:00', '07:00','22:35', '07:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (52, '22:00', '07:00','23:30', '08:30', 15);
INSERT INTO sleep_data(id, target_bedtime, target_wake_up_time, actual_bedtime, actual_wakeup_time, user_data_id) VALUES (53, '22:30', '07:30','22:30', '07:00', 16);