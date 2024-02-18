CREATE DATABASE timeschedulers;
USE timeschedulers;

CREATE TABLE timeschedulers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(50),
    time VARCHAR(10),
    whattodo VARCHAR(50),
    monthly VARCHAR(20),
    weekly VARCHAR(20),
    daily VARCHAR(20)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=euckr;
INSERT INTO timeschedulers (subject, time, whattodo, monthly, weekly, daily) 
VALUES 
('자료구조', '9시', '복습', '8월', '1주', '월요일'),
('논리회로', '10시', '시험', '9월', '2주', '화요일'),
('컴퓨터구조', '11시', '실습', '10월', '3주', '수요일'),
('데이터베이스', '12시', '필기', '11월', '4주', '목요일'),
('전공영어기초', '13시', '과제', '12월', '5주', '금요일');