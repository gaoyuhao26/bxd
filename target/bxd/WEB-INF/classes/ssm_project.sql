/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : ssm_project

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 20/01/2021 21:58:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for deal_record
-- ----------------------------
DROP TABLE IF EXISTS `deal_record`;
CREATE TABLE `deal_record`  (
  `record_id` int NOT NULL AUTO_INCREMENT COMMENT '操作记录表id',
  `expensive_id` int NOT NULL COMMENT '报销单id',
  `em_id` int NOT NULL COMMENT '操作员工id',
  `deal_time` datetime NULL DEFAULT NULL COMMENT '处理方式',
  `deal_way` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理方式',
  `deal_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理结果',
  `comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理结果备注',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of deal_record
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dep_id` int NOT NULL COMMENT '部门id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '办公地点',
  PRIMARY KEY (`dep_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '开发部', '武汉智慧园16栋1楼');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `em_id` int NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `login_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `dep_id` int NOT NULL COMMENT '部门id',
  `position_id` int NOT NULL COMMENT '职位id',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态(0:离职 1:在职)',
  PRIMARY KEY (`em_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '905myx6esebmoembb9of95ot0550ee69', '李职员', '13100000001', 1, 1, 1);

-- ----------------------------
-- Table structure for expense_report
-- ----------------------------
DROP TABLE IF EXISTS `expense_report`;
CREATE TABLE `expense_report`  (
  `expense_id` int NOT NULL AUTO_INCREMENT COMMENT '报销单id',
  `cause` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报销类型',
  `em_id` int NOT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `next_deal_em` int NULL DEFAULT NULL COMMENT '待处理人',
  `total_amount` decimal(10, 0) NOT NULL COMMENT '报销总金额',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`expense_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报销单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expense_report
-- ----------------------------

-- ----------------------------
-- Table structure for expense_report_detail
-- ----------------------------
DROP TABLE IF EXISTS `expense_report_detail`;
CREATE TABLE `expense_report_detail`  (
  `expensive_detail_id` int NOT NULL AUTO_INCREMENT COMMENT '报销单细节表id',
  `expensive_id` int NULL DEFAULT NULL COMMENT '报销单id',
  `item` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` decimal(10, 0) NOT NULL COMMENT '费用明细',
  `comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '费用备注',
  PRIMARY KEY (`expensive_detail_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报销单细节表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expense_report_detail
-- ----------------------------

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `position_id` int NOT NULL AUTO_INCREMENT COMMENT '职位表id',
  `position_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职位名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`position_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '职位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, '普通员工', '2021-01-20 02:03:21');

SET FOREIGN_KEY_CHECKS = 1;
