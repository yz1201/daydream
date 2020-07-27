SELECT * FROM emp WHERE DEPTNO = '10';
CREATE TABLE tb_user ( 
user_id BIGINT PRIMARY KEY  auto_increment,
username VARCHAR(100),
`PASSWORD` VARCHAR(40)
);
ALTER TABLE tb_user CHANGE id user_id PRIMARY KEY auto_increment;
DROP TABLE tb_user;

INSERT INTO tb_user(username, `password`) VALUES('root','root');
INSERT INTO tb_user(username, `password`) VALUES('root1','root1');
INSERT INTO tb_user(username, `password`) VALUES('root2','root2');
INSERT INTO tb_user(username, `password`) VALUES('root3','root3');