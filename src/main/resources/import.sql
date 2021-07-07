insert into app_user_role
values (0, 'Trainee'),
       (1, 'Manager');


insert into coach_profile (coach_id,coach_name,contact,introduction,image_url,is_active)
values (0,'Bob','coach_profile@gmail.com','Hi im Bob','coach_image.jpg',true);

select setval('coach_profile_coach_id_seq', 0);
-- alter sequence coach_profile_coach_id_seq restart with 1;

insert into app_user (user_id, age, diet_preference_type, email, exercise_frequency_type, first_name,
                      gender, height, is_premium, last_name, password, phone, profile_image_url, workout_intensity,
                      role_role_id, is_active, created_date, last_modified_date)
values (0, 18, 2, 'conmeo1@gmail.com', 2, 'firstNameMember1', 1, 182, true, 'lastNameMember1',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu',
        '00000001', 'memberImageLink1', 0.7, 0, true, '2021-07-05 11:23:26.988', '2021-07-05 11:23:26.988'),
       (1, 18, 2, 'conmeo2@gmail.com', 2, 'firstNameMember1', 1, 182, false, 'lastNameMember1',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu',
        '00000003', 'memberImageLink2', 1, 0, true, '2021-07-05 11:23:26.988', '2021-07-05 11:23:26.988'),
       (2, 40, null, 'admin@fitme.vn', null, 'firstNameAdmin1', 1, null, null, 'lastNameAdmin1',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000002', 'managerImage1', null, 1, true, '2021-07-05 11:23:26.988', '2021-07-05 11:23:26.988');

select setval('app_user_user_id_seq', 2);
-- alter sequence app_user_user_id_seq restart with 3;

INSERT INTO exercise (exercise_id, base_duration, base_kcal, base_rep_per_round, created_date, description, image_url,
                      is_active, last_modified_date, exercise_name, video_url, creator_user_id)
VALUES (1, 60, 20, 10, '2021-07-05 11:23:26.988', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:23:26.988', 'Nâng chân', 'HIIT_extended.jpg', 2),
       (2, 70, 20, 12, '2021-07-05 11:23:40.265', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:23:40.265', 'Đạp xe', 'HIIT_extended.jpg', 2),
       (3, 120, 25, 15, '2021-07-05 11:23:51.878', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:23:51.878', 'Gập bụng', 'HIIT_extended.jpg', 2),
       (4, 120, 30, 15, '2021-07-05 11:24:00.132', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:00.132', 'Plank', 'HIIT_extended.jpg', 2),
       (5, 120, 40, 16, '2021-07-05 11:24:12.006', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:12.006', 'Kéo tay trước', 'HIIT_extended.jpg', 2),
       (6, 60, 20, 10, '2021-07-05 11:24:29.067', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:29.067', 'Hít đất', 'HIIT_extended.jpg', 2),
       (7, 90, 20, 12, '2021-07-05 11:24:45.307', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:45.307', 'Chạy chân chạm hông', 'HIIT_extended.jpg', 2),
       (8, 90, 20, 15, '2021-07-05 11:24:57.527', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:57.527', 'Hít đất nghiêng', 'HIIT_extended.jpg', 2),
       (9, 100, 25, 16, '2021-07-05 11:25:09.906', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:25:09.906', 'Kéo xà đơn (tay trước)', 'HIIT_extended.jpg', 2),
       (10, 75, 20, 12, '2021-07-05 11:25:24.77', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:25:24.77', 'Kéo xà đơn (lưng)', 'HIIT_extended.jpg', 2);

select setval('exercise_exercise_id_seq', 10);
-- alter sequence exercise_exercise_id_seq restart with 11;


INSERT INTO public.meal (meal_id, calories, carb_amount, cooking_time, created_date, description, fat_amount, image_url,
                         is_active, is_premium, last_modified_date, "name", coach_profile_coach_id, creator_user_id)
VALUES (1, 1000.0, 2.0, 7000, '2021-07-05 11:29:08.178',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 13.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:29:08.178', 'Meal 1', 0, 2),
       (2, 400.0, 1.0, 6000, '2021-07-05 11:29:33.885',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 15.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:29:33.885', 'Meal 2', 0, 2),
       (3, 600.0, 2.0, 6500, '2021-07-05 11:29:55.037',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 13.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:29:55.037', 'Meal 3', 0, 2),
       (4, 200.0, 0.5, 5000, '2021-07-05 11:30:18.418',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 1.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:30:18.418', 'Meal 4', 0, 2),
       (5, 450.0, 1.5, 6000, '2021-07-05 11:30:45.337',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 10.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:30:45.337', 'Meal 5', 0, 2),
       (6, 150.0, 0.5, 3000, '2021-07-05 11:31:08.605',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 0.5, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:31:08.605', 'Meal 6', 0, 2),
       (7, 500.0, 3.5, 6000, '2021-07-05 11:31:25.836',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 7.5, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:31:25.836', 'Meal 7', 0, 2),
       (8, 800.0, 2.0, 7000, '2021-07-05 11:32:02.899',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 9.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:32:02.899', 'Meal 8', 0, 2),
       (9, 600.0, 1.0, 7000, '2021-07-05 11:32:17.613',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 7.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:32:17.613', 'Meal 9', 0, 2),
       (10, 300.0, 2.0, 7000, '2021-07-05 11:32:33.08',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 2.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:32:33.08', 'Meal 10', 0, 2);

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
VALUES (11, 4),
       (12, 7),
       (13, 5),
       (14, 7),
       (15, 7),
       (16, 7),
       (17, 4),
       (17, 5),
       (18, 4),
       (19, 5),
       (20, 7);

-- theo diet type: cai nay set cho nguoi an chay het
INSERT INTO meal_tag (meal_id, tag_id)
VALUES (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1);

INSERT INTO public.workout (workout_id, created_date, description, estimated_calories, estimated_duration, image_url,
                            is_active, is_premium, last_modified_date, "level", workout_name, coach_profile_coach_id,
                            creator_user_id)
VALUES (1, '2021-07-05 11:21:21.938', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 715, 2910,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:21.938', 2, 'HIIT extended1', 0, 2),
       (2, '2021-07-05 11:21:26.319', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 750, 2460,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:26.319', 2, 'HIIT extended2', 0, 2),
       (3, '2021-07-05 11:21:29.588', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 565, 2610,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:29.588', 2, 'HIIT extended3', 0, 2),
       (4, '2021-07-05 11:21:33.64', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 540, 2610,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:33.64', 2, 'HIIT extended4', 0, 2),
       (5, '2021-07-05 11:21:37.645', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 575, 2760,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:37.645', 2, 'HIIT extended5', 0, 2),
       (6, '2021-07-05 11:21:40.838', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 350, 3360,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:40.838', 2, 'HIIT extended6', 0, 2),
       (7, '2021-07-05 11:21:43.855', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 440, 2710,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:43.855', 2, 'HIIT extended7', 0, 2),
       (8, '2021-07-05 11:21:47.43', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 1565, 5685,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:47.43', 2, 'HIIT extended8', 0, 2),
       (9, '2021-07-05 11:21:50.443', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 750, 2860,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:21:50.443', 2, 'HIIT extended9', 0, 2),
       (10, '2021-07-05 11:22:04.329', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 425, 2660,
        'HIIT_extended.jpg', true, true, '2021-07-05 11:22:04.329', 1, 'HIIT extended10', 0, 2);

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

select setval('workout_workout_id_seq', 10);
-- alter sequence workout_workout_id_seq restart with 11;


