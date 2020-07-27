#3,查询雇员姓名，根据其服务年限，将最老的雇员排在最前面； 
SELECT  ename, hiredate HIREDATE FROM emp ORDER BY hiredate;
#4,查询出在（任何年份的）2月受聘的所有雇员；要求采用两种方式实现，每种实现方式【日期函数，字符串函数】得分值一半
  #4.1日期函数
SELECT * FROM emp WHERE MONTH(hiredate) = 2;
	#4.2字符串函数
SELECT * FROM emp WHERE SUBSTR(SUBSTRING_INDEX(hiredate,'-',2),6) ='02';

#5，查询出所有的普通员工；提示：在EMP表中mgr字段的取值代表上级领导的员工编号 
SELECT * FROM emp WHERE empno NOT IN (SELECT e2.empno FROM emp e1, emp e2 WHERE e1.mgr = e2.empno);

#6, 求出每个部门的平均工资，及它的等级;
SELECT t1.deptno, t1.avgSal, t2.grade FROM (SELECT deptno , AVG(sal) avgSal FROM emp GROUP BY deptno ) t1 LEFT JOIN salgrade t2 ON t1.avgSal BETWEEN t2.losal AND t2.hisal;

#7,求平均薪水最高的部门的部门编号;

SELECT AVG(sal),deptno FROM emp GROUP BY deptno ;

SELECT t2.maxSal maxSal, t1.deptno FROM (SELECT deptno , AVG(sal) avgSal FROM emp GROUP BY deptno)  t1, (SELECT MAX(temp.avgSal) maxSal FROM (SELECT deptno , AVG(sal) avgSal FROM emp GROUP BY deptno ) temp) t2  WHERE t1.avgSal = t2.maxSal ;
-- 8,给任职日期超过35年的人加薪10%；
--    要求：
--       1. 使用jdbc.properties文件保存数据库连接的基本信息.
--       2. 使用c3p0或druid数据库连接池来获取Connection对象
--       3. 使用JDBC来实现员工加薪10%的操作，编写正确的SQL语句
--       4. 使用PreparedStatement对象执行SQL语句 
--       5. 关闭数据库资源 
--       6. 包名，类名及代码要符合Java命名规范
--       7.上传整个java工程

update emp set sal = sal * 1.1 WHERE empno = 7369;