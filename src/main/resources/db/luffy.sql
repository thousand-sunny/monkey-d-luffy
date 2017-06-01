/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : luffy

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-05-14 22:43:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_t`;
CREATE TABLE `sys_permission_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_code` varchar(20) DEFAULT NULL COMMENT '资源编码',
  `resource_name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `parent_code` varchar(20) DEFAULT NULL COMMENT '父节点',
  `operation_code` varchar(20) DEFAULT NULL COMMENT '操作编码',
  `operation_name` varchar(50) DEFAULT NULL COMMENT '操作名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_permission_t
-- ----------------------------
INSERT INTO `sys_permission_t` VALUES ('1000', 'SystemManage', '系统管理', '0', null, null, '2017-05-11 22:13:59', '2017-05-11 22:14:01');
INSERT INTO `sys_permission_t` VALUES ('1001', 'UserService', '用户服务', 'SystemManage', 'insert', '增加', '2017-05-11 22:08:56', '2017-05-11 22:08:58');
INSERT INTO `sys_permission_t` VALUES ('1002', 'UserService', '用户服务', 'SystemManage', 'update', '修改', '2017-05-11 22:08:56', '2017-05-11 22:08:58');
INSERT INTO `sys_permission_t` VALUES ('1003', 'UserService', '用户服务', 'SystemManage', 'delete', '删除', '2017-05-11 22:08:56', '2017-05-11 22:08:58');
INSERT INTO `sys_permission_t` VALUES ('1004', 'UserService', '用户服务', 'SystemManage', 'query', '查询', '2017-05-11 22:08:56', '2017-05-11 22:08:58');
INSERT INTO `sys_permission_t` VALUES ('1005', 'UserService', '用户服务', 'SystemManage', 'grant', '授权', '2017-05-11 22:08:56', '2017-05-11 22:08:58');

-- ----------------------------
-- Table structure for sys_role_permission_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_t`;
CREATE TABLE `sys_role_permission_t` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_permission_t
-- ----------------------------
INSERT INTO `sys_role_permission_t` VALUES ('1', '100', '1001');
INSERT INTO `sys_role_permission_t` VALUES ('2', '100', '1002');
INSERT INTO `sys_role_permission_t` VALUES ('3', '100', '1003');
INSERT INTO `sys_role_permission_t` VALUES ('4', '100', '1004');

-- ----------------------------
-- Table structure for sys_role_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_t`;
CREATE TABLE `sys_role_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_t
-- ----------------------------
INSERT INTO `sys_role_t` VALUES ('100', 'admin', '系统管理员', '1', '2017-05-11 22:16:12', '2017-05-11 22:16:14');

-- ----------------------------
-- Table structure for sys_user_role_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_t`;
CREATE TABLE `sys_user_role_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_role_index` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role_t
-- ----------------------------
INSERT INTO `sys_user_role_t` VALUES ('1', '1', '100');

-- ----------------------------
-- Table structure for sys_user_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_t`;
CREATE TABLE `sys_user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `status` varchar(2) NOT NULL DEFAULT '1' COMMENT '状态(1/正常，0/锁定)',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_t
-- ----------------------------
INSERT INTO `sys_user_t` VALUES ('1', 'luffy', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('3', 'luffy1', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('4', 'luffy1\r\n', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('5', 'luffy2', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('6', 'luffy3', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('7', 'luffy4', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('8', 'luffy5', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('9', 'luffy6', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('10', 'luffy7', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
INSERT INTO `sys_user_t` VALUES ('11', 'luffy8', '123456', 'Luffy', '1', 'luffy@op.com', '16866668888', '1', null, '2017-05-10 23:07:29', '2017-05-10 23:07:31');
