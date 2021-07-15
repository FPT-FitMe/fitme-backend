INSERT INTO public.post (content_body,content_header,created_date,image_url,is_active,last_modified_date,post_name,reading_time,coach_profile_coach_id,creator_user_id)
VALUES  ('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Nước vô cùng quan trọng với cơ thể','2021-07-14 19:38:45.407','https://images.unsplash.com/photo-1600679472233-eabc13b79f07?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,'2021-07-14 19:38:45.407','Tại sao bạn cần nước?',7,0,2),
        ('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Fastfood gây ảnh hưởng xấu tới cơ thể','2021-07-14 19:39:30.402','https://images.unsplash.com/photo-1529973565457-a60a2ccf750d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,'2021-07-14 19:39:30.402','Hạn chế ăn fastfood',10,1,2),
        ('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Rau xanh cung cấp nhiều vitamin và dưỡng chất','2021-07-14 19:40:26.936','https://images.unsplash.com/photo-1565895405139-e188df996e0b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=733&q=80',true,'2021-07-14 19:40:26.936','Rau xanh tốt cho cơ thể bạn',8,1,2);

insert into app_user_role
values (0, 'Trainee'),
       (1, 'Manager');
insert into coach_profile (coach_id,coach_name,contact,introduction,image_url,is_active)
values (0,'Lalisa','lalisa@gmail.com','Hi im Lalisa','https://i.pinimg.com/originals/0f/56/51/0f56511d7e416da63782dd0cc73816f1.png',true),
       (1,'Rose','rose@gmail.com','Hi im Rose','https://i.imgur.com/zCiPWRn.jpeg',true),
<<<<<<< HEAD
<<<<<<< HEAD
       (2,'Ariana Grande','arina@gmail.com','Hi im Arina Grande','"https://i.imgur.com/u4EVYZC.jpg',true);
-- select setval('coach_profile_coach_id_seq', 1);
-- alter sequence coach_profile_coach_id_seq restart with 1;
=======
       (2,'Ariana Grande','arina@gmail.com','Hi im Arina Grande','https://i.imgur.com/u4EVYZC.jpg',true);

-- select setval('coach_profile_coach_id_seq', 1);
-- alter sequence coach_profile_coach_id_seq restart with 1;
INSERT INTO public.post (content_body,content_header,created_date,image_url,is_active,last_modified_date,post_name,reading_time,coach_profile_coach_id,creator_user_id) VALUES
	 ('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Nước vô cùng quan trọng với cơ thể','2021-07-14 19:38:45.407','https://images.unsplash.com/photo-1600679472233-eabc13b79f07?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,'2021-07-14 19:38:45.407','Tại sao bạn cần nước?',7,0,2),
	 ('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Fastfood gây ảnh hưởng xấu tới cơ thể','2021-07-14 19:39:30.402','https://images.unsplash.com/photo-1529973565457-a60a2ccf750d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,'2021-07-14 19:39:30.402','Hạn chế ăn fastfood',10,1,2),
	 ('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Rau xanh cung cấp nhiều vitamin và dưỡng chất','2021-07-14 19:40:26.936','https://images.unsplash.com/photo-1565895405139-e188df996e0b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=733&q=80',true,'2021-07-14 19:40:26.936','Rau xanh tốt cho cơ thể bạn',8,1,2);
>>>>>>> 453c2ad (Add fake data)
=======
       (2,'Ariana Grande','arina@gmail.com','Hi im Arina Grande','"https://i.imgur.com/u4EVYZC.jpg',true);
-- select setval('coach_profile_coach_id_seq', 1);
-- alter sequence coach_profile_coach_id_seq restart with 1;
>>>>>>> accee57 (Fix fake data for meal,post,pratice)
insert into app_user (user_id, age, diet_preference_type, email, exercise_frequency_type, first_name,
                      gender, height, is_premium, last_name, password, phone, profile_image_url, workout_intensity,
                      role_role_id, is_active, created_date, last_modified_date)
values (0, 18, 2, 'conmeo1@gmail.com', 2, 'Ngô', 1, 182, true, 'Đức',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu',
        '00000001', 'memberImageLink1', 0.7, 0, true, '2021-07-05 11:23:26.988', '2021-07-05 11:23:26.988'),
       (1, 18, 2, 'conmeo2@gmail.com', 2, 'Kim', 1, 182, false, 'Hằng',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu',
        '00000003', 'memberImageLink2', 1, 0, true, '2021-07-05 11:23:26.988', '2021-07-05 11:23:26.988'),
       (2, 40, null, 'admin@fitme.vn', null, 'Cẩm', 1, null, null, 'Long',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000002', 'managerImage1', null, 1, true, '2021-07-05 11:23:26.988', '2021-07-05 11:23:26.988');
select setval('app_user_user_id_seq', 2);
-- alter sequence app_user_user_id_seq restart with 3;
INSERT INTO exercise (exercise_id, base_duration, base_kcal, base_rep_per_round, created_date, description, image_url,
                      is_active, last_modified_date, exercise_name, video_url, creator_user_id)
VALUES (1, 60, 20, 10, '2021-07-05 11:23:26.988', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1598136490941-30d885318abd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=749&q=80',
        true, '2021-07-05 11:23:26.988', 'Nâng chân', 'https://media.giphy.com/media/UQa44aVsUd6t7lIigt/giphy.gif', 2),
       (2, 70, 20, 12, '2021-07-05 11:23:40.265', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1551984427-6d77a1918093?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=653&q=80',
        true, '2021-07-05 11:23:40.265', 'Đạp xe', 'https://media.giphy.com/media/57Uzij2DnzdBvGi3qv/giphy.gif', 2),
       (3, 120, 25, 15, '2021-07-05 11:23:51.878', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:23:51.878', 'Gập bụng', 'https://media.giphy.com/media/uGP4yfwMnf8Tm/giphy.gif', 2),
       (4, 120, 30, 15, '2021-07-05 11:24:00.132', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1598632640487-6ea4a4e8b963?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80',
        true, '2021-07-05 11:24:00.132', 'Plank', 'https://media.giphy.com/media/xT8qBff8cRRFf7k2u4/giphy.gif', 2),
       (5, 120, 40, 16, '2021-07-05 11:24:12.006', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:12.006', 'Kéo tay trước', 'https://media.giphy.com/media/WsFX0H7qz5M1o8VpxH/giphy.gif', 2),
       (6, 60, 20, 10, '2021-07-05 11:24:29.067', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1616803689943-5601631c7fec?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:29.067', 'Hít đất', 'https://media.giphy.com/media/7e3P2VsI7Js2c/giphy.gif', 2),
       (7, 90, 20, 12, '2021-07-05 11:24:45.307', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1599058917727-824293170100?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=749&q=80',
        true, '2021-07-05 11:24:45.307', 'Chạy chân chạm hông', 'https://media.giphy.com/media/JRlqKEzTDKci5JPcaL/giphy.gif', 2),
       (8, 90, 20, 15, '2021-07-05 11:24:57.527', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:57.527', 'Hít đất nghiêng', 'https://media.giphy.com/media/xTiTngQQf1U8IdowNi/giphy.gif', 2),
       (9, 100, 25, 16, '2021-07-05 11:25:09.906', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1516208962313-9d183d94f577?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=667&q=80',
        true, '2021-07-05 11:25:09.906', 'Kéo xà đơn (tay trước)', 'https://media.giphy.com/media/iGMpf3IMQxpuw1mHjI/giphy.gif', 2),
       (10, 75, 20, 12, '2021-07-05 11:25:24.77', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1605296867424-35fc25c9212a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:25:24.77', 'Kéo xà đơn (lưng)', 'https://media.giphy.com/media/xUPGcD1LxZUkKUMOB2/giphy.gif', 2);
select setval('exercise_exercise_id_seq', 10);
-- alter sequence exercise_exercise_id_seq restart with 11;
INSERT INTO public.meal (meal_id, calories, carb_amount, cooking_time, created_date, description, fat_amount, image_url,
                         is_active, is_premium, last_modified_date, "name", coach_profile_coach_id, creator_user_id)
VALUES (1, 600, 2.0, 7, '2021-07-05 11:29:08.178',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 13.0, 'https://images.unsplash.com/photo-1568096889942-6eedde686635?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, false,
        '2021-07-05 11:29:08.178', 'Phở bò', 0, 2),
       (2, 400, 1.0, 6, '2021-07-05 11:29:33.885',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 15.0, 'https://images.unsplash.com/photo-1461530927168-44328109da52?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, false,
        '2021-07-05 11:29:33.885', 'Salad thập cẩm', 0, 2),
       (3, 800, 2.0, 6.5, '2021-07-05 11:29:55.037',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 13.0, 'https://images.unsplash.com/photo-1563379926898-05f4575a45d8?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80', true, false,
        '2021-07-05 11:29:55.037', 'Pasta hải sản', 0, 2),
       (4, 300, 0.5, 5, '2021-07-05 11:30:18.418',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 1.0, 'https://images.unsplash.com/photo-1550304943-4f24f54ddde9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, false,
        '2021-07-05 11:30:18.418', 'Salad bắp cải', 0, 2),
       (5, 350, 1.5, 10, '2021-07-05 11:30:45.337',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 10.0, 'https://images.unsplash.com/photo-1533622597524-a1215e26c0a2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, false,
        '2021-07-05 11:30:45.337', 'Salad thập cẩm', 0, 2),
       (6, 250.0, 0.5, 25, '2021-07-05 11:31:08.605',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 0.5, 'https://images.unsplash.com/photo-1512003867696-6d5ce6835040?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, false,
        '2021-07-05 11:31:08.605', 'Lẩu rau củ', 0, 2),
       (7, 450.0, 3.5, 15, '2021-07-05 11:31:25.836',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 7.5, 'https://images.unsplash.com/photo-1503764654157-72d979d9af2f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80', true, false,
        '2021-07-05 11:31:25.836', 'Hủ tiếu giảm cân', 0, 2),
<<<<<<< HEAD
<<<<<<< HEAD
       (8, 900.0, 2.0, 7, '2021-07-05 11:32:02.899',
=======
       (8, 900.0, 2.0, 7000, '2021-07-05 11:32:02.899',
>>>>>>> 453c2ad (Add fake data)
=======
       (8, 900.0, 2.0, 7, '2021-07-05 11:32:02.899',
>>>>>>> accee57 (Fix fake data for meal,post,pratice)
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 9.0, 'https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, false,
        '2021-07-05 11:32:02.899', 'Carry gà', 0, 2),
       (9, 550.0, 1.0, 7, '2021-07-05 11:32:17.613',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 7.0, 'https://images.unsplash.com/photo-1473093295043-cdd812d0e601?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, false,
        '2021-07-05 11:32:17.613', 'Pasta rau củ', 0, 2),
       (10, 350.0, 2.0, 7, '2021-07-05 11:32:33.08',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 2.0, 'https://images.unsplash.com/photo-1495214783159-3503fd1b572d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, false,
        '2021-07-05 11:32:33.08', 'Pancake trái cây', 0, 2);
<<<<<<< HEAD
<<<<<<< HEAD
select setval('meal_meal_id_seq', 10);
=======

>>>>>>> 453c2ad (Add fake data)
=======
>>>>>>> accee57 (Fix fake data for meal,post,pratice)
select setval('meal_meal_id_seq', 10);
-- alter sequence meal_meal_id_seq restart with 11;
INSERT INTO public.tag (tag_id, tag_name, tag_type,is_active)
VALUES (0, 'test', NULL,true),
       (1, 'Chay', 'meal',true),
       (2, 'Không chứa thịt', 'meal',true),
       (3, 'Không tinh bột', 'meal',true),
       (4, 'Bữa sáng', 'meal',true),
       (5, 'Bữa trưa', 'meal',true),
       (6, 'Bữa xế', 'meal',true),
       (7, 'Bữa tối', 'meal',true),
       (8, 'Cardio', 'exercise',true),
       (9, 'Toàn thân', 'exercise',true);
INSERT INTO public.tag (tag_id, tag_name, tag_type,is_active)
VALUES (10, 'Khởi động', 'exercise',true),
       (11, 'Thư giãn', 'exercise',true),
       (12, 'Đốt calo nhanh', 'exercise',true),
       (13, 'Nâng sức mạnh', 'exercise',true),
       (14, 'Cho người bắt đầu', 'exercise',true);
select setval('tag_tag_id_seq', 14);
-- alter sequence tag_tag_id_seq restart with 15;
-- theo buoi
INSERT INTO meal_tag (meal_id, tag_id)
VALUES (1, 4),
       (2, 7),
       (3, 5),
       (4, 4),
       (5, 5),
       (6, 7),
       (7, 4),
       (7, 5),
       (8, 7),
       (9, 4),
       (10, 7);
-- theo diet type: cai nay set cho nguoi an chay het
INSERT INTO meal_tag (meal_id, tag_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (8, 1),
       (9, 1),
       (10, 1);
INSERT INTO public.workout (workout_id, created_date, description, estimated_calories, estimated_duration, image_url,
                            is_active, is_premium, last_modified_date, "level", workout_name, coach_profile_coach_id,
                            creator_user_id)
VALUES (1, '2021-07-05 11:21:21.938', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 715, 120,
<<<<<<< HEAD
        'https://images.unsplash.com/photo-1599058917212-d750089bc07e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80', true, false, '2021-07-05 11:21:21.938', 2, 'Full body workout', 0, 2),
       (2, '2021-07-05 11:21:26.319', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 750, 150,
        'https://images.unsplash.com/photo-1533681717801-1bbd2ec8d269?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, false, '2021-07-05 11:21:26.319', 2, 'Morning workout', 0, 2),
       (3, '2021-07-05 11:21:29.588', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 565, 120,
        'https://images.unsplash.com/photo-1532384748853-8f54a8f476e2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, false, '2021-07-05 11:21:29.588', 2, 'Arm workout', 0, 2),
       (4, '2021-07-05 11:21:33.64', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 540, 45,
        'https://images.unsplash.com/photo-1434682772747-f16d3ea162c3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80', true, false, '2021-07-05 11:21:33.64', 2, 'Leg workout', 0, 2),
       (5, '2021-07-05 11:21:37.645', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 575, 75,
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, true, '2021-07-05 11:21:37.645', 2, 'Abs workout', 0, 2),
       (6, '2021-07-05 11:21:40.838', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 350, 60,
        'https://images.unsplash.com/photo-1434682881908-b43d0467b798?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80', true, false, '2021-07-05 11:21:40.838', 2, 'Cardio workout', 0, 2),
       (7, '2021-07-05 11:21:43.855', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 440, 60,
        'https://images.unsplash.com/photo-1434754205268-ad3b5f549b11?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80', true, true, '2021-07-05 11:21:43.855', 2, 'Back workout', 0, 2),
       (8, '2021-07-05 11:21:47.43', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 1565, 90,
        'https://images.unsplash.com/photo-1598136490929-292a0a7890c2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80', true, false, '2021-07-05 11:21:47.43', 2, 'Strenght workout', 0, 2),
       (9, '2021-07-05 11:21:50.443', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 750, 80,
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, false, '2021-07-05 11:21:50.443', 2, 'Endurance workout', 0, 2),
       (10, '2021-07-05 11:22:04.329', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 425, 120,
        'https://images.unsplash.com/photo-1480264104733-84fb0b925be3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, true, '2021-07-05 11:22:04.329', 1, 'Bodyweight workout', 0, 2);
INSERT INTO public.post (content_body,content_header,created_date,image_url,is_active,last_modified_date,post_name,reading_time,coach_profile_coach_id,creator_user_id) VALUES
('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Nước vô cùng quan trọng với cơ thể','2021-07-14 19:38:45.407','https://images.unsplash.com/photo-1600679472233-eabc13b79f07?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,'2021-07-14 19:38:45.407','Tại sao bạn cần nước?',7,0,2),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Fastfood gây ảnh hưởng xấu tới cơ thể','2021-07-14 19:39:30.402','https://images.unsplash.com/photo-1529973565457-a60a2ccf750d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,'2021-07-14 19:39:30.402','Hạn chế ăn fastfood',10,1,2),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Rau xanh cung cấp nhiều vitamin và dưỡng chất','2021-07-14 19:40:26.936','https://images.unsplash.com/photo-1565895405139-e188df996e0b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=733&q=80',true,'2021-07-14 19:40:26.936','Rau xanh tốt cho cơ thể bạn',8,1,2);
=======
        'https://images.unsplash.com/photo-1599058917212-d750089bc07e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80', true, true, '2021-07-05 11:21:21.938', 2, 'Full body workout', 0, 2),
       (2, '2021-07-05 11:21:26.319', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 750, 150,
        'https://images.unsplash.com/photo-1533681717801-1bbd2ec8d269?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, true, '2021-07-05 11:21:26.319', 2, 'Morning workout', 0, 2),
       (3, '2021-07-05 11:21:29.588', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 565, 120,
        'https://images.unsplash.com/photo-1532384748853-8f54a8f476e2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, true, '2021-07-05 11:21:29.588', 2, 'Arm workout', 0, 2),
       (4, '2021-07-05 11:21:33.64', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 540, 45,
        'https://images.unsplash.com/photo-1434682772747-f16d3ea162c3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80', true, true, '2021-07-05 11:21:33.64', 2, 'Leg workout', 0, 2),
       (5, '2021-07-05 11:21:37.645', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 575, 75,
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, true, '2021-07-05 11:21:37.645', 2, 'Abs workout', 0, 2),
       (6, '2021-07-05 11:21:40.838', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 350, 60,
        'https://images.unsplash.com/photo-1434682881908-b43d0467b798?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80', true, true, '2021-07-05 11:21:40.838', 2, 'Cardio workout', 0, 2),
       (7, '2021-07-05 11:21:43.855', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 440, 60,
        'https://images.unsplash.com/photo-1434754205268-ad3b5f549b11?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80', true, true, '2021-07-05 11:21:43.855', 2, 'Back workout', 0, 2),
       (8, '2021-07-05 11:21:47.43', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 1565, 90,
        'https://images.unsplash.com/photo-1598136490929-292a0a7890c2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80', true, true, '2021-07-05 11:21:47.43', 2, 'Strenght workout', 0, 2),
       (9, '2021-07-05 11:21:50.443', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 750, 80,
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80', true, true, '2021-07-05 11:21:50.443', 2, 'Endurance workout', 0, 2),
       (10, '2021-07-05 11:22:04.329', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 425, 120,
        'https://images.unsplash.com/photo-1480264104733-84fb0b925be3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80', true, true, '2021-07-05 11:22:04.329', 1, 'Bodyweight workout', 0, 2);

>>>>>>> 453c2ad (Add fake data)
select setval('workout_workout_id_seq', 10);
-- alter sequence workout_workout_id_seq restart with 11;
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (1, 1, 1, 1),
       (2, 2, 2, 1),
       (3, 3, 3, 1),
       (4, 4, 4, 1),
       (5, 5, 5, 1),
       (6, 1, 1, 2),
       (7, 2, 2, 2),
       (8, 3, 4, 2),
       (9, 4, 8, 2),
       (10, 5, 9, 2);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (11, 1, 2, 3),
       (12, 2, 6, 3),
       (13, 3, 5, 3),
       (14, 4, 3, 3),
       (15, 5, 1, 3),
       (16, 1, 9, 4),
       (17, 2, 3, 4),
       (18, 3, 2, 4),
       (19, 4, 5, 4),
       (20, 5, 7, 4);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (21, 1, 8, 5),
       (22, 2, 9, 5),
       (23, 3, 1, 5),
       (24, 4, 1, 5),
       (25, 5, 2, 5),
       (26, 1, 2, 6),
       (27, 2, 2, 6),
       (28, 3, 1, 6),
       (29, 4, 1, 6),
       (30, 5, 2, 6);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (31, 1, 3, 7),
       (32, 2, 2, 7),
       (33, 3, 5, 7),
       (34, 4, 1, 7),
       (35, 5, 7, 7),
       (36, 1, 4, 8),
       (37, 2, 5, 8),
       (38, 3, 3, 8),
       (39, 4, 2, 8),
       (40, 5, 9, 8);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (41, 6, 9, 8),
       (42, 7, 9, 8),
       (43, 8, 9, 8),
       (44, 9, 2, 8),
       (45, 10, 1, 8),
       (46, 1, 9, 9),
       (47, 2, 9, 9),
       (48, 3, 9, 9),
       (49, 4, 2, 9),
       (50, 5, 1, 9);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (51, 1, 7, 10),
       (52, 2, 1, 10),
       (53, 3, 6, 10),
       (54, 4, 2, 10),
       (55, 5, 1, 10);
INSERT INTO weight_log(weight_log_id, created_at, value, trainee_user_id)
VALUES (1, '2021-07-06 11:23:26.988', 71.3, 1),
       (2, '2021-07-09 11:23:26.988', 71.2, 1),
       (3, '2021-07-12 11:23:26.988', 71.3, 1),
       (4, '2021-07-15 11:23:26.988', 71.2, 1),
       (5, '2021-07-22 11:23:26.988', 71.1, 1),
       (6, '2021-07-23 11:23:26.988', 71.0, 1),
       (7, '2021-07-27 11:23:26.988', 70.9, 1),
       (8, '2021-07-30 11:23:26.988', 70.85, 1),
       (9, '2021-08-01 11:23:26.988', 70.5, 1),
       (10, '2021-08-10 11:23:26.988', 70.2, 1),
       (11, '2021-08-14 11:23:26.988', 70.25, 1);
select setval('weight_log_weight_log_id_seq', 11);
select setval('workout_workout_id_seq', 10);
select setval('workout_exercise_workout_exercise_id_seq', 55);
<<<<<<< HEAD
-- alter sequence workout_workout_id_seq restart with 11;
=======
-- alter sequence workout_workout_id_seq restart with 11;

>>>>>>> accee57 (Fix fake data for meal,post,pratice)
