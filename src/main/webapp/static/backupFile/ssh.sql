/*
Navicat MySQL Data Transfer

Source Server         : zheng
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : ssh

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-03-08 09:20:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('14', '123', 'zheng');
INSERT INTO `user` VALUES ('15', 'fuck', '123');
INSERT INTO `user` VALUES ('16', 'fuck', '123');
INSERT INTO `user` VALUES ('17', 'fu', '12');
INSERT INTO `user` VALUES ('18', 'fu', '12');
INSERT INTO `user` VALUES ('19', 'fu', '12');
INSERT INTO `user` VALUES ('20', 'fu', '12');
INSERT INTO `user` VALUES ('22', 'zheng', '123456');
INSERT INTO `user` VALUES ('23', 'huicheng', '123456');
INSERT INTO `user` VALUES ('24', 'huicheng', '123456');
