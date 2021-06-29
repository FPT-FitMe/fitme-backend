
insert into app_user_role values
    (0, 'Trainee'),
    (1, 'Manager');

insert into coach_profile values
    (0, 'coach_profile@gmail.com', 'Hi im Bob');

insert into app_user (user_id, age, diet_preference_type, email, exercise_frequency_type, first_name, gender, height, is_premium, last_name, password, phone, profile_image_url, workout_intensity, role_role_id) values
    (0, 18, 2, 'conmeo1@gmail.com', 2, 'first_name_1', 1, 182, true, 'last_name_1', '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000001', 'image.com', 2, 0),
    (1, 18, 2, 'conmeo2@gmail.com', 2, 'first_name_2',1, 182, true, 'last_name_2', '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000003', 'image.com', 2, 0),
    (2, 40 , null, 'admin@fitme.vn', null, 'first_name_admin_1', 1 , 'last_name_admin_1', null , null, '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000002', 'image.com', 2, 1);

insert into tag values
    (0, 'test'),
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

insert into workout (workout_id, description, image_url, name, is_premium, level, coach_profile_coach_id, creator_user_id) values
    (0, 'Workout description here', 'workout_image.jpg', 'workout name 1', false, 1, 0, 2),
    (1, 'Workout description here', 'workout_image.jpg', 'workout name 1' ,false, 1, 0, 2),
    (2, 'Workout description here', 'workout_image.jpg', 'workout name 1', false, 1, 0, 2);

insert into exercise (exercise_id, base_duration, base_rep_per_round, description, exercise_order, image_url, name, video_url)  values
    (0, 600, 10, 'Exercise Description', 0, 'exercise_image.jpg', 'exercise name 1', 'exercise_video.mp4'),
    (1, 600, 10, 'Exercise Description', 1, 'exercise_image.jpg', 'exercise name 2', 'exercise_video.mp4'),
    (2, 600, 10, 'Exercise Description', 1, 'exercise_image.jpg', 'exercise name 3', 'exercise_video.mp4'),
    (3, 600, 10, 'Exercise Description', 2, 'exercise_image.jpg', 'exercise name 4', 'exercise_video.mp4'),
    (4, 600, 10, 'Exercise Description', 0, 'exercise_image.jpg', 'exercise name 5', 'exercise_video.mp4');

insert into exercise_workout (exercise_id, workout_id) values
    (0, 0), (0, 1), (1, 0), (2, 2), (0, 1);

insert into exercise_tag (exercise_id, tag_id) values
    (0, 1);

insert into workout_tag (workout_id, tag_id) values
    (0, 1);


insert into meal (meal_id, calories, carb_amount, cooking_time, description, fat_amount, image_url, is_premium, name, coach_profile_coach_id, creator_user_id) values
    (0, 2000, 800, 15, 'Meal description here', 45, 'meal.com/image', false, 'Meal name', 0, 2),
    (1, 2000, 800, 15, 'Meal description here', 45, 'meal.com/image', false, 'Meal name 2', 0, 2),
    (2, 2000, 800, 15, 'Meal description here', 45, 'meal.com/image', false, 'Meal name 3', 0, 2);


