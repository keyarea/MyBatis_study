/*
MySQL Backup
Database: mybatis_test
Backup Time: 2019-11-05 18:11:10
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `mybatis_test`.`t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacherName` varchar(100) DEFAULT NULL,
  `class_name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `mybatis_test`.`t_teacher` WRITE;
DELETE FROM `mybatis_test`.`t_teacher`;
INSERT INTO `mybatis_test`.`t_teacher` (`id`,`teacherName`,`class_name`,`address`,`birth_date`) VALUES (1, 'admin', '语文', '宝安区', '2018-11-13'),(2, 'tomcat', '数学', '福田区', '2010-10-07'),(3, 'jerry', '英语', '南山区', '2010-07-15');
UNLOCK TABLES;
COMMIT;
