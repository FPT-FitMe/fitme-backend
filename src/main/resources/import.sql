insert into app_user_role
values (0, 'Trainee'),
       (1, 'Manager');

insert into coach_profile
values (0, 'coach_profile@gmail.com', 'Hi im Bob');

insert into app_user (user_id, age, diet_preference_type, email, exercise_frequency_type, first_name,
                      gender, height, is_premium, last_name, password, phone, profile_image_url, workout_intensity,
                      role_role_id)
values (0, 18, 2, 'conmeo1@gmail.com', 2, 'firstNameMember1', 1, 182, true, 'lastNameMember1',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu',
        '00000001', 'memberImageLink1', 0.7, 0),
       (1, 18, 2, 'conmeo2@gmail.com', 2, 'firstNameMember1', 1, 182, false, 'lastNameMember1',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu',
        '00000003', 'memberImageLink2', 1, 0),
       (2, 40, null, 'admin@fitme.vn', null, 'firstNameAdmin1', 1, null, null, 'lastNameAdmin1',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000002', 'managerImage1', null, 1);

INSERT INTO exercise (exercise_id, base_duration, base_kcal, base_rep_per_round, created_date, description, image_url,
                      is_active, last_modified_date, name, video_url, creator_user_id)
VALUES (21, 60, 20, 10, '2021-07-05 11:23:26.988', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:23:26.988', 'Nâng chân', 'HIIT_extended.jpg', 2),
       (22, 70, 20, 12, '2021-07-05 11:23:40.265', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:23:40.265', 'Đạp xe', 'HIIT_extended.jpg', 2),
       (23, 120, 25, 15, '2021-07-05 11:23:51.878', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:23:51.878', 'Gập bụng', 'HIIT_extended.jpg', 2),
       (24, 120, 30, 15, '2021-07-05 11:24:00.132', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:00.132', 'Plank', 'HIIT_extended.jpg', 2),
       (25, 120, 40, 16, '2021-07-05 11:24:12.006', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:12.006', 'Kéo tay trước', 'HIIT_extended.jpg', 2),
       (26, 60, 20, 10, '2021-07-05 11:24:29.067', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:29.067', 'Hít đất', 'HIIT_extended.jpg', 2),
       (27, 90, 20, 12, '2021-07-05 11:24:45.307', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:45.307', 'Chạy chân chạm hông', 'HIIT_extended.jpg', 2),
       (28, 90, 20, 15, '2021-07-05 11:24:57.527', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:24:57.527', 'Hít đất nghiêng', 'HIIT_extended.jpg', 2),
       (29, 100, 25, 16, '2021-07-05 11:25:09.906', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:25:09.906', 'Kéo xà đơn (tay trước)', 'HIIT_extended.jpg', 2),
       (30, 75, 20, 12, '2021-07-05 11:25:24.77', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',
        'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
        true, '2021-07-05 11:25:24.77', 'Kéo xà đơn (lưng)', 'HIIT_extended.jpg', 2);

INSERT INTO public.meal (meal_id, calories, carb_amount, cooking_time, created_date, description, fat_amount, image_url,
                         is_active, is_premium, last_modified_date, "name", coach_profile_coach_id, creator_user_id)
VALUES (11, 1000.0, 2.0, 7000, '2021-07-05 11:29:08.178',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 13.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:29:08.178', 'Meal 1', 0, 2),
       (12, 400.0, 1.0, 6000, '2021-07-05 11:29:33.885',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 15.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:29:33.885', 'Meal 2', 0, 2),
       (13, 600.0, 2.0, 6500, '2021-07-05 11:29:55.037',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 13.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:29:55.037', 'Meal 3', 0, 2),
       (14, 200.0, 0.5, 5000, '2021-07-05 11:30:18.418',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 1.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:30:18.418', 'Meal 4', 0, 2),
       (15, 450.0, 1.5, 6000, '2021-07-05 11:30:45.337',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 10.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:30:45.337', 'Meal 5', 0, 2),
       (16, 150.0, 0.5, 3000, '2021-07-05 11:31:08.605',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 0.5, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:31:08.605', 'Meal 6', 0, 2),
       (17, 500.0, 3.5, 6000, '2021-07-05 11:31:25.836',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 7.5, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:31:25.836', 'Meal 7', 0, 2),
       (18, 800.0, 2.0, 7000, '2021-07-05 11:32:02.899',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 9.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:32:02.899', 'Meal 8', 0, 2),
       (19, 600.0, 1.0, 7000, '2021-07-05 11:32:17.613',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 7.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:32:17.613', 'Meal 9', 0, 2),
       (20, 300.0, 2.0, 7000, '2021-07-05 11:32:33.08',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do', 2.0, 'HIIT_extended.jpg', true, false,
        '2021-07-05 11:32:33.08', 'Meal 10', 0, 2);

INSERT INTO public.tag (tag_id, tag_name, tag_type)
VALUES (0, 'test', NULL),
       (1, 'Chay', NULL),
       (2, 'Không chứa thịt', NULL),
       (3, 'Không tinh bột', NULL),
       (4, 'Bữa sáng', NULL),
       (5, 'Bữa trưa', NULL),
       (6, 'Bữa xế', NULL),
       (7, 'Bữa tối', NULL),
       (8, 'Cardio', NULL),
       (9, 'Toàn thân', NULL);
INSERT INTO public.tag (tag_id, tag_name, tag_type)
VALUES (10, 'Khởi động', NULL),
       (11, 'Thư giãn', NULL),
       (12, 'Đốt calo nhanh', NULL),
       (13, 'Nâng sức mạnh', NULL),
       (14, 'Cho người bắt đầu', NULL);

INSERT INTO public.workout (workout_id, created_date, description, estimated_calories, estimated_duration, image_url,
                            is_active, is_premium, last_modified_date, "level", "name", coach_profile_coach_id,
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
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (31, 1, 21, 1),
       (32, 2, 22, 1),
       (33, 3, 23, 1),
       (34, 4, 24, 1),
       (35, 5, 25, 1),
       (36, 1, 21, 2),
       (37, 2, 27, 2),
       (38, 3, 24, 2),
       (39, 4, 28, 2),
       (40, 5, 29, 2);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (41, 1, 22, 3),
       (42, 2, 26, 3),
       (43, 3, 25, 3),
       (44, 4, 23, 3),
       (45, 5, 21, 3),
       (46, 1, 29, 4),
       (47, 2, 23, 4),
       (48, 3, 22, 4),
       (49, 4, 25, 4),
       (50, 5, 27, 4);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (51, 1, 28, 5),
       (52, 2, 29, 5),
       (53, 3, 21, 5),
       (54, 4, 21, 5),
       (55, 5, 22, 5),
       (56, 1, 22, 6),
       (57, 2, 22, 6),
       (58, 3, 21, 6),
       (59, 4, 21, 6),
       (60, 5, 22, 6);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (61, 1, 23, 7),
       (62, 2, 22, 7),
       (63, 3, 25, 7),
       (64, 4, 21, 7),
       (65, 5, 27, 7),
       (66, 1, 24, 8),
       (67, 2, 25, 8),
       (68, 3, 23, 8),
       (69, 4, 22, 8),
       (70, 5, 29, 8);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (71, 6, 29, 8),
       (72, 7, 29, 8),
       (73, 8, 29, 8),
       (74, 9, 22, 8),
       (75, 10, 21, 8),
       (76, 1, 29, 9),
       (77, 2, 29, 9),
       (78, 3, 29, 9),
       (79, 4, 22, 9),
       (80, 5, 21, 9);
INSERT INTO public.workout_exercise (workout_exercise_id, exercise_order, exercise_id, workout_id)
VALUES (81, 1, 27, 10),
       (82, 2, 21, 10),
       (83, 3, 26, 10),
       (84, 4, 22, 10),
       (85, 5, 21, 10);

-- insert into workout (workout_id, description, estimated_calories, estimated_duration, image_url, name, isPremium,
--                      level, coach_profile_coach_id, creator_user_id, isActive)
-- values (0, 'Workout description here', 0, 0, 'workout_image.jpg', 'workout name 1', false, 1, 0, 2, true),
--        (1, 'Workout description here', 0, 0, 'workout_image.jpg', 'workout name 1', false, 1, 0, 2, true),
--        (2, 'Workout description here', 0, 0, 'workout_image.jpg', 'workout name 1', false, 1, 0, 2, true);

-- insert into meal (meal_id, calories, carb_amount, cooking_time, description, fat_amount, image_url, is_premium, name,
--                   coach_profile_coach_id, creator_user_id)
-- values (0, 2000, 800, 15, 'Meal description here', 45, 'meal.com/image', false, 'Meal name', 0, 2),
--        (1, 2000, 800, 15, 'Meal description here', 45, 'meal.com/image', false, 'Meal name 2', 0, 2),
--        (2, 2000, 800, 15, 'Meal description here', 45, 'meal.com/image', false, 'Meal name 3', 0, 2);

