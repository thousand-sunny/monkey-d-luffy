# monkey-d-luffy
monkey-d-luffy
/*
MySQL Backup
Source Server Version: 5.5.28
Source Database: luffy
Date: 2017/5/8 星期一 22:06:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `sys_permission_t`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_t`;
CREATE TABLE `sys_permission_t` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_code` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '资源编码',
  `resource_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '资源名称',
  `parent_code` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '父节点',
  `operation_code` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '操作编码',
  `operation_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '操作名称',
  `uri` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  KEY `id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_permission_t`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_t`;
CREATE TABLE `sys_role_permission_t` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_t`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_t`;
CREATE TABLE `sys_role_t` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色描述',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  KEY `id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_role_t`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_t`;
CREATE TABLE `sys_user_role_t` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_t`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_t`;
CREATE TABLE `sys_user_t` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `status` varchar(2) CHARACTER SET utf8mb4 NOT NULL DEFAULT '1' COMMENT '状态(1/正常，2/锁定)',
  `email` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '电话',
  `sex` varchar(1) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
