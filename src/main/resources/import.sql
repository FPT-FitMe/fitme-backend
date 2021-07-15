INSERT INTO public.app_user_role (role_id,role_name) VALUES
(0,'Trainee'),
(1,'Manager');

INSERT INTO public.app_user (user_id,age,created_date,diet_preference_type,email,exercise_frequency_type,first_name,gender,height,is_active,is_premium,last_modified_date,last_name,"password",phone,profile_image_url,workout_intensity,role_role_id) VALUES
(0,18,'2021-07-05 11:23:26.988',2,'conmeo1@gmail.com',2,'Ngô',1,182.0,true,true,'2021-07-05 11:23:26.988','Đức','$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu','00000001','memberImageLink1',0.7,0),
(1,18,'2021-07-05 11:23:26.988',2,'conmeo2@gmail.com',2,'Kim',1,182.0,true,false,'2021-07-05 11:23:26.988','Hằng','$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu','00000003','memberImageLink2',1.0,0),
(2,40,'2021-07-05 11:23:26.988',NULL,'admin@fitme.vn',NULL,'Cẩm',1,NULL,true,NULL,'2021-07-05 11:23:26.988','Long','$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu','00000002','managerImage1',NULL,1),
(3,NULL,'2021-06-19 23:56:21.57',NULL,'conmeo3@gmail.com',NULL,'Hữu',NULL,NULL,true,false,'2021-06-19 23:56:21.57','Huy','$2a$10$EvTHoGcXD4fBbi9m8Dhs/eMYRsFcvW3zY.G1j8nZ8GaSfv8QQK9/C',NULL,NULL,NULL,0);

select setval('app_user_user_id_seq', 3);

INSERT INTO public.coach_profile (coach_id,contact,image_url,introduction,is_active,coach_name) VALUES
(0,'lalisa@gmail.com','https://i.pinimg.com/originals/0f/56/51/0f56511d7e416da63782dd0cc73816f1.png','Hi im Lalisa',true,'Lalisa'),
(1,'rose@gmail.com','https://i.imgur.com/zCiPWRn.jpeg','Hi im Rose',true,'Rose'),
(2,'arina@gmail.com','https://i.imgur.com/u4EVYZC.jpg','Hi im Arina Grande',true,'Ariana Grande');

select setval('coach_profile_coach_id_seq', 2);

INSERT INTO public.tag (tag_id,is_active,tag_name,tag_type) VALUES
(0,true,'test',NULL),
(1,true,'Chay','meal'),
(2,true,'Không chứa thịt','meal'),
(3,true,'Không tinh bột','meal'),
(4,true,'Bữa sáng','meal'),
(5,true,'Bữa trưa','meal'),
(6,true,'Bữa xế','meal'),
(7,true,'Bữa tối','meal'),
(8,true,'Cardio','exercise'),
(9,true,'Toàn thân','exercise');
INSERT INTO public.tag (tag_id,is_active,tag_name,tag_type) VALUES
(10,true,'Khởi động','exercise'),
(11,true,'Thư giãn','exercise'),
(12,true,'Đốt calo nhanh','exercise'),
(13,true,'Nâng sức mạnh','exercise'),
(14,true,'Cho người bắt đầu','exercise');

select setval('tag_tag_id_seq', 14);

INSERT INTO public.exercise (exercise_id,base_duration,base_kcal,base_rep_per_round,created_date,description,image_url,is_active,last_modified_date,exercise_name,video_url,creator_user_id) VALUES
(1,60,20,10,'2021-07-05 11:23:26.988','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1598136490941-30d885318abd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=749&q=80',true,'2021-07-05 11:23:26.988','Nâng chân','https://media.giphy.com/media/UQa44aVsUd6t7lIigt/giphy.gif',2),
(2,70,20,12,'2021-07-05 11:23:40.265','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1551984427-6d77a1918093?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=653&q=80',true,'2021-07-05 11:23:40.265','Đạp xe','https://media.giphy.com/media/57Uzij2DnzdBvGi3qv/giphy.gif',2),
(3,120,25,15,'2021-07-05 11:23:51.878','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,'2021-07-05 11:23:51.878','Gập bụng','https://media.giphy.com/media/uGP4yfwMnf8Tm/giphy.gif',2),
(4,120,30,15,'2021-07-05 11:24:00.132','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1598632640487-6ea4a4e8b963?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80',true,'2021-07-05 11:24:00.132','Plank','https://media.giphy.com/media/xT8qBff8cRRFf7k2u4/giphy.gif',2),
(5,120,40,16,'2021-07-05 11:24:12.006','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,'2021-07-05 11:24:12.006','Kéo tay trước','https://media.giphy.com/media/WsFX0H7qz5M1o8VpxH/giphy.gif',2),
(6,60,20,10,'2021-07-05 11:24:29.067','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1616803689943-5601631c7fec?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,'2021-07-05 11:24:29.067','Hít đất','https://media.giphy.com/media/7e3P2VsI7Js2c/giphy.gif',2),
(7,90,20,12,'2021-07-05 11:24:45.307','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1599058917727-824293170100?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=749&q=80',true,'2021-07-05 11:24:45.307','Chạy chân chạm hông','https://media.giphy.com/media/JRlqKEzTDKci5JPcaL/giphy.gif',2),
(8,90,20,15,'2021-07-05 11:24:57.527','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,'2021-07-05 11:24:57.527','Hít đất nghiêng','https://media.giphy.com/media/xTiTngQQf1U8IdowNi/giphy.gif',2),
(9,100,25,16,'2021-07-05 11:25:09.906','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1516208962313-9d183d94f577?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=667&q=80',true,'2021-07-05 11:25:09.906','Kéo xà đơn (tay trước)','https://media.giphy.com/media/iGMpf3IMQxpuw1mHjI/giphy.gif',2),
(10,75,20,12,'2021-07-05 11:25:24.77','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','https://images.unsplash.com/photo-1605296867424-35fc25c9212a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,'2021-07-05 11:25:24.77','Kéo xà đơn (lưng)','https://media.giphy.com/media/xUPGcD1LxZUkKUMOB2/giphy.gif',2);

select setval('exercise_exercise_id_seq', 10);

INSERT INTO public.meal (meal_id,calories,carb_amount,cooking_time,created_date,description,fat_amount,image_url,is_active,is_premium,last_modified_date,"name",coach_profile_coach_id,creator_user_id) VALUES
(1,600.0,2.0,7,'2021-07-05 11:29:08.178','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',13.0,'https://images.unsplash.com/photo-1568096889942-6eedde686635?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:29:08.178','Phở bò',0,2),
(2,400.0,1.0,6,'2021-07-05 11:29:33.885','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',15.0,'https://images.unsplash.com/photo-1461530927168-44328109da52?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:29:33.885','Salad thập cẩm',0,2),
(3,800.0,2.0,7,'2021-07-05 11:29:55.037','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',13.0,'https://images.unsplash.com/photo-1563379926898-05f4575a45d8?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',true,false,'2021-07-05 11:29:55.037','Pasta hải sản',0,2),
(4,300.0,0.5,5,'2021-07-05 11:30:18.418','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',1.0,'https://images.unsplash.com/photo-1550304943-4f24f54ddde9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:30:18.418','Salad bắp cải',0,2),
(5,350.0,1.5,10,'2021-07-05 11:30:45.337','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',10.0,'https://images.unsplash.com/photo-1533622597524-a1215e26c0a2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:30:45.337','Salad thập cẩm',0,2),
(6,250.0,0.5,25,'2021-07-05 11:31:08.605','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',0.5,'https://images.unsplash.com/photo-1512003867696-6d5ce6835040?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:31:08.605','Lẩu rau củ',0,2),
(7,450.0,3.5,15,'2021-07-05 11:31:25.836','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',7.5,'https://images.unsplash.com/photo-1503764654157-72d979d9af2f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80',true,false,'2021-07-05 11:31:25.836','Hủ tiếu giảm cân',0,2),
(8,900.0,2.0,7,'2021-07-05 11:32:02.899','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',9.0,'https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:32:02.899','Carry gà',0,2),
(9,550.0,1.0,7,'2021-07-05 11:32:17.613','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',7.0,'https://images.unsplash.com/photo-1473093295043-cdd812d0e601?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:32:17.613','Pasta rau củ',0,2),
(10,350.0,2.0,7,'2021-07-05 11:32:33.08','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',2.0,'https://images.unsplash.com/photo-1495214783159-3503fd1b572d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:32:33.08','Pancake trái cây',0,2);

select setval('meal_meal_id_seq', 10);

INSERT INTO public.meal_tag (meal_id,tag_id) VALUES
(1,4),
(2,7),
(3,5),
(4,7),
(5,7),
(6,7),
(7,4),
(7,5),
(8,4),
(9,5);
INSERT INTO public.meal_tag (meal_id,tag_id) VALUES
(10,7),
(1,1),
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(8,1),
(9,1),
(10,1);

INSERT INTO public.post (post_id,content_body,content_header,created_date,image_url,is_active,last_modified_date,post_name,reading_time,coach_profile_coach_id,creator_user_id) VALUES
(1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Nước vô cùng quan trọng với cơ thể','2021-07-14 19:38:45.407','https://images.unsplash.com/photo-1600679472233-eabc13b79f07?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,'2021-07-14 19:38:45.407','Tại sao bạn cần nước?',7,0,2),
(2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Fastfood gây ảnh hưởng xấu tới cơ thể','2021-07-14 19:39:30.402','https://images.unsplash.com/photo-1529973565457-a60a2ccf750d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,'2021-07-14 19:39:30.402','Hạn chế ăn fastfood',10,1,2),
(3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do','Rau xanh cung cấp nhiều vitamin và dưỡng chất','2021-07-14 19:40:26.936','https://images.unsplash.com/photo-1565895405139-e188df996e0b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=733&q=80',true,'2021-07-14 19:40:26.936','Rau xanh tốt cho cơ thể bạn',8,1,2);
select setval('post_post_id_seq', 3);

INSERT INTO public.weight_log (weight_log_id,created_at,value,trainee_user_id) VALUES
(1,'2021-07-06 11:23:26.988',71.3,1),
(2,'2021-07-09 11:23:26.988',71.2,1),
(3,'2021-07-12 11:23:26.988',71.3,1),
(4,'2021-07-15 11:23:26.988',71.2,1),
(5,'2021-07-22 11:23:26.988',71.1,1),
(6,'2021-07-23 11:23:26.988',71.0,1),
(7,'2021-07-27 11:23:26.988',70.9,1),
(8,'2021-07-30 11:23:26.988',70.85,1),
(9,'2021-08-01 11:23:26.988',70.5,1),
(10,'2021-08-10 11:23:26.988',70.2,1);
INSERT INTO public.weight_log (weight_log_id,created_at,value,trainee_user_id) VALUES
(11,'2021-08-14 11:23:26.988',70.25,1),
(12,'2021-06-19 23:56:43.687',69.0,3),
(13,'2021-06-26 23:56:13.177',68.5,3),
(14,'2021-07-03 23:56:33.972',68.0,3),
(15,'2021-07-10 23:56:53.589',67.0,3),
(16,'2021-07-15 23:59:02.555',67.5,3);

select setval('weight_log_weight_log_id_seq', 16);

INSERT INTO public.workout (workout_id,created_date,description,estimated_calories,estimated_duration,image_url,is_active,is_premium,last_modified_date,"level",workout_name,coach_profile_coach_id,creator_user_id) VALUES
(1,'2021-07-05 11:21:21.938','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',715,120,'https://images.unsplash.com/photo-1599058917212-d750089bc07e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80',true,false,'2021-07-05 11:21:21.938',2,'Full body workout',0,2),
(2,'2021-07-05 11:21:26.319','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',750,150,'https://images.unsplash.com/photo-1533681717801-1bbd2ec8d269?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:21:26.319',2,'Morning workout',0,2),
(3,'2021-07-05 11:21:29.588','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',565,120,'https://images.unsplash.com/photo-1532384748853-8f54a8f476e2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:21:29.588',2,'Arm workout',0,2),
(4,'2021-07-05 11:21:33.64','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',540,45,'https://images.unsplash.com/photo-1434682772747-f16d3ea162c3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80',true,false,'2021-07-05 11:21:33.64',2,'Leg workout',0,2),
(5,'2021-07-05 11:21:37.645','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',575,75,'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,true,'2021-07-05 11:21:37.645',2,'Abs workout',0,2),
(6,'2021-07-05 11:21:40.838','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',350,60,'https://images.unsplash.com/photo-1434682881908-b43d0467b798?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80',true,false,'2021-07-05 11:21:40.838',2,'Cardio workout',0,2),
(7,'2021-07-05 11:21:43.855','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',440,60,'https://images.unsplash.com/photo-1434754205268-ad3b5f549b11?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80',true,true,'2021-07-05 11:21:43.855',2,'Back workout',0,2),
(8,'2021-07-05 11:21:47.43','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',1565,90,'https://images.unsplash.com/photo-1598136490929-292a0a7890c2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80',true,false,'2021-07-05 11:21:47.43',2,'Strenght workout',0,2),
(9,'2021-07-05 11:21:50.443','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',750,80,'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',true,false,'2021-07-05 11:21:50.443',2,'Endurance workout',0,2),
(10,'2021-07-05 11:22:04.329','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do',425,120,'https://images.unsplash.com/photo-1480264104733-84fb0b925be3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',true,true,'2021-07-05 11:22:04.329',1,'Bodyweight workout',0,2);

select setval('workout_workout_id_seq', 10);

INSERT INTO public.workout_exercise (workout_exercise_id,exercise_order,exercise_id,workout_id) VALUES
(1,1,1,1),
(2,2,2,1),
(3,3,3,1),
(4,4,4,1),
(5,5,5,1),
(6,1,1,2),
(7,2,2,2),
(8,3,4,2),
(9,4,8,2),
(10,5,9,2);
INSERT INTO public.workout_exercise (workout_exercise_id,exercise_order,exercise_id,workout_id) VALUES
(11,1,2,3),
(12,2,6,3),
(13,3,5,3),
(14,4,3,3),
(15,5,1,3),
(16,1,9,4),
(17,2,3,4),
(18,3,2,4),
(19,4,5,4),
(20,5,7,4);
INSERT INTO public.workout_exercise (workout_exercise_id,exercise_order,exercise_id,workout_id) VALUES
(21,1,8,5),
(22,2,9,5),
(23,3,1,5),
(24,4,1,5),
(25,5,2,5),
(26,1,2,6),
(27,2,2,6),
(28,3,1,6),
(29,4,1,6),
(30,5,2,6);
INSERT INTO public.workout_exercise (workout_exercise_id,exercise_order,exercise_id,workout_id) VALUES
(31,1,3,7),
(32,2,2,7),
(33,3,5,7),
(34,4,1,7),
(35,5,7,7),
(36,1,4,8),
(37,2,5,8),
(38,3,3,8),
(39,4,2,8),
(40,5,9,8);
INSERT INTO public.workout_exercise (workout_exercise_id,exercise_order,exercise_id,workout_id) VALUES
(41,6,9,8),
(42,7,9,8),
(43,8,9,8),
(44,9,2,8),
(45,10,1,8),
(46,1,9,9),
(47,2,9,9),
(48,3,9,9),
(49,4,2,9),
(50,5,1,9);
INSERT INTO public.workout_exercise (workout_exercise_id,exercise_order,exercise_id,workout_id) VALUES
(51,1,7,10),
(52,2,1,10),
(53,3,6,10),
(54,4,2,10),
(55,5,1,10);

select setval('workout_exercise_workout_exercise_id_seq', 55);

INSERT INTO public.target (id,complete_date,has_finished,start_date,starting_bmi,target_bmi,trainee_user_id) VALUES
(1,'2021-09-17 00:00:00',false,'2021-06-19 00:00:00',22.790329,21.799444,3);

select setval('target_id_seq', 1);

INSERT INTO public.weight_log (weight_log_id,created_at,value,trainee_user_id) VALUES
(18,'2021-07-06 11:23:26.988',71.3,1),
(2,'2021-07-09 11:23:26.988',71.2,1),
(3,'2021-07-12 11:23:26.988',71.3,1),
(4,'2021-07-15 11:23:26.988',71.2,1),
(5,'2021-07-22 11:23:26.988',71.1,1),
(6,'2021-07-23 11:23:26.988',71.0,1),
(7,'2021-07-27 11:23:26.988',70.9,1),
(8,'2021-07-30 11:23:26.988',70.85,1),
(9,'2021-08-01 11:23:26.988',70.5,1),
(10,'2021-08-10 11:23:26.988',70.2,1);
INSERT INTO public.weight_log (weight_log_id,created_at,value,trainee_user_id) VALUES
(19,'2021-08-14 11:23:26.988',70.25,1),
(12,'2021-06-19 00:13:55.695',69.0,3),
(13,'2021-06-19 00:14:20.146',69.0,3),
(14,'2021-06-26 00:14:40.7',68.5,3),
(15,'2021-07-03 00:15:06.613',67.5,3),
(16,'2021-07-10 00:15:22.405',68.0,3),
(17,'2021-07-15 00:15:56.516',67.5,3);

select setval('weight_log_weight_log_id_seq', 19);

INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(2,'2021-06-19 00:00:00',1),
(7,'2021-06-20 00:00:00',1),
(12,'2021-06-21 00:00:00',1),
(16,'2021-06-22 00:00:00',1),
(21,'2021-06-23 00:00:00',1),
(26,'2021-06-24 00:00:00',1),
(31,'2021-06-25 00:00:00',1),
(35,'2021-06-26 00:00:00',1),
(40,'2021-06-27 00:00:00',1),
(45,'2021-06-28 00:00:00',1);
INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(49,'2021-06-29 00:00:00',1),
(54,'2021-06-30 00:00:00',1),
(59,'2021-07-01 00:00:00',1),
(64,'2021-07-02 00:00:00',1),
(68,'2021-07-03 00:00:00',1),
(73,'2021-07-04 00:00:00',1),
(78,'2021-07-05 00:00:00',1),
(82,'2021-07-06 00:00:00',1),
(87,'2021-07-07 00:00:00',1),
(92,'2021-07-08 00:00:00',1);
INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(97,'2021-07-09 00:00:00',1),
(101,'2021-07-10 00:00:00',1),
(106,'2021-07-11 00:00:00',1),
(111,'2021-07-12 00:00:00',1),
(115,'2021-07-13 00:00:00',1),
(120,'2021-07-14 00:00:00',1),
(125,'2021-07-15 00:00:00',1),
(130,'2021-07-16 00:00:00',1),
(134,'2021-07-17 00:00:00',1),
(139,'2021-07-18 00:00:00',1);
INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(144,'2021-07-19 00:00:00',1),
(148,'2021-07-20 00:00:00',1),
(153,'2021-07-21 00:00:00',1),
(158,'2021-07-22 00:00:00',1),
(163,'2021-07-23 00:00:00',1),
(167,'2021-07-24 00:00:00',1),
(172,'2021-07-25 00:00:00',1),
(177,'2021-07-26 00:00:00',1),
(181,'2021-07-27 00:00:00',1),
(186,'2021-07-28 00:00:00',1);
INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(191,'2021-07-29 00:00:00',1),
(196,'2021-07-30 00:00:00',1),
(200,'2021-07-31 00:00:00',1),
(205,'2021-08-01 00:00:00',1),
(210,'2021-08-02 00:00:00',1),
(214,'2021-08-03 00:00:00',1),
(219,'2021-08-04 00:00:00',1),
(224,'2021-08-05 00:00:00',1),
(229,'2021-08-06 00:00:00',1),
(233,'2021-08-07 00:00:00',1);
INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(238,'2021-08-08 00:00:00',1),
(243,'2021-08-09 00:00:00',1),
(247,'2021-08-10 00:00:00',1),
(252,'2021-08-11 00:00:00',1),
(257,'2021-08-12 00:00:00',1),
(262,'2021-08-13 00:00:00',1),
(266,'2021-08-14 00:00:00',1),
(271,'2021-08-15 00:00:00',1),
(276,'2021-08-16 00:00:00',1),
(280,'2021-08-17 00:00:00',1);
INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(285,'2021-08-18 00:00:00',1),
(290,'2021-08-19 00:00:00',1),
(295,'2021-08-20 00:00:00',1),
(299,'2021-08-21 00:00:00',1),
(304,'2021-08-22 00:00:00',1),
(309,'2021-08-23 00:00:00',1),
(313,'2021-08-24 00:00:00',1),
(318,'2021-08-25 00:00:00',1),
(323,'2021-08-26 00:00:00',1),
(328,'2021-08-27 00:00:00',1);
INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(332,'2021-08-28 00:00:00',1),
(337,'2021-08-29 00:00:00',1),
(342,'2021-08-30 00:00:00',1),
(346,'2021-08-31 00:00:00',1),
(351,'2021-09-01 00:00:00',1),
(356,'2021-09-02 00:00:00',1),
(361,'2021-09-03 00:00:00',1),
(365,'2021-09-04 00:00:00',1),
(370,'2021-09-05 00:00:00',1),
(375,'2021-09-06 00:00:00',1);
INSERT INTO public.plan (plan_id,"date",target_id) VALUES
(379,'2021-09-07 00:00:00',1),
(384,'2021-09-08 00:00:00',1),
(389,'2021-09-09 00:00:00',1),
(394,'2021-09-10 00:00:00',1);

select setval('plan_plan_id_seq', 394);


INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(3,NULL,8,2),
(4,NULL,3,2),
(5,NULL,2,2),
(8,NULL,8,7),
(9,NULL,9,7),
(10,NULL,2,7),
(13,NULL,8,12),
(14,NULL,3,12),
(15,NULL,2,12),
(17,NULL,8,16);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(18,NULL,3,16),
(19,NULL,2,16),
(22,NULL,8,21),
(23,NULL,3,21),
(24,NULL,2,21),
(27,NULL,8,26),
(28,NULL,3,26),
(29,NULL,2,26),
(32,NULL,8,31),
(33,NULL,3,31);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(34,NULL,2,31),
(36,NULL,8,35),
(37,NULL,3,35),
(38,NULL,2,35),
(41,NULL,8,40),
(42,NULL,3,40),
(43,NULL,2,40),
(46,NULL,8,45),
(47,NULL,3,45),
(48,NULL,2,45);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(50,NULL,8,49),
(51,NULL,3,49),
(52,NULL,2,49),
(55,NULL,8,54),
(56,NULL,3,54),
(57,NULL,2,54),
(60,NULL,8,59),
(61,NULL,9,59),
(62,NULL,2,59),
(65,NULL,8,64);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(66,NULL,3,64),
(67,NULL,2,64),
(69,NULL,8,68),
(70,NULL,3,68),
(71,NULL,2,68),
(74,NULL,8,73),
(75,NULL,3,73),
(76,NULL,2,73),
(79,NULL,8,78),
(80,NULL,3,78);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(81,NULL,2,78),
(83,NULL,8,82),
(84,NULL,9,82),
(85,NULL,2,82),
(88,NULL,8,87),
(89,NULL,3,87),
(90,NULL,2,87),
(93,NULL,8,92),
(94,NULL,3,92),
(95,NULL,2,92);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(98,NULL,8,97),
(99,NULL,3,97),
(100,NULL,2,97),
(102,NULL,8,101),
(103,NULL,3,101),
(104,NULL,2,101),
(107,NULL,8,106),
(108,NULL,9,106),
(109,NULL,2,106),
(112,NULL,8,111);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(113,NULL,3,111),
(114,NULL,2,111),
(116,NULL,8,115),
(117,NULL,3,115),
(118,NULL,2,115),
(121,NULL,8,120),
(122,NULL,3,120),
(123,NULL,2,120),
(126,NULL,8,125),
(127,NULL,3,125);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(128,NULL,2,125),
(131,NULL,8,130),
(132,NULL,3,130),
(133,NULL,2,130),
(135,NULL,8,134),
(136,NULL,9,134),
(137,NULL,2,134),
(140,NULL,8,139),
(141,NULL,3,139),
(142,NULL,2,139);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(145,NULL,8,144),
(146,NULL,3,144),
(147,NULL,2,144),
(149,NULL,8,148),
(150,NULL,3,148),
(151,NULL,2,148),
(154,NULL,8,153),
(155,NULL,3,153),
(156,NULL,2,153),
(159,NULL,8,158);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(160,NULL,3,158),
(161,NULL,2,158),
(164,NULL,8,163),
(165,NULL,3,163),
(166,NULL,2,163),
(168,NULL,8,167),
(169,NULL,3,167),
(170,NULL,2,167),
(173,NULL,8,172),
(174,NULL,3,172);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(175,NULL,2,172),
(178,NULL,8,177),
(179,NULL,3,177),
(180,NULL,2,177),
(182,NULL,8,181),
(183,NULL,9,181),
(184,NULL,2,181),
(187,NULL,8,186),
(188,NULL,3,186),
(189,NULL,2,186);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(192,NULL,8,191),
(193,NULL,3,191),
(194,NULL,2,191),
(197,NULL,8,196),
(198,NULL,3,196),
(199,NULL,2,196),
(201,NULL,8,200),
(202,NULL,3,200),
(203,NULL,2,200),
(206,NULL,8,205);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(207,NULL,9,205),
(208,NULL,2,205),
(211,NULL,8,210),
(212,NULL,3,210),
(213,NULL,2,210),
(215,NULL,8,214),
(216,NULL,3,214),
(217,NULL,2,214),
(220,NULL,8,219),
(221,NULL,3,219);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(222,NULL,2,219),
(225,NULL,8,224),
(226,NULL,3,224),
(227,NULL,2,224),
(230,NULL,8,229),
(231,NULL,3,229),
(232,NULL,2,229),
(234,NULL,8,233),
(235,NULL,3,233),
(236,NULL,2,233);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(239,NULL,8,238),
(240,NULL,3,238),
(241,NULL,2,238),
(244,NULL,8,243),
(245,NULL,3,243),
(246,NULL,2,243),
(248,NULL,8,247),
(249,NULL,3,247),
(250,NULL,2,247),
(253,NULL,8,252);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(254,NULL,3,252),
(255,NULL,2,252),
(258,NULL,8,257),
(259,NULL,9,257),
(260,NULL,2,257),
(263,NULL,8,262),
(264,NULL,3,262),
(265,NULL,2,262),
(267,NULL,8,266),
(268,NULL,3,266);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(269,NULL,2,266),
(272,NULL,8,271),
(273,NULL,3,271),
(274,NULL,2,271),
(277,NULL,8,276),
(278,NULL,3,276),
(279,NULL,2,276),
(281,NULL,8,280),
(282,NULL,3,280),
(283,NULL,2,280);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(286,NULL,8,285),
(287,NULL,9,285),
(288,NULL,2,285),
(291,NULL,8,290),
(292,NULL,3,290),
(293,NULL,2,290),
(296,NULL,8,295),
(297,NULL,3,295),
(298,NULL,2,295),
(300,NULL,8,299);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(301,NULL,3,299),
(302,NULL,2,299),
(305,NULL,8,304),
(306,NULL,3,304),
(307,NULL,2,304),
(310,NULL,8,309),
(311,NULL,3,309),
(312,NULL,2,309),
(314,NULL,8,313),
(315,NULL,3,313);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(316,NULL,2,313),
(319,NULL,8,318),
(320,NULL,3,318),
(321,NULL,2,318),
(324,NULL,8,323),
(325,NULL,9,323),
(326,NULL,2,323),
(329,NULL,8,328),
(330,NULL,3,328),
(331,NULL,2,328);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(333,NULL,8,332),
(334,NULL,3,332),
(335,NULL,2,332),
(338,NULL,8,337),
(339,NULL,9,337),
(340,NULL,2,337),
(343,NULL,8,342),
(344,NULL,3,342),
(345,NULL,2,342),
(347,NULL,8,346);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(348,NULL,3,346),
(349,NULL,2,346),
(352,NULL,8,351),
(353,NULL,3,351),
(354,NULL,2,351),
(357,NULL,8,356),
(358,NULL,3,356),
(359,NULL,2,356),
(362,NULL,8,361),
(363,NULL,3,361);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(364,NULL,2,361),
(366,NULL,8,365),
(367,NULL,3,365),
(368,NULL,2,365),
(371,NULL,8,370),
(372,NULL,3,370),
(373,NULL,2,370),
(376,NULL,8,375),
(377,NULL,3,375),
(378,NULL,2,375);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(380,NULL,8,379),
(381,NULL,9,379),
(382,NULL,2,379),
(385,NULL,8,384),
(386,NULL,3,384),
(387,NULL,2,384),
(390,NULL,8,389),
(391,NULL,3,389),
(392,NULL,2,389),
(395,NULL,8,394);
INSERT INTO public.plan_meal (plan_meal_id,status,meal_id,plan_id) VALUES
(396,NULL,3,394),
(397,NULL,2,394);

select setval('plan_meal_plan_meal_id_seq', 397);

INSERT INTO public.plan_workout (plan_workout,status,plan_id,workout_id) VALUES
(6,NULL,2,6),
(11,NULL,7,2),
(20,NULL,16,1),
(25,NULL,21,3),
(30,NULL,26,8),
(39,NULL,35,1),
(44,NULL,40,6),
(53,NULL,49,2),
(58,NULL,54,4),
(63,NULL,59,3);
INSERT INTO public.plan_workout (plan_workout,status,plan_id,workout_id) VALUES
(72,NULL,68,4),
(77,NULL,73,2),
(86,NULL,82,9),
(91,NULL,87,3),
(96,NULL,92,1),
(105,NULL,101,9),
(110,NULL,106,4),
(119,NULL,115,8),
(124,NULL,120,2),
(129,NULL,125,3);
INSERT INTO public.plan_workout (plan_workout,status,plan_id,workout_id) VALUES
(138,NULL,134,9),
(143,NULL,139,2),
(152,NULL,148,4),
(157,NULL,153,1),
(162,NULL,158,8),
(171,NULL,167,1),
(176,NULL,172,8),
(185,NULL,181,9),
(190,NULL,186,6),
(195,NULL,191,3);
INSERT INTO public.plan_workout (plan_workout,status,plan_id,workout_id) VALUES
(204,NULL,200,1),
(209,NULL,205,4),
(218,NULL,214,2),
(223,NULL,219,3),
(228,NULL,224,9),
(237,NULL,233,9),
(242,NULL,238,3),
(251,NULL,247,6),
(256,NULL,252,1),
(261,NULL,257,4);
INSERT INTO public.plan_workout (plan_workout,status,plan_id,workout_id) VALUES
(270,NULL,266,1),
(275,NULL,271,6),
(284,NULL,280,8),
(289,NULL,285,9),
(294,NULL,290,3),
(303,NULL,299,8),
(308,NULL,304,4),
(317,NULL,313,9),
(322,NULL,318,6),
(327,NULL,323,1);
INSERT INTO public.plan_workout (plan_workout,status,plan_id,workout_id) VALUES
(336,NULL,332,1),
(341,NULL,337,6),
(350,NULL,346,9),
(355,NULL,351,2),
(360,NULL,356,3),
(369,NULL,365,4),
(374,NULL,370,6),
(383,NULL,379,2),
(388,NULL,384,8),
(393,NULL,389,1);

-- SELECT setval('plan_workout_plan_workout_seq', max(plan_workout)) FROM plan_workout;
select setval('plan_workout_plan_workout_seq', 393);

