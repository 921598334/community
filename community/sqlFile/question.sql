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

 Date: 21/02/2020 20:26:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `description` text CHARACTER SET utf8,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `view_count` int(11) unsigned zerofill DEFAULT NULL,
  `like_count` int(11) unsigned zerofill DEFAULT NULL,
  `tag` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of question
-- ----------------------------
BEGIN;
INSERT INTO `question` VALUES (4, '11问题', '32', 1580977437285, 1580977437285, 7, 3, 00000000056, 00000000000, '32');
INSERT INTO `question` VALUES (5, '11问题1', 'fsdfsd', 1580978465199, 1580978465199, 7, 1, 00000000004, 00000000000, 'daf');
INSERT INTO `question` VALUES (9, 'qqqq22222', 'qqqq22222', 1581064705403, 1581239770946, 7, 0, 00000000001, 00000000000, 'qqq');
INSERT INTO `question` VALUES (10, '问题1', '问题1', 1581065055064, 1581065055064, 8, 0, 00000000001, 00000000000, 'spring');
INSERT INTO `question` VALUES (11, '问题2', '问题1', 1581065055064, 1581065055064, 8, 0, 00000000001, 00000000000, '问题1');
INSERT INTO `question` VALUES (12, '问题3', '问题1', 1581065055064, 1581065055064, 8, 0, 00000000000, 00000000000, '问题1');
INSERT INTO `question` VALUES (13, '问题4', '问题1', 1581065055064, 1581065055064, 9, 0, 00000000000, 00000000000, '问题1');
INSERT INTO `question` VALUES (14, '问题5', '问题1', 1581065055064, 1581065055064, 9, 0, 00000000000, 00000000000, '问题1');
INSERT INTO `question` VALUES (15, '问题6', '问题1', 1581065055064, 1581065055064, 9, 0, 00000000000, 00000000000, '问题1');
INSERT INTO `question` VALUES (16, '问题7', '问题1', 1581065055064, 1581065055064, 9, 0, 00000000000, 00000000000, 'spring,java');
INSERT INTO `question` VALUES (17, '问题8', '问题1', 1581065055064, 1581065055064, 7, 0, 00000000000, 00000000000, '问题1');
INSERT INTO `question` VALUES (18, '问题9', '问题1', 1581065055064, 1581065055064, 7, 0, 00000000000, 00000000000, '问题1');
INSERT INTO `question` VALUES (19, '问题10', '问题1', 1581065055064, 1581065055064, 7, 0, 00000000000, 00000000000, '问题1');
INSERT INTO `question` VALUES (20, '问题11', '问题1', 1581065055064, 1581065055064, 7, 0, 00000000000, 00000000000, 'C#,java');
INSERT INTO `question` VALUES (21, 'qqqq已经修改了', 'qqqqy已经修改了', 1581231999997, 1581231999997, 7, 0, 00000000000, 00000000000, 'qqq');
INSERT INTO `question` VALUES (22, '问题1修改了', '问题1修改了', 1581232234250, 1581232234250, 9, 0, 00000000000, 00000000000, '问题1');
INSERT INTO `question` VALUES (23, 'qqqq5555555', 'qqqq555555', 1581232349763, 1581232349763, 9, 0, 00000000000, 00000000000, 'qqq');
INSERT INTO `question` VALUES (24, '444qqqq', 'qqqq44', 1581232381039, 1581232381039, 9, 1, 00000000002, 00000000000, 'java');
INSERT INTO `question` VALUES (25, '新问题', '新问题', 1581239999990, 1581239999990, 9, 1, 00000000016, 00000000000, 'dfa,dafad');
INSERT INTO `question` VALUES (26, '最后的问题', '最后的问题', 1581478885474, 1581478885474, 7, 3, 00000000014, 00000000000, 'java,spring');
INSERT INTO `question` VALUES (27, 'sadfda', 'd s f s d', 1581499812710, 1581499812710, 8, 2, 00000000021, 00000000000, 'win,python');
INSERT INTO `question` VALUES (28, '富有文本', '\'\'\'java\r\npackage com.example.community.controller;\r\n\r\nimport com.example.community.dto.FileDTO;\r\nimport org.springframework.stereotype.Controller;\r\nimport org.springframework.web.bind.annotation.GetMapping;\r\n\r\n//上传图片后需要返回的结果\r\n@Controller\r\npublic class FileController {\r\n[![](https://img.5xdmw.com/uploads/allimg/202002/393c6ef5f2133e70.jpg)](https://img.5xdmw.com/uploads/allimg/202002/393c6ef5f2133e70.jpg)\r\n    @GetMapping(\"/file/upload\")\r\n    public FileDTO upload(){\r\n\r\n        FileDTO fileDTO = new FileDTO();\r\n        fileDTO.setSuccess(1);\r\n        fileDTO.setUrl(\"www.baidu.com\");\r\n        return  fileDTO;\r\n    }\r\n\r\n}\r\n\r\n\'\'\'\r\n\r\n\r\n\r\ndafd的豆瓣小组\r\ndafd的豆瓣小组小组主页 加入的小组 喜欢 推荐 加入的小组 (1) 兼职信息 (174625) 喜欢的讨论 (0) 没有喜欢的讨论 推荐的讨论 (0) 没有推荐的讨论...\r\nwww.douban.com/group/p...  - 百度快照\r\nDAFD_百度翻译\r\nDAFD	\r\nabbr.	德顿空军仓库(Dayton Air Force Depot); 陆军部前方仓库(Department of the Army Forward Depot);\r\n进行更多翻译\r\nfanyi.baidu.com \r\ndafd是什么意思_dafd在线翻译_英语_读音_用法_例句_海词词典\r\n海词词典,最权威的学习词典,为您提供dafd的在线\r\n\r\n', 1581598080399, 1581598080399, 8, 3, 00000000089, 00000000000, 'win');
INSERT INTO `question` VALUES (29, '图片测试问题', '<p>&nbsp;</p>\r\n\r\n<p>提问了</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"/upload/65acdda845754a8c909ffc54971077bc.jpg\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>哈哈哈</p>\r\n', 1581836962481, 1581836962481, 9, 0, 00000000009, 00000000000, 'win');
INSERT INTO `question` VALUES (30, '图片上传测试2', '<p>图片上传测试2图片上传测试2</p><p>图片上传测试2<img src=\"/upload/775dfdb098764cecb4d3e647bdf8c672.jpeg\" title=\"2.jpeg\" alt=\"2.jpeg\"/></p>', 1581910037122, 1581910037122, 9, 0, 00000000006, 00000000000, 'win');
INSERT INTO `question` VALUES (31, '图片测试3', '<p>图片测试3图片测试3图片测试3图片测试3</p><p>图片测试3图片测试3<img src=\"http://localhost:8086/upload/5671d8c82442445e9c72793919689b16.jpg\" title=\"1.jpg\" alt=\"1.jpg\"/></p>', 1581910419542, 1581910419542, 9, 0, 00000000009, 00000000000, 'win');
INSERT INTO `question` VALUES (32, '成功了', '<p>sfafdas</p><p><img src=\"/upload/1f85045cec8e4642b9199a507c0e28bd.jpeg\" title=\"3.jpeg\" alt=\"3.jpeg\" width=\"239\" height=\"262\"/></p><p>dafdadafs</p>', 1581919055788, 1581919055788, 9, 0, 00000000006, 00000000000, 'linux');
INSERT INTO `question` VALUES (33, '成功了图片上传', '<p><img alt=\"\" height=\"352\" src=\"/upload/ec2fefa66a5e40d6ae219ca7fb4919b0.jpeg\" width=\"359\" /></p>\r\n\r\n<p>dfdsafafdaadsfafda</p>\r\n', 1581919976386, 1581919976386, 10, 0, 00000000009, 00000000000, 'win');
INSERT INTO `question` VALUES (34, '2发起的问题', '<p>但是很大沙发阿斯顿发的啥的时候</p>\r\n', 1581995051988, 1581995051988, 7, 0, 00000000001, 00000000000, 'win');
INSERT INTO `question` VALUES (35, 'd5的提问', '<p>真正的肥仔</p>\r\n\r\n<p><img alt=\"\" height=\"726\" src=\"/upload/acba3011cd7e485f8072be71fecc24cc.jpg\" width=\"580\" /></p>\r\n', 1581995408905, 1582074724442, 5, 0, 00000000007, 00000000000, 'win');
INSERT INTO `question` VALUES (37, '学习', '<h2>概述</h2>\r\n\r\n<p>我们经常需要把某种模式匹配到的所有路由，全都映射到同个组件。例如，我们有一个 User 组件，对于所有 ID 各不相同的用户，都要使用这个组件来渲染。此时我们就需要传递参数了；</p>\r\n\r\n<h2><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E4%BD%BF%E7%94%A8%E8%B7%AF%E5%BE%84%E5%8C%B9%E9%85%8D%E7%9A%84%E6%96%B9%E5%BC%8F\">#</a>使用路径匹配的方式</h2>\r\n\r\n<h3><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E4%BF%AE%E6%94%B9%E8%B7%AF%E7%94%B1%E9%85%8D%E7%BD%AE\">#</a>修改路由配置</h3>\r\n\r\n<pre>\r\n<code>{path: &#39;/user/profile/:id&#39;, name:&#39;UserProfile&#39;, component: UserProfile}\r\n</code></pre>\r\n\r\n<p>1</p>\r\n\r\n<p>说明：主要是在&nbsp;<code>path</code>&nbsp;属性中增加了&nbsp;<code>:id</code>&nbsp;这样的占位符</p>\r\n\r\n<h3><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E4%BC%A0%E9%80%92%E5%8F%82%E6%95%B0\">#</a>传递参数</h3>\r\n\r\n<h4><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#router-link\">#</a>router-link</h4>\r\n\r\n<pre>\r\n<code>&lt;router-link :to=&quot;{name: &#39;UserProfile&#39;, params: {id: 1}}&quot;&gt;个人信息&lt;/router-link&gt;\r\n</code></pre>\r\n\r\n<p>1</p>\r\n\r\n<p>说明：此时我们将&nbsp;<code>to</code>&nbsp;改为了&nbsp;<code>:to</code>，是为了将这一属性当成对象使用，注意&nbsp;<strong>router-link 中的 name 属性名称</strong>&nbsp;一定要和&nbsp;<strong>路由中的 name 属性名称</strong>&nbsp;匹配，因为这样 Vue 才能找到对应的路由路径；</p>\r\n\r\n<h4><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E4%BB%A3%E7%A0%81%E6%96%B9%E5%BC%8F\">#</a>代码方式</h4>\r\n\r\n<pre>\r\n<code>this.$router.push({ name: &#39;UserProfile&#39;, params: {id: 1}});\r\n</code></pre>\r\n\r\n<p>1</p>\r\n\r\n<h3><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E6%8E%A5%E6%94%B6%E5%8F%82%E6%95%B0\">#</a>接收参数</h3>\r\n\r\n<p>在目标组件中使用</p>\r\n\r\n<pre>\r\n<code>{{ $route.params.id }}\r\n</code></pre>\r\n\r\n<p>1</p>\r\n\r\n<p>来接收参数</p>\r\n\r\n<h2><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E4%BD%BF%E7%94%A8-props-%E7%9A%84%E6%96%B9%E5%BC%8F\">#</a>使用&nbsp;<code>props</code>&nbsp;的方式</h2>\r\n\r\n<h3><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E4%BF%AE%E6%94%B9%E8%B7%AF%E7%94%B1%E9%85%8D%E7%BD%AE-2\">#</a>修改路由配置</h3>\r\n\r\n<pre>\r\n<code>{path: &#39;/user/profile/:id&#39;, name:&#39;UserProfile&#39;, component: UserProfile, props: true}\r\n</code></pre>\r\n\r\n<p>1</p>\r\n\r\n<p>说明：主要增加了&nbsp;<code>props: true</code>&nbsp;属性</p>\r\n\r\n<h3><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E4%BC%A0%E9%80%92%E5%8F%82%E6%95%B0-2\">#</a>传递参数</h3>\r\n\r\n<p>同上</p>\r\n\r\n<h3><a href=\"https://www.funtl.com/zh/vue-router/%E5%8F%82%E6%95%B0%E4%BC%A0%E9%80%92.html#%E6%8E%A5%E6%94%B6%E5%8F%82%E6%95%B0-2\">#</a>接收参数</h3>\r\n\r\n<p>为目标组件增加&nbsp;<code>props</code>&nbsp;属性，代码如下：</p>\r\n\r\n<pre>\r\n<code>  export default {\r\n    props: [&#39;id&#39;],\r\n    name: &quot;UserProfile&quot;\r\n  }\r\n</code></pre>\r\n\r\n<p>1<br />\r\n2<br />\r\n3<br />\r\n4</p>\r\n\r\n<p>模板中使用</p>\r\n\r\n<pre>\r\n<code>{{ id }}\r\n</code></pre>\r\n\r\n<p>1</p>\r\n\r\n<p>接收参数</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"/upload/5b68c2c4c8614f3287027d3fa7547f7a.txt\" /><img alt=\"\" height=\"941\" src=\"/upload/053bce1f595f40f99acae0ea7293a8b4.jpeg\" width=\"960\" /></p>\r\n', 1582078236599, 1582078236599, 5, 0, 00000000003, 00000000000, 'mac');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
