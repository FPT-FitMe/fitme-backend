insert into app_user_role values
    (DEFAULT, 'Trainee'),
    (DEFAULT, 'Manager');

insert into tag values
    (DEFAULT, 'Chay'),
    (DEFAULT, 'Không chứa thịt'),
    (DEFAULT, 'Không tinh bột'),
    (DEFAULT, 'Bữa sáng'),
    (DEFAULT, 'Bữa trưa'),
    (DEFAULT, 'Bữa xế'),
    (DEFAULT, 'Bữa tối'),
    (DEFAULT, 'Cardio'),
    (DEFAULT, 'Toàn thân'),
    (DEFAULT, 'Khởi động'),
    (DEFAULT, 'Thư giãn'),
    (DEFAULT, 'Đốt calo nhanh'),
    (DEFAULT, 'Nâng sức mạnh'),
    (DEFAULT, 'Cho người bắt đầu');

insert into app_user values
    (DEFAULT, 18, 2, 'conmeo1@gmail.com', 2, 1, 182, 4, '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000001', 'fitmeUser', 2, 1),
    (DEFAULT, 18, 2, 'conmeo2@gmail.com', 2, 1, 182, 4, '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000003', 'conmeo', 2, 1),
    (DEFAULT, 40 , null, 'admin@fitme.vn', null , 1 , null , null, '$2y$12$A.ciYhmETo0aS3OMkbkvROfxNFXjVyyB2m2OGJX9/hwkjZNSSYoUu', '00000002', 'fitmeManager', 2, 2);

