/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : dev_schema

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2015-04-19 23:07:54
*/
-- CREATE DATABASE `dev_schema` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci  ;
-- USE dev_schema;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `Employee`
-- ----------------------------
DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `age` int(11) NOT NULL,
  `mail` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Employee
-- ----------------------------
INSERT INTO `Employee` VALUES ('1', '店小二', '18', 'dianxiaoer@126.com');
INSERT INTO `Employee` VALUES ('2', '店小三', '19', 'dianxiaosan@163.com');
