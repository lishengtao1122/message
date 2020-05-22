/*
 Navicat Premium Data Transfer

 Source Server         : lst
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : message

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 22/05/2020 14:44:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Idempotent_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '幂等键',
  `task` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `param` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `try_sum` int(3) NULL DEFAULT 0 COMMENT '尝试次数',
  `sts` int(3) NULL DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `finish_date` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_idempotentKey`(`Idempotent_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES (1, '682536-682539', NULL, NULL, 0, 0, '结束流程实例:682536', '2020-05-22 10:12:08', NULL);
INSERT INTO `t_message` VALUES (2, '682536-682539', NULL, NULL, 0, 0, '结束流程实例:682536', '2020-05-22 10:16:18', NULL);
INSERT INTO `t_message` VALUES (3, '682536-682539', NULL, NULL, 0, 0, '结束流程实例:682536', '2020-05-22 10:20:44', NULL);
INSERT INTO `t_message` VALUES (4, '682536-682539', NULL, NULL, 0, 0, '结束流程实例:682536', '2020-05-22 10:22:41', NULL);
INSERT INTO `t_message` VALUES (5, '682536-682539', NULL, NULL, 0, 0, '结束流程实例:682536', '2020-05-22 10:24:04', NULL);
INSERT INTO `t_message` VALUES (6, '682536-682539', NULL, NULL, 0, 0, '结束流程实例:682536', '2020-05-22 10:25:13', NULL);
INSERT INTO `t_message` VALUES (7, '682536-682539', NULL, NULL, 0, 0, '结束流程实例:682536', '2020-05-22 10:26:33', NULL);

-- ----------------------------
-- Table structure for t_message_log
-- ----------------------------
DROP TABLE IF EXISTS `t_message_log`;
CREATE TABLE `t_message_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `m_id` int(11) NULL DEFAULT NULL COMMENT '消息Id',
  `msg` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
