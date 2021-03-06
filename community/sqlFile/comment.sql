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

 Date: 21/02/2020 20:25:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类id',
  `type` int(11) DEFAULT NULL COMMENT '父类类型，用来区分是1级回复还是2级回复',
  `commentator` int(11) DEFAULT NULL COMMENT '评论人id',
  `gmt_create` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `like_count` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `comment` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (1, 1, 1, 1, 1581300959128, 1581300959128, 0, '2121');
INSERT INTO `comment` VALUES (2, 4, 1, 13, 1581407022263, 1581407022263, 0, '回复1');
INSERT INTO `comment` VALUES (3, 4, 1, 13, 1581428289708, 1581428289708, 0, '666');
INSERT INTO `comment` VALUES (4, 4, 1, 13, 1581428301631, 1581428301631, 0, 'hahahaha');
INSERT INTO `comment` VALUES (5, 5, 1, 13, 1581430168049, 1581430168049, 0, '问题111的回复');
INSERT INTO `comment` VALUES (6, 2, 2, 13, 1581407022263, 1581407022263, 0, '评论1');
INSERT INTO `comment` VALUES (7, 2, 2, 13, 1581407022263, 1581407022263, 0, '评论2');
INSERT INTO `comment` VALUES (8, 3, 2, 13, 1581477497709, 1581477497709, 0, '666的评论');
INSERT INTO `comment` VALUES (9, 3, 2, 13, 1581477497709, 1581477497709, 0, '666的评论');
INSERT INTO `comment` VALUES (10, 4, 2, 13, 1581477596729, 1581477596729, 0, 'ha ha hd的评论');
INSERT INTO `comment` VALUES (11, 26, 1, 13, 1581479325177, 1581479325177, 0, 'hello');
INSERT INTO `comment` VALUES (12, 11, 2, 13, 1581479337067, 1581479337067, 0, 'hello的回复');
INSERT INTO `comment` VALUES (13, 26, 1, 13, 1581565422167, 1581565422167, 0, '新的回复1');
INSERT INTO `comment` VALUES (14, 27, 1, 13, 1581566559065, 1581566559065, 0, '这个是个有趣的问题');
INSERT INTO `comment` VALUES (15, 14, 2, 13, 1581567216378, 1581567216378, 0, '废话');
INSERT INTO `comment` VALUES (16, 14, 2, 13, 1581571672232, 1581571672232, 0, '废话');
INSERT INTO `comment` VALUES (17, 24, 1, 13, 1581571704776, 1581571704776, 0, '好问题');
INSERT INTO `comment` VALUES (18, 27, 1, 13, 1581572852743, 1581572852743, 0, 'sdf的回复');
INSERT INTO `comment` VALUES (19, 14, 2, 13, 1581572881039, 1581572881039, 0, '对sdf这个问题下的“这个是个有趣的问题”的评论');
INSERT INTO `comment` VALUES (20, 26, 1, 13, 1581572951095, 1581572951095, 0, '对最后对问题对回复');
INSERT INTO `comment` VALUES (21, 25, 1, 13, 1581572963953, 1581572963953, 0, '对新问题对回复');
INSERT INTO `comment` VALUES (22, 21, 2, 13, 1581572994716, 1581572994716, 0, '对新问题对评论');
INSERT INTO `comment` VALUES (23, 21, 2, 13, 1581573083678, 1581573083678, 0, '对新问题对评论2');
INSERT INTO `comment` VALUES (24, 14, 2, 13, 1581573495592, 1581573495592, 0, '评论1');
INSERT INTO `comment` VALUES (25, 18, 2, 13, 1581573500078, 1581573500078, 0, '评论2');
INSERT INTO `comment` VALUES (26, 18, 2, 13, 1581573504474, 1581573504474, 0, '评论3');
INSERT INTO `comment` VALUES (27, 21, 2, 13, 1581573513213, 1581573513213, 0, '评论4');
INSERT INTO `comment` VALUES (28, 21, 2, 13, 1581573516381, 1581573516381, 0, '评论5');
INSERT INTO `comment` VALUES (29, 28, 1, 15, 1581646645589, 1581646645589, 0, '有意思的问题');
INSERT INTO `comment` VALUES (30, 29, 2, 13, 1581646730546, 1581646730546, 0, 'haha');
INSERT INTO `comment` VALUES (31, 29, 2, 13, 1581753696750, 1581753696750, 0, '666');
INSERT INTO `comment` VALUES (32, 29, 2, 13, 1581754047267, 1581754047267, 0, 'dasf');
INSERT INTO `comment` VALUES (35, 28, 1, 15, 1581759579245, 1581759579245, 0, 'this is interesting');
INSERT INTO `comment` VALUES (36, 35, 2, 13, 1581759640452, 1581759640452, 0, '谢谢');
INSERT INTO `comment` VALUES (37, 35, 2, 15, 1581760118134, 1581760118134, 0, '再次评论');
INSERT INTO `comment` VALUES (38, 28, 1, 15, 1581760150549, 1581760150549, 0, '回复一下');
INSERT INTO `comment` VALUES (39, 38, 2, 15, 1581760163460, 1581760163460, 0, '哈哈哈');
INSERT INTO `comment` VALUES (40, 38, 2, 13, 1581820055497, 1581820055497, 0, 'ok');
INSERT INTO `comment` VALUES (41, 35, 1, 7, 1581995513530, 1581995513530, 0, '美丽');
INSERT INTO `comment` VALUES (42, 41, 2, 5, 1581995558773, 1581995558773, 0, '谢谢');
INSERT INTO `comment` VALUES (43, 41, 2, 16, 1582012091926, 1582012091926, 0, '厉害');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
