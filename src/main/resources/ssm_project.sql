/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : ssm_project

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 04/03/2021 15:29:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for deal_record
-- ----------------------------
DROP TABLE IF EXISTS `deal_record`;
CREATE TABLE `deal_record`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作记录表id',
  `expensive_id` int(11) NOT NULL COMMENT '报销单id',
  `em_id` int(11) NOT NULL COMMENT '操作员工id',
  `deal_time` datetime NULL DEFAULT NULL COMMENT '处理时间',
  `deal_way` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理方式',
  `deal_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理结果',
  `comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理结果备注',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of deal_record
-- ----------------------------
INSERT INTO `deal_record` VALUES (1, 2, 2, '2021-02-27 23:01:10', '审批通过', '已处理', NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '办公地点',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态 1：启动 0：停用',
  PRIMARY KEY (`dep_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '开发部', '武汉智慧园16栋1楼顶顶顶顶踩踩踩踩踩尺寸v别难为你认同和there12sdfghjmhgfdsaDFGHJFDSASDFGHJKLKJHGFDSDFGHJKLKJHGFDSSDFGHJKLKJHGF', 1);
INSERT INTO `department` VALUES (2, '销售部', '武汉智慧园16栋1楼', 1);
INSERT INTO `department` VALUES (3, '技术部门', '武汉智慧园16栋1楼', 1);
INSERT INTO `department` VALUES (4, '财务部门', '武汉智慧园16栋1楼', 1);
INSERT INTO `department` VALUES (5, '人力资源部门', '武汉智慧园16栋1楼', 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `em_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `login_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `dep_id` int(11) NOT NULL COMMENT '部门id',
  `position_id` int(11) NOT NULL COMMENT '职位id',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态(0:离职 1:在职)',
  `head_portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`em_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '555xbef5mxoe5tobefsb5t0o5y56sbt5', '李职员', '13100000001', 1, 1, 1, '308ea37e4b3c4197b231261fb313f6dfu=2961930554,3770526367&fm=15&gp=0.jpg');
INSERT INTO `employee` VALUES (2, '905myx6esebmoembb9of95ot0550ee69', '刘经理', '3223', 2, 2, 1, '');
INSERT INTO `employee` VALUES (3, '905myx6esebmoembb9of95ot0550ee69', '木子', '8888888', 3, 3, 1, '');
INSERT INTO `employee` VALUES (4, '905myx6esebmoembb9of95ot0550ee69', '小酒', 'sdfsgbnhj', 4, 4, 1, '');
INSERT INTO `employee` VALUES (5, 'xe009emoetb05f00o59y6yeem0sfbsso', '婵婵', '3454646', 5, 5, 1, '');
INSERT INTO `employee` VALUES (10, '905myx6esebmoembb9of95ot0550ee69', '546', '5112', 1, 1, 1, NULL);
INSERT INTO `employee` VALUES (11, '905myx6esebmoembb9of95ot0550ee69', '张三', '21345', 2, 6, 1, NULL);
INSERT INTO `employee` VALUES (12, '905myx6esebmoembb9of95ot0550ee69', '王五', '23456', 4, 8, 1, NULL);
INSERT INTO `employee` VALUES (13, '905myx6esebmoembb9of95ot0550ee69', '赵六', '2345678654', 5, 9, 1, NULL);

-- ----------------------------
-- Table structure for expense_report
-- ----------------------------
DROP TABLE IF EXISTS `expense_report`;
CREATE TABLE `expense_report`  (
  `expense_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报销单id',
  `cause` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '报销类型',
  `em_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `next_deal_em` int(11) NULL DEFAULT NULL COMMENT '待处理人',
  `total_amount` decimal(10, 0) NOT NULL COMMENT '报销总金额',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`expense_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '报销单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of expense_report
-- ----------------------------
INSERT INTO `expense_report` VALUES (2, '差旅费', 12, '2020-12-17 01:01:46', 1, 123, '通过');
INSERT INTO `expense_report` VALUES (6, '差旅费', 1, '2021-03-01 01:24:08', 2, 2000, '通过');
INSERT INTO `expense_report` VALUES (7, '公司团建', 11, '2021-03-01 01:38:04', 5, 3855, '通过');
INSERT INTO `expense_report` VALUES (8, '公司生活消费', 13, '2021-03-01 01:47:10', 2, 23000, '通过');
INSERT INTO `expense_report` VALUES (9, '材料采购', 2, '2021-03-01 14:52:11', 1, 5000, '通过');
INSERT INTO `expense_report` VALUES (10, '办公费', 4, '2021-03-01 22:14:54', 1, 800, '通过');
INSERT INTO `expense_report` VALUES (11, '办公费', 12, '2021-03-01 22:15:35', 2, 800, '通过');
INSERT INTO `expense_report` VALUES (12, '办公费', 10, '2021-03-01 22:16:16', 4, 800, '未处理');
INSERT INTO `expense_report` VALUES (13, '办公费', 10, '2021-03-01 22:16:18', 4, 800, '未处理');
INSERT INTO `expense_report` VALUES (14, '办公费', 10, '2021-03-01 22:16:18', 4, 800, '未处理');
INSERT INTO `expense_report` VALUES (15, '办公费', 1, '2021-03-01 22:16:37', 1, 0, '未处理');
INSERT INTO `expense_report` VALUES (16, '办公费', 1, '2021-03-01 22:16:37', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (17, '办公费', 1, '2021-03-01 22:16:47', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (18, '差旅费', 11, '2021-03-01 22:30:52', 3, 8, '通过');
INSERT INTO `expense_report` VALUES (19, '办公费', 5, '2021-03-01 22:31:34', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (20, '办公', 1, '2021-03-01 22:32:27', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (21, '', 1, '2021-03-01 22:35:20', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (22, '', 1, '2021-03-23 22:36:57', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (23, '', 1, '2021-03-01 22:37:19', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (24, '', 1, '2021-03-01 22:37:37', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (25, '', 1, '2021-03-01 22:37:41', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (26, '', 1, '2021-03-01 22:37:46', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (27, '', 1, '2021-03-01 22:38:58', 1, 0, '通过');
INSERT INTO `expense_report` VALUES (28, '水电费', 12, '2021-03-01 22:53:41', 1, 23232, '通过');
INSERT INTO `expense_report` VALUES (29, '2314', 1, '2021-03-01 22:54:31', 1, 243, '通过');
INSERT INTO `expense_report` VALUES (30, 'hhhh', 1, '2021-03-02 22:04:52', 1, 34567, '通过');
INSERT INTO `expense_report` VALUES (31, 'fghj', 1, '2021-03-02 23:28:03', 1, 14626, '通过');
INSERT INTO `expense_report` VALUES (32, 'dwefwef', 1, '2021-03-03 00:43:13', 1, 4475, '通过');
INSERT INTO `expense_report` VALUES (33, 'lmklm', 1, '2021-03-03 00:53:13', 1, 877, '通过');

-- ----------------------------
-- Table structure for expense_report_detail
-- ----------------------------
DROP TABLE IF EXISTS `expense_report_detail`;
CREATE TABLE `expense_report_detail`  (
  `expensive_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报销单细节表id',
  `expensive_id` int(11) NULL DEFAULT NULL COMMENT '报销单id',
  `item` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报销项目',
  `amount` decimal(10, 0) NOT NULL COMMENT '费用明细',
  `comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '费用备注',
  PRIMARY KEY (`expensive_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '报销单细节表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of expense_report_detail
-- ----------------------------
INSERT INTO `expense_report_detail` VALUES (21, 2, '车票', 123, '');
INSERT INTO `expense_report_detail` VALUES (22, 7, '饮料食品', 500, '30人份');
INSERT INTO `expense_report_detail` VALUES (23, 7, '车票', 3000, '来回大巴车票非，单票50');
INSERT INTO `expense_report_detail` VALUES (24, 8, '电费', 2800, '一月用电量');
INSERT INTO `expense_report_detail` VALUES (25, 8, '水费', 200, '一月份的用水费');
INSERT INTO `expense_report_detail` VALUES (26, 8, '房租', 20000, '一月份房租');
INSERT INTO `expense_report_detail` VALUES (27, 9, '', 5000, '');
INSERT INTO `expense_report_detail` VALUES (28, 10, '谈判吃饭', 800, '');
INSERT INTO `expense_report_detail` VALUES (29, 11, '谈判吃饭', 800, '');
INSERT INTO `expense_report_detail` VALUES (30, 12, '谈判吃饭', 800, '');
INSERT INTO `expense_report_detail` VALUES (31, 13, '谈判吃饭', 800, '');
INSERT INTO `expense_report_detail` VALUES (32, 14, '谈判吃饭', 800, '');
INSERT INTO `expense_report_detail` VALUES (33, 18, '地铁', 8, '顺义到朝阳');
INSERT INTO `expense_report_detail` VALUES (34, 28, '范围发', 23232, '');
INSERT INTO `expense_report_detail` VALUES (37, 31, '的', 12312, 'ewdqw');
INSERT INTO `expense_report_detail` VALUES (38, 31, 'deqwedq', 2314, 'dff');
INSERT INTO `expense_report_detail` VALUES (39, 32, 'dfwef', 3244, 'wqeqw');
INSERT INTO `expense_report_detail` VALUES (40, 32, 'qwedq', 1231, 'wdqw');
INSERT INTO `expense_report_detail` VALUES (41, 33, 'jnj', 877, 'mkmk');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `position_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职位表id',
  `position_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '职位名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '状态 1：启动 0：停用',
  PRIMARY KEY (`position_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '职位表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, '总经理', '2021-01-20 02:03:21', 1);
INSERT INTO `position` VALUES (2, '销售部门经理', '2021-03-07 23:47:02', 1);
INSERT INTO `position` VALUES (3, '技术部门经理', '2021-02-13 23:47:02', 1);
INSERT INTO `position` VALUES (4, '财务部门经理', '2021-02-13 23:47:02', 1);
INSERT INTO `position` VALUES (5, '人力资源部门经理', '2021-02-13 23:47:02', 1);
INSERT INTO `position` VALUES (6, '销售部门员工', '2021-02-28 15:18:15', 1);
INSERT INTO `position` VALUES (7, '技术部门员工', '2021-02-28 15:18:49', 1);
INSERT INTO `position` VALUES (8, '财务部门员工', '2021-02-27 15:25:39', 1);
INSERT INTO `position` VALUES (9, '人力资源部门员工', '2021-02-28 23:12:43', 1);

SET FOREIGN_KEY_CHECKS = 1;
