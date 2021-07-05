insert into app_user_role
values (0, 'Trainee'),
       (1, 'Manager');

insert into coach_profile
values (0, 'coach_profile@gmail.com', 'Hi im Bob');

insert into app_user (user_id, age, diet_preference_type, email, exercise_frequency_type, first_name,
                      gender, height, is_premium, last_name, password, phone, profile_image_url, workout_intensity, role_role_id)
values (DEFAULT, 18, 2, 'conmeo1@gmail.com', 2, 'firstNameMember1', 1, 182, true, 'lastNameMember1', '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu',
        '00000001', 'memberImageLink1', 0.7, 0),
       (DEFAULT, 18, 2, 'conmeo2@gmail.com', 2, 'firstNameMember1', 1, 182, false, 'lastNameMember1', '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu',
        '00000003', 'memberImageLink2', 1, 0),
       (DEFAULT, 40, null, 'admin@fitme.vn', null, 'firstNameAdmin1', 1, null, null, 'lastNameAdmin1',
        '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000002', 'managerImage1', null, 1);

insert into tag
values (0, 'test'),
       (1, 'Chay'),
       (2, 'Không chứa thịt'),
       (3, 'Không tinh bột'),
       (4, 'Bữa sáng'),
       (5, 'Bữa trưa'),
       (6, 'Bữa xế'),
       (7, 'Bữa tối'),
       (8, 'Cardio'),
       (9, 'Toàn thân'),
       (10, 'Khởi động'),
       (11, 'Thư giãn'),
       (12, 'Đốt calo nhanh'),
       (13, 'Nâng sức mạnh'),
       (14, 'Cho người bắt đầu');

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

