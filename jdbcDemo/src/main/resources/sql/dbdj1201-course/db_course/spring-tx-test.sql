CREATE TABLE tb_dept(
dept_id INT PRIMARY KEY auto_increment COMMENT '主键',dept_name varchar(20) COMMENT '部门名称',
dept_work varchar(100) COMMENT '部门业务'
);

SHOW tables;
CREATE TABLE tb_emp(
emp_id INT PRIMARY KEY auto_increment COMMENT '主键',
emp_name	VARCHAR(100) COMMENT '员工姓名',
emp_job VARCHAR(20) COMMENT '员工职位',
dept_id INT,
CONSTRAINT fk_dept_emp FOREIGN KEY(dept_id) REFERENCES tb_dept(dept_id)
);
SELECT * FROM dept;
UPDATE dept SET DNAME ='sss' WHERE LOC = 'NEW YORK';