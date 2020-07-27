CREATE DATABASE db_case_user;
USE db_case_user;
CREATE TABLE tb_user(
id INT PRIMARY KEY auto_increment,
`name` VARCHAR(100),
gender VARCHAR(2),
age int,
address VARCHAR(100),
qq VARCHAR(20),
email VARCHAR(60),
username VARCHAR(100),
`password` VARCHAR(100)
);

INSERT INTO tb_user VALUES(1,'test','m',1,'sadasd','23523532','14141@21412.com','test','test');

DROP TABLE tb_user;

SELECT * FROM tb_user LIMIT 0,5;
DELETE FROM tb_user WHERE id=102;
SELECT COUNT(1) FROM tb_user;
select * from tb_user where 1 = 1  and name LIKE '%s%' AND address LIKE '%w%' AND email LIKE '%1%' LIMIT 0,50;
