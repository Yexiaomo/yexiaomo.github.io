/*
 Navicat Premium Data Transfer

 Source Server         : wdnmd
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : javatest2

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 20/10/2019 15:12:14
*/

CREATE DATABASE javatest2 CHARACTER SET=UTF8;
USE javatest2;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course`  (
  `CID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseIntroduce` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ccredit` int(200) NULL DEFAULT NULL,
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_course
-- ----------------------------
INSERT INTO `tb_course` VALUES ('A001', '需求工程', '武总', '软件工程中的需求工程', 10);
INSERT INTO `tb_course` VALUES ('A002', 'Java', '小茗', 'Java从入门到放弃', 10);
INSERT INTO `tb_course` VALUES ('A003', '软件架构', '张老师', '成为架构师的第一步', 10);
INSERT INTO `tb_course` VALUES ('A004', '中间件', '张老师', '成为工程师的必备', 10);
INSERT INTO `tb_course` VALUES ('A005', '中国特色社会主义理论', '陆老师', '花朵必上', 10);
INSERT INTO `tb_course` VALUES ('C002', '数据结构和算法', '秦老师', '程序员必备', 15);

-- ----------------------------
-- Table structure for tb_graduate
-- ----------------------------
DROP TABLE IF EXISTS `tb_graduate`;
CREATE TABLE `tb_graduate`  (
  `stuID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tutor` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `researchDirection` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stuCredit` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`stuID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_graduate
-- ----------------------------
INSERT INTO `tb_graduate` VALUES ('201901', '李四', '女', '1995-1-1', '19级', '软件工程专硕', '太仓2班', '李老师', 40);
INSERT INTO `tb_graduate` VALUES ('201902', '李一', '女', '1995-1-1', '19级', '软件工程专硕', '太仓2班', '李老师', 20);
INSERT INTO `tb_graduate` VALUES ('201903', '王二', '女', '1995-1-1', '19级', '软件工程专硕', '太仓2班', '李老师', 30);
INSERT INTO `tb_graduate` VALUES ('201904', '孙四', '女', '1995-1-1', '19级', '软件工程专硕', '太仓2班', '李老师', 40);
INSERT INTO `tb_graduate` VALUES ('201905', '章六', '女', '1995-1-1', '19级', '软件工程专硕', '太仓2班', '李老师', 40);
INSERT INTO `tb_graduate` VALUES ('201906', '强哥', '女', '1995-1-1', '19级', '软件工程专硕', '太仓2班', '李老师', 30);
INSERT INTO `tb_graduate` VALUES ('201909', '张点', '男', '1998-1-1', '20', '软件工程专硕', '未知', 'AI', 0);

-- ----------------------------
-- Table structure for tb_gsc
-- ----------------------------
DROP TABLE IF EXISTS `tb_gsc`;
CREATE TABLE `tb_gsc`  (
  `courseID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`courseID`, `studentID`) USING BTREE,
  INDEX `underGraduate_foreign_key`(`studentID`) USING BTREE,
  CONSTRAINT `tb_gsc_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `tb_course` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_gsc_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `tb_graduate` (`stuID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_gsc
-- ----------------------------
INSERT INTO `tb_gsc` VALUES ('A001', '201901');
INSERT INTO `tb_gsc` VALUES ('A002', '201901');
INSERT INTO `tb_gsc` VALUES ('A003', '201901');
INSERT INTO `tb_gsc` VALUES ('A004', '201901');
INSERT INTO `tb_gsc` VALUES ('A001', '201902');
INSERT INTO `tb_gsc` VALUES ('A004', '201902');
INSERT INTO `tb_gsc` VALUES ('A002', '201903');
INSERT INTO `tb_gsc` VALUES ('A003', '201903');
INSERT INTO `tb_gsc` VALUES ('A004', '201903');
INSERT INTO `tb_gsc` VALUES ('A001', '201904');
INSERT INTO `tb_gsc` VALUES ('A002', '201904');
INSERT INTO `tb_gsc` VALUES ('A003', '201904');
INSERT INTO `tb_gsc` VALUES ('A005', '201904');
INSERT INTO `tb_gsc` VALUES ('A001', '201905');
INSERT INTO `tb_gsc` VALUES ('A002', '201905');
INSERT INTO `tb_gsc` VALUES ('A003', '201905');
INSERT INTO `tb_gsc` VALUES ('A004', '201905');
INSERT INTO `tb_gsc` VALUES ('A001', '201906');
INSERT INTO `tb_gsc` VALUES ('A003', '201906');
INSERT INTO `tb_gsc` VALUES ('A005', '201906');

-- ----------------------------
-- Table structure for tb_ugsc
-- ----------------------------
DROP TABLE IF EXISTS `tb_ugsc`;
CREATE TABLE `tb_ugsc`  (
  `courseID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`courseID`, `studentID`) USING BTREE,
  INDEX `underGraduate_foreign_key`(`studentID`) USING BTREE,
  CONSTRAINT `course_foreign_key` FOREIGN KEY (`courseID`) REFERENCES `tb_course` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `underGraduate_foreign_key` FOREIGN KEY (`studentID`) REFERENCES `tb_undergraduate` (`stuID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_ugsc
-- ----------------------------
INSERT INTO `tb_ugsc` VALUES ('A001', '201801');
INSERT INTO `tb_ugsc` VALUES ('A002', '201801');
INSERT INTO `tb_ugsc` VALUES ('A003', '201801');
INSERT INTO `tb_ugsc` VALUES ('A001', '201802');
INSERT INTO `tb_ugsc` VALUES ('A002', '201802');
INSERT INTO `tb_ugsc` VALUES ('A004', '201802');
INSERT INTO `tb_ugsc` VALUES ('A005', '201802');
INSERT INTO `tb_ugsc` VALUES ('A002', '201803');
INSERT INTO `tb_ugsc` VALUES ('A003', '201803');
INSERT INTO `tb_ugsc` VALUES ('A001', '201804');
INSERT INTO `tb_ugsc` VALUES ('A003', '201804');
INSERT INTO `tb_ugsc` VALUES ('A004', '201804');
INSERT INTO `tb_ugsc` VALUES ('A005', '201804');
INSERT INTO `tb_ugsc` VALUES ('A001', '201805');
INSERT INTO `tb_ugsc` VALUES ('A001', '201806');
INSERT INTO `tb_ugsc` VALUES ('A002', '201806');
INSERT INTO `tb_ugsc` VALUES ('A003', '201806');
INSERT INTO `tb_ugsc` VALUES ('A004', '201806');
INSERT INTO `tb_ugsc` VALUES ('A005', '201806');

-- ----------------------------
-- Table structure for tb_undergraduate
-- ----------------------------
DROP TABLE IF EXISTS `tb_undergraduate`;
CREATE TABLE `tb_undergraduate`  (
  `stuID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `banji` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `headmaster` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stuCredit` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`stuID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_undergraduate
-- ----------------------------
INSERT INTO `tb_undergraduate` VALUES ('201801', '公孙是', '男', '1999-1-1', '19级', '计算机科学', '165班', '六合老师', 40);
INSERT INTO `tb_undergraduate` VALUES ('201802', '孙悟空', '男', '1999-1-1', '19级', '计算机科学', '165班', '六合老师', 30);
INSERT INTO `tb_undergraduate` VALUES ('201803', '唐山', '男', '1999-1-1', '19级', '计算机科学', '165班', '六合老师', 20);
INSERT INTO `tb_undergraduate` VALUES ('201804', '猪八戒', '男', '1999-1-1', '19级', '计算机科学', '165班', '六合老师', 40);
INSERT INTO `tb_undergraduate` VALUES ('201805', '华农兄弟', '男', '1999-1-1', '19级', '计算机科学', '165班', '六合老师', 10);
INSERT INTO `tb_undergraduate` VALUES ('201806', 'CSGO', '男', '1999-1-1', '19级', '计算机科学', '165班', '六合老师', 50);
INSERT INTO `tb_undergraduate` VALUES ('201807', '范晓博', '男', '1997-12-05', '19', '计算机科学', '19本科班', '未知', 0);

SET FOREIGN_KEY_CHECKS = 1;
