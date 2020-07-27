#  1、返回部门号及其本部门的最低工资。
SELECT
	DEPTNO,
	MIN( SAL ) 
FROM
	emp 
GROUP BY
	DEPTNO;# 2、计算出员工的年薪，并且以年薪排序。
SELECT
	ENAME,
	SAL * 12 AS allSal 
FROM
	emp 
ORDER BY
	allSal;--   3、返回员工工作及其从事此工作的最低工资。
SELECT
	t1.DEPTNO,
	t1.minSal,
	t2.DNAME 
FROM
	( SELECT DEPTNO, MIN( SAL ) minSal FROM emp GROUP BY DEPTNO ) t1,
	dept t2 
WHERE
	t1.DEPTNO = t2.DEPTNO;--  select job,min(sal) from emp group by job;
-- 
--   4、查找和scott从事相同工作的员工信息
SELECT
	* 
FROM
	emp 
WHERE
	JOB = ( SELECT JOB FROM emp WHERE ENAME = 'scott' );-- select * from emp where job = (select job from emp where ename =  'scott');
--   5、工资水平多于james的员工信息。
SELECT
	* 
FROM
	emp 
WHERE
	SAL > ( SELECT SAL FROM emp WHERE ENAME = 'james' );--  select * from emp where sal > (select sal from emp where ename = 'james');
-- 
--   6、返回工资大于平均工资的员工信息。
SELECT
	* 
FROM
	emp 
WHERE
	SAL > ( SELECT AVG( SAL ) FROM emp );-- select * from emp where sal > (select avg(sal) from emp);
-- 
--   7、返回销售部(sales)所有员工的姓名。
SELECT
	ENAME 
FROM
	emp 
WHERE
	DEPTNO = ( SELECT DEPTNO FROM dept WHERE DNAME = 'sales' );-- select ename from emp where deptno = ( select deptno from dept where dname = 'sales');
-- 
--   8、返回工资高于30部门所有员工工资水平的员工信息。
SELECT
	* 
FROM
	emp 
WHERE
	SAL > ( SELECT MAX( SAL ) FROM emp WHERE DEPTNO = 30 );
SELECT
	* 
FROM
	emp 
WHERE
	sal > ALL ( SELECT sal FROM emp WHERE deptno = 30 );-- 
--   9、返回查找最高工资和最低工资的职员信息
SELECT
	* 
FROM
	emp 
WHERE
	SAL = ( SELECT MAX( SAL ) FROM emp ) 
	OR SAL = ( SELECT MIN( SAL ) FROM emp );--  select emp.* from emp,(select min(sal) min_sal, max(sal) max_sal from emp where job='职员') sal where emp.job='职员' and (emp.sal=sal.min_sal or emp.sal=sal.max_sal);
-- 
-- 
--   10、返回拥有员工的部门名、部门号。
SELECT DISTINCT
	t1.DNAME,
	t1.DEPTNO 
FROM
	dept t1
	INNER JOIN emp t2 ON t1.DEPTNO = t2.DEPTNO;
SELECT DISTINCT
	dept.deptno,
	dept.dname 
FROM
	dept
	INNER JOIN emp ON dept.deptno = emp.deptno;-- 
--   11、返回员工的姓名、所在部门名及其工资。
SELECT
	t1.ename,
	t1.sal,
	t2.DNAME 
FROM
	emp t1,
	dept t2 
WHERE
	t1.DEPTNO = t2.DEPTNO;-- select emp.ename,dept.dname,emp.sal from emp,dept where emp.deptno=dept.deptno;
-- 
--   12、返回从事职员工作的员工姓名和所在部门名称。
SELECT
	t1.ENAME,
	t2.DNAME 
FROM
	( SELECT ENAME, DEPTNO FROM emp WHERE JOB = '职员' ) t1,
	dept t2 
WHERE
	t1.DEPTNO = t2.DEPTNO;--  select emp.ename,dept.dname from emp,dept where emp.deptno=dept.deptno and emp.job ='职员';
-- 
--   13、返回部门号、部门名、部门所在位置及其每个部门的员工总数。
SELECT
	t2.DEPTNO,
	t2.DNAME,
	t2.LOC,
	t1.counts 人数 
FROM
	( SELECT COUNT( 1 ) counts, DEPTNO FROM emp GROUP BY DEPTNO ) t1,
	dept t2 
WHERE
	t1.DEPTNO = t2.DEPTNO;-- select dept.deptno,dept.dname,dept.loc ,count(*)from dept,emp where dept.deptno=emp.deptno group by dept.deptno;
--   14、返回员工(职员或者销售员)和所属经理的姓名。
SELECT
	t1.ENAME 员工姓名,
	t2.ENAME 经理大名 
FROM
	emp t1,
	emp t2 
WHERE
	t1.MGR = t2.EMPNO;-- select * from emp where job in('职员','销售员');
-- select * from emp where job='经理';
-- select e1.ename,e2.ename from emp e1,(select empno,ename from emp where job='经理') e2 where e1.mgr=e2.empno;
-- 
--   15、返回员工(职员或者销售员)的入职日期早于其经理入职日期的员工及其经理姓名。
SELECT
	t1.ENAME 老员工了,
	t1.HIREDATE,
	t2.ENAME 肖经理,
	t2.HIREDATE 
FROM
	( SELECT ENAME, HIREDATE, MGR FROM emp WHERE JOB IN ( '职员', '销售员' ) ) t1,
	( SELECT HIREDATE, EMPNO, ENAME FROM emp WHERE JOB = '经理' ) t2 
WHERE
	t1.HIREDATE < t2.HIREDATE AND t1.MGR = t2.EMPNO; -- 	select empno,ename,hiredate from emp where job='经理';
-- select e1.ename,e2.ename, from emp e1,(select empno,ename,hiredate from emp where job='经理') e2 where e1.mgr=e2.empno and e1.hiredate<e2.hiredate;
-- 
-- 
--   16、返回工资处于第四级别的员工的姓名和工资。
SELECT ENAME, SAL FROM emp WHERE SAL >= ( SELECT LOSAL FROM salgrade WHERE GRADE = 4 ) 
AND SAL <= ( SELECT HISAL FROM salgrade WHERE GRADE = 4 );


SELECT
	emp.ename,
	emp.sal 
FROM
	emp,
	( SELECT losal, hisal FROM salgrade WHERE grade = 4 ) salgrade 
WHERE
	emp.sal BETWEEN salgrade.losal 
	AND salgrade.hisal;
--