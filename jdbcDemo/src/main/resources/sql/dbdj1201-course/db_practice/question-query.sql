-- 1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数
EXPLAIN SELECT
	t4.*,
	t3.score01,
	t3.score02 
FROM
	(
SELECT
	t1.S,
	t1.score score01,
	t2.score score02 
FROM
	sc t1,
	sc t2 
WHERE
	t1.S = t2.S 
	AND t1.C = '01' 
	AND t2.C = '02' 
	AND t1.score < t2.score 
	) t3,
	student t4 
WHERE
	t3.S = t4.S;
EXPLAIN SELECT
	a.*,
	b.score AS '01分数',
	c.score AS '02分数' 
FROM
	student a
	INNER JOIN sc b ON a.s = b.s 
	AND b.c = '01'
	INNER JOIN sc c ON a.s = c.s 
	AND c.c = '02' 
WHERE
	b.score > c.score;-- 2、查询"01"课程比"02"课程成绩低的学生的信息及课程分数
-- ?
-- 3、查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
EXPLAIN SELECT
	t2.S,
	t1.Sname,
	AVG( IFNULL( score, 0 ) ) averageScore 
FROM
	student t1
	INNER JOIN sc t2 ON t1.s = t2.s 
GROUP BY
	t1.S,
	t1.Sname 
HAVING
	AVG( IFNULL( t2.score, 0 ) ) >= 60;
EXPLAIN SELECT
	a.s,
	a.sname,
	AVG( b.score ) AS avgnum 
FROM
	student a
	INNER JOIN sc b ON a.s = b.s 
GROUP BY
	a.s,
	a.sname 
HAVING
	AVG( b.score ) > 60;-- 4、查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩
-- ?
-- 5、查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩
SELECT
	t1.S,
	t1.scores,
	t1.courses,
	t2.Sname 
FROM
	( SELECT SUM( score ) scores, count( C ) courses, S FROM sc GROUP BY S ) t1,
	student t2 
WHERE
	t1.S = t2.S;-- 6、查询"李"姓老师的数量
SELECT
	COUNT( 1 ) 
FROM
	teacher 
WHERE
	Tname LIKE '李%';-- 7、查询学过"张三"老师授课的同学的信息
SELECT
	t6.* 
FROM
	student t6,
	(
SELECT
	S 
FROM
	sc t1,
	( SELECT C FROM ( SELECT T FROM teacher WHERE Tname = '张三' ) t2, course t3 WHERE t2.T = t3.T ) t4 
WHERE
	t1.C = t4.C 
	) t5 
WHERE
	t5.S = t6.S;
SELECT
	a.* 
FROM
	student a
	INNER JOIN sc b ON a.s = b.s
	INNER JOIN course c ON b.c = c.c
	INNER JOIN teacher d ON c.t = d.t 
WHERE
	d.tname = '张三' 
GROUP BY
	1,
	2,
	3,
	4;
-- 8、查询没学过"张三"老师授课的同学的信息
EXPLAIN SELECT
	a.* 
FROM
	student a
	LEFT JOIN sc b ON a.s = b.s 
WHERE
	NOT EXISTS (
SELECT
	* 
FROM
	course aa
	INNER JOIN teacher b ON aa.t = b.t
	INNER JOIN sc c ON aa.c = c.c 
WHERE
	b.tname = '张三' 
	AND c.s = a.s 
	) 
GROUP BY
	1,
	2,
	3,
	4;