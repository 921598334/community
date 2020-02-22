/*
 Navicat MySQL Data Transfer

 Source Server         : lab server
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : 122.114.178.53:3306
 Source Schema         : communitytest

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 21/02/2020 20:25:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for githubuser
-- ----------------------------
DROP TABLE IF EXISTS `githubuser`;
CREATE TABLE `githubuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT 'github的账号',
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT 'github的用户名',
  `user_id` int(11) DEFAULT NULL COMMENT '对应用户账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of githubuser
-- ----------------------------
BEGIN;
INSERT INTO `githubuser` VALUES (13, '37922736', 'BoBO', 5);
INSERT INTO `githubuser` VALUES (27, '61035612', '1397257371', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
