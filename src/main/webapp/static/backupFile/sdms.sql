/*
Navicat MySQL Data Transfer

Source Server         : zheng
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : sdms

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-03-08 13:19:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_num` varchar(20) NOT NULL,
  `name` varchar(200) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '0003', 'zheng', '7CD86ECB09AA48C6E620B340F6A74592', '18813757484', '0');
INSERT INTO `admin` VALUES ('2', '0002', 'zheng', 'FCD04E26E900E94B9ED6DD604FED2B64', '18813757484', '0');
INSERT INTO `admin` VALUES ('3', '0001', 'zheng', 'A906449D5769FA7361D7ECC6AA3F6D28', '18813757482', '1');

-- ----------------------------
-- Table structure for admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_login_log`;
CREATE TABLE `admin_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_time` datetime NOT NULL,
  `ip` varchar(15) NOT NULL,
  `admin_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=661 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_login_log
-- ----------------------------
INSERT INTO `admin_login_log` VALUES ('12', '2017-01-02 15:09:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('13', '2017-01-02 15:10:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('14', '2017-01-02 15:31:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('15', '2017-01-02 15:36:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('16', '2017-01-02 15:40:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('17', '2017-01-02 16:00:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('18', '2017-01-02 16:04:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('19', '2017-01-02 16:08:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('20', '2017-01-02 16:11:14', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('21', '2017-01-02 16:12:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('22', '2017-01-02 16:17:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('23', '2017-01-02 16:19:53', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('24', '2017-01-02 16:45:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('25', '2017-01-02 16:48:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('26', '2017-01-02 16:52:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('27', '2017-01-02 16:53:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('28', '2017-01-03 10:26:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('29', '2017-01-03 10:29:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('30', '2017-01-03 10:55:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('31', '2017-01-03 10:57:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('32', '2017-01-03 14:56:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('33', '2017-01-03 14:58:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('34', '2017-01-03 15:12:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('35', '2017-01-03 16:08:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('36', '2017-01-03 16:50:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('37', '2017-01-04 08:36:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('38', '2017-01-04 08:41:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('39', '2017-01-04 08:44:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('40', '2017-01-04 08:52:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('41', '2017-01-04 09:04:02', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('42', '2017-01-04 09:05:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('43', '2017-01-04 09:14:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('44', '2017-01-04 09:36:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('45', '2017-01-04 09:37:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('46', '2017-01-04 10:08:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('47', '2017-01-04 10:10:00', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('48', '2017-01-04 10:16:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('49', '2017-01-04 13:56:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('50', '2017-01-04 14:09:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('51', '2017-01-04 14:19:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('52', '2017-01-04 14:21:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('53', '2017-01-04 14:23:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('54', '2017-01-04 14:57:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('55', '2017-01-04 15:10:21', '0:0:0:0:0:0:0:1', '3');
INSERT INTO `admin_login_log` VALUES ('56', '2017-01-04 15:18:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('57', '2017-01-04 15:27:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('58', '2017-01-04 15:38:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('59', '2017-01-04 15:47:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('60', '2017-01-04 16:10:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('61', '2017-01-06 11:56:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('62', '2017-01-09 11:24:57', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('63', '2017-01-09 14:55:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('64', '2017-01-09 14:55:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('65', '2017-01-10 11:36:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('66', '2017-01-10 11:46:46', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('67', '2017-01-10 11:53:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('68', '2017-01-10 11:57:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('69', '2017-01-10 12:03:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('70', '2017-01-10 12:06:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('71', '2017-01-10 13:17:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('72', '2017-01-10 13:18:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('73', '2017-01-10 13:21:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('74', '2017-01-10 13:24:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('75', '2017-01-10 13:27:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('76', '2017-01-10 13:30:17', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('77', '2017-01-10 13:54:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('78', '2017-01-10 16:07:00', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('79', '2017-01-10 16:56:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('80', '2017-01-10 16:57:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('81', '2017-01-10 20:19:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('82', '2017-01-10 20:50:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('83', '2017-01-10 21:25:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('84', '2017-01-11 10:35:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('85', '2017-01-11 12:02:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('86', '2017-01-11 12:06:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('87', '2017-01-11 13:18:24', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('88', '2017-01-11 16:11:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('89', '2017-01-11 16:16:36', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('90', '2017-01-11 16:28:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('91', '2017-01-11 18:10:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('92', '2017-01-11 18:59:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('93', '2017-01-12 11:35:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('94', '2017-01-12 15:23:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('95', '2017-01-12 15:32:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('96', '2017-01-12 15:32:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('97', '2017-01-12 16:30:53', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('98', '2017-01-12 16:30:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('99', '2017-01-12 16:32:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('100', '2017-01-12 21:14:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('101', '2017-01-12 23:15:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('102', '2017-01-13 10:16:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('103', '2017-01-13 12:44:53', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('104', '2017-01-13 14:22:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('105', '2017-01-13 15:08:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('106', '2017-01-13 22:09:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('107', '2017-01-13 22:33:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('108', '2017-01-13 23:25:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('109', '2017-01-14 14:34:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('110', '2017-01-14 14:54:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('111', '2017-01-14 15:01:46', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('112', '2017-01-14 15:54:57', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('113', '2017-01-14 15:56:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('114', '2017-01-14 18:31:38', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('115', '2017-01-14 18:40:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('116', '2017-01-14 20:39:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('117', '2017-01-14 20:44:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('118', '2017-01-14 21:04:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('119', '2017-01-14 21:37:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('120', '2017-01-14 23:06:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('121', '2017-01-14 23:13:14', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('122', '2017-01-14 23:15:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('123', '2017-01-14 23:36:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('124', '2017-01-14 23:52:24', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('125', '2017-01-14 23:59:14', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('126', '2017-01-15 10:56:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('127', '2017-01-15 11:59:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('128', '2017-01-15 15:19:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('129', '2017-01-15 16:53:55', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('130', '2017-01-15 18:00:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('131', '2017-01-15 20:20:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('132', '2017-01-15 20:57:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('133', '2017-01-15 21:01:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('134', '2017-01-15 21:07:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('135', '2017-01-15 23:01:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('136', '2017-01-16 10:01:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('137', '2017-01-16 10:09:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('138', '2017-01-16 10:29:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('139', '2017-01-16 10:41:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('140', '2017-01-16 10:50:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('141', '2017-01-16 14:08:00', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('142', '2017-01-16 15:23:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('143', '2017-01-16 15:54:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('144', '2017-01-16 18:27:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('145', '2017-01-17 14:44:48', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('146', '2017-01-17 18:33:24', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('147', '2017-01-17 20:37:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('148', '2017-01-18 08:52:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('149', '2017-01-18 09:07:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('150', '2017-01-18 10:25:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('151', '2017-01-18 11:08:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('152', '2017-01-18 12:43:14', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('153', '2017-01-18 13:31:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('154', '2017-01-18 13:43:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('155', '2017-01-18 22:22:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('156', '2017-01-19 10:05:53', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('157', '2017-01-19 10:17:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('158', '2017-01-19 10:23:32', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('159', '2017-01-19 11:01:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('160', '2017-01-19 12:01:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('161', '2017-01-19 14:20:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('162', '2017-01-19 14:43:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('163', '2017-01-19 14:54:04', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('164', '2017-01-19 14:55:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('165', '2017-01-19 15:04:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('166', '2017-01-19 16:06:36', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('167', '2017-01-19 17:59:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('168', '2017-01-19 22:12:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('169', '2017-01-19 22:44:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('170', '2017-01-19 23:04:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('171', '2017-01-20 09:13:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('172', '2017-01-20 10:10:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('173', '2017-01-20 10:13:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('174', '2017-01-20 11:38:32', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('175', '2017-01-20 14:32:46', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('176', '2017-01-20 14:57:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('177', '2017-01-20 14:59:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('178', '2017-01-20 15:03:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('179', '2017-01-20 15:28:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('180', '2017-01-20 15:28:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('181', '2017-01-20 15:29:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('182', '2017-01-20 15:46:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('183', '2017-01-20 16:18:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('184', '2017-01-20 17:25:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('185', '2017-01-20 18:25:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('186', '2017-01-20 18:25:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('187', '2017-01-20 20:25:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('188', '2017-01-20 21:39:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('189', '2017-01-20 21:43:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('190', '2017-01-20 21:43:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('191', '2017-01-20 21:46:24', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('192', '2017-01-20 21:49:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('193', '2017-01-20 21:51:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('194', '2017-01-20 22:07:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('195', '2017-01-21 10:15:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('196', '2017-01-21 11:00:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('197', '2017-01-21 11:05:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('198', '2017-01-21 11:21:46', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('199', '2017-01-21 11:32:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('200', '2017-01-21 14:17:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('201', '2017-01-21 16:44:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('202', '2017-01-21 16:46:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('203', '2017-01-21 19:45:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('204', '2017-01-21 22:35:17', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('205', '2017-01-21 22:44:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('206', '2017-01-21 22:48:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('207', '2017-01-22 09:57:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('208', '2017-01-22 12:51:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('209', '2017-01-22 13:01:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('210', '2017-01-22 13:04:00', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('211', '2017-01-22 14:18:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('212', '2017-01-22 14:22:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('213', '2017-01-22 14:26:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('214', '2017-01-22 14:31:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('215', '2017-01-22 14:37:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('216', '2017-01-22 15:57:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('217', '2017-01-22 16:55:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('218', '2017-01-22 16:58:02', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('219', '2017-01-22 21:50:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('220', '2017-01-22 22:08:36', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('221', '2017-01-22 22:08:36', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('222', '2017-01-22 22:34:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('223', '2017-01-22 23:13:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('224', '2017-01-22 23:21:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('225', '2017-01-22 23:28:46', '192.168.52.1', '4');
INSERT INTO `admin_login_log` VALUES ('226', '2017-01-23 20:10:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('227', '2017-01-23 20:45:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('228', '2017-01-23 20:47:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('229', '2017-01-23 20:51:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('230', '2017-01-23 21:15:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('231', '2017-01-23 21:16:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('232', '2017-01-23 21:19:14', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('233', '2017-01-23 21:20:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('234', '2017-01-23 21:22:48', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('235', '2017-01-23 21:26:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('236', '2017-01-23 21:29:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('237', '2017-01-23 21:33:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('238', '2017-01-23 21:35:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('239', '2017-01-23 21:37:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('240', '2017-01-23 21:46:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('241', '2017-01-23 21:49:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('242', '2017-01-23 21:50:53', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('243', '2017-01-23 21:59:59', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('244', '2017-01-23 22:09:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('245', '2017-01-23 22:14:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('246', '2017-01-23 23:15:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('247', '2017-01-23 23:20:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('248', '2017-01-23 23:23:24', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('249', '2017-01-24 09:46:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('250', '2017-01-24 10:36:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('251', '2017-01-24 10:38:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('252', '2017-01-24 11:21:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('253', '2017-01-24 11:38:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('254', '2017-01-24 11:43:53', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('255', '2017-01-24 13:36:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('256', '2017-01-24 18:20:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('257', '2017-01-24 21:57:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('258', '2017-01-24 22:05:17', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('259', '2017-01-24 22:06:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('260', '2017-01-24 23:58:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('261', '2017-01-25 08:39:04', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('262', '2017-01-25 09:31:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('263', '2017-01-25 10:09:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('264', '2017-01-25 10:36:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('265', '2017-01-25 10:42:02', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('266', '2017-01-25 10:49:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('267', '2017-01-25 10:53:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('268', '2017-01-25 12:40:38', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('269', '2017-01-25 20:18:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('270', '2017-01-25 20:34:17', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('271', '2017-01-25 20:34:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('272', '2017-01-25 20:54:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('273', '2017-01-25 20:58:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('274', '2017-01-25 21:03:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('275', '2017-01-25 21:10:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('276', '2017-01-25 21:13:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('277', '2017-01-25 21:24:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('278', '2017-01-25 21:33:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('279', '2017-01-25 21:36:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('280', '2017-01-25 21:41:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('281', '2017-01-25 21:52:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('282', '2017-01-25 21:57:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('283', '2017-01-25 22:41:04', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('284', '2017-01-25 22:45:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('285', '2017-01-30 09:44:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('286', '2017-01-30 15:47:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('287', '2017-01-30 15:56:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('288', '2017-01-30 16:03:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('289', '2017-01-30 16:08:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('290', '2017-01-30 16:17:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('291', '2017-01-30 16:19:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('292', '2017-01-30 16:29:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('293', '2017-01-30 16:49:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('294', '2017-01-30 17:42:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('295', '2017-01-30 17:49:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('296', '2017-01-30 17:52:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('297', '2017-01-30 18:10:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('298', '2017-01-30 20:52:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('299', '2017-01-31 10:10:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('300', '2017-01-31 10:50:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('301', '2017-01-31 10:52:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('302', '2017-01-31 11:27:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('303', '2017-01-31 11:29:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('304', '2017-01-31 14:29:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('305', '2017-01-31 15:03:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('306', '2017-01-31 15:48:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('307', '2017-01-31 15:59:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('308', '2017-01-31 17:10:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('309', '2017-01-31 21:56:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('310', '2017-01-31 21:58:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('311', '2017-01-31 22:53:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('312', '2017-01-31 23:16:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('313', '2017-01-31 23:22:46', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('314', '2017-02-01 09:33:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('315', '2017-02-01 09:49:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('316', '2017-02-01 09:54:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('317', '2017-02-01 09:58:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('318', '2017-02-01 11:17:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('319', '2017-02-01 11:23:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('320', '2017-02-01 11:54:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('321', '2017-02-01 14:02:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('322', '2017-02-01 14:07:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('323', '2017-02-01 14:48:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('324', '2017-02-01 14:57:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('325', '2017-02-01 15:01:57', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('326', '2017-02-01 16:03:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('327', '2017-02-01 16:11:55', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('328', '2017-02-01 16:17:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('329', '2017-02-01 16:19:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('330', '2017-02-01 16:28:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('331', '2017-02-01 16:33:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('332', '2017-02-01 20:49:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('333', '2017-02-01 20:51:02', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('334', '2017-02-01 21:24:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('335', '2017-02-02 09:34:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('336', '2017-02-02 11:07:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('337', '2017-02-02 13:59:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('338', '2017-02-02 14:00:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('339', '2017-02-02 14:17:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('340', '2017-02-02 15:16:17', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('341', '2017-02-02 15:36:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('342', '2017-02-02 15:48:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('343', '2017-02-02 15:56:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('344', '2017-02-02 16:00:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('345', '2017-02-02 16:04:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('346', '2017-02-02 16:19:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('347', '2017-02-02 22:01:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('348', '2017-02-02 22:04:10', '192.168.52.1', '1');
INSERT INTO `admin_login_log` VALUES ('349', '2017-02-02 23:03:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('350', '2017-02-03 09:33:59', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('351', '2017-02-03 09:47:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('352', '2017-02-03 09:56:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('353', '2017-02-03 10:23:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('354', '2017-02-03 10:44:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('355', '2017-02-03 20:14:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('356', '2017-02-03 20:19:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('357', '2017-02-03 20:20:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('358', '2017-02-03 20:54:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('359', '2017-02-03 21:42:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('360', '2017-02-03 22:01:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('361', '2017-02-03 22:24:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('362', '2017-02-03 22:27:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('363', '2017-02-04 13:13:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('364', '2017-02-04 13:35:32', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('365', '2017-02-04 14:07:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('366', '2017-02-04 14:18:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('367', '2017-02-04 14:20:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('368', '2017-02-04 14:23:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('369', '2017-02-04 14:34:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('370', '2017-02-04 14:59:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('371', '2017-02-04 15:20:17', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('372', '2017-02-04 15:20:36', '192.168.52.1', '1');
INSERT INTO `admin_login_log` VALUES ('373', '2017-02-04 15:23:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('374', '2017-02-04 15:34:57', '192.168.52.1', '1');
INSERT INTO `admin_login_log` VALUES ('375', '2017-02-04 15:35:32', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('376', '2017-02-04 15:50:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('377', '2017-02-04 16:05:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('378', '2017-02-04 16:08:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('379', '2017-02-04 16:16:41', '192.168.52.1', '1');
INSERT INTO `admin_login_log` VALUES ('380', '2017-02-04 16:17:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('381', '2017-02-04 16:23:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('382', '2017-02-04 16:25:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('383', '2017-02-04 16:34:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('384', '2017-02-04 16:37:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('385', '2017-02-04 16:41:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('386', '2017-02-04 17:59:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('387', '2017-02-04 20:10:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('388', '2017-02-04 20:46:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('389', '2017-02-04 20:50:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('390', '2017-02-05 10:05:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('391', '2017-02-05 10:29:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('392', '2017-02-05 10:47:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('393', '2017-02-05 11:44:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('394', '2017-02-05 11:48:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('395', '2017-02-05 11:51:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('396', '2017-02-05 13:59:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('397', '2017-02-06 09:30:36', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('398', '2017-02-06 09:35:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('399', '2017-02-06 10:54:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('400', '2017-02-06 10:56:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('401', '2017-02-06 10:59:28', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('402', '2017-02-06 11:04:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('403', '2017-02-06 11:09:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('404', '2017-02-06 11:15:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('405', '2017-02-06 13:30:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('406', '2017-02-06 13:39:04', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('407', '2017-02-06 15:42:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('408', '2017-02-06 16:09:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('409', '2017-02-06 16:14:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('410', '2017-02-06 16:16:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('411', '2017-02-06 16:19:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('412', '2017-02-06 16:35:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('413', '2017-02-06 17:01:04', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('414', '2017-02-06 17:54:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('415', '2017-02-06 20:44:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('416', '2017-02-06 20:54:57', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('417', '2017-02-06 21:03:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('418', '2017-02-07 14:35:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('419', '2017-02-07 15:57:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('420', '2017-02-07 16:35:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('421', '2017-02-07 16:48:17', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('422', '2017-02-07 16:52:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('423', '2017-02-07 16:55:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('424', '2017-02-07 17:08:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('425', '2017-02-07 17:50:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('426', '2017-02-08 13:12:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('427', '2017-02-08 15:28:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('428', '2017-02-08 15:48:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('429', '2017-02-08 16:45:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('430', '2017-02-08 17:46:32', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('431', '2017-02-08 19:34:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('432', '2017-02-08 20:56:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('433', '2017-02-08 21:06:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('434', '2017-02-08 21:10:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('435', '2017-02-08 21:18:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('436', '2017-02-08 21:44:59', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('437', '2017-02-08 22:02:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('438', '2017-02-08 22:16:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('439', '2017-02-09 13:56:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('440', '2017-02-09 14:36:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('441', '2017-02-09 17:06:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('442', '2017-02-09 17:51:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('443', '2017-02-09 21:00:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('444', '2017-02-09 21:00:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('445', '2017-02-09 21:48:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('446', '2017-02-09 22:07:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('447', '2017-02-10 09:54:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('448', '2017-02-10 09:58:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('449', '2017-02-10 15:51:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('450', '2017-02-10 16:56:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('451', '2017-02-10 17:00:38', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('452', '2017-02-10 17:07:24', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('453', '2017-02-10 17:16:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('454', '2017-02-10 17:23:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('455', '2017-02-10 17:27:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('456', '2017-02-10 17:29:04', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('457', '2017-02-10 18:54:38', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('458', '2017-02-10 21:29:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('459', '2017-02-10 22:53:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('460', '2017-02-10 22:53:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('461', '2017-02-10 23:05:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('462', '2017-02-10 23:08:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('463', '2017-02-11 10:10:59', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('464', '2017-02-11 11:25:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('465', '2017-02-11 15:32:38', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('466', '2017-02-12 13:40:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('467', '2017-02-12 14:14:46', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('468', '2017-02-12 17:09:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('469', '2017-02-12 18:30:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('470', '2017-02-12 20:46:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('471', '2017-02-12 21:28:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('472', '2017-02-12 21:36:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('473', '2017-02-12 21:54:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('474', '2017-02-13 16:41:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('475', '2017-02-13 18:18:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('476', '2017-02-14 18:15:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('477', '2017-02-14 18:45:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('478', '2017-02-14 20:36:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('479', '2017-02-15 09:39:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('480', '2017-02-15 10:48:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('481', '2017-02-15 10:55:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('482', '2017-02-15 12:36:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('483', '2017-02-15 13:11:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('484', '2017-02-15 13:26:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('485', '2017-02-15 13:29:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('486', '2017-02-15 13:32:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('487', '2017-02-15 14:02:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('488', '2017-02-15 14:42:58', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('489', '2017-02-15 15:05:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('490', '2017-02-15 16:38:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('491', '2017-02-15 16:47:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('492', '2017-02-15 21:35:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('493', '2017-02-16 10:38:25', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('494', '2017-02-16 12:28:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('495', '2017-02-16 13:07:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('496', '2017-02-16 13:44:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('497', '2017-02-16 16:47:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('498', '2017-02-16 17:03:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('499', '2017-02-16 18:33:38', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('500', '2017-02-16 19:23:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('501', '2017-02-16 20:19:46', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('502', '2017-02-16 22:03:59', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('503', '2017-02-17 09:32:14', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('504', '2017-02-17 10:26:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('505', '2017-02-17 15:08:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('506', '2017-02-17 15:48:57', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('507', '2017-02-17 15:57:00', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('508', '2017-02-17 16:15:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('509', '2017-02-17 17:34:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('510', '2017-02-17 18:55:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('511', '2017-02-17 21:26:57', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('512', '2017-02-17 22:13:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('513', '2017-02-18 10:16:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('514', '2017-02-18 12:25:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('515', '2017-02-18 13:07:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('516', '2017-02-18 15:54:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('517', '2017-02-18 16:37:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('518', '2017-02-18 19:31:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('519', '2017-02-18 21:25:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('520', '2017-02-20 08:54:09', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('521', '2017-02-20 09:58:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('522', '2017-02-20 12:39:24', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('523', '2017-02-21 10:02:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('524', '2017-02-21 10:37:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('525', '2017-02-21 10:43:32', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('526', '2017-02-21 10:49:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('527', '2017-02-21 12:45:55', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('528', '2017-02-21 12:53:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('529', '2017-02-21 12:56:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('530', '2017-02-21 13:03:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('531', '2017-02-21 13:07:02', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('532', '2017-02-21 13:10:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('533', '2017-02-21 20:14:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('534', '2017-02-21 21:28:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('535', '2017-02-21 21:37:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('536', '2017-02-22 09:46:17', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('537', '2017-02-22 09:59:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('538', '2017-02-22 10:03:02', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('539', '2017-02-23 12:52:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('540', '2017-02-23 13:06:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('541', '2017-02-23 13:26:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('542', '2017-02-23 13:39:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('543', '2017-02-23 16:00:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('544', '2017-02-23 16:35:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('545', '2017-02-23 16:46:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('546', '2017-02-24 10:20:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('547', '2017-02-24 14:13:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('548', '2017-02-24 14:25:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('549', '2017-02-24 15:50:45', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('550', '2017-02-24 16:43:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('551', '2017-02-26 14:21:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('552', '2017-02-27 09:09:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('553', '2017-02-27 10:27:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('554', '2017-02-27 10:31:48', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('555', '2017-02-27 10:37:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('556', '2017-02-27 11:10:12', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('557', '2017-02-27 13:04:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('558', '2017-02-27 13:15:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('559', '2017-02-27 13:54:16', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('560', '2017-02-27 14:30:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('561', '2017-02-27 14:46:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('562', '2017-02-27 14:52:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('563', '2017-02-27 15:10:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('564', '2017-02-27 15:32:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('565', '2017-02-27 16:48:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('566', '2017-02-27 16:48:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('567', '2017-02-27 17:54:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('568', '2017-02-27 18:44:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('569', '2017-02-27 18:49:33', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('570', '2017-02-27 18:51:55', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('571', '2017-02-28 09:10:38', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('572', '2017-02-28 09:34:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('573', '2017-02-28 09:38:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('574', '2017-02-28 09:41:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('575', '2017-02-28 09:46:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('576', '2017-02-28 10:17:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('577', '2017-02-28 10:19:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('578', '2017-02-28 10:40:00', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('579', '2017-02-28 14:33:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('580', '2017-02-28 15:22:32', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('581', '2017-02-28 15:55:36', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('582', '2017-02-28 16:07:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('583', '2017-02-28 16:09:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('584', '2017-02-28 16:16:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('585', '2017-02-28 16:36:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('586', '2017-02-28 16:47:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('587', '2017-02-28 18:24:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('588', '2017-02-28 18:42:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('589', '2017-02-28 19:02:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('590', '2017-02-28 19:12:47', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('591', '2017-02-28 19:14:41', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('592', '2017-02-28 19:28:01', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('593', '2017-02-28 19:38:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('594', '2017-02-28 19:49:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('595', '2017-03-01 09:01:27', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('596', '2017-03-01 09:51:15', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('597', '2017-03-01 10:02:52', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('598', '2017-03-01 10:56:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('599', '2017-03-01 12:42:49', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('600', '2017-03-01 13:38:54', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('601', '2017-03-01 14:30:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('602', '2017-03-01 18:36:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('603', '2017-03-01 19:28:43', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('604', '2017-03-01 19:41:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('605', '2017-03-02 09:25:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('606', '2017-03-02 09:44:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('607', '2017-03-02 10:02:05', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('608', '2017-03-02 13:24:02', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('609', '2017-03-02 13:57:21', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('610', '2017-03-02 14:03:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('611', '2017-03-02 14:08:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('612', '2017-03-02 15:02:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('613', '2017-03-02 15:05:30', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('614', '2017-03-02 16:40:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('615', '2017-03-02 16:45:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('616', '2017-03-02 16:48:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('617', '2017-03-02 16:52:44', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('618', '2017-03-02 17:42:10', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('619', '2017-03-02 17:57:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('620', '2017-03-03 09:57:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('621', '2017-03-03 10:01:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('622', '2017-03-03 10:14:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('623', '2017-03-03 10:26:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('624', '2017-03-03 10:33:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('625', '2017-03-03 10:44:14', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('626', '2017-03-03 10:59:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('627', '2017-03-03 13:13:03', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('628', '2017-03-03 16:08:35', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('629', '2017-03-03 16:14:00', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('630', '2017-03-03 16:33:20', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('631', '2017-03-03 16:36:29', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('632', '2017-03-03 18:31:48', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('633', '2017-03-03 18:34:06', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('634', '2017-03-03 18:40:04', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('635', '2017-03-03 19:27:50', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('636', '2017-03-05 10:17:13', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('637', '2017-03-05 10:26:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('638', '2017-03-05 10:41:19', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('639', '2017-03-05 13:18:42', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('640', '2017-03-05 14:17:23', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('641', '2017-03-05 15:02:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('642', '2017-03-06 08:12:51', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('643', '2017-03-06 09:13:07', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('644', '2017-03-06 09:16:24', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('645', '2017-03-06 09:23:39', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('646', '2017-03-06 09:35:02', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('647', '2017-03-06 10:08:31', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('648', '2017-03-06 10:11:56', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('649', '2017-03-06 10:19:40', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('650', '2017-03-06 12:50:36', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('651', '2017-03-06 13:25:18', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('652', '2017-03-06 14:54:08', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('653', '2017-03-06 15:00:26', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('654', '2017-03-06 15:07:34', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('655', '2017-03-06 15:14:11', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('656', '2017-03-06 15:32:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('657', '2017-03-06 15:35:32', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('658', '2017-03-06 16:48:37', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('659', '2017-03-06 18:30:22', '192.168.52.1', '3');
INSERT INTO `admin_login_log` VALUES ('660', '2017-03-07 08:43:49', '192.168.52.1', '3');

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `floor_number` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `bed_number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES ('9', '1', '1', '15', '16', '3', '6', '1', '4');
INSERT INTO `building` VALUES ('10', '2', '0', '14', '21', '1', '6', '0', '4');
INSERT INTO `building` VALUES ('12', '2', '0', '15', '16', '3', '5', '1', '4');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(20) DEFAULT NULL,
  `phone` varchar(40) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('16', '1号楼', '数学与信息学院', '50', '020-83848847', '1');
INSERT INTO `department` VALUES ('18', '15号楼', '经济与管理学院', '51', '90388', '0');
INSERT INTO `department` VALUES ('19', '16号楼', '材料与能源学院', '54', '9090', '0');
INSERT INTO `department` VALUES ('20', '15号楼', '外国语学院', '54', '343252', '0');
INSERT INTO `department` VALUES ('21', '17号楼', '外国语学院', '54', '020-83848841', '1');
INSERT INTO `department` VALUES ('22', '18号楼', '材料与能源学院', '78', '904454', '0');
INSERT INTO `department` VALUES ('23', '17号楼', '材料与能源学院', '56', '020-83848842', '1');
INSERT INTO `department` VALUES ('24', '18号楼', '能源材料学院', '57', '9342342343', '0');
INSERT INTO `department` VALUES ('25', '16号·', '海洋学院', '43', '2332423', '0');
INSERT INTO `department` VALUES ('26', '15号楼', '人文与法学院', '45', '123423432', '0');
INSERT INTO `department` VALUES ('27', '1号楼', '农学院', '90', '020-8384884', '0');

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building_id` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `bed_number` int(11) NOT NULL,
  `student_number` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `building_number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('93', '9', '1', '101', '3', '3', '1', '1');
INSERT INTO `dormitory` VALUES ('94', '9', '1', '102', '4', '2', '1', '1');
INSERT INTO `dormitory` VALUES ('95', '9', '1', '103', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('96', '9', '1', '104', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('97', '9', '1', '105', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('98', '9', '1', '106', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('99', '9', '1', '107', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('100', '9', '1', '108', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('101', '9', '1', '109', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('102', '9', '1', '110', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('103', '9', '1', '111', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('104', '9', '1', '112', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('105', '9', '1', '113', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('106', '9', '1', '114', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('107', '9', '1', '115', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('108', '9', '2', '201', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('109', '9', '2', '202', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('110', '9', '2', '203', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('111', '9', '2', '204', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('112', '9', '2', '205', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('113', '9', '2', '206', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('114', '9', '2', '207', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('115', '9', '2', '208', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('116', '9', '2', '209', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('117', '9', '2', '210', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('118', '9', '2', '211', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('119', '9', '2', '212', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('120', '9', '2', '213', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('121', '9', '2', '214', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('122', '9', '2', '215', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('123', '9', '3', '301', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('124', '9', '3', '302', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('125', '9', '3', '303', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('126', '9', '3', '304', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('127', '9', '3', '305', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('128', '9', '3', '306', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('129', '9', '3', '307', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('130', '9', '3', '308', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('131', '9', '3', '309', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('132', '9', '3', '310', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('133', '9', '3', '311', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('134', '9', '3', '312', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('135', '9', '3', '313', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('136', '9', '3', '314', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('137', '9', '3', '315', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('138', '9', '4', '401', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('139', '9', '4', '402', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('140', '9', '4', '403', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('141', '9', '4', '404', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('142', '9', '4', '405', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('143', '9', '4', '406', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('144', '9', '4', '407', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('145', '9', '4', '408', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('146', '9', '4', '409', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('147', '9', '4', '410', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('148', '9', '4', '411', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('149', '9', '4', '412', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('150', '9', '4', '413', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('151', '9', '4', '414', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('152', '9', '4', '415', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('153', '9', '5', '501', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('154', '9', '5', '502', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('155', '9', '5', '503', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('156', '9', '5', '504', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('157', '9', '5', '505', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('158', '9', '5', '506', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('159', '9', '5', '507', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('160', '9', '5', '508', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('161', '9', '5', '509', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('162', '9', '5', '510', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('163', '9', '5', '511', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('164', '9', '5', '512', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('165', '9', '5', '513', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('166', '9', '5', '514', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('167', '9', '5', '515', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('168', '9', '6', '601', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('169', '9', '6', '602', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('170', '9', '6', '603', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('171', '9', '6', '604', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('172', '9', '6', '605', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('173', '9', '6', '606', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('174', '9', '6', '607', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('175', '9', '6', '608', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('176', '9', '6', '609', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('177', '9', '6', '610', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('178', '9', '6', '611', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('179', '9', '6', '612', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('180', '9', '6', '613', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('181', '9', '6', '614', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('182', '9', '6', '615', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('183', '9', '1', '117', '4', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('184', '9', '1', '119', '6', '0', '1', '1');
INSERT INTO `dormitory` VALUES ('185', '12', '1', '101', '4', '3', '1', '2');
INSERT INTO `dormitory` VALUES ('186', '12', '1', '102', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('187', '12', '1', '103', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('188', '12', '1', '104', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('189', '12', '1', '105', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('190', '12', '1', '106', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('191', '12', '1', '107', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('192', '12', '1', '108', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('193', '12', '1', '109', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('194', '12', '1', '110', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('195', '12', '1', '111', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('196', '12', '1', '112', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('197', '12', '1', '113', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('198', '12', '1', '114', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('199', '12', '1', '115', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('200', '12', '2', '201', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('201', '12', '2', '202', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('202', '12', '2', '203', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('203', '12', '2', '204', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('204', '12', '2', '205', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('205', '12', '2', '206', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('206', '12', '2', '207', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('207', '12', '2', '208', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('208', '12', '2', '209', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('209', '12', '2', '210', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('210', '12', '2', '211', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('211', '12', '2', '212', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('212', '12', '2', '213', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('213', '12', '2', '214', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('214', '12', '2', '215', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('215', '12', '3', '301', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('216', '12', '3', '302', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('217', '12', '3', '303', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('218', '12', '3', '304', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('219', '12', '3', '305', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('220', '12', '3', '306', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('221', '12', '3', '307', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('222', '12', '3', '308', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('223', '12', '3', '309', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('224', '12', '3', '310', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('225', '12', '3', '311', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('226', '12', '3', '312', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('227', '12', '3', '313', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('228', '12', '3', '314', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('229', '12', '3', '315', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('230', '12', '4', '401', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('231', '12', '4', '402', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('232', '12', '4', '403', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('233', '12', '4', '404', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('234', '12', '4', '405', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('235', '12', '4', '406', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('236', '12', '4', '407', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('237', '12', '4', '408', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('238', '12', '4', '409', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('239', '12', '4', '410', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('240', '12', '4', '411', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('241', '12', '4', '412', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('242', '12', '4', '413', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('243', '12', '4', '414', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('244', '12', '4', '415', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('245', '12', '5', '501', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('246', '12', '5', '502', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('247', '12', '5', '503', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('248', '12', '5', '504', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('249', '12', '5', '505', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('250', '12', '5', '506', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('251', '12', '5', '507', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('252', '12', '5', '508', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('253', '12', '5', '509', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('254', '12', '5', '510', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('255', '12', '5', '511', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('256', '12', '5', '512', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('257', '12', '5', '513', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('258', '12', '5', '514', '4', '0', '1', '2');
INSERT INTO `dormitory` VALUES ('259', '12', '5', '515', '4', '0', '1', '2');

-- ----------------------------
-- Table structure for electric_record
-- ----------------------------
DROP TABLE IF EXISTS `electric_record`;
CREATE TABLE `electric_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `electric_rate` decimal(8,2) NOT NULL,
  `status` int(11) NOT NULL,
  `pay` int(11) NOT NULL,
  `dormitory_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of electric_record
-- ----------------------------
INSERT INTO `electric_record` VALUES ('10', '2017-01-01', '2017-02-01', '40.01', '1', '1', '93');
INSERT INTO `electric_record` VALUES ('11', '2017-01-01', '2017-02-23', '40.05', '0', '0', '94');
INSERT INTO `electric_record` VALUES ('12', '2017-02-01', '2017-02-08', '10.00', '0', '0', '96');
INSERT INTO `electric_record` VALUES ('13', '2017-02-01', '2017-02-09', '10.90', '1', '1', '96');

-- ----------------------------
-- Table structure for goods_record
-- ----------------------------
DROP TABLE IF EXISTS `goods_record`;
CREATE TABLE `goods_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods` varchar(100) NOT NULL,
  `leave_time` datetime NOT NULL,
  `building_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_record
-- ----------------------------
INSERT INTO `goods_record` VALUES ('1', '电脑主机', '2017-02-02 01:25:00', '9', '阿三', '1');
INSERT INTO `goods_record` VALUES ('2', '台式笔记本', '2017-02-03 09:30:11', '9', '张三', '0');
INSERT INTO `goods_record` VALUES ('3', '行李箱', '2017-02-04 09:30:15', '9', '李四', '0');
INSERT INTO `goods_record` VALUES ('4', '台式电脑', '2017-02-01 09:55:43', '9', 'admin', '0');
INSERT INTO `goods_record` VALUES ('5', '钱', '2017-02-02 09:55:42', '9', '你懂的', '0');
INSERT INTO `goods_record` VALUES ('6', '好大物件', '2017-02-03 20:45:28', '9', '张三', '1');

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classNumber` int(11) NOT NULL,
  `departmentId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `remark` varchar(255) NOT NULL DEFAULT '无',
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES ('1', '3', '16', '网络工程', '53', '搭网络', '1');
INSERT INTO `profession` VALUES ('2', '2', '21', '英语专业', '54', '与外国人沟通', '1');
INSERT INTO `profession` VALUES ('3', '3', '16', '软件工程', '54', '开发', '0');
INSERT INTO `profession` VALUES ('4', '2', '16', '软件工程', '54', '码农', '1');
INSERT INTO `profession` VALUES ('5', '2', '23', '化学', '89', '研究化学', '1');

-- ----------------------------
-- Table structure for public_note
-- ----------------------------
DROP TABLE IF EXISTS `public_note`;
CREATE TABLE `public_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `context` varchar(255) NOT NULL,
  `public_time` datetime NOT NULL,
  `adminId` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_note
-- ----------------------------
INSERT INTO `public_note` VALUES ('1', '欢度春节', '一切皆有可能 ', '2017-01-22 14:32:26', '3', '0');
INSERT INTO `public_note` VALUES ('2', '首次使用', '请记得重置密码', '2017-01-22 16:06:06', '3', '0');
INSERT INTO `public_note` VALUES ('3', '正在开发中', '成功', '2017-01-22 16:07:23', '3', '0');
INSERT INTO `public_note` VALUES ('4', '谁持彩练当空舞', '奶爱你的 等你爱我有手看透爱情看透你 仍做爱爱摇什锦锉 你的人我的你 you are doing what ', '2017-01-22 16:45:35', '3', '1');
INSERT INTO `public_note` VALUES ('5', '加油', '只要努力就有结果', '2017-01-22 23:14:47', '3', '1');
INSERT INTO `public_note` VALUES ('6', '万物皆有灵性', ' 世界万物皆有灵性', '2017-01-24 11:49:22', '3', '1');
INSERT INTO `public_note` VALUES ('7', 'sb', 'sos', '2017-02-08 15:58:40', '3', '0');

-- ----------------------------
-- Table structure for repairs_record
-- ----------------------------
DROP TABLE IF EXISTS `repairs_record`;
CREATE TABLE `repairs_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dormitory_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `repairs_time` datetime NOT NULL,
  `is_repairs` int(11) NOT NULL,
  `context` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repairs_record
-- ----------------------------
INSERT INTO `repairs_record` VALUES ('1', '93', '1', '0', '2017-02-04 23:28:48', '1', '宿舍四支灯管都查杯具了，肿么', '蔡晓晖');
INSERT INTO `repairs_record` VALUES ('2', '93', '1', '0', '2017-02-06 21:45:01', '1', '阳台水龙头坏了', '蔡晓晖');
INSERT INTO `repairs_record` VALUES ('3', '93', '1', '1', '2017-02-06 21:49:45', '1', 'wft3r3r3', '蔡晓晖');
INSERT INTO `repairs_record` VALUES ('4', '93', '1', '1', '2017-02-06 21:55:17', '0', '4r23r32r3', '蔡晓晖');

-- ----------------------------
-- Table structure for sanitation
-- ----------------------------
DROP TABLE IF EXISTS `sanitation`;
CREATE TABLE `sanitation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `status` int(11) NOT NULL,
  `dormitory_id` int(11) NOT NULL,
  `degree` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sanitation
-- ----------------------------
INSERT INTO `sanitation` VALUES ('1', '2017-01-01', '2017-02-02', '1', '93', '2');
INSERT INTO `sanitation` VALUES ('2', '2017-01-01', '2017-02-02', '0', '94', '2');
INSERT INTO `sanitation` VALUES ('3', '2017-01-01', '2017-02-02', '1', '96', '1');
INSERT INTO `sanitation` VALUES ('4', '2017-01-02', '2017-02-17', '0', '96', '2');
INSERT INTO `sanitation` VALUES ('5', '2017-01-01', '2017-02-28', '0', '101', '3');
INSERT INTO `sanitation` VALUES ('6', '2017-01-01', '2017-02-23', '1', '97', '3');

-- ----------------------------
-- Table structure for send_water_record
-- ----------------------------
DROP TABLE IF EXISTS `send_water_record`;
CREATE TABLE `send_water_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dormitory_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `is_send` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of send_water_record
-- ----------------------------
INSERT INTO `send_water_record` VALUES ('2', '93', '1', '1', '2017-02-05 16:30:22', '1', '蔡晓晖', '2');
INSERT INTO `send_water_record` VALUES ('3', '93', '1', '1', '2017-02-07 10:40:50', '1', '蔡晓晖', '4');
INSERT INTO `send_water_record` VALUES ('4', '93', '1', '1', '2017-02-27 19:30:12', '0', '蔡晓晖', '3');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(30) NOT NULL,
  `sex` int(11) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `profession_id` int(11) NOT NULL DEFAULT '0',
  `dormitory_id` int(11) NOT NULL DEFAULT '0',
  `class_number` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `grade` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '201330350101', '1', '18813757486', '1', '93', '1', 'AB59B417F527721A5731F3D5E0220D26', '1', '2013', '蔡晓晖');
INSERT INTO `student` VALUES ('2', '201330350102', '1', '0', '1', '93', '1', '74420D047689723137452973A7F1D8EC', '1', '2013', '陈焕财');
INSERT INTO `student` VALUES ('3', '201330350103', '1', '0', '1', '93', '1', '5F5D399B6F873C71C6C973B6D2D4C34E', '1', '2013', '陈嘉生');
INSERT INTO `student` VALUES ('5', '201330350103', '1', '0', '1', '0', '1', '5F5D399B6F873C71C6C973B6D2D4C34E', '0', '2013', '陈俊成');
INSERT INTO `student` VALUES ('6', '201630350101', '0', '0', '2', '0', '1', '8781419511431C3CEAB52024E7DFCD75', '1', '2016', '加油');
INSERT INTO `student` VALUES ('7', '201530350101', '1', '0', '1', '0', '1', '3D6A11DB26C5AA55A56C0B5D61B601CC', '1', '2015', '陈二月');
INSERT INTO `student` VALUES ('8', '201330340101', '1', '0', '4', '0', '1', '3F02BDA8C112BE03E938E376072B8E2B', '1', '2013', '啊');
INSERT INTO `student` VALUES ('9', '201330350156', '0', '0', '1', '0', '2', 'F28CD248A87F21898058CA6B246D3EA6', '1', '2015', '天啊');
INSERT INTO `student` VALUES ('10', '201330350111', '0', '18813757434', '1', '185', '1', '5D22B9CA3822F04E18089120280F103B', '1', '2013', '花');
INSERT INTO `student` VALUES ('11', '201330350112', '1', '18813757434', '1', '94', '2', 'EC26AB8326A0137789C0B746128B6123', '1', '2013', '草');
INSERT INTO `student` VALUES ('12', '201430350101', '0', '18813757412', '1', '0', '1', '2761CA62C1CF79103599198A183ACE83', '1', '2014', '水');
INSERT INTO `student` VALUES ('13', '201430350110', '1', '18813767123', '1', '0', '2', 'EDBF4178B72290DCEA16ADE33AC30063', '1', '2014', '火');
INSERT INTO `student` VALUES ('14', '201330350301', '0', '18813757401', '1', '185', '2', '70D7B7DFDFE7DBFAA9D4D1B2D212FEFF', '1', '2013', '天');
INSERT INTO `student` VALUES ('15', '201330350319', '0', '18813757401', '1', '185', '2', '6765D1375186E48DEEA7790305C3D15F', '1', '2013', '天');
INSERT INTO `student` VALUES ('16', '201330350107', '1', '0', '1', '94', '1', '97655EFD3CB9B530B9C6842464AB29C2', '1', '2013', '郭');

-- ----------------------------
-- Table structure for visit_record
-- ----------------------------
DROP TABLE IF EXISTS `visit_record`;
CREATE TABLE `visit_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dormitory_id` int(11) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `visited_name` varchar(255) DEFAULT NULL,
  `visitor` varchar(255) DEFAULT NULL,
  `visitor_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visit_record
-- ----------------------------
INSERT INTO `visit_record` VALUES ('1', '93', '2017-02-02 19:30:04', '2017-02-02 18:05:04', '1', '普', '奥巴', '434234234');
INSERT INTO `visit_record` VALUES ('2', '94', '2017-02-03 21:55:39', '2017-02-03 21:45:38', '0', '李四', '阿三', '4342323423');
INSERT INTO `visit_record` VALUES ('3', '95', '2017-02-04 23:55:11', '2017-02-01 21:45:10', '0', '叶', '花', '43923423423');

-- ----------------------------
-- Table structure for water_record
-- ----------------------------
DROP TABLE IF EXISTS `water_record`;
CREATE TABLE `water_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `water_rate` decimal(8,2) NOT NULL,
  `status` int(11) NOT NULL,
  `pay` int(11) NOT NULL,
  `dormitory_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_record
-- ----------------------------
INSERT INTO `water_record` VALUES ('1', '2017-01-03', '2017-02-21', '10.09', '1', '1', '93');
INSERT INTO `water_record` VALUES ('2', '2017-01-01', '2017-02-28', '20.40', '0', '0', '94');
INSERT INTO `water_record` VALUES ('3', '2017-01-01', '2017-02-28', '40.00', '0', '0', '95');
INSERT INTO `water_record` VALUES ('4', '2017-01-01', '2017-02-28', '20.04', '0', '0', '96');
INSERT INTO `water_record` VALUES ('5', '2017-01-01', '2017-02-28', '20.04', '0', '0', '95');
INSERT INTO `water_record` VALUES ('6', '2017-01-01', '2017-02-28', '20.06', '0', '0', '98');
INSERT INTO `water_record` VALUES ('7', '2017-01-01', '2017-02-01', '10.10', '0', '0', '96');
INSERT INTO `water_record` VALUES ('8', '2017-02-01', '2017-02-02', '14.03', '0', '0', '93');
INSERT INTO `water_record` VALUES ('9', '2017-01-01', '2017-02-08', '10.02', '1', '1', '94');
