INSERT INTO app_user (email, name, password) VALUES
                                                 ('bob.johnson@example.com', 'Bob Johnson', '$2a$10$5rbjIdGOCX4Oj1VfNE1tQOXpWi/FZ2bOEqZ3c5h13EIV6P/65eT62'),
                                                 ('alice.smith@example.com', 'Alice Smith', '$2a$10$T1IsEHt5ZP/O1tXvB/e.fO19Mi8hG4ELKflSHH/5.jR2S9Kg/K22'),
                                                 ('carol.davis@example.com', 'Carol Davis', '$2a$10$TEIziM9LByxwh0A6KcV/.9T2i5fXERk6aYlOXr25m3sC.GO7ZgL/C'),
                                                 ('dave.miller@example.com', 'Dave Miller', '$2a$10$7Rm7cfI8IW8SwN2EZXHfX.6zsyoyke3Yz/i7Yf.yx4rmZ9.yQyS0G'),
                                                 ('eve.wilson@example.com', 'Eve Wilson', '$2a$10$ZR4eYy39DZs74FbWdkSleuBf5prgOM.COwq4e5d72PV2XTK6KK7au'),
                                                 ('frank.moore@example.com', 'Frank Moore', '$2a$10$3SOvFz1mPwrjf3CZFsFO9upn8/XUk4WnhKKqNQ46ZjRyT9TA8WzBi'),
                                                 ('grace.lee@example.com', 'Grace Lee', '$2a$10$3WS6NGy3ZmReD5xOsCOt3u0WrpSy6Zzhm6x5K9I2B4k5IR6C4OZiC'),
                                                 ('henry.taylor@example.com', 'Henry Taylor', '$2a$10$Cj/UICzL1ihOpxi7V3Jd8.JgDhUgtzF8czOgl9N9F2ukgJSxPZ7PO'),
                                                 ('isabella.jackson@example.com', 'Isabella Jackson', '$2a$10$8g5iOF5vxeuIY7mGnSZvAOxy/LQWmUV7lhEwFdG7Ri2kHxN9USkqK'),
                                                 ('jackson.harris@example.com', 'Jackson Harris', '$2a$10$RP4SxA2nnk.DdXrbgdO8xO/hR9uvHZb/4qI5Vlm7smF/eZ53vppSK'),
                                                 ('karen.clark@example.com', 'Karen Clark', '$2a$10$ggJ8wwOjqTx9cRhmlgY6pOcNsM6rfEXe2H4tBoGG8I5peIldedDAq'),
                                                 ('liam.martin@example.com', 'Liam Martin', '$2a$10$5J8EjS.Oxv8rQFpfS2ieZO5MfnFhQqFZqJ6XzFpxeLkeb/f1Lf9dO'),
                                                 ('mona.wilson@example.com', 'Mona Wilson', '$2a$10$u8MSz6U3APY7S/xKPRZ5uO32Mv/HXtU0Rk8oaBthZkp5Y4OXM3P/.u'),
                                                 ('nora.martin@example.com', 'Nora Martin', '$2a$10$EYvsqErLmjJ5I5txW7D3OeTVK8ZwhFsXnL09BOJwMlT0neI7ADYPK'),
                                                 ('oliver.jones@example.com', 'Oliver Jones', '$2a$10$B8p0ZB7XEepI9ZpRWQ/kdP9GbvvjOsyh0HvvYTOmC7uNLo9iE8OuW'),
                                                 ('peter.white@example.com', 'Peter White', '$2a$10$T7JHa2b0NkzR/J4seX46BOQ8D.tL88rkUBr7uhDC0FJHzJxFHRsEi'),
                                                 ('quinn.moore@example.com', 'Quinn Moore', '$2a$10$TH61Kj1G9NBspb76YjxoXeBXzn/qDb68wL28DDdpDrMOvzdN3hO4u'),
                                                 ('rachel.lee@example.com', 'Rachel Lee', '$2a$10$6whN09lK4Q5kMVs6eWIjuOgLQoTWpwi1L.TG6nElVGg3L7dn6Fe6i'),
                                                 ('samuel.davis@example.com', 'Samuel Davis', '$2a$10$0g75Xybr6L2pF.t2lD8niOCeX6yKo4cZOW63lILowV7XaPftDlB2G'),
                                                 ('tina.johnson@example.com', 'Tina Johnson', '$2a$10$QYFmwoSe7ZVEuIZ7TEfwq.GxRvUbBSrx/O14DzfKPiz7Tz4IjOziO'),
                                                 ('uriel.smith@example.com', 'Uriel Smith', '$2a$10$EwU53RCrGTU8zzd.82LeN.OuLAGyvhv8dqom/xgWkYekWcboc/YQi');


INSERT INTO user_role(user_id, role) VALUES
                                            (1, 'RIDER'),
                                            (2, 'RIDER'),
                                            (3, 'DRIVER'),
                                            (4, 'RIDER'),
                                            (5, 'DRIVER'),
                                            (6, 'RIDER'),
                                            (7, 'DRIVER'),
                                            (8, 'RIDER'),
                                            (9, 'RIDER'),
                                            (10, 'DRIVER'),
                                            (11, 'RIDER'),
                                            (12, 'DRIVER'),
                                            (13, 'RIDER'),
                                            (14, 'DRIVER'),
                                            (15, 'RIDER'),
                                            (16, 'DRIVER'),
                                            (17, 'RIDER'),
                                            (18, 'DRIVER'),
                                            (19, 'RIDER'),
                                            (20, 'RIDER');

INSERT INTO rider(user_id,rating) VALUES
(1,4.9);


INSERT INTO driver (user_id, rating, available, vehicle_id, current_location) VALUES
                                                                                      ( 1, 4.5, true, 'V001', ST_GeomFromText('POINT(44.0094 36.1911)')),  -- Center of Erbil, Iraq
                                                                                      ( 2, 3.9, true, 'V002', ST_GeomFromText('POINT(44.0120 36.1890)')), -- Near Erbil Citadel
                                                                                      ( 3, 4.2, true, 'V003', ST_GeomFromText('POINT(44.0220 36.1920)')),  -- Erbil International Airport area
                                                                                      ( 4, 4.8, true, 'V004', ST_GeomFromText('POINT(44.0070 36.1800)')),  -- Sami Abdulrahman Park
                                                                                      ( 5, 3.5, true, 'V005', ST_GeomFromText('POINT(44.0130 36.1830)')), -- Erbil Tower
                                                                                      ( 6, 4.0, true, 'V006', ST_GeomFromText('POINT(44.0170 36.1970)')),  -- Family Mall area
                                                                                      ( 7, 4.3, true, 'V007', ST_GeomFromText('POINT(44.0050 36.1950)')),  -- Naza Mall area
                                                                                      ( 8, 4.6, true, 'V008', ST_GeomFromText('POINT(44.0200 36.1840)')),  -- Erbil Citadel Museum
                                                                                      ( 9, 3.8, true, 'V009', ST_GeomFromText('POINT(44.0160 36.1920)')), -- Qaysari Bazaar
                                                                                      ( 10, 4.1, true, 'V010', ST_GeomFromText('POINT(44.0100 36.1900)')), -- Shani Restaurant
                                                                                      ( 11, 4.7, true, 'V011', ST_GeomFromText('POINT(44.0060 36.1880)')), -- Erbil Sports Club
                                                                                      ( 12, 3.9, true, 'V012', ST_GeomFromText('POINT(44.0180 36.1750)')), -- English Village
                                                                                      ( 13, 4.2, true, 'V013', ST_GeomFromText('POINT(44.0110 36.1960)')), -- Tanjero Park
                                                                                      ( 14, 4.0, true, 'V014', ST_GeomFromText('POINT(44.0140 36.1870)')), -- Minaretn Mosque
                                                                                      ( 15, 3.7, true, 'V015', ST_GeomFromText('POINT(44.0190 36.1800)')), -- Majidi Mall
                                                                                      ( 16, 4.4, true, 'V016', ST_GeomFromText('POINT(44.0070 36.1930)')), -- Mazi Mall
                                                                                      ( 17, 4.1, true, 'V017', ST_GeomFromText('POINT(44.0120 36.1820)')), -- Koya University
                                                                                      ( 18, 4.5, true, 'V018', ST_GeomFromText('POINT(44.0080 36.1910)')), -- Iraqi Kurdistan Parliament
                                                                                      ( 19, 3.6, true, 'V019', ST_GeomFromText('POINT(44.0150 36.1850)')), -- Bazar Restaurant
                                                                                      ( 20, 4.2, true, 'V020', ST_GeomFromText('POINT(44.0200 36.1870)')); -- Erbil University

INSERT INTO wallet (user_id, balance) VALUES  (1,100), (3, 500);
