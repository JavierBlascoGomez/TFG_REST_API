-- Insert mock data into public."user"
INSERT INTO public."user" (id, birth_date, email, name, password_hash, surnames, username) VALUES
                                                                                                     (1, '2000-09-25', 'bluemood_admin@bluemoon.com', 'Project', '$2y$10$mYRmCJmc5jcD2dniHqJISOfQRqTXCayVibX/t2kynDPyL0X0cUOpy', 'Admin', 'project_admin'),
                                                                                                     (2, '2000-08-24', 'user2@example.com', 'User 2', 'hash2', 'Surname 2', 'username2'),
                                                                                                     (3, '2000-07-23', 'user3@example.com', 'User 3', 'hash3', 'Surname 3', 'username3');

INSERT INTO public."roles" (id, name) VALUES (1, 'ADMIN');

INSERT INTO public."users_roles" (user_id, role_id) VALUES (1,1);

-- Insert mock data into public."tfg_register"
INSERT INTO public."tfg_register" (id, date, serum_creatinine, result, user_id) VALUES
                                                            (1, '2024-01-30 09:30:00', 1.2, 8.4, 1),
                                                            (2, '2024-02-01 11:15:00', 1.3, 3.4, 2),
                                                            (3, '2024-02-02 15:00:00', 1.1, 4.6, 3);