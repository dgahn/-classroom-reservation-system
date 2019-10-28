INSERT INTO account (account_id, id, password, name, email, role) VALUES
(1000, 'a2010201', '$2a$10$MjpTZ5Aw0Ws0BsOsf07iFu9Abe6gS8TKC.Ic/rGpgFXM5oLuQZwnm', '안덕기1', 'a2010201@naver.com', 'ADMIN'),
(1001, 'a2010202', '$2a$10$MjpTZ5Aw0Ws0BsOsf07iFu9Abe6gS8TKC.Ic/rGpgFXM5oLuQZwnm', '안덕기2', 'a2010202@naver.com', 'BASIC'),
(1002, 'a2010203', '$2a$10$MjpTZ5Aw0Ws0BsOsf07iFu9Abe6gS8TKC.Ic/rGpgFXM5oLuQZwnm', '안덕기3', 'a2010203@naver.com', 'BASIC'),
(1003, 'a2010204', '$2a$10$MjpTZ5Aw0Ws0BsOsf07iFu9Abe6gS8TKC.Ic/rGpgFXM5oLuQZwnm', '안덕기4', 'a2010204@naver.com', 'BASIC')
;

INSERT INTO classroom (classroom_id, capacity, floor, number) VALUES
(1000, 30, 1, '102호'),
(1001, 30, 1, '103호'),
(1002, 30, 1, '104호'),
(1003, 30, 1, '105호')
;

INSERT INTO reservation (reservation_id, account_id, classroom_id, start_date, end_date, reservation_status, title) VALUES
(1000, 1000, 1000, now(), DATEADD('MINUTE', 30, now()), 'APPROVAL', '강의 1');

INSERT INTO account_reservation(account_reservation_id, account_id, reservation_id) VALUES
(1000, 1000, 1000),
(1001, 1001, 1000),
(1002, 1002, 1000),
(1003, 1003, 1000);

INSERT INTO reservation (reservation_id, account_id, classroom_id, start_date, end_date, reservation_status, title) VALUES
(1001, 1000, 1000, DATEADD('MINUTE', 30, now()), DATEADD('HOUR', 1, now()), 'APPROVAL', '강의 2');

INSERT INTO account_reservation(account_reservation_id, account_id, reservation_id) VALUES
(1004, 1000, 1001),
(1005, 1001, 1001),
(1006, 1002, 1001),
(1007, 1003, 1001);



