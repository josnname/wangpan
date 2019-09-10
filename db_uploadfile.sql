/*
 Navicat Premium Data Transfer

 Source Server         : 谷歌
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : db_uploadfile

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/09/2019 19:38:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for filerepository
-- ----------------------------
DROP TABLE IF EXISTS `filerepository`;
CREATE TABLE `filerepository`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `realpath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `filesize` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of filerepository
-- ----------------------------
INSERT INTO `filerepository` VALUES (51, '4f5414d8b3a1259865c79b3c9dadcdce', 'H:/fileUploadRepository/xy.jpg', '39.1 KB');
INSERT INTO `filerepository` VALUES (52, '7541ecbb05a91a017d3ac13aa26d98ed', 'H:/fileUploadRepository/bf.jpg', '53 KB');
INSERT INTO `filerepository` VALUES (53, '7541ecbb05a91a017d3ac13aa26d98ed', 'H:/fileUploadRepository/xy.jpg', '39.1 KB');
INSERT INTO `filerepository` VALUES (54, '6ed7ed7e9d0d233ee53dd2a0fc6fcac1', 'H:/fileUploadRepository/v.PNG', '267.5 KB');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `type` varchar(30) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `qimg` varchar(300) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0 COMMENT '0 未解决   1已解决',
  `answer` varchar(500) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `animg` varchar(100) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `creationtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `accountid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmtcreate` datetime(0) NULL DEFAULT NULL,
  `gmtmodified` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('789d5488-999b-4880-934d-9213d9a69d70', '林间有风', '28743778', '2019-09-10 13:25:54', '2019-09-10 13:25:54');
INSERT INTO `user` VALUES ('0a0c68fd-3962-4eee-a19b-d498c059fd37', '林间有风', '28743778', '2019-09-10 14:02:40', '2019-09-10 14:02:40');
INSERT INTO `user` VALUES ('af3d449b-a5e2-470f-b93e-787bd5fed25e', '林间有风', '28743778', '2019-09-10 14:05:03', '2019-09-10 14:05:03');

-- ----------------------------
-- Table structure for userfile
-- ----------------------------
DROP TABLE IF EXISTS `userfile`;
CREATE TABLE `userfile`  (
  `uid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `virtualpath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `realpath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `iconSign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `filesize` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mtime` datetime(0) NULL DEFAULT NULL,
  `survival` int(255) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userfile
-- ----------------------------
INSERT INTO `userfile` VALUES ('28743778', '/4343', 'H:/fileUploadRepository/xy.jpg', '#icon-file_pic', '-', '2019-09-10 10:25:09', 1);
INSERT INTO `userfile` VALUES ('28743778', '/bf.jpg', 'H:/fileUploadRepository/bf.jpg', '#icon-file_pic', '-', '2019-09-10 10:25:30', 1);
INSERT INTO `userfile` VALUES ('28743778', '/4343', 'H:/fileUploadRepository/xy.jpg', '#icon-file_pic', '-', '2019-09-10 10:25:41', 1);
INSERT INTO `userfile` VALUES ('28743778', '/dsds ', NULL, '#icon-folder', '-', '2019-09-10 12:18:03', 1);
INSERT INTO `userfile` VALUES ('28743778', '/32323', NULL, '#icon-folder', '-', '2019-09-10 12:18:28', 1);
INSERT INTO `userfile` VALUES ('28743778', '/v.PNG', 'H:/fileUploadRepository/v.PNG', '#icon-file_pic', '-', '2019-09-10 12:18:36', 1);

SET FOREIGN_KEY_CHECKS = 1;
