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

 Date: 21/02/2020 20:26:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notifier` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `outer_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of notification
-- ----------------------------
BEGIN;
INSERT INTO `notification` VALUES (3, 7, 9, 27, 1, 1, 1581572852772);
INSERT INTO `notification` VALUES (4, 7, 9, 26, 1, 1, 1581572951105);
INSERT INTO `notification` VALUES (5, 7, 9, 25, 1, 1, 1581572963961);
INSERT INTO `notification` VALUES (6, 7, 9, 21, 2, 1, 1581573083716);
INSERT INTO `notification` VALUES (7, 8, 9, 14, 2, 1, 1581573495614);
INSERT INTO `notification` VALUES (8, 8, 9, 18, 2, 1, 1581573500082);
INSERT INTO `notification` VALUES (9, 8, 9, 18, 2, 1, 1581573504478);
INSERT INTO `notification` VALUES (10, 8, 9, 21, 2, 1, 1581573513218);
INSERT INTO `notification` VALUES (11, 8, 7, 21, 2, 1, 1581573516386);
INSERT INTO `notification` VALUES (12, 8, 7, 28, 1, 1, 1581646645639);
INSERT INTO `notification` VALUES (13, 8, 7, 29, 2, 0, 1581646730550);
INSERT INTO `notification` VALUES (14, 9, 7, 29, 2, 0, 1581753696893);
INSERT INTO `notification` VALUES (15, 9, 7, 29, 2, 0, 1581754047412);
INSERT INTO `notification` VALUES (18, 9, 7, 28, 1, 1, 1581759579499);
INSERT INTO `notification` VALUES (19, 9, 7, 35, 2, 1, 1581759640563);
INSERT INTO `notification` VALUES (20, 9, 7, 35, 2, 0, 1581760118240);
INSERT INTO `notification` VALUES (21, 9, 7, 28, 1, 1, 1581760150714);
INSERT INTO `notification` VALUES (22, 9, 7, 38, 2, 0, 1581760163533);
INSERT INTO `notification` VALUES (23, 10, 7, 38, 2, 1, 1581820055603);
INSERT INTO `notification` VALUES (24, 7, 5, 35, 1, 1, 1581995513750);
INSERT INTO `notification` VALUES (25, 5, 7, 41, 2, 1, 1581995558863);
INSERT INTO `notification` VALUES (26, 16, 7, 41, 2, 0, 1582012092033);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
