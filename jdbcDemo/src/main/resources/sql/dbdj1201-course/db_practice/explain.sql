CREATE TABLE IF NOT EXISTS article(
`id` INT UNSIGNED NOT NULL PRIMARY KEY auto_increment,
`author_id` INT UNSIGNED NOT NULL,
`category_id` INT UNSIGNED NOT null,
`views` INT UNSIGNED NOT NULL,
`comments` INT UNSIGNED NOT NULL,
`title` VARCHAR(255) NOT NULL,
`content` LONGTEXT NOT NULL
);

INSERT INTO `article`(`author_id`,`category_id`,`views`,`comments`, `title`,`content`) VALUES
(1,1,1,1,'1','1'),
(2,2,2,2,'2','2'),
(3,3,3,3,'3','3');

SELECT * FROM article;
EXPLAIN  SELECT t3.* FROM (SELECT MAX(views) maxViews FROM (SELECT * FROM article WHERE category_id = 1 AND comments >1) t1 ) t2 INNER JOIN article t3 ON t3.views = t2.maxViews;

EXPLAIN SELECT id, author_id FROM article WHERE category_id =1 AND comments >1 ORDER BY views DESC LIMIT 1;
SHOW INDEX FROM article;
CREATE INDEX  idx_article_ccv ON article(category_id, comments,views) ;
CREATE INDEX idv_article_cv ON article(category_id, views);
DROP INDEX  idx_article_ccv ON article;

# ================================================================================================
CREATE TABLE class
(
	id INT UNSIGNED NOT NULL auto_increment PRIMARY KEY ,
	card int UNSIGNED not NULL
);

CREATE TABLE book(
bookid INT UNSIGNED NOT NULL auto_increment PRIMARY key,
card INT UNSIGNED NOT NULL
);

SELECT * FROM class;

TRUNCATE class;

SELECT * FROM book INNER JOIN class ON book.card != class.card AND book.bookid = class.id;


EXPLAIN SELECT * FROM class RIGHT JOIN book ON class.card = book.card;

ALTER TABLE book ADD INDEX idx_book (card);
DROP INDEX idx_book on 	book;
ALTER TABLE class ADD INDEX idx_class (card);
-- ########################################################################################### --
CREATE TABLE phone(
phoneid INT UNSIGNED NOT NULL auto_increment PRIMARY KEY,
card INT UNSIGNED NOT NULL
);

SELECT * FROM phone;

SHOW INDEX FROM class;

DROP INDEX idx_class ON class;
CREATE INDEX idx_class ON class(card);
CREATE INDEX idx_phone ON phone(card);
EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card LEFT JOIN phone ON book.card = phone.card;
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

CREATE TABLE staffs(
id INT PRIMARY KEY auto_increment,
`name` VARCHAR(24) NOT NULL DEFAULT '' COMMENT '姓名',
age INT NOT NULL DEFAULT 0 COMMENT '年龄',
pos VARCHAR(20) NOT NULL DEFAULT '' COMMENT '职位',
add_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间'
 ) COMMENT '员工记录表';

ALTER TABLE staffs ADD INDEX idx_staffs_nameAgePos (name, age, pos);
SELECT * FROM staffs;

SHOW INDEX FROM staffs;
EXPLAIN SELECT * FROM staffs WHERE age = 29 AND  NAME = 'dbdj1201-8347' AND pos = 'dev' ;
EXPLAIN SELECT * FROM staffs WHERE age = '29' AND pos = 'dev';

EXPLAIN SELECT * FROM staffs WHERE `name` ='dbdj1201-8347'  OR age = 25;
ALTER TABLE staffs ADD INDEX idx_staffs_name(`name`);

EXPLAIN SELECT * FROM staffs	 WHERE `name`	 LIKE '555%22%';



# =================================================================================================================
CREATE TABLE tbl_user(
id INT NOT NULL auto_increment PRIMARY KEY,
`name` VARCHAR(20) DEFAULT null,
age INT DEFAULT NULL,
email VARCHAR(29) DEFAULT NULL
);
SELECT * FROM tbl_user;
SHOW INDEX FROM tbl_user;
EXPLAIN SELECT `name`, age FROM tbl_user WHERE `name` LIKE '%aa%';

EXPLAIN SELECT `name` FROM tbl_user WHERE `name` = 1233;

DROP INDEX idx_user_nameAgeEmail ON tbl_user;

CREATE INDEX idx_user_nameAge ON tbl_user(`name`, age);
CREATE INDEX idx_user_nameAgeEmail ON tbl_user(`name`, age, email);

# ======================================================================================================================
CREATE TABLE test03(
id INT PRIMARY KEY NOT NULL auto_increment,
c1 char(10),
c2 char(10),
c3 char(10),
c4 char(10),
c5 char(10)
);

SELECT * FROM test03;

CREATE INDEX idx_test03_c1234 ON test03(c1,c2,c3,c4);

SHOW INDEX FROM test03;

EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c2 ='a2' AND c3 = 'a3' AND c4= 'a4';

EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c2 ='a2' AND c4 = 'a4' AND c3= 'a3';

EXPLAIN SELECT * FROM test03 WHERE c4 = 'a4' AND c3 ='a3' AND c2 = 'a2' AND c1= 'a1';

EXPLAIN SELECT * FROM test03 WHERE c4 = 'a4' AND c3 = 'a3' AND c2 > 'a2' AND c1= 'a1';

EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c2 ='a2' AND c3 > 'a3' AND c4= 'a4';
# c3其实也用到了，但是是排序而不是查找，所以没统计进key_len ,c4 断了
EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c2 ='a2' AND c4= 'a4' ORDER BY c3;
# c3 排序去了
EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c2 = 'a2' ORDER BY c3;

EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c2 = 'a2'  ORDER BY c4;

EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c5 = 'a5' ORDER BY c2, c3;

EXPLAIN SELECT * FROM test03 WHERE c5 = 'c5';
# order by 中间乱，filesort来见面
EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c5 = 'a5' ORDER BY c3, c2;

EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c2 = 'a2' ORDER BY c2, c3;
# 排序字段c2已经是常量了
EXPLAIN SELECT * FROM test03 WHERE c1 = 'a1' AND c2 = 'a2' AND c5 = 'c5' ORDER BY c3，c2;

EXPLAIN SELECT c2,c3 FROM test03 WHERE c1 = 'a1' AND c3 = 'a3' GROUP BY c3, c2;

# ******************* ???? 为啥是using index？？？？？？？？还是因为排序字段有个常量*************************
EXPLAIN SELECT c2, c4 FROM test03 WHERE c1 = 'a1' AND c4 = 'a4' GROUP BY  c4, c2 ;



