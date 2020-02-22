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

 Date: 21/02/2020 20:26:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `passwd` varchar(50) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `avatar_url` varchar(50) DEFAULT NULL,
  `github_id` int(11) DEFAULT NULL COMMENT '对应github账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (5, 'd5', 'd', 'aa8dd6f4-ad6d-446e-889f-d0d79c70eb20', 1581991329901, 1582079692685, '/images/pika.jpg', 13);
INSERT INTO `user` VALUES (7, 'd7', 'd', 'f85135d4-95cb-4e54-8b05-fa57fdecd5b6', 1581991544579, 1581995492116, '/images/pika.jpg', NULL);
INSERT INTO `user` VALUES (8, 'd8', 'd', NULL, 1581991550378, 1581991550378, '/images/pika.jpg', NULL);
INSERT INTO `user` VALUES (9, 'd9', 'd', NULL, 1581991555125, 1581991555125, '/images/pika.jpg', NULL);
INSERT INTO `user` VALUES (10, 'd10', 'd', NULL, 1581991559956, 1581991559956, '/images/pika.jpg', NULL);
INSERT INTO `user` VALUES (11, 'd11', 'd', '4176442c-06ad-4acc-b8cf-a6c2e7806623', 1581991540221, 1581994207610, '/images/pika.jpg', NULL);
INSERT INTO `user` VALUES (14, '123', '123', NULL, 1582011352473, 1582011352473, '/images/pika.jpg', NULL);
INSERT INTO `user` VALUES (15, '12', '12', NULL, 1582011612518, 1582011612518, '/images/pika.jpg', NULL);
INSERT INTO `user` VALUES (16, '13', '13', '0e46a4b5-5c2b-44c0-840e-e02a6487a83a', 1582012039465, 1582012068390, '/upload/b33586e36cbf438ea2bd3c0c96d529ae.jpeg', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
