INSERT INTO user_type (id, type) VALUES (1, 'Customer');
INSERT INTO user_type (id, type) VALUES (2, 'Driver');
INSERT INTO lang (id, lang_name) VALUES (1, 'Arabic');
INSERT INTO lang (id, lang_name) VALUES (2, 'English');
INSERT INTO notification_template (id, body, header, vars_number) VALUES (1, 'welcome to SWVL', 'Hi', 0);
INSERT INTO notification_template (id, body, header, vars_number) VALUES (2, 'Use promo code {1} to get 50% discount', 'Promo code', 1);
INSERT INTO user (user_name,name, email,phone_number,lang_id, user_type_id) VALUES ('user_name1','name1','name1@g.com','011', 1, 1);
INSERT INTO user (user_name,name, email,phone_number,lang_id, user_type_id) VALUES ('user_name2','name2','name2@g.com','012', 1, 2);
INSERT INTO user (user_name,name, email,phone_number,lang_id, user_type_id) VALUES ('user_name3','name3','name3@g.com','013', 2, 1);
INSERT INTO notification_type (id,type) VALUES ( 1, 'PUSH_NOTIFICATION');
INSERT INTO notification_type (id,type) VALUES ( 2, 'SMS_NOTIFICATION');
INSERT INTO user_group (id, group_name) VALUES (1, 'group1');
INSERT INTO user_group (id, group_name) VALUES (2, 'group2');
INSERT INTO user_group_user (group_id, user_id) VALUES (1, 1);
INSERT INTO user_group_user (group_id, user_id) VALUES (1, 2);
INSERT INTO user_group_user (group_id, user_id) VALUES (2, 1);
INSERT INTO user_group_user (group_id, user_id) VALUES (2, 3);
