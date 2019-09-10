/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 80017
Source Host           : 10.59.8.51:3306
Source Database       : docker-mybatis-demo

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-09-10 11:30:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangshan');
INSERT INTO `user` VALUES ('2', '李四');
