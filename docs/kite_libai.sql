/*
 Navicat Premium Dump SQL

 Source Server         : kite-local
 Source Server Type    : MySQL
 Source Server Version : 50723 (5.7.23)
 Source Host           : localhost:3306
 Source Schema         : kite_libai

 Target Server Type    : MySQL
 Target Server Version : 50723 (5.7.23)
 File Encoding         : 65001

 Date: 21/08/2026 18:36:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_account_login_log
-- ----------------------------
DROP TABLE IF EXISTS `auth_account_login_log`;
CREATE TABLE `auth_account_login_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `account_type` varchar(32) NOT NULL DEFAULT '' COMMENT 'accountType',
  `login_type` varchar(32) NOT NULL DEFAULT '' COMMENT 'loginType',
  `login_channel` varchar(32) NOT NULL DEFAULT '' COMMENT 'loginChannel',
  `app_name` varchar(32) DEFAULT '' COMMENT 'appName',
  `app_version` varchar(32) DEFAULT '' COMMENT 'appVersion',
  `device_id` varchar(128) DEFAULT '' COMMENT 'deviceId',
  `ip` varchar(50) DEFAULT '' COMMENT 'ip',
  `user_agent` text COMMENT 'userAgent',
  `login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录记录';

-- ----------------------------
-- Records of auth_account_login_log
-- ----------------------------
BEGIN;
INSERT INTO `auth_account_login_log` (`id`, `account_id`, `account_type`, `login_type`, `login_channel`, `app_name`, `app_version`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2080138115908927489, 2054830914063605762, '', 'message_login', 'app', '', '', '', '', NULL, '2026-07-23 11:49:09', '2026-07-23 11:49:09');
INSERT INTO `auth_account_login_log` (`id`, `account_id`, `account_type`, `login_type`, `login_channel`, `app_name`, `app_version`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2080138976185843713, 2054830914063605762, '', 'message_login', 'app', '', '', '', '', NULL, '2026-07-23 11:52:34', '2026-07-23 11:52:34');
INSERT INTO `auth_account_login_log` (`id`, `account_id`, `account_type`, `login_type`, `login_channel`, `app_name`, `app_version`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2080196357452210178, 2054830914063605762, '', 'message_login', 'app', '', '', '', '', NULL, '2026-07-23 15:40:34', '2026-07-23 15:40:34');
INSERT INTO `auth_account_login_log` (`id`, `account_id`, `account_type`, `login_type`, `login_channel`, `app_name`, `app_version`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2080197468745637890, 2053751786136739842, '', 'message_login', 'app', '', '', '', '', NULL, '2026-07-23 15:44:59', '2026-07-23 15:44:59');
INSERT INTO `auth_account_login_log` (`id`, `account_id`, `account_type`, `login_type`, `login_channel`, `app_name`, `app_version`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2080229122465861634, 2053751786136739842, '', 'message_login', 'app', '', '', '', '', NULL, '2026-07-23 17:50:46', '2026-07-23 17:50:46');
COMMIT;

-- ----------------------------
-- Table structure for auth_account_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `auth_account_refresh_token`;
CREATE TABLE `auth_account_refresh_token` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `refresh_token` varchar(256) NOT NULL COMMENT 'refresh_token',
  `device_id` varchar(128) DEFAULT '' COMMENT 'deviceId',
  `ip` varchar(50) DEFAULT '' COMMENT 'ip',
  `expires_time` datetime NOT NULL COMMENT '凭证过期时间',
  `login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='auth_account_refresh_token';

-- ----------------------------
-- Records of auth_account_refresh_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` bigint(20) NOT NULL COMMENT '权限id',
  `parent_id` bigint(20) NOT NULL COMMENT '父级权限id',
  `name` varchar(64) NOT NULL COMMENT '权限名称',
  `permission` varchar(64) DEFAULT NULL COMMENT '权限标识',
  `orders` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限';

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
BEGIN;
INSERT INTO `auth_permission` (`id`, `parent_id`, `name`, `permission`, `orders`, `create_time`, `update_time`) VALUES (2088178825836961793, 3035907647007617026, '编辑权限', 'system:permission:update', 0, '2026-08-14 16:20:03', '2026-08-14 16:20:03');
INSERT INTO `auth_permission` (`id`, `parent_id`, `name`, `permission`, `orders`, `create_time`, `update_time`) VALUES (2088179449857122305, 3035907700317220865, '设置权限', 'system:role:assign', 0, '2026-08-14 16:22:32', '2026-08-14 16:22:32');
INSERT INTO `auth_permission` (`id`, `parent_id`, `name`, `permission`, `orders`, `create_time`, `update_time`) VALUES (2088185887480410113, 3035907647007617026, '权限删除', 'system:permission:delete', 0, '2026-08-14 16:48:07', '2026-08-14 16:48:07');
INSERT INTO `auth_permission` (`id`, `parent_id`, `name`, `permission`, `orders`, `create_time`, `update_time`) VALUES (3034408848011427841, -1, '系统管理', 'system:manager', 9000, '2019-01-04 16:57:26', '2020-08-09 10:21:03');
INSERT INTO `auth_permission` (`id`, `parent_id`, `name`, `permission`, `orders`, `create_time`, `update_time`) VALUES (3035907496964780034, 3034408848011427841, '用户管理', 'system:user', 2, '2019-01-08 20:12:32', '2019-01-12 09:16:25');
INSERT INTO `auth_permission` (`id`, `parent_id`, `name`, `permission`, `orders`, `create_time`, `update_time`) VALUES (3035907647007617021, 3035907647007617026, '新增权限', 'system:permission:create', 0, '2026-08-14 16:16:36', '2026-08-14 16:16:39');
INSERT INTO `auth_permission` (`id`, `parent_id`, `name`, `permission`, `orders`, `create_time`, `update_time`) VALUES (3035907647007617026, 3034408848011427841, '权限管理', 'system:permission', 5, '2019-01-08 20:13:07', '2020-07-27 16:57:21');
INSERT INTO `auth_permission` (`id`, `parent_id`, `name`, `permission`, `orders`, `create_time`, `update_time`) VALUES (3035907700317220865, 3034408848011427841, '角色管理', 'system:role', 3, '2019-01-08 20:13:20', '2019-01-08 20:13:20');
COMMIT;

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` bigint(20) NOT NULL COMMENT '角色id',
  `code` varchar(64) NOT NULL COMMENT '角色编码',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `description` varchar(255) NOT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_code` (`code`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
BEGIN;
INSERT INTO `auth_role` (`id`, `code`, `name`, `description`, `create_time`, `update_time`) VALUES (1217016869463126017, 'kite-admin', '管理员', '系统管理员', '2020-01-14 17:33:27', '2020-07-27 16:56:39');
INSERT INTO `auth_role` (`id`, `code`, `name`, `description`, `create_time`, `update_time`) VALUES (1293792194569768962, 'kite-001', '内容管理员', '内容管理员', '2020-08-13 14:11:11', '2020-08-13 14:11:11');
COMMIT;

-- ----------------------------
-- Table structure for auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_role_permission` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联';

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (1471048486521774082, 1217016869463126017, 3034408848011427841, '2021-12-15 17:24:25');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (1471048486521774083, 1217016869463126017, 3035907496964780034, '2021-12-15 17:24:25');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (1471048486525968386, 1217016869463126017, 3035907700317220865, '2021-12-15 17:24:25');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (1471048486525968387, 1217016869463126017, 3035907647007617026, '2021-12-15 17:24:25');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (1471048486525968388, 1217016869463126017, 3035907647007617021, '2026-08-14 16:18:57');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (1471048486525968389, 1217016869463126017, 2088179449857122305, '2026-08-14 16:24:31');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (2088180245457870849, 1293792194569768962, 3035907700317220865, '2026-08-14 16:25:42');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (2088180245462065154, 1293792194569768962, 2088179449857122305, '2026-08-14 16:25:42');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (2088180245466259457, 1293792194569768962, 3034408848011427841, '2026-08-14 16:25:42');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (2088186064257740802, 1217016869463126017, 2088178825836961793, '2026-08-14 16:48:49');
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (2088186064257740803, 1217016869463126017, 2088185887480410113, '2026-08-14 16:48:49');
COMMIT;

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `username` varchar(254) NOT NULL COMMENT '用户名',
  `password` varchar(254) NOT NULL COMMENT '密码',
  `name` varchar(254) NOT NULL DEFAULT '' COMMENT '姓名',
  `mobile` varchar(254) NOT NULL DEFAULT '' COMMENT '手机号',
  `avatar` varchar(254) NOT NULL DEFAULT '' COMMENT '头像',
  `email` varchar(254) NOT NULL DEFAULT '' COMMENT '邮箱',
  `status` varchar(64) NOT NULL DEFAULT 'NORMAL' COMMENT '用户状态:NORMAL-正常,LOCKING-锁定',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_username` (`username`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号信息';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
BEGIN;
INSERT INTO `auth_user` (`id`, `username`, `password`, `name`, `mobile`, `avatar`, `email`, `status`, `create_time`, `update_time`) VALUES (1216914154267365377, 'admin', '$2a$10$/ybvoPjVLu.AQ.6OTSDPu.6QxTCegRS2h9QOyVaOBRUTMWhnR.4RG', 'kite', '13683238601', '', 'kite@iiifi.com', 'NORMAL', '2020-01-14 10:45:18', '2026-08-14 15:59:30');
COMMIT;

-- ----------------------------
-- Table structure for auth_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_login_log`;
CREATE TABLE `auth_user_login_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `device_id` varchar(128) DEFAULT '' COMMENT 'deviceId',
  `ip` varchar(50) DEFAULT '' COMMENT 'ip',
  `user_agent` text COMMENT 'userAgent',
  `login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录记录';

-- ----------------------------
-- Records of auth_user_login_log
-- ----------------------------
BEGIN;
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2052344439665348610, 1216914154267365377, '1221221212121', '', NULL, '2026-05-07 19:07:00', '2026-05-07 19:07:00');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053749456746127362, 1216914154267365377, '1221221212121', '', NULL, '2026-05-11 16:10:02', '2026-05-11 16:10:02');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053749496118059009, 1216914154267365377, '', '', NULL, '2026-05-11 16:10:11', '2026-05-11 16:10:11');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053750373163802626, 1216914154267365377, '', '', NULL, '2026-05-11 16:13:40', '2026-05-11 16:13:40');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053751786166099970, 2053751786136739842, '', '', NULL, '2026-05-11 16:19:17', '2026-05-11 16:19:17');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053753698965893121, 2053753698923950082, '', '', NULL, '2026-05-11 16:26:53', '2026-05-11 16:26:53');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053758667932680193, 1216914154267365377, '', '', NULL, '2026-05-11 16:46:38', '2026-05-11 16:46:38');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053758924913491970, 2053753698923950082, '', '', NULL, '2026-05-11 16:47:39', '2026-05-11 16:47:39');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053761697935540226, 2053753698923950082, '', '', NULL, '2026-05-11 16:58:40', '2026-05-11 16:58:40');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053761734673448962, 2053753698923950082, '', '', NULL, '2026-05-11 16:58:49', '2026-05-11 16:58:49');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053785092282765314, 2053753698923950082, '', '', NULL, '2026-05-11 18:31:38', '2026-05-11 18:31:38');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053787872208625665, 2053753698923950082, '', '', NULL, '2026-05-11 18:42:41', '2026-05-11 18:42:41');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2053792318493822977, 2053753698923950082, '', '', NULL, '2026-05-11 19:00:21', '2026-05-11 19:00:21');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054032182371946498, 2053753698923950082, '21221121222121', '', NULL, '2026-05-12 10:53:29', '2026-05-12 10:53:29');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054517506994982914, 2053753698923950082, '', '', NULL, '2026-05-13 19:01:59', '2026-05-13 19:01:59');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054517639824396289, 2053753698923950082, '', '', NULL, '2026-05-13 19:02:31', '2026-05-13 19:02:31');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054520492408246274, 2054520492349526017, '', '', NULL, '2026-05-13 19:13:51', '2026-05-13 19:13:51');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054520509525200897, 2054520492349526017, '', '', NULL, '2026-05-13 19:13:55', '2026-05-13 19:13:55');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054520514105380865, 2054520492349526017, '', '', NULL, '2026-05-13 19:13:56', '2026-05-13 19:13:56');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054520517670539266, 2054520492349526017, '', '', NULL, '2026-05-13 19:13:57', '2026-05-13 19:13:57');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054520521319583746, 2054520492349526017, '', '', NULL, '2026-05-13 19:13:58', '2026-05-13 19:13:58');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054520524746330113, 2054520492349526017, '', '', NULL, '2026-05-13 19:13:59', '2026-05-13 19:13:59');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054520535013986305, 2054520492349526017, '', '', NULL, '2026-05-13 19:14:01', '2026-05-13 19:14:01');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054521862309904386, 2054520492349526017, '', '', NULL, '2026-05-13 19:19:18', '2026-05-13 19:19:18');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054826832619929602, 2053753698923950082, '', '', NULL, '2026-05-14 15:31:08', '2026-05-14 15:31:08');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2054830914172657665, 2054830914063605762, '', '', NULL, '2026-05-14 15:47:22', '2026-05-14 15:47:22');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2055232987540258818, 1216914154267365377, '', '', NULL, '2026-05-15 18:25:03', '2026-05-15 18:25:03');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2055233031169409026, 1216914154267365377, '', '', NULL, '2026-05-15 18:25:14', '2026-05-15 18:25:14');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2055233825327316993, 1216914154267365377, '', '', NULL, '2026-05-15 18:28:23', '2026-05-15 18:28:23');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2055235358437703681, 1216914154267365377, '', '', NULL, '2026-05-15 18:34:29', '2026-05-15 18:34:29');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2056222351103623169, 1216914154267365377, '', '', NULL, '2026-05-18 11:56:26', '2026-05-18 11:56:26');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2070420278806171650, 1216914154267365377, '', '', NULL, '2026-06-26 16:13:56', '2026-06-26 16:13:56');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2070422380756455426, 1216914154267365377, '', '', NULL, '2026-06-26 16:22:17', '2026-06-26 16:22:17');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2070430911089917953, 1216914154267365377, '', '', NULL, '2026-06-26 16:56:11', '2026-06-26 16:56:11');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2070432032667779074, 1216914154267365377, '', '', NULL, '2026-06-26 17:00:38', '2026-06-26 17:00:38');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2070441836861763586, 1216914154267365377, '', '', NULL, '2026-06-26 17:39:36', '2026-06-26 17:39:36');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2071502208268042242, 1216914154267365377, '', '', NULL, '2026-06-29 15:53:08', '2026-06-29 15:53:08');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2071502483519242242, 1216914154267365377, '', '', NULL, '2026-06-29 15:54:13', '2026-06-29 15:54:13');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2071502538372349954, 1216914154267365377, '', '', NULL, '2026-06-29 15:54:26', '2026-06-29 15:54:26');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2071503598088421377, 1216914154267365377, '', '', NULL, '2026-06-29 15:58:39', '2026-06-29 15:58:39');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2071509650456510466, 1216914154267365377, '', '', NULL, '2026-06-29 16:22:42', '2026-06-29 16:22:42');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2071512525685456897, 1216914154267365377, '', '', NULL, '2026-06-29 16:34:08', '2026-06-29 16:34:08');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2071513807481536514, 1216914154267365377, '', '', NULL, '2026-06-29 16:39:13', '2026-06-29 16:39:13');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2071842468785033217, 1216914154267365377, '', '', NULL, '2026-06-30 14:25:12', '2026-06-30 14:25:12');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088174030430429186, 1216914154267365377, '', '', NULL, '2026-08-14 16:01:00', '2026-08-14 16:01:00');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088175335152553986, 1216914154267365377, '', '', NULL, '2026-08-14 16:06:11', '2026-08-14 16:06:11');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088175610554798082, 1216914154267365377, '', '', NULL, '2026-08-14 16:07:17', '2026-08-14 16:07:17');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088176685303255041, 1216914154267365377, '', '', NULL, '2026-08-14 16:11:33', '2026-08-14 16:11:33');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088178324009459713, 1216914154267365377, '', '', NULL, '2026-08-14 16:18:04', '2026-08-14 16:18:04');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088179664152502273, 1216914154267365377, '', '', NULL, '2026-08-14 16:23:23', '2026-08-14 16:23:23');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088181589040242690, 1216914154267365377, '', '', NULL, '2026-08-14 16:31:02', '2026-08-14 16:31:02');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088184409961328641, 1216914154267365377, '', '', NULL, '2026-08-14 16:42:15', '2026-08-14 16:42:15');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2088185810376519682, 1216914154267365377, '', '', NULL, '2026-08-14 16:47:48', '2026-08-14 16:47:48');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2089198489656446977, 1216914154267365377, '', '', NULL, '2026-08-17 11:51:50', '2026-08-17 11:51:50');
INSERT INTO `auth_user_login_log` (`id`, `user_id`, `device_id`, `ip`, `user_agent`, `login_time`, `create_time`) VALUES (2089263644931084289, 1216914154267365377, '', '', NULL, '2026-08-17 16:10:44', '2026-08-17 16:10:44');
COMMIT;

-- ----------------------------
-- Table structure for auth_user_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_refresh_token`;
CREATE TABLE `auth_user_refresh_token` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `refresh_token` varchar(256) NOT NULL COMMENT 'refresh_token',
  `device_id` varchar(128) DEFAULT '' COMMENT 'deviceId',
  `ip` varchar(50) DEFAULT '' COMMENT 'ip',
  `expires_time` datetime NOT NULL COMMENT '凭证过期时间',
  `login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='auth_account_refresh_token';

-- ----------------------------
-- Records of auth_user_refresh_token
-- ----------------------------
BEGIN;
INSERT INTO `auth_user_refresh_token` (`id`, `user_id`, `refresh_token`, `device_id`, `ip`, `expires_time`, `login_time`, `update_time`) VALUES (2089198489597726722, 1216914154267365377, 'fe932abb471eb99782b41d471b42a241', '', '', '2026-08-17 14:51:50', '2026-08-17 11:51:50', '2026-08-17 11:51:50');
INSERT INTO `auth_user_refresh_token` (`id`, `user_id`, `refresh_token`, `device_id`, `ip`, `expires_time`, `login_time`, `update_time`) VALUES (2089263644893335553, 1216914154267365377, '2dd546f136651046f9287d2e8fe0c5c7', '', '', '2026-08-21 21:34:00', '2026-08-17 16:10:44', '2026-08-21 18:34:00');
COMMIT;

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_user_role` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
BEGIN;
INSERT INTO `auth_user_role` (`id`, `user_id`, `role_id`, `create_time`) VALUES (1236683336013959170, 1216914154267365377, 1217016869463126017, '2020-03-09 00:00:58');
INSERT INTO `auth_user_role` (`id`, `user_id`, `role_id`, `create_time`) VALUES (1237642464832098305, 1237400800515375105, 1217016869463126017, '2020-03-11 15:32:13');
INSERT INTO `auth_user_role` (`id`, `user_id`, `role_id`, `create_time`) VALUES (1293798585980186625, 1293791646034497537, 1293792194569768962, '2020-08-13 14:36:35');
INSERT INTO `auth_user_role` (`id`, `user_id`, `role_id`, `create_time`) VALUES (1293798603365576705, 1293791723117416449, 1293792194569768962, '2020-08-13 14:36:39');
INSERT INTO `auth_user_role` (`id`, `user_id`, `role_id`, `create_time`) VALUES (1377875172308049921, 1216914154267365377, 1293792194569768962, '2021-04-02 14:46:55');
INSERT INTO `auth_user_role` (`id`, `user_id`, `role_id`, `create_time`) VALUES (1382632098598338562, 1380405525467897857, 1217016869463126017, '2021-04-15 17:49:15');
COMMIT;

-- ----------------------------
-- Table structure for id_alloc
-- ----------------------------
DROP TABLE IF EXISTS `id_alloc`;
CREATE TABLE `id_alloc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_type` varchar(64) NOT NULL COMMENT '业务标识',
  `max_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '当前已分配的最大ID',
  `step` int(11) NOT NULL DEFAULT '1000' COMMENT '每次取的号段长度',
  `max_step` int(11) NOT NULL DEFAULT '1' COMMENT '随机步进上限，1=严格递增无浪费，越大越模糊越浪费',
  `version` int(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_biz_type` (`biz_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='号段分配表';

-- ----------------------------
-- Records of id_alloc
-- ----------------------------
BEGIN;
INSERT INTO `id_alloc` (`id`, `biz_type`, `max_id`, `step`, `max_step`, `version`, `update_time`) VALUES (1, 'unique_id', 10001899000, 150000, 1, 241, '2026-08-21 18:36:18');
INSERT INTO `id_alloc` (`id`, `biz_type`, `max_id`, `step`, `max_step`, `version`, `update_time`) VALUES (2, 'order_no', 10007560000, 300000, 10, 269, '2026-08-21 18:36:18');
INSERT INTO `id_alloc` (`id`, `biz_type`, `max_id`, `step`, `max_step`, `version`, `update_time`) VALUES (3, 'user_no', 10003735000, 300000, 3, 306, '2026-08-21 18:36:18');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级权限ID',
  `name` varchar(100) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限标识',
  `type` varchar(20) NOT NULL COMMENT '权限类型：MENU-菜单，BUTTON-按钮，API-API接口',
  `path` varchar(200) DEFAULT NULL COMMENT '路由路径',
  `component` varchar(200) DEFAULT NULL COMMENT '组件路径',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `visible` tinyint(1) DEFAULT '1' COMMENT '是否可见：0-隐藏，1-显示',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (1, 0, '系统管理', 'system', 'MENU', '/system', 'Layout', 'Setting', 1, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (2, 0, '用户管理', 'user', 'MENU', '/system/users', 'Users', 'User', 2, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (3, 0, '角色管理', 'role', 'MENU', '/system/roles', 'Roles', 'UserFilled', 3, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (4, 0, '权限管理', 'permission', 'MENU', '/system/permissions', 'Permissions', 'Lock', 4, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (5, 2, '新增用户', 'user:add', 'BUTTON', NULL, NULL, NULL, 1, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (6, 2, '编辑用户', 'user:edit', 'BUTTON', NULL, NULL, NULL, 2, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (7, 2, '删除用户', 'user:delete', 'BUTTON', NULL, NULL, NULL, 3, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (8, 3, '新增角色', 'role:add', 'BUTTON', NULL, NULL, NULL, 1, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (9, 3, '编辑角色', 'role:edit', 'BUTTON', NULL, NULL, NULL, 2, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (10, 3, '删除角色', 'role:delete', 'BUTTON', NULL, NULL, NULL, 3, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (11, 4, '新增权限', 'permission:add', 'BUTTON', NULL, NULL, NULL, 1, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (12, 4, '编辑权限', 'permission:edit', 'BUTTON', NULL, NULL, NULL, 2, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `permission_code`, `type`, `path`, `component`, `icon`, `sort`, `visible`, `status`, `remark`, `create_time`, `update_time`) VALUES (13, 4, '删除权限', 'permission:delete', 'BUTTON', NULL, NULL, NULL, 3, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `data_scope` tinyint(1) DEFAULT '1' COMMENT '数据范围：1-全部数据，2-本部门及以下，3-本部门，4-仅本人',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `role_code`, `role_name`, `description`, `data_scope`, `sort`, `status`, `remark`, `create_time`, `update_time`) VALUES (1, 'SUPER_ADMIN', '超级管理员', '拥有所有权限', 1, 1, 1, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (1, 1, 1, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (2, 1, 2, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (3, 1, 3, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (4, 1, 4, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (5, 1, 5, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (6, 1, 6, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (7, 1, 7, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (8, 1, 8, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (9, 1, 9, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (10, 1, 10, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (11, 1, 11, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (12, 1, 12, '2026-05-19 19:15:37');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `create_time`) VALUES (13, 1, 13, '2026-05-19 19:15:37');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别：0-未知，1-男，2-女',
  `status` varchar(20) DEFAULT 'NORMAL' COMMENT '状态：NORMAL-正常，LOCKED-锁定，DISABLED-禁用',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_mobile` (`mobile`),
  KEY `idx_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `mobile`, `email`, `avatar`, `gender`, `status`, `last_login_time`, `last_login_ip`, `create_time`, `update_time`) VALUES (1, 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '系统管理员', '13800138000', 'admin@example.com', NULL, 0, 'NORMAL', NULL, NULL, '2026-05-19 19:15:37', '2026-05-19 19:15:37');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_role` (`user_id`,`role_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `create_time`) VALUES (1, 1, 1, '2026-05-19 19:15:37');
COMMIT;

-- ----------------------------
-- Table structure for t_base_prohibited_word
-- ----------------------------
DROP TABLE IF EXISTS `t_base_prohibited_word`;
CREATE TABLE `t_base_prohibited_word` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `word` varchar(64) NOT NULL DEFAULT '' COMMENT '词语',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否生效',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='违禁词';

-- ----------------------------
-- Records of t_base_prohibited_word
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_base_sensitive_word
-- ----------------------------
DROP TABLE IF EXISTS `t_base_sensitive_word`;
CREATE TABLE `t_base_sensitive_word` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `word` varchar(64) NOT NULL DEFAULT '' COMMENT '词语',
  `status` tinyint(1) NOT NULL COMMENT '是否生效',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='敏感词';

-- ----------------------------
-- Records of t_base_sensitive_word
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_article
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_article`;
CREATE TABLE `t_biz_article` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `author_id` bigint(20) NOT NULL COMMENT '作者id',
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '内容类型',
  `author_type` varchar(64) NOT NULL DEFAULT '' COMMENT '作者类型',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(255) DEFAULT NULL COMMENT '副标题',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面图',
  `pictures` text COMMENT '图片',
  `excerpt` varchar(255) DEFAULT NULL COMMENT '节选',
  `detail` text NOT NULL COMMENT '内容',
  `video` varchar(255) DEFAULT '' COMMENT '视频连接',
  `video_length` int(11) DEFAULT NULL COMMENT '视频时长',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章';

-- ----------------------------
-- Records of t_biz_article
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_blacklist`;
CREATE TABLE `t_biz_blacklist` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '作者id',
  `their_id` bigint(20) NOT NULL COMMENT '被拉黑者的id',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='黑名单';

-- ----------------------------
-- Records of t_biz_blacklist
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_browse
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_browse`;
CREATE TABLE `t_biz_browse` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '用户id',
  `entity_id` bigint(20) NOT NULL COMMENT '实体id',
  `entity_type` varchar(32) NOT NULL COMMENT '实体类型',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='浏览';

-- ----------------------------
-- Records of t_biz_browse
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_channel`;
CREATE TABLE `t_biz_channel` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `category` varchar(64) DEFAULT NULL COMMENT '分类',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `orders` bigint(20) NOT NULL DEFAULT '0' COMMENT '排序',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='频道';

-- ----------------------------
-- Records of t_biz_channel
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_comment`;
CREATE TABLE `t_biz_comment` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '用户id',
  `entity_id` bigint(20) NOT NULL COMMENT '实体id',
  `entity_type` varchar(32) NOT NULL COMMENT '实体类型',
  `content` text NOT NULL COMMENT '内容',
  `likes` int(11) NOT NULL DEFAULT '0' COMMENT '喜欢数',
  `replies` int(11) NOT NULL DEFAULT '0' COMMENT '回复数',
  `authored` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否作者',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论';

-- ----------------------------
-- Records of t_biz_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_entry`;
CREATE TABLE `t_biz_entry` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `author_id` bigint(20) NOT NULL COMMENT '作者id',
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '内容类型',
  `author_type` varchar(64) NOT NULL DEFAULT '' COMMENT '作者类型',
  `entity_type` varchar(64) NOT NULL DEFAULT '' COMMENT '实体类型',
  `entity_id` bigint(20) DEFAULT NULL COMMENT '实体id',
  `channel_id` bigint(20) DEFAULT NULL COMMENT '频道id',
  `circle_id` bigint(20) DEFAULT NULL COMMENT '圈子id',
  `place_id` bigint(20) DEFAULT NULL COMMENT '地点id',
  `longitude` varchar(64) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(64) DEFAULT NULL COMMENT '纬度',
  `country` varchar(64) DEFAULT NULL COMMENT '国家',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `district` varchar(64) DEFAULT NULL COMMENT '区县',
  `place` varchar(64) DEFAULT NULL COMMENT '位置',
  `likes` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `replies` int(11) NOT NULL DEFAULT '0' COMMENT '回复数',
  `browses` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `favorites` int(11) NOT NULL DEFAULT '0' COMMENT '收藏数',
  `plays` int(11) NOT NULL DEFAULT '0' COMMENT '播放数',
  `composite_orders` bigint(20) NOT NULL DEFAULT '0' COMMENT '综合排序',
  `hot_orders` bigint(20) NOT NULL DEFAULT '0' COMMENT '最热排序',
  `new_orders` bigint(20) NOT NULL DEFAULT '0' COMMENT '最新排序',
  `status` varchar(64) NOT NULL DEFAULT '' COMMENT '状态:草稿-draft,审核中-under_review,疑似-suspected,发布-released,未通过-fail',
  `released_at` datetime DEFAULT NULL COMMENT '发布时间',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='作品';

-- ----------------------------
-- Records of t_biz_entry
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_favorite
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_favorite`;
CREATE TABLE `t_biz_favorite` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '用户id',
  `entity_id` bigint(20) NOT NULL COMMENT '实体id',
  `entity_type` varchar(32) NOT NULL COMMENT '实体类型',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏';

-- ----------------------------
-- Records of t_biz_favorite
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_friend`;
CREATE TABLE `t_biz_friend` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '用户id',
  `friend_id` bigint(20) NOT NULL COMMENT '好友账号id',
  `friend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否好友',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_account_id` (`account_id`),
  KEY `index_friend_id` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='好友';

-- ----------------------------
-- Records of t_biz_friend
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_like
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_like`;
CREATE TABLE `t_biz_like` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '用户id',
  `entity_id` bigint(20) NOT NULL COMMENT '实体id',
  `entity_type` varchar(32) NOT NULL COMMENT '实体类型',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='喜欢';

-- ----------------------------
-- Records of t_biz_like
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_place
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_place`;
CREATE TABLE `t_biz_place` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` varchar(64) DEFAULT '' COMMENT '类型',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `subtitle` varchar(64) DEFAULT NULL COMMENT '副标题',
  `cover` varchar(255) DEFAULT '' COMMENT '封面',
  `labels` varchar(255) DEFAULT NULL COMMENT '标签',
  `telephone` varchar(64) DEFAULT NULL COMMENT '电话',
  `description` text COMMENT '描述',
  `longitude` varchar(64) NOT NULL DEFAULT '' COMMENT '经度',
  `latitude` varchar(64) NOT NULL DEFAULT '' COMMENT '纬度',
  `country` varchar(64) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(64) NOT NULL DEFAULT '' COMMENT '省',
  `city` varchar(64) NOT NULL DEFAULT '' COMMENT '市',
  `district` varchar(64) NOT NULL DEFAULT '' COMMENT '区',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '详细地址',
  `status` varchar(64) NOT NULL DEFAULT '' COMMENT '状态',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='位置';

-- ----------------------------
-- Records of t_biz_place
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_reply`;
CREATE TABLE `t_biz_reply` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '用户id',
  `entity_id` bigint(20) NOT NULL COMMENT '实体id',
  `entity_type` varchar(32) NOT NULL COMMENT '实体类型',
  `comment_id` bigint(20) NOT NULL COMMENT '评论id',
  `to_account_id` bigint(20) NOT NULL COMMENT '被回复者id',
  `content` text NOT NULL COMMENT '内容',
  `likes` int(11) NOT NULL DEFAULT '0' COMMENT '喜欢数',
  `authored` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否作者',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回复';

-- ----------------------------
-- Records of t_biz_reply
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_topic`;
CREATE TABLE `t_biz_topic` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `category` varchar(64) DEFAULT NULL COMMENT '分类',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `orders` bigint(20) NOT NULL COMMENT '排序',
  `reads` int(11) NOT NULL DEFAULT '0' COMMENT '阅读数',
  `mentions` int(11) NOT NULL DEFAULT '0' COMMENT '讨论数',
  `authors` int(11) NOT NULL DEFAULT '0' COMMENT '作者人数',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='话题';

-- ----------------------------
-- Records of t_biz_topic
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_biz_user_social
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_user_social`;
CREATE TABLE `t_biz_user_social` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '用户id',
  `following` int(11) NOT NULL DEFAULT '0' COMMENT '关注数',
  `followers` int(11) NOT NULL DEFAULT '0' COMMENT '粉丝数',
  `entries` int(11) NOT NULL DEFAULT '0' COMMENT '作品数',
  `replies` int(11) NOT NULL DEFAULT '0' COMMENT '回复数',
  `likes` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `browses` int(11) NOT NULL DEFAULT '0' COMMENT '作品浏览数',
  `plays` int(11) NOT NULL DEFAULT '0' COMMENT '播放数',
  `last_released_at` datetime DEFAULT NULL COMMENT '最后发布文章时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_idx_account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户社交信息';

-- ----------------------------
-- Records of t_biz_user_social
-- ----------------------------
BEGIN;
INSERT INTO `t_biz_user_social` (`id`, `account_id`, `following`, `followers`, `entries`, `replies`, `likes`, `browses`, `plays`, `last_released_at`, `created_at`, `updated_at`) VALUES (2054830914076188673, 2054830914063605762, 0, 0, 0, 0, 0, 0, 0, NULL, '2026-05-14 15:47:21', '2026-05-14 15:47:21');
INSERT INTO `t_biz_user_social` (`id`, `account_id`, `following`, `followers`, `entries`, `replies`, `likes`, `browses`, `plays`, `last_released_at`, `created_at`, `updated_at`) VALUES (2054830914076188675, 2053753698923950082, 0, 0, 0, 0, 0, 0, 0, NULL, '2026-05-14 15:47:21', '2026-05-14 15:49:40');
COMMIT;

-- ----------------------------
-- Table structure for t_im_chat
-- ----------------------------
DROP TABLE IF EXISTS `t_im_chat`;
CREATE TABLE `t_im_chat` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `last_msg_id` bigint(20) DEFAULT NULL COMMENT '最后一条消息id',
  `last_content` text COMMENT '最后一条消息的内容',
  `lasted_at` datetime DEFAULT NULL COMMENT '最后一条消息时间',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会话';

-- ----------------------------
-- Records of t_im_chat
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_im_chat_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_im_chat_detail`;
CREATE TABLE `t_im_chat_detail` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `chat_id` bigint(20) NOT NULL COMMENT '会话id',
  `account_id` bigint(20) NOT NULL COMMENT '账号id',
  `friend_id` bigint(20) NOT NULL COMMENT '好友id',
  `relation` varchar(64) NOT NULL DEFAULT '' COMMENT '关系:stranger-陌生人,friend-好友',
  `unread` int(11) NOT NULL DEFAULT '0' COMMENT '未读消息条数',
  `status` varchar(64) NOT NULL DEFAULT 'invisible' COMMENT '状态:visible-可见,invisible-不可见',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_chat_id` (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会话';

-- ----------------------------
-- Records of t_im_chat_detail
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_im_message
-- ----------------------------
DROP TABLE IF EXISTS `t_im_message`;
CREATE TABLE `t_im_message` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '消息类型',
  `chat_id` bigint(20) NOT NULL COMMENT '会话id',
  `account_id` bigint(20) NOT NULL COMMENT '发送者id',
  `content` text NOT NULL COMMENT '消息内容',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_chat_id` (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息';

-- ----------------------------
-- Records of t_im_message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_im_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_im_notice`;
CREATE TABLE `t_im_notice` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '通知类型',
  `receive_id` bigint(20) DEFAULT NULL COMMENT '接收人id',
  `content` text NOT NULL COMMENT '通知内容',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_receive_id` (`receive_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知';

-- ----------------------------
-- Records of t_im_notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_im_notice_remind
-- ----------------------------
DROP TABLE IF EXISTS `t_im_notice_remind`;
CREATE TABLE `t_im_notice_remind` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '用户id',
  `system` int(11) NOT NULL DEFAULT '0' COMMENT '系统通知',
  `likes` int(11) NOT NULL DEFAULT '0' COMMENT '点赞',
  `forwards` int(11) NOT NULL DEFAULT '0' COMMENT '转发',
  `replies` int(11) NOT NULL DEFAULT '0' COMMENT '评论',
  `follows` int(11) NOT NULL DEFAULT '0' COMMENT '关注',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知';

-- ----------------------------
-- Records of t_im_notice_remind
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_sys_account
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_account`;
CREATE TABLE `t_sys_account` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` varchar(64) DEFAULT NULL COMMENT '用户类型：正常-normal,马甲号-vest',
  `mobile` varchar(64) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `initials` varchar(64) DEFAULT NULL COMMENT '昵称首字母',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `gender` varchar(64) NOT NULL DEFAULT 'unknown' COMMENT '性别:unknown-未知,female-女,male-男',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `country` varchar(64) DEFAULT NULL COMMENT '国家',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `district` varchar(64) DEFAULT NULL COMMENT '区',
  `identified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '实名认证',
  `introduction` varchar(255) DEFAULT '' COMMENT '个人介绍',
  `registered_at` datetime NOT NULL COMMENT '注册时间',
  `status` varchar(64) DEFAULT 'normal' COMMENT '用户状态: normal-正常,locked-锁定',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Records of t_sys_account
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_account` (`id`, `type`, `mobile`, `password`, `name`, `nickname`, `initials`, `avatar`, `gender`, `birthday`, `country`, `province`, `city`, `district`, `identified`, `introduction`, `registered_at`, `status`, `created_at`, `updated_at`) VALUES (1380405525467897857, 'normal', 'admin', '$2a$10$OqdpAy.e0R4V/l.Ljr6oNu/VWxhvm4bEdJ1sGY/X7CCvDrRcsHPjC', NULL, '花朝', 'H', '', 'unknown', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2021-04-09 14:36:08', 'normal', '2021-04-09 14:21:39', '2021-04-09 14:21:39');
INSERT INTO `t_sys_account` (`id`, `type`, `mobile`, `password`, `name`, `nickname`, `initials`, `avatar`, `gender`, `birthday`, `country`, `province`, `city`, `district`, `identified`, `introduction`, `registered_at`, `status`, `created_at`, `updated_at`) VALUES (1465300418740396034, 'normal', 'admin1', '$2a$10$OqdpAy.e0R4V/l.Ljr6oNu/VWxhvm4bEdJ1sGY/X7CCvDrRcsHPjC', NULL, '来年花朝', 'L', '', 'string', 'string', NULL, NULL, NULL, NULL, 0, 'string', '2021-11-29 20:43:38', 'forbidden', '2021-11-29 20:43:38', '2021-11-29 20:43:38');
INSERT INTO `t_sys_account` (`id`, `type`, `mobile`, `password`, `name`, `nickname`, `initials`, `avatar`, `gender`, `birthday`, `country`, `province`, `city`, `district`, `identified`, `introduction`, `registered_at`, `status`, `created_at`, `updated_at`) VALUES (2053751786136739842, 'personal', '13683238601', NULL, NULL, '用户mabhqffyre', 'Y', NULL, 'unknown', NULL, NULL, NULL, NULL, NULL, 0, '', '2026-05-11 16:19:17', 'normal', NULL, NULL);
INSERT INTO `t_sys_account` (`id`, `type`, `mobile`, `password`, `name`, `nickname`, `initials`, `avatar`, `gender`, `birthday`, `country`, `province`, `city`, `district`, `identified`, `introduction`, `registered_at`, `status`, `created_at`, `updated_at`) VALUES (2053753698923950082, 'personal', '13683238603', '$2a$10$9RQBV83DauR55.IS/Ez2A.oFI2GhScBFaesrYaY2nb4tfyFFQeuDu', '寒霜', '一剑寒霜尽', 'Y', NULL, 'male', '2026-01-01', '中国', '山东省', '菏泽市', '东明县', 0, '碎影无尽灭', '2026-05-11 16:26:53', 'normal', NULL, NULL);
INSERT INTO `t_sys_account` (`id`, `type`, `mobile`, `password`, `name`, `nickname`, `initials`, `avatar`, `gender`, `birthday`, `country`, `province`, `city`, `district`, `identified`, `introduction`, `registered_at`, `status`, `created_at`, `updated_at`) VALUES (2054520492349526017, 'personal', '13683238602', NULL, NULL, NULL, '', NULL, 'unknown', NULL, NULL, NULL, NULL, NULL, 0, '', '2026-05-13 19:13:51', 'normal', NULL, NULL);
INSERT INTO `t_sys_account` (`id`, `type`, `mobile`, `password`, `name`, `nickname`, `initials`, `avatar`, `gender`, `birthday`, `country`, `province`, `city`, `district`, `identified`, `introduction`, `registered_at`, `status`, `created_at`, `updated_at`) VALUES (2054830914063605762, 'personal', '13683238604', NULL, NULL, NULL, '', NULL, 'unknown', NULL, NULL, NULL, NULL, NULL, 0, '', '2026-05-14 15:47:21', 'normal', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dictionary`;
CREATE TABLE `t_sys_dictionary` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `value` varchar(64) NOT NULL COMMENT '数据值',
  `label` varchar(64) NOT NULL COMMENT '标签名',
  `type` varchar(64) NOT NULL COMMENT '类型',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `seq` int(10) NOT NULL COMMENT '排序字段',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

-- ----------------------------
-- Records of t_sys_dictionary
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_sys_token
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_token`;
CREATE TABLE `t_sys_token` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` varchar(64) NOT NULL COMMENT '类型:APP,WEB,MINI',
  `way` varchar(64) NOT NULL COMMENT '生成方式:PWD_LOGIN,MESSAGE_LOGIN,OAUTH_LOGIN,SCAN_LOGIN,QUICK_LOGIN',
  `token` varchar(255) NOT NULL COMMENT '票据',
  `device_id` varchar(255) DEFAULT NULL COMMENT '设备id',
  `account_id` bigint(20) NOT NULL COMMENT '账号id',
  `expires_time` datetime NOT NULL COMMENT '失效时间',
  `last_fresh_time` datetime NOT NULL COMMENT '最后刷新时间',
  `status` varchar(64) NOT NULL COMMENT 'token状态:NORMAL-正常,EXPIRES_INVALID-失效,EXCLUSION_INVALID-排他失效,LOCKING_INVALID-账号锁定失效',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(254) DEFAULT NULL COMMENT '创建者',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(254) DEFAULT NULL COMMENT '更新者',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号信息';

-- ----------------------------
-- Records of t_sys_token
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867414100078776322, 'WEB', 'PWD_LOGIN', 'a_web_34917d3519fb312c6a7a8e91ea29670f', NULL, 1380405525467897857, '2024-12-13 23:39:51', '2024-12-13 11:39:51', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867414206077227009, 'WEB', 'PWD_LOGIN', 'a_web_dd8456b36392d0a598aab56c486eade6', NULL, 1380405525467897857, '2024-12-13 23:40:16', '2024-12-13 11:40:16', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867414259252613122, 'WEB', 'PWD_LOGIN', 'a_web_3a39d550db52169505deea17545a23ad', NULL, 1380405525467897857, '2024-12-13 23:40:29', '2024-12-13 11:40:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867414324637618178, 'WEB', 'PWD_LOGIN', 'a_web_4e5e5e9a5f3b349eb252c2c7cb1e663e', NULL, 1380405525467897857, '2024-12-13 23:40:45', '2024-12-13 11:40:45', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867415515455066114, 'WEB', 'PWD_LOGIN', 'a_web_9f47668be53c02667571472a7fca1445', NULL, 1380405525467897857, '2024-12-13 23:45:28', '2024-12-13 11:45:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867415792627257345, 'WEB', 'PWD_LOGIN', 'a_web_77f74f6e63f7e6969723c2ed833a442e', NULL, 1380405525467897857, '2024-12-13 23:46:35', '2024-12-13 11:46:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867416196622618625, 'WEB', 'PWD_LOGIN', 'a_web_584ea38190b04fa21e71c2eb598dfc83', NULL, 1380405525467897857, '2024-12-13 23:48:11', '2024-12-13 11:48:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867416733690642434, 'WEB', 'PWD_LOGIN', 'a_web_f35fda48d3ab4823146dac977f1ab658', NULL, 1380405525467897857, '2024-12-13 23:50:19', '2024-12-13 11:50:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867416780251611137, 'WEB', 'PWD_LOGIN', 'a_web_fded1c530e46e964c65fe45960a16c0e', NULL, 1380405525467897857, '2024-12-13 23:50:30', '2024-12-13 11:50:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867417440753827842, 'WEB', 'PWD_LOGIN', 'a_web_9f3d6f6acd85e3682c3b510610c790ae', NULL, 1380405525467897857, '2024-12-13 23:53:07', '2024-12-13 11:53:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867418915785687042, 'WEB', 'PWD_LOGIN', 'a_web_1a30eb19e755af36130189759013517a', NULL, 1380405525467897857, '2024-12-13 23:58:59', '2024-12-13 11:58:59', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419243612487682, 'WEB', 'PWD_LOGIN', 'a_web_a91d7ef9e93e5a977109a5ece6963a0b', NULL, 1380405525467897857, '2024-12-14 00:00:17', '2024-12-13 12:00:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419243822202881, 'WEB', 'PWD_LOGIN', 'a_web_d9f1b3198be99043570b8645daa7dc7e', NULL, 1380405525467897857, '2024-12-14 00:00:17', '2024-12-13 12:00:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419243822202882, 'WEB', 'PWD_LOGIN', 'a_web_b3e1a0c27217ddc94c05b1bca89933a4', NULL, 1380405525467897857, '2024-12-14 00:00:17', '2024-12-13 12:00:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419246921793538, 'WEB', 'PWD_LOGIN', 'a_web_a105fe0cad564627cfb7667f9ddb6314', NULL, 1380405525467897857, '2024-12-14 00:00:18', '2024-12-13 12:00:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419246925987841, 'WEB', 'PWD_LOGIN', 'a_web_4fa196252c972284422164172e3c4264', NULL, 1380405525467897857, '2024-12-14 00:00:18', '2024-12-13 12:00:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419247588687874, 'WEB', 'PWD_LOGIN', 'a_web_e16ff8e12f21b35c3d3fe68914888463', NULL, 1380405525467897857, '2024-12-14 00:00:18', '2024-12-13 12:00:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419247802597377, 'WEB', 'PWD_LOGIN', 'a_web_8c0487222661114e85f0a1a9f73716b4', NULL, 1380405525467897857, '2024-12-14 00:00:18', '2024-12-13 12:00:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419248444325889, 'WEB', 'PWD_LOGIN', 'a_web_45315c8d8fc814397a5012773ebfaf5b', NULL, 1380405525467897857, '2024-12-14 00:00:18', '2024-12-13 12:00:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419248498851841, 'WEB', 'PWD_LOGIN', 'a_web_c985404cd2c4c550595d76dcc3c7120a', NULL, 1380405525467897857, '2024-12-14 00:00:18', '2024-12-13 12:00:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419248544989185, 'WEB', 'PWD_LOGIN', 'a_web_f8a0525118e9160d0386982158f0a460', NULL, 1380405525467897857, '2024-12-14 00:00:18', '2024-12-13 12:00:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419250205933570, 'WEB', 'PWD_LOGIN', 'a_web_db2e547bde7dd9082093d7ebf4393c45', NULL, 1380405525467897857, '2024-12-14 00:00:19', '2024-12-13 12:00:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419250432425986, 'WEB', 'PWD_LOGIN', 'a_web_d526ffd80f6b565ebe8d98b5a36a3fad', NULL, 1380405525467897857, '2024-12-14 00:00:19', '2024-12-13 12:00:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419252500217857, 'WEB', 'PWD_LOGIN', 'a_web_cd9318c62c6fa35b07300a6da6e4d10a', NULL, 1380405525467897857, '2024-12-14 00:00:19', '2024-12-13 12:00:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419252500217858, 'WEB', 'PWD_LOGIN', 'a_web_4d0028da09c8f96bcac14d3a14a6d149', NULL, 1380405525467897857, '2024-12-14 00:00:19', '2024-12-13 12:00:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419252525383682, 'WEB', 'PWD_LOGIN', 'a_web_0195c0c8afeb839004e83fc7ec9ab87f', NULL, 1380405525467897857, '2024-12-14 00:00:19', '2024-12-13 12:00:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419252928036866, 'WEB', 'PWD_LOGIN', 'a_web_74bcbba73ff0643d02cf1715f4fcc843', NULL, 1380405525467897857, '2024-12-14 00:00:20', '2024-12-13 12:00:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419253120974850, 'WEB', 'PWD_LOGIN', 'a_web_6f492b78c1868f1ecea3e8bf3c5f58fa', NULL, 1380405525467897857, '2024-12-14 00:00:20', '2024-12-13 12:00:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419253666234370, 'WEB', 'PWD_LOGIN', 'a_web_78eff6a9dbfbb3c9f487975f080ed0b7', NULL, 1380405525467897857, '2024-12-14 00:00:20', '2024-12-13 12:00:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419253724954626, 'WEB', 'PWD_LOGIN', 'a_web_bbf5ca9839e6ba3bb1edee574563f2cc', NULL, 1380405525467897857, '2024-12-14 00:00:20', '2024-12-13 12:00:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419253938864130, 'WEB', 'PWD_LOGIN', 'a_web_971beb9b6d723d51e1dc3d8a84aee86f', NULL, 1380405525467897857, '2024-12-14 00:00:20', '2024-12-13 12:00:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419254865805313, 'WEB', 'PWD_LOGIN', 'a_web_08eb553f63107d989a7ec09e75d78553', NULL, 1380405525467897857, '2024-12-14 00:00:20', '2024-12-13 12:00:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419255813718017, 'WEB', 'PWD_LOGIN', 'a_web_2ef0a07ee1c70166c701cd5165fbb6ab', NULL, 1380405525467897857, '2024-12-14 00:00:20', '2024-12-13 12:00:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419257021677570, 'WEB', 'PWD_LOGIN', 'a_web_fd2041585df24994e0784c13c50b9bcc', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419257168478209, 'WEB', 'PWD_LOGIN', 'a_web_264ebe9b448adf48cd3739a723ba1320', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419257227198465, 'WEB', 'PWD_LOGIN', 'a_web_64678c96a517d90d2c38c6e51e0e1695', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419257785040897, 'WEB', 'PWD_LOGIN', 'a_web_1ed725afabfc8ea629b7952a6809851d', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419257806012417, 'WEB', 'PWD_LOGIN', 'a_web_b072c0cf3467118161c6d4a4b00551cf', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419260230320129, 'WEB', 'PWD_LOGIN', 'a_web_7d6cb8220c052af205a5da5d092d8014', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419260821716994, 'WEB', 'PWD_LOGIN', 'a_web_ad62470e658263afb368a5fc442d28e5', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419260872048642, 'WEB', 'PWD_LOGIN', 'a_web_7a26a144288d4bb979019d1f5f2a4b50', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419260905603074, 'WEB', 'PWD_LOGIN', 'a_web_846a66107d522edadad3821acc77e258', NULL, 1380405525467897857, '2024-12-14 00:00:21', '2024-12-13 12:00:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419261954179074, 'WEB', 'PWD_LOGIN', 'a_web_a49b7d17c4c64dadc4128fe9f4ce768d', NULL, 1380405525467897857, '2024-12-14 00:00:22', '2024-12-13 12:00:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419261983539202, 'WEB', 'PWD_LOGIN', 'a_web_111c64fe42bcb75a8e44ab1f146a1b0f', NULL, 1380405525467897857, '2024-12-14 00:00:22', '2024-12-13 12:00:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419261987733506, 'WEB', 'PWD_LOGIN', 'a_web_19f9fe0e633a8f0b6ffcd88abc4c40eb', NULL, 1380405525467897857, '2024-12-14 00:00:22', '2024-12-13 12:00:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419263732563969, 'WEB', 'PWD_LOGIN', 'a_web_c63e0957740c90a3a37ad46913407d97', NULL, 1380405525467897857, '2024-12-14 00:00:22', '2024-12-13 12:00:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419266295283714, 'WEB', 'PWD_LOGIN', 'a_web_3c1f4301e5a9f5b84c8e214eb735738c', NULL, 1380405525467897857, '2024-12-14 00:00:23', '2024-12-13 12:00:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419266412724225, 'WEB', 'PWD_LOGIN', 'a_web_41a26c8fb75d2f6d7bb19f73375256b4', NULL, 1380405525467897857, '2024-12-14 00:00:23', '2024-12-13 12:00:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419269524897793, 'WEB', 'PWD_LOGIN', 'a_web_d41ee493e8c07aa90b0f4a237ab89a7c', NULL, 1380405525467897857, '2024-12-14 00:00:23', '2024-12-13 12:00:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419269852053505, 'WEB', 'PWD_LOGIN', 'a_web_2e0c18f579b71da4be88e75969a9eedf', NULL, 1380405525467897857, '2024-12-14 00:00:24', '2024-12-13 12:00:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419269931745281, 'WEB', 'PWD_LOGIN', 'a_web_a8913dfc6b5043ee3329f80463b8a14e', NULL, 1380405525467897857, '2024-12-14 00:00:24', '2024-12-13 12:00:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419272083423234, 'WEB', 'PWD_LOGIN', 'a_web_8f00c9469d877a472e9fae39753c4c3f', NULL, 1380405525467897857, '2024-12-14 00:00:24', '2024-12-13 12:00:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419278098055169, 'WEB', 'PWD_LOGIN', 'a_web_957a26a3a183c11337d89f68b4e41c78', NULL, 1380405525467897857, '2024-12-14 00:00:26', '2024-12-13 12:00:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419278160969730, 'WEB', 'PWD_LOGIN', 'a_web_51ce155ea45da0b0e1e955a1844986f9', NULL, 1380405525467897857, '2024-12-14 00:00:26', '2024-12-13 12:00:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419278194524161, 'WEB', 'PWD_LOGIN', 'a_web_386395220fa80f26be76f9b13b0919a6', NULL, 1380405525467897857, '2024-12-14 00:00:26', '2024-12-13 12:00:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419278232272898, 'WEB', 'PWD_LOGIN', 'a_web_b0cda0372780f2c21faaa8ce3a19f3d2', NULL, 1380405525467897857, '2024-12-14 00:00:26', '2024-12-13 12:00:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419278232272899, 'WEB', 'PWD_LOGIN', 'a_web_93c7532a870703226fe8e487876c6aad', NULL, 1380405525467897857, '2024-12-14 00:00:26', '2024-12-13 12:00:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419278391656449, 'WEB', 'PWD_LOGIN', 'a_web_171019d3193df58cf432a5593b927dbf', NULL, 1380405525467897857, '2024-12-14 00:00:26', '2024-12-13 12:00:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419281117954049, 'WEB', 'PWD_LOGIN', 'a_web_c7747ad0d93298195ce1e9cf7ac5d747', NULL, 1380405525467897857, '2024-12-14 00:00:26', '2024-12-13 12:00:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419281218617345, 'WEB', 'PWD_LOGIN', 'a_web_419508c3d677cd675862aa71e198ef0a', NULL, 1380405525467897857, '2024-12-14 00:00:26', '2024-12-13 12:00:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419283949109250, 'WEB', 'PWD_LOGIN', 'a_web_f87f3e7a7c2d27ce50d95a18b2aa5671', NULL, 1380405525467897857, '2024-12-14 00:00:27', '2024-12-13 12:00:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419284850884610, 'WEB', 'PWD_LOGIN', 'a_web_1a26a6896248323d770c885b61107b99', NULL, 1380405525467897857, '2024-12-14 00:00:27', '2024-12-13 12:00:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419284951547905, 'WEB', 'PWD_LOGIN', 'a_web_ad0f8c918176826ead3cb91551d7c347', NULL, 1380405525467897857, '2024-12-14 00:00:27', '2024-12-13 12:00:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419284955742210, 'WEB', 'PWD_LOGIN', 'a_web_ddbd675246152252fe6906d43886b665', NULL, 1380405525467897857, '2024-12-14 00:00:27', '2024-12-13 12:00:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419285035433985, 'WEB', 'PWD_LOGIN', 'a_web_ec7492d0121a69abf2463d5c4ae6f532', NULL, 1380405525467897857, '2024-12-14 00:00:27', '2024-12-13 12:00:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419292794896386, 'WEB', 'PWD_LOGIN', 'a_web_3dd7df8cb7e6df06bc0ec2772cf57d52', NULL, 1380405525467897857, '2024-12-14 00:00:29', '2024-12-13 12:00:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419292799090690, 'WEB', 'PWD_LOGIN', 'a_web_103602fa0d3769a4187248333a126432', NULL, 1380405525467897857, '2024-12-14 00:00:29', '2024-12-13 12:00:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419292828450817, 'WEB', 'PWD_LOGIN', 'a_web_92f8473b8623ce97dd13b651cd7631ac', NULL, 1380405525467897857, '2024-12-14 00:00:29', '2024-12-13 12:00:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419296976617473, 'WEB', 'PWD_LOGIN', 'a_web_21f63a2d054c9535cfc32c8cc351e2b6', NULL, 1380405525467897857, '2024-12-14 00:00:30', '2024-12-13 12:00:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419297136001025, 'WEB', 'PWD_LOGIN', 'a_web_4c2608f1d63215292d44b64c095e0a93', NULL, 1380405525467897857, '2024-12-14 00:00:30', '2024-12-13 12:00:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419297748369409, 'WEB', 'PWD_LOGIN', 'a_web_d8950327a9eebb0ed56b213053c148ca', NULL, 1380405525467897857, '2024-12-14 00:00:30', '2024-12-13 12:00:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419298188771329, 'WEB', 'PWD_LOGIN', 'a_web_6269722be1cfadec29f00728f23f09a5', NULL, 1380405525467897857, '2024-12-14 00:00:30', '2024-12-13 12:00:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419299388342273, 'WEB', 'PWD_LOGIN', 'a_web_d50d3be9f37ef6536464bc677d611c1c', NULL, 1380405525467897857, '2024-12-14 00:00:31', '2024-12-13 12:00:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419301036703745, 'WEB', 'PWD_LOGIN', 'a_web_5e92cd157005cf546709ccfd0ed3171c', NULL, 1380405525467897857, '2024-12-14 00:00:31', '2024-12-13 12:00:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419302324355073, 'WEB', 'PWD_LOGIN', 'a_web_bc2e5e1b44c998e6c739febe4f805c41', NULL, 1380405525467897857, '2024-12-14 00:00:31', '2024-12-13 12:00:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419302404046849, 'WEB', 'PWD_LOGIN', 'a_web_59612ecc686cd2ab490cbf36eb291fd3', NULL, 1380405525467897857, '2024-12-14 00:00:31', '2024-12-13 12:00:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419303746224129, 'WEB', 'PWD_LOGIN', 'a_web_5b8129aab41dee022901bfe01ea55374', NULL, 1380405525467897857, '2024-12-14 00:00:32', '2024-12-13 12:00:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419305801433089, 'WEB', 'PWD_LOGIN', 'a_web_107b6f17677414b8534bcda3b71da93a', NULL, 1380405525467897857, '2024-12-14 00:00:32', '2024-12-13 12:00:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419306543824897, 'WEB', 'PWD_LOGIN', 'a_web_b276be7cfe440cfa1a67cf0808a11859', NULL, 1380405525467897857, '2024-12-14 00:00:32', '2024-12-13 12:00:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419312411656194, 'WEB', 'PWD_LOGIN', 'a_web_71f6af870522f1af0d2bad0ac5645d48', NULL, 1380405525467897857, '2024-12-14 00:00:34', '2024-12-13 12:00:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419312621371393, 'WEB', 'PWD_LOGIN', 'a_web_0752a2ca23ac1601a114912f01a80f46', NULL, 1380405525467897857, '2024-12-14 00:00:34', '2024-12-13 12:00:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419314559139842, 'WEB', 'PWD_LOGIN', 'a_web_5b35f84bb489d6a52663310cb6032441', NULL, 1380405525467897857, '2024-12-14 00:00:34', '2024-12-13 12:00:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419318078160897, 'WEB', 'PWD_LOGIN', 'a_web_2fad790f43b6694d621a34d2a71856d8', NULL, 1380405525467897857, '2024-12-14 00:00:35', '2024-12-13 12:00:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419319575527425, 'WEB', 'PWD_LOGIN', 'a_web_1ef92473ba7da4b6256741b4d872f2a5', NULL, 1380405525467897857, '2024-12-14 00:00:35', '2024-12-13 12:00:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419321664290818, 'WEB', 'PWD_LOGIN', 'a_web_02c6bc077697f385c6099de53614f209', NULL, 1380405525467897857, '2024-12-14 00:00:36', '2024-12-13 12:00:36', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419327712477185, 'WEB', 'PWD_LOGIN', 'a_web_f4527da0848f512858f7d03aa17a9b4a', NULL, 1380405525467897857, '2024-12-14 00:00:37', '2024-12-13 12:00:37', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419327985106945, 'WEB', 'PWD_LOGIN', 'a_web_0d3e41d8095006faa6ab782e2ad32536', NULL, 1380405525467897857, '2024-12-14 00:00:37', '2024-12-13 12:00:37', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419331353133058, 'WEB', 'PWD_LOGIN', 'a_web_75e8e2bbaa970b8af9b878a360f37c56', NULL, 1380405525467897857, '2024-12-14 00:00:38', '2024-12-13 12:00:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419333366398978, 'WEB', 'PWD_LOGIN', 'a_web_ca4cf38a8f1ce50647919edeb732e1b8', NULL, 1380405525467897857, '2024-12-14 00:00:39', '2024-12-13 12:00:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419333378981890, 'WEB', 'PWD_LOGIN', 'a_web_8fa74d3962feb8779de3dccd398a3a29', NULL, 1380405525467897857, '2024-12-14 00:00:39', '2024-12-13 12:00:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419336134639617, 'WEB', 'PWD_LOGIN', 'a_web_57189cf6ec922723665b1db48d84bd84', NULL, 1380405525467897857, '2024-12-14 00:00:39', '2024-12-13 12:00:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419336323383298, 'WEB', 'PWD_LOGIN', 'a_web_6bcd1acbe0ed67e7add4c75a62b4e0d2', NULL, 1380405525467897857, '2024-12-14 00:00:39', '2024-12-13 12:00:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419338101768193, 'WEB', 'PWD_LOGIN', 'a_web_ca496a60fc3886be1ba8eb46de9eaa29', NULL, 1380405525467897857, '2024-12-14 00:00:40', '2024-12-13 12:00:40', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419340152782849, 'WEB', 'PWD_LOGIN', 'a_web_beea1a6379f81f30fba22baff39e63aa', NULL, 1380405525467897857, '2024-12-14 00:00:40', '2024-12-13 12:00:40', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419340215697410, 'WEB', 'PWD_LOGIN', 'a_web_2358fdffbcad4979b5f74b56122a550f', NULL, 1380405525467897857, '2024-12-14 00:00:40', '2024-12-13 12:00:40', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419340312166401, 'WEB', 'PWD_LOGIN', 'a_web_cdf33fd9e1eb44f2deb7de8820c40e62', NULL, 1380405525467897857, '2024-12-14 00:00:40', '2024-12-13 12:00:40', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419342740668418, 'WEB', 'PWD_LOGIN', 'a_web_35c5f471301f0209805718835c1e23a2', NULL, 1380405525467897857, '2024-12-14 00:00:41', '2024-12-13 12:00:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419344015736834, 'WEB', 'PWD_LOGIN', 'a_web_b94fee87f30cf16e48d9c2a74450ebb5', NULL, 1380405525467897857, '2024-12-14 00:00:41', '2024-12-13 12:00:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419344170926082, 'WEB', 'PWD_LOGIN', 'a_web_c1b82c02658f0031d6b59d1f2c23ee73', NULL, 1380405525467897857, '2024-12-14 00:00:41', '2024-12-13 12:00:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419349191507969, 'WEB', 'PWD_LOGIN', 'a_web_b4bce1986e7075b28e52b1ae7aa814c9', NULL, 1380405525467897857, '2024-12-14 00:00:42', '2024-12-13 12:00:42', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419349250228226, 'WEB', 'PWD_LOGIN', 'a_web_d42f64aaaa5a2526f848cde4d4438a81', NULL, 1380405525467897857, '2024-12-14 00:00:42', '2024-12-13 12:00:42', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419351238328321, 'WEB', 'PWD_LOGIN', 'a_web_6f4f3b88719ad2c9a0475e3f60d19d0b', NULL, 1380405525467897857, '2024-12-14 00:00:43', '2024-12-13 12:00:43', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419351494180866, 'WEB', 'PWD_LOGIN', 'a_web_cc7cba7a899983254d0bc693566c7ca3', NULL, 1380405525467897857, '2024-12-14 00:00:43', '2024-12-13 12:00:43', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419354006568961, 'WEB', 'PWD_LOGIN', 'a_web_4e2ef63f5e46cffc260ce9743fa6d304', NULL, 1380405525467897857, '2024-12-14 00:00:44', '2024-12-13 12:00:44', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419360256081921, 'WEB', 'PWD_LOGIN', 'a_web_7b6e3ba51b954a7f870ea9883849f040', NULL, 1380405525467897857, '2024-12-14 00:00:45', '2024-12-13 12:00:45', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419360935559170, 'WEB', 'PWD_LOGIN', 'a_web_44ee091ee604ee274ba8949d4dcbbe8c', NULL, 1380405525467897857, '2024-12-14 00:00:45', '2024-12-13 12:00:45', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419361996718081, 'WEB', 'PWD_LOGIN', 'a_web_783c704298033e3882de7916f2ee1f89', NULL, 1380405525467897857, '2024-12-14 00:00:45', '2024-12-13 12:00:45', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419362755887105, 'WEB', 'PWD_LOGIN', 'a_web_361650b324036ce0d4b0ee600a5da792', NULL, 1380405525467897857, '2024-12-14 00:00:46', '2024-12-13 12:00:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419367340261377, 'WEB', 'PWD_LOGIN', 'a_web_74205569ba32e6fd212dc740f886f03e', NULL, 1380405525467897857, '2024-12-14 00:00:47', '2024-12-13 12:00:47', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419373740769281, 'WEB', 'PWD_LOGIN', 'a_web_5f4a0b94c9baf67dac8d5dfe004eadae', NULL, 1380405525467897857, '2024-12-14 00:00:48', '2024-12-13 12:00:48', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419373992427521, 'WEB', 'PWD_LOGIN', 'a_web_3be622b5ea2679d1e8f8c114c0e57cfd', NULL, 1380405525467897857, '2024-12-14 00:00:48', '2024-12-13 12:00:48', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419375552708610, 'WEB', 'PWD_LOGIN', 'a_web_74219fdd1102a9cbdbf71292c4509f8f', NULL, 1380405525467897857, '2024-12-14 00:00:49', '2024-12-13 12:00:49', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419375598845953, 'WEB', 'PWD_LOGIN', 'a_web_35d9a776e53464a82e7427f49ad855b8', NULL, 1380405525467897857, '2024-12-14 00:00:49', '2024-12-13 12:00:49', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419375770812417, 'WEB', 'PWD_LOGIN', 'a_web_e30daea8e52021b24f8876584c121439', NULL, 1380405525467897857, '2024-12-14 00:00:49', '2024-12-13 12:00:49', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419376928440321, 'WEB', 'PWD_LOGIN', 'a_web_1e151bb805825fecb00b6a3a6e18b3a6', NULL, 1380405525467897857, '2024-12-14 00:00:49', '2024-12-13 12:00:49', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419379822510081, 'WEB', 'PWD_LOGIN', 'a_web_a15033a50e521e2085e50558d3faee9b', NULL, 1380405525467897857, '2024-12-14 00:00:50', '2024-12-13 12:00:50', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419383836459010, 'WEB', 'PWD_LOGIN', 'a_web_7bfc5551a6964eb5fd6313f34501d626', NULL, 1380405525467897857, '2024-12-14 00:00:51', '2024-12-13 12:00:51', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419383949705218, 'WEB', 'PWD_LOGIN', 'a_web_561738724c8628894fbd2c47d520b8a5', NULL, 1380405525467897857, '2024-12-14 00:00:51', '2024-12-13 12:00:51', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419391121965058, 'WEB', 'PWD_LOGIN', 'a_web_d2de094153d9d8ec66deefc2c5dc9881', NULL, 1380405525467897857, '2024-12-14 00:00:52', '2024-12-13 12:00:52', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419393751793666, 'WEB', 'PWD_LOGIN', 'a_web_81ec330751bf0ed11e9814aad0feeffa', NULL, 1380405525467897857, '2024-12-14 00:00:53', '2024-12-13 12:00:53', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419395660201986, 'WEB', 'PWD_LOGIN', 'a_web_44dc1d997fb7ffd56c127a47f867fb8c', NULL, 1380405525467897857, '2024-12-14 00:00:54', '2024-12-13 12:00:54', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419395672784898, 'WEB', 'PWD_LOGIN', 'a_web_7f919aa4a8da23724c894ffa47dfda42', NULL, 1380405525467897857, '2024-12-14 00:00:54', '2024-12-13 12:00:54', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419399552516098, 'WEB', 'PWD_LOGIN', 'a_web_a802eadb72d68835b42f73c5036755a7', NULL, 1380405525467897857, '2024-12-14 00:00:54', '2024-12-13 12:00:54', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419399594459138, 'WEB', 'PWD_LOGIN', 'a_web_3826ad5e7b0f008256baec9c105acf42', NULL, 1380405525467897857, '2024-12-14 00:00:54', '2024-12-13 12:00:54', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419400777252865, 'WEB', 'PWD_LOGIN', 'a_web_7a1ceb7b34b53a719eb3e964ee0ea86b', NULL, 1380405525467897857, '2024-12-14 00:00:55', '2024-12-13 12:00:55', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419402459168770, 'WEB', 'PWD_LOGIN', 'a_web_92aeea0322a96071cf30452a83d0eee9', NULL, 1380405525467897857, '2024-12-14 00:00:55', '2024-12-13 12:00:55', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419406296956929, 'WEB', 'PWD_LOGIN', 'a_web_80f8701d7863e1ae8d3619fb46f18ffe', NULL, 1380405525467897857, '2024-12-14 00:00:56', '2024-12-13 12:00:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419406301151233, 'WEB', 'PWD_LOGIN', 'a_web_5485d6c5c93db34b8aa6bbc48a78d8a5', NULL, 1380405525467897857, '2024-12-14 00:00:56', '2024-12-13 12:00:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419406523449345, 'WEB', 'PWD_LOGIN', 'a_web_d72c14d5c3ce188e1a85f7f1154c55c4', NULL, 1380405525467897857, '2024-12-14 00:00:56', '2024-12-13 12:00:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419406611529729, 'WEB', 'PWD_LOGIN', 'a_web_8e9062b1d0d1bd50be560e42f06616b3', NULL, 1380405525467897857, '2024-12-14 00:00:56', '2024-12-13 12:00:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419406707998721, 'WEB', 'PWD_LOGIN', 'a_web_bb08b0cf7f056a38d8660d7c46a91d75', NULL, 1380405525467897857, '2024-12-14 00:00:56', '2024-12-13 12:00:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419406741553153, 'WEB', 'PWD_LOGIN', 'a_web_465784840a144add5efead85a3c96b6f', NULL, 1380405525467897857, '2024-12-14 00:00:56', '2024-12-13 12:00:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419406833827842, 'WEB', 'PWD_LOGIN', 'a_web_1caae66c6703cde2080435130e08edef', NULL, 1380405525467897857, '2024-12-14 00:00:56', '2024-12-13 12:00:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419408616407041, 'WEB', 'PWD_LOGIN', 'a_web_f528d555b75bcdb0dc391363601a58b7', NULL, 1380405525467897857, '2024-12-14 00:00:57', '2024-12-13 12:00:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419408901619714, 'WEB', 'PWD_LOGIN', 'a_web_889a3ab4f4aec04d7ab575ad6163eac5', NULL, 1380405525467897857, '2024-12-14 00:00:57', '2024-12-13 12:00:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419409195220994, 'WEB', 'PWD_LOGIN', 'a_web_e6fc5e76d9d4446491567df4763dc19a', NULL, 1380405525467897857, '2024-12-14 00:00:57', '2024-12-13 12:00:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419415570563074, 'WEB', 'PWD_LOGIN', 'a_web_f0a6328efb38a182cad060eb63ea0a79', NULL, 1380405525467897857, '2024-12-14 00:00:58', '2024-12-13 12:00:58', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419415616700418, 'WEB', 'PWD_LOGIN', 'a_web_9e8b96efccbca3f875e2b94374722fa3', NULL, 1380405525467897857, '2024-12-14 00:00:58', '2024-12-13 12:00:58', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419417562857473, 'WEB', 'PWD_LOGIN', 'a_web_f5b2a06b4fb5253ba73662a1795f79de', NULL, 1380405525467897857, '2024-12-14 00:00:59', '2024-12-13 12:00:59', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419417646743554, 'WEB', 'PWD_LOGIN', 'a_web_1e26169d199807c22e1bc3dc1c2189d0', NULL, 1380405525467897857, '2024-12-14 00:00:59', '2024-12-13 12:00:59', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419420901523457, 'WEB', 'PWD_LOGIN', 'a_web_6949a9115da2ab87114bbc90cb9ec062', NULL, 1380405525467897857, '2024-12-14 00:01:00', '2024-12-13 12:01:00', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419422877040641, 'WEB', 'PWD_LOGIN', 'a_web_a216c5cabeb2486cd196f6d5b9465678', NULL, 1380405525467897857, '2024-12-14 00:01:00', '2024-12-13 12:01:00', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419422877040642, 'WEB', 'PWD_LOGIN', 'a_web_5a72765969f7697199c61ff49291efb8', NULL, 1380405525467897857, '2024-12-14 00:01:00', '2024-12-13 12:01:00', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419422956732418, 'WEB', 'PWD_LOGIN', 'a_web_80533066f1b17895562f04c8ae59145c', NULL, 1380405525467897857, '2024-12-14 00:01:00', '2024-12-13 12:01:00', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419426375090177, 'WEB', 'PWD_LOGIN', 'a_web_7c265e0649acc5d87ad8ed2843757e8b', NULL, 1380405525467897857, '2024-12-14 00:01:01', '2024-12-13 12:01:01', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419426446393345, 'WEB', 'PWD_LOGIN', 'a_web_41a74761f72287991a8a0b70d84253f9', NULL, 1380405525467897857, '2024-12-14 00:01:01', '2024-12-13 12:01:01', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419427478192130, 'WEB', 'PWD_LOGIN', 'a_web_2544be3e94fdb091587318416adeed9e', NULL, 1380405525467897857, '2024-12-14 00:01:01', '2024-12-13 12:01:01', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419428388356098, 'WEB', 'PWD_LOGIN', 'a_web_7647e0a803a14138853f27ceb94d61da', NULL, 1380405525467897857, '2024-12-14 00:01:01', '2024-12-13 12:01:01', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419429311102977, 'WEB', 'PWD_LOGIN', 'a_web_9dd56fc8d54e730a91c7954d48f357ac', NULL, 1380405525467897857, '2024-12-14 00:01:02', '2024-12-13 12:01:02', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419433375383553, 'WEB', 'PWD_LOGIN', 'a_web_70f76fc106af777765e1551334d3b378', NULL, 1380405525467897857, '2024-12-14 00:01:03', '2024-12-13 12:01:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419433404743681, 'WEB', 'PWD_LOGIN', 'a_web_ec8fde788eb501f451f5be738df0dddd', NULL, 1380405525467897857, '2024-12-14 00:01:03', '2024-12-13 12:01:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419433555738626, 'WEB', 'PWD_LOGIN', 'a_web_18022aab3c604dff706cd4d825d7ee22', NULL, 1380405525467897857, '2024-12-14 00:01:03', '2024-12-13 12:01:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419433652207617, 'WEB', 'PWD_LOGIN', 'a_web_d2ccadce24178ea4cc569b3477942894', NULL, 1380405525467897857, '2024-12-14 00:01:03', '2024-12-13 12:01:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419434713366530, 'WEB', 'PWD_LOGIN', 'a_web_b39d8566b2b6fecd4d35e8989805402f', NULL, 1380405525467897857, '2024-12-14 00:01:03', '2024-12-13 12:01:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419434730143746, 'WEB', 'PWD_LOGIN', 'a_web_308bf7021dcbed452c09395e7225e1a1', NULL, 1380405525467897857, '2024-12-14 00:01:03', '2024-12-13 12:01:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419434730143747, 'WEB', 'PWD_LOGIN', 'a_web_af412368184d5c90640e3305b95184c2', NULL, 1380405525467897857, '2024-12-14 00:01:03', '2024-12-13 12:01:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419436252676097, 'WEB', 'PWD_LOGIN', 'a_web_651f036096f5a5cd5f9ee6c7ad3a983d', NULL, 1380405525467897857, '2024-12-14 00:01:03', '2024-12-13 12:01:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419437380943873, 'WEB', 'PWD_LOGIN', 'a_web_9f4b5f2e87d9c2871863f202d4f9b796', NULL, 1380405525467897857, '2024-12-14 00:01:04', '2024-12-13 12:01:04', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419438609874946, 'WEB', 'PWD_LOGIN', 'a_web_21ca0a594878891d2ec8e8d5b85d9b45', NULL, 1380405525467897857, '2024-12-14 00:01:04', '2024-12-13 12:01:04', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419441914986497, 'WEB', 'PWD_LOGIN', 'a_web_f380f4f29c1f290629f762abeb454fe3', NULL, 1380405525467897857, '2024-12-14 00:01:05', '2024-12-13 12:01:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419441927569410, 'WEB', 'PWD_LOGIN', 'a_web_f925a84c5776a07d7f0176283f00504e', NULL, 1380405525467897857, '2024-12-14 00:01:05', '2024-12-13 12:01:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419444758724610, 'WEB', 'PWD_LOGIN', 'a_web_16f5192e0af7833d841767cfddcb80fb', NULL, 1380405525467897857, '2024-12-14 00:01:05', '2024-12-13 12:01:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419444804861953, 'WEB', 'PWD_LOGIN', 'a_web_87e6590160f28a8b7523f81d1edf55d8', NULL, 1380405525467897857, '2024-12-14 00:01:05', '2024-12-13 12:01:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419444834222081, 'WEB', 'PWD_LOGIN', 'a_web_6982f32712d4d784ca202ce0da3ebdda', NULL, 1380405525467897857, '2024-12-14 00:01:05', '2024-12-13 12:01:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419445077491714, 'WEB', 'PWD_LOGIN', 'a_web_d57765ef21c56813f2eb47563b0b2e58', NULL, 1380405525467897857, '2024-12-14 00:01:05', '2024-12-13 12:01:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419445211709441, 'WEB', 'PWD_LOGIN', 'a_web_c6bc8bb83ac35b4aa4bcd67921ca5a3a', NULL, 1380405525467897857, '2024-12-14 00:01:05', '2024-12-13 12:01:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419445245263874, 'WEB', 'PWD_LOGIN', 'a_web_b7d831c0f56c5f5e134789200a3fe2cb', NULL, 1380405525467897857, '2024-12-14 00:01:05', '2024-12-13 12:01:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419446839099393, 'WEB', 'PWD_LOGIN', 'a_web_20553ce1053d68cfa2c3e47b062627c5', NULL, 1380405525467897857, '2024-12-14 00:01:06', '2024-12-13 12:01:06', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419448231608322, 'WEB', 'PWD_LOGIN', 'a_web_6bbe19d2f605e52f0d5f2c2255dc5b06', NULL, 1380405525467897857, '2024-12-14 00:01:06', '2024-12-13 12:01:06', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419449099829250, 'WEB', 'PWD_LOGIN', 'a_web_d09033e86e453f8b07eed4a7f1dc9e91', NULL, 1380405525467897857, '2024-12-14 00:01:06', '2024-12-13 12:01:06', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419449099829251, 'WEB', 'PWD_LOGIN', 'a_web_de24592c8d0684d747b02385a60882e0', NULL, 1380405525467897857, '2024-12-14 00:01:06', '2024-12-13 12:01:06', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419450299400194, 'WEB', 'PWD_LOGIN', 'a_web_7fedb9bddea2ae1f97afdc4c72e2983d', NULL, 1380405525467897857, '2024-12-14 00:01:07', '2024-12-13 12:01:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419450379091970, 'WEB', 'PWD_LOGIN', 'a_web_4c015737290a66b30e79dc60c0e95217', NULL, 1380405525467897857, '2024-12-14 00:01:07', '2024-12-13 12:01:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419451880652801, 'WEB', 'PWD_LOGIN', 'a_web_71861cf691d3d098d0ae4fd2073849a3', NULL, 1380405525467897857, '2024-12-14 00:01:07', '2024-12-13 12:01:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419452606267394, 'WEB', 'PWD_LOGIN', 'a_web_60ce0ce04d194b8e242290ce83087a4f', NULL, 1380405525467897857, '2024-12-14 00:01:07', '2024-12-13 12:01:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419456393723905, 'WEB', 'PWD_LOGIN', 'a_web_de9524de268e748bf34bee53df8ae462', NULL, 1380405525467897857, '2024-12-14 00:01:08', '2024-12-13 12:01:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419456393723906, 'WEB', 'PWD_LOGIN', 'a_web_49c47dfc944d91e9283c99b7cae9e1c7', NULL, 1380405525467897857, '2024-12-14 00:01:08', '2024-12-13 12:01:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419456414695425, 'WEB', 'PWD_LOGIN', 'a_web_31b4592f61789ddd0bcf4d79dae76e97', NULL, 1380405525467897857, '2024-12-14 00:01:08', '2024-12-13 12:01:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419457412939777, 'WEB', 'PWD_LOGIN', 'a_web_5c67b108fb1d33219f720fcb79ca3eb3', NULL, 1380405525467897857, '2024-12-14 00:01:08', '2024-12-13 12:01:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419457417134082, 'WEB', 'PWD_LOGIN', 'a_web_6e6958a02d0ec7374e56da27842e4069', NULL, 1380405525467897857, '2024-12-14 00:01:08', '2024-12-13 12:01:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419458285355009, 'WEB', 'PWD_LOGIN', 'a_web_b748e4148e539188ef71c903e5282243', NULL, 1380405525467897857, '2024-12-14 00:01:08', '2024-12-13 12:01:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419458285355010, 'WEB', 'PWD_LOGIN', 'a_web_02118dc0cfce6210eb408f86ddb7500a', NULL, 1380405525467897857, '2024-12-14 00:01:08', '2024-12-13 12:01:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419459472343042, 'WEB', 'PWD_LOGIN', 'a_web_33f80bf7e081626aa3743464249a1d24', NULL, 1380405525467897857, '2024-12-14 00:01:09', '2024-12-13 12:01:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419460202151938, 'WEB', 'PWD_LOGIN', 'a_web_948addc389283dac94a567d606c6f8ce', NULL, 1380405525467897857, '2024-12-14 00:01:09', '2024-12-13 12:01:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419460906795010, 'WEB', 'PWD_LOGIN', 'a_web_4c33dcd977a71873c23317290046d0a4', NULL, 1380405525467897857, '2024-12-14 00:01:09', '2024-12-13 12:01:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419464480342017, 'WEB', 'PWD_LOGIN', 'a_web_5abfe741cfa2cd54144659e439a0e777', NULL, 1380405525467897857, '2024-12-14 00:01:10', '2024-12-13 12:01:10', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419464484536322, 'WEB', 'PWD_LOGIN', 'a_web_b43ce495ebdb47d60b9f670b7856795f', NULL, 1380405525467897857, '2024-12-14 00:01:10', '2024-12-13 12:01:10', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419464803303425, 'WEB', 'PWD_LOGIN', 'a_web_d94c0d7553dc2b5c5e25c609c59bfd72', NULL, 1380405525467897857, '2024-12-14 00:01:10', '2024-12-13 12:01:10', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419464811692033, 'WEB', 'PWD_LOGIN', 'a_web_84748a09fba4820ddde77c3f76242582', NULL, 1380405525467897857, '2024-12-14 00:01:10', '2024-12-13 12:01:10', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419467420549121, 'WEB', 'PWD_LOGIN', 'a_web_baf2c61dc58f5cfd866b0710bcfe2050', NULL, 1380405525467897857, '2024-12-14 00:01:11', '2024-12-13 12:01:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419467508629506, 'WEB', 'PWD_LOGIN', 'a_web_fd34ac9bd86872ff1e2c0e9db75c32f6', NULL, 1380405525467897857, '2024-12-14 00:01:11', '2024-12-13 12:01:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419467521212418, 'WEB', 'PWD_LOGIN', 'a_web_028ce42cfb5ca482548b53f37266e729', NULL, 1380405525467897857, '2024-12-14 00:01:11', '2024-12-13 12:01:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419467554766849, 'WEB', 'PWD_LOGIN', 'a_web_59210681244c6b40141c2e1625330feb', NULL, 1380405525467897857, '2024-12-14 00:01:11', '2024-12-13 12:01:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419468448153602, 'WEB', 'PWD_LOGIN', 'a_web_ccc823bdb08ed38ee4fcf2028b0eee88', NULL, 1380405525467897857, '2024-12-14 00:01:11', '2024-12-13 12:01:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419471866511361, 'WEB', 'PWD_LOGIN', 'a_web_d32c3cc63e5f5e2120b084c6691fbf48', NULL, 1380405525467897857, '2024-12-14 00:01:12', '2024-12-13 12:01:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419473057693697, 'WEB', 'PWD_LOGIN', 'a_web_128aa6003b1b8b6cb155ccfbfd786f1d', NULL, 1380405525467897857, '2024-12-14 00:01:12', '2024-12-13 12:01:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419473082859522, 'WEB', 'PWD_LOGIN', 'a_web_b49ab9f0aeb632765ca32928c4370731', NULL, 1380405525467897857, '2024-12-14 00:01:12', '2024-12-13 12:01:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419473670062082, 'WEB', 'PWD_LOGIN', 'a_web_8dbc0d0bde31e566a29fd6467241fae7', NULL, 1380405525467897857, '2024-12-14 00:01:12', '2024-12-13 12:01:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419473691033602, 'WEB', 'PWD_LOGIN', 'a_web_f70725f8b656f742947ddef976dd0e0d', NULL, 1380405525467897857, '2024-12-14 00:01:12', '2024-12-13 12:01:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419476069203969, 'WEB', 'PWD_LOGIN', 'a_web_9fc077e335215ed0cc4be7cee8a7043a', NULL, 1380405525467897857, '2024-12-14 00:01:13', '2024-12-13 12:01:13', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419476161478657, 'WEB', 'PWD_LOGIN', 'a_web_f8d60ef5d150d44a7a8ca1927b33bdbf', NULL, 1380405525467897857, '2024-12-14 00:01:13', '2024-12-13 12:01:13', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419480527749122, 'WEB', 'PWD_LOGIN', 'a_web_30aa17a48794981fdaf1ba3bee31fdcd', NULL, 1380405525467897857, '2024-12-14 00:01:14', '2024-12-13 12:01:14', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419480552914945, 'WEB', 'PWD_LOGIN', 'a_web_9221583802571a09bb46389aac614aa9', NULL, 1380405525467897857, '2024-12-14 00:01:14', '2024-12-13 12:01:14', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419482155139074, 'WEB', 'PWD_LOGIN', 'a_web_3a9042d84cfd79e7f42447b15f2c7868', NULL, 1380405525467897857, '2024-12-14 00:01:14', '2024-12-13 12:01:14', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419482452934657, 'WEB', 'PWD_LOGIN', 'a_web_a1d78af7b74779711d363f5669a6e49d', NULL, 1380405525467897857, '2024-12-14 00:01:14', '2024-12-13 12:01:14', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419482511654913, 'WEB', 'PWD_LOGIN', 'a_web_54c346e76d68c336a3b875ecb89b1437', NULL, 1380405525467897857, '2024-12-14 00:01:14', '2024-12-13 12:01:14', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419483174354946, 'WEB', 'PWD_LOGIN', 'a_web_a767753a8eb4ebfa2333b50d67818699', NULL, 1380405525467897857, '2024-12-14 00:01:14', '2024-12-13 12:01:14', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419484810133506, 'WEB', 'PWD_LOGIN', 'a_web_abeaf23aefb3106c448d2da48b66f950', NULL, 1380405525467897857, '2024-12-14 00:01:15', '2024-12-13 12:01:15', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419484843687937, 'WEB', 'PWD_LOGIN', 'a_web_d2165f705c00bf3abfbddc2a093675d0', NULL, 1380405525467897857, '2024-12-14 00:01:15', '2024-12-13 12:01:15', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419489524531202, 'WEB', 'PWD_LOGIN', 'a_web_cee08a2a1f94e29f35322b089a1db848', NULL, 1380405525467897857, '2024-12-14 00:01:16', '2024-12-13 12:01:16', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419489532919810, 'WEB', 'PWD_LOGIN', 'a_web_de4ee86788a2f9e1b7dd9b761771c8ee', NULL, 1380405525467897857, '2024-12-14 00:01:16', '2024-12-13 12:01:16', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419489532919811, 'WEB', 'PWD_LOGIN', 'a_web_9c413e74de1c6cf59a2190cb39846665', NULL, 1380405525467897857, '2024-12-14 00:01:16', '2024-12-13 12:01:16', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419490476638210, 'WEB', 'PWD_LOGIN', 'a_web_c15b5c50038e76fe119a09579c41d776', NULL, 1380405525467897857, '2024-12-14 00:01:16', '2024-12-13 12:01:16', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419492124999681, 'WEB', 'PWD_LOGIN', 'a_web_1f15735e8fe4a56fa48e93122936c18b', NULL, 1380405525467897857, '2024-12-14 00:01:17', '2024-12-13 12:01:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419495983759361, 'WEB', 'PWD_LOGIN', 'a_web_21781a77e28e59ffc56c9a2df322d0ec', NULL, 1380405525467897857, '2024-12-14 00:01:17', '2024-12-13 12:01:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419496008925186, 'WEB', 'PWD_LOGIN', 'a_web_7c9eecdc40d755b7362b5232f1d131a5', NULL, 1380405525467897857, '2024-12-14 00:01:17', '2024-12-13 12:01:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419498672308226, 'WEB', 'PWD_LOGIN', 'a_web_78e828173264f67ee0fdb693c00e8e36', NULL, 1380405525467897857, '2024-12-14 00:01:18', '2024-12-13 12:01:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419498735222786, 'WEB', 'PWD_LOGIN', 'a_web_6a0ea82fa4fc871c911d884ae8973742', NULL, 1380405525467897857, '2024-12-14 00:01:18', '2024-12-13 12:01:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419499498586113, 'WEB', 'PWD_LOGIN', 'a_web_9edeff56c006198553cfe3c6e9e4cab3', NULL, 1380405525467897857, '2024-12-14 00:01:18', '2024-12-13 12:01:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419501880950786, 'WEB', 'PWD_LOGIN', 'a_web_3455247a0c3b4b414b2302f0c7c5e7be', NULL, 1380405525467897857, '2024-12-14 00:01:19', '2024-12-13 12:01:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419502510096385, 'WEB', 'PWD_LOGIN', 'a_web_39b515797d15f3d23a8d629bfc97b0e5', NULL, 1380405525467897857, '2024-12-14 00:01:19', '2024-12-13 12:01:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419502518484993, 'WEB', 'PWD_LOGIN', 'a_web_0589df98663492349230e4bb706a8650', NULL, 1380405525467897857, '2024-12-14 00:01:19', '2024-12-13 12:01:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419502535262210, 'WEB', 'PWD_LOGIN', 'a_web_b1bd6cb454ec630ed8e3d685a6e1fa61', NULL, 1380405525467897857, '2024-12-14 00:01:19', '2024-12-13 12:01:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419504741466114, 'WEB', 'PWD_LOGIN', 'a_web_aa35813073566f5d505be0d191c64045', NULL, 1380405525467897857, '2024-12-14 00:01:20', '2024-12-13 12:01:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419504741466115, 'WEB', 'PWD_LOGIN', 'a_web_8dda4859d7c2c30403d8ab54b1eae1fc', NULL, 1380405525467897857, '2024-12-14 00:01:20', '2024-12-13 12:01:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419508105297921, 'WEB', 'PWD_LOGIN', 'a_web_dad29e93eed3564bd4889e83a25ca7c9', NULL, 1380405525467897857, '2024-12-14 00:01:20', '2024-12-13 12:01:20', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419511569793026, 'WEB', 'PWD_LOGIN', 'a_web_1e8327d3b262c4c79ec66c14ccb0167a', NULL, 1380405525467897857, '2024-12-14 00:01:21', '2024-12-13 12:01:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419512136024065, 'WEB', 'PWD_LOGIN', 'a_web_4b809a9a309bf6acb26b475b2a490e97', NULL, 1380405525467897857, '2024-12-14 00:01:21', '2024-12-13 12:01:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419513415286786, 'WEB', 'PWD_LOGIN', 'a_web_27f288b3c7490ab11be996c9be798428', NULL, 1380405525467897857, '2024-12-14 00:01:22', '2024-12-13 12:01:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419513952157698, 'WEB', 'PWD_LOGIN', 'a_web_1a2a9bb64eb28425c063cdc4b24bd269', NULL, 1380405525467897857, '2024-12-14 00:01:22', '2024-12-13 12:01:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419515705376770, 'WEB', 'PWD_LOGIN', 'a_web_9a2556ab1367e53b715b487d6fc18f0a', NULL, 1380405525467897857, '2024-12-14 00:01:22', '2024-12-13 12:01:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419515789262849, 'WEB', 'PWD_LOGIN', 'a_web_8ceb97fb8d42f4123ce8ff09899d7105', NULL, 1380405525467897857, '2024-12-14 00:01:22', '2024-12-13 12:01:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419518549114882, 'WEB', 'PWD_LOGIN', 'a_web_a76b99c8369998ff5ff379477a8f4af4', NULL, 1380405525467897857, '2024-12-14 00:01:23', '2024-12-13 12:01:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419518574280705, 'WEB', 'PWD_LOGIN', 'a_web_d462598e35ff7ab3c0c0c21827f93f6b', NULL, 1380405525467897857, '2024-12-14 00:01:23', '2024-12-13 12:01:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419519790628865, 'WEB', 'PWD_LOGIN', 'a_web_e0d8fd4333be28c4ab61a8be28f11b89', NULL, 1380405525467897857, '2024-12-14 00:01:23', '2024-12-13 12:01:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419520126173185, 'WEB', 'PWD_LOGIN', 'a_web_40f1739d4934f26f730eb1a99539c031', NULL, 1380405525467897857, '2024-12-14 00:01:23', '2024-12-13 12:01:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419523288678401, 'WEB', 'PWD_LOGIN', 'a_web_73567f717542078734d54d625635c7e3', NULL, 1380405525467897857, '2024-12-14 00:01:24', '2024-12-13 12:01:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419523334815746, 'WEB', 'PWD_LOGIN', 'a_web_056f9bf67f7044a7e9cec69b7da20f0a', NULL, 1380405525467897857, '2024-12-14 00:01:24', '2024-12-13 12:01:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419523666165762, 'WEB', 'PWD_LOGIN', 'a_web_dbff84b828a30de8ce8504a7c9134fac', NULL, 1380405525467897857, '2024-12-14 00:01:24', '2024-12-13 12:01:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419523666165763, 'WEB', 'PWD_LOGIN', 'a_web_0554db931bf07749ae0f9572f2c381db', NULL, 1380405525467897857, '2024-12-14 00:01:24', '2024-12-13 12:01:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419523750051841, 'WEB', 'PWD_LOGIN', 'a_web_ca94e3ecb08ccbc1f15c28ab9eedf4a2', NULL, 1380405525467897857, '2024-12-14 00:01:24', '2024-12-13 12:01:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419523896852482, 'WEB', 'PWD_LOGIN', 'a_web_3335bfdc03accd858d7f10fbc4e8aa03', NULL, 1380405525467897857, '2024-12-14 00:01:24', '2024-12-13 12:01:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419524114956290, 'WEB', 'PWD_LOGIN', 'a_web_3428b45f1afb76df091f1987b17fa358', NULL, 1380405525467897857, '2024-12-14 00:01:24', '2024-12-13 12:01:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419525054480386, 'WEB', 'PWD_LOGIN', 'a_web_fa747716cf7ff1032fd8e1b55d51a529', NULL, 1380405525467897857, '2024-12-14 00:01:24', '2024-12-13 12:01:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419526262439937, 'WEB', 'PWD_LOGIN', 'a_web_c2c37776f666643d6e3ae2be0e659c6b', NULL, 1380405525467897857, '2024-12-14 00:01:25', '2024-12-13 12:01:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419526694453250, 'WEB', 'PWD_LOGIN', 'a_web_7253830b34fd40f49d11bdec13657655', NULL, 1380405525467897857, '2024-12-14 00:01:25', '2024-12-13 12:01:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419529190064129, 'WEB', 'PWD_LOGIN', 'a_web_3c77c4c4d2a6e3fb292c3287d19a7cfe', NULL, 1380405525467897857, '2024-12-14 00:01:25', '2024-12-13 12:01:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419529211035650, 'WEB', 'PWD_LOGIN', 'a_web_9db558599d38f834665c95abda6cf91a', NULL, 1380405525467897857, '2024-12-14 00:01:25', '2024-12-13 12:01:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419529420750849, 'WEB', 'PWD_LOGIN', 'a_web_8f241f1376aa98b87834ed489a037a21', NULL, 1380405525467897857, '2024-12-14 00:01:25', '2024-12-13 12:01:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419529450110978, 'WEB', 'PWD_LOGIN', 'a_web_1eea8a5c287f001f54a521c5ed1501d2', NULL, 1380405525467897857, '2024-12-14 00:01:25', '2024-12-13 12:01:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419530792288257, 'WEB', 'PWD_LOGIN', 'a_web_9635ecbf40d684edeba65764480934c6', NULL, 1380405525467897857, '2024-12-14 00:01:26', '2024-12-13 12:01:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419531597594626, 'WEB', 'PWD_LOGIN', 'a_web_e7c4ae7d884f9dfeb1997791353de06a', NULL, 1380405525467897857, '2024-12-14 00:01:26', '2024-12-13 12:01:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419532511952897, 'WEB', 'PWD_LOGIN', 'a_web_850b4a20f2ea1f301a47d0a0b2c28e57', NULL, 1380405525467897857, '2024-12-14 00:01:26', '2024-12-13 12:01:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419532537118722, 'WEB', 'PWD_LOGIN', 'a_web_8303f867c44d8c3f33ee044a4f405f46', NULL, 1380405525467897857, '2024-12-14 00:01:26', '2024-12-13 12:01:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419532549701634, 'WEB', 'PWD_LOGIN', 'a_web_133cf301662d15ba79ac9538000b7a62', NULL, 1380405525467897857, '2024-12-14 00:01:26', '2024-12-13 12:01:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419533816381441, 'WEB', 'PWD_LOGIN', 'a_web_d22d9069d5dfe074ad6aad1bb75f3b57', NULL, 1380405525467897857, '2024-12-14 00:01:26', '2024-12-13 12:01:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419533833158658, 'WEB', 'PWD_LOGIN', 'a_web_dba444902b39f7040b7bdb54b1c28f49', NULL, 1380405525467897857, '2024-12-14 00:01:27', '2024-12-13 12:01:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419535087255553, 'WEB', 'PWD_LOGIN', 'a_web_0174a6dc867e7882700336386242e7b6', NULL, 1380405525467897857, '2024-12-14 00:01:27', '2024-12-13 12:01:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419535670263809, 'WEB', 'PWD_LOGIN', 'a_web_029ad35fd1b5bfff72c25e60bdef3c14', NULL, 1380405525467897857, '2024-12-14 00:01:27', '2024-12-13 12:01:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419536655925249, 'WEB', 'PWD_LOGIN', 'a_web_ac88beb5020fde7f255fca3576a22217', NULL, 1380405525467897857, '2024-12-14 00:01:27', '2024-12-13 12:01:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419536664313857, 'WEB', 'PWD_LOGIN', 'a_web_9f0819b23527b2cbbc8a346ac4b56484', NULL, 1380405525467897857, '2024-12-14 00:01:27', '2024-12-13 12:01:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419538442698753, 'WEB', 'PWD_LOGIN', 'a_web_52c9e8bde81b11eaab9a50647ef8cbff', NULL, 1380405525467897857, '2024-12-14 00:01:28', '2024-12-13 12:01:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419540086865922, 'WEB', 'PWD_LOGIN', 'a_web_91f59c8f1999dbeede2b515d34443010', NULL, 1380405525467897857, '2024-12-14 00:01:28', '2024-12-13 12:01:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419540099448833, 'WEB', 'PWD_LOGIN', 'a_web_4a53c9a48f64e28bab01da00e0c4c134', NULL, 1380405525467897857, '2024-12-14 00:01:28', '2024-12-13 12:01:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419542016245761, 'WEB', 'PWD_LOGIN', 'a_web_a13b7f785f2710ef5630431c9089e275', NULL, 1380405525467897857, '2024-12-14 00:01:28', '2024-12-13 12:01:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419542246932482, 'WEB', 'PWD_LOGIN', 'a_web_58fd34bdc4aa16447011a27fc8e16c03', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419542276292610, 'WEB', 'PWD_LOGIN', 'a_web_6f3ed9df15ccbbf81f7f0a25a231e32f', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419542335012865, 'WEB', 'PWD_LOGIN', 'a_web_a26755ad5f3c6c6fd546aee9ad77cb33', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419542888660994, 'WEB', 'PWD_LOGIN', 'a_web_cd7d2102da289d1b3577b9157f5872ee', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419542959964162, 'WEB', 'PWD_LOGIN', 'a_web_bc670b4e5e9af3bb77532e8d34918ae2', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419545262637058, 'WEB', 'PWD_LOGIN', 'a_web_fac4dd50711289b94e1561d0bcf3914d', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419545375883265, 'WEB', 'PWD_LOGIN', 'a_web_7d47e1a9a30a9dc18eef0241efffcaf9', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419545409437698, 'WEB', 'PWD_LOGIN', 'a_web_1c0d54bf0d4dbb7fb2044091026a4870', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419545417826305, 'WEB', 'PWD_LOGIN', 'a_web_6dc0d8113f3893fa1404b380d88c6d5c', NULL, 1380405525467897857, '2024-12-14 00:01:29', '2024-12-13 12:01:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419547238154242, 'WEB', 'PWD_LOGIN', 'a_web_87db4eb1251a4eb7bd5647f9bf54f76d', NULL, 1380405525467897857, '2024-12-14 00:01:30', '2024-12-13 12:01:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419549205282817, 'WEB', 'PWD_LOGIN', 'a_web_82a3288d8bc4ff11055e249bf56be14f', NULL, 1380405525467897857, '2024-12-14 00:01:30', '2024-12-13 12:01:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419549217865729, 'WEB', 'PWD_LOGIN', 'a_web_8be7125430c399f8cac9633abfac3313', NULL, 1380405525467897857, '2024-12-14 00:01:30', '2024-12-13 12:01:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419551726059522, 'WEB', 'PWD_LOGIN', 'a_web_fd4a97fe9e477834bfd3f93cd9cbfbee', NULL, 1380405525467897857, '2024-12-14 00:01:31', '2024-12-13 12:01:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419551742836738, 'WEB', 'PWD_LOGIN', 'a_web_0f23aa995a15b7d28e9cf6fa96143d71', NULL, 1380405525467897857, '2024-12-14 00:01:31', '2024-12-13 12:01:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419551755419649, 'WEB', 'PWD_LOGIN', 'a_web_d4100eb2014d19ca4f8d178c6051351d', NULL, 1380405525467897857, '2024-12-14 00:01:31', '2024-12-13 12:01:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419552388759553, 'WEB', 'PWD_LOGIN', 'a_web_14abdce0f721681bb12cb8325b1b34ae', NULL, 1380405525467897857, '2024-12-14 00:01:31', '2024-12-13 12:01:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419552678166529, 'WEB', 'PWD_LOGIN', 'a_web_45651b2c45e5c5858c1a92279ab92850', NULL, 1380405525467897857, '2024-12-14 00:01:31', '2024-12-13 12:01:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419553282146305, 'WEB', 'PWD_LOGIN', 'a_web_e50380174ce31e6f8a6d607e6db17d4f', NULL, 1380405525467897857, '2024-12-14 00:01:31', '2024-12-13 12:01:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419555085697026, 'WEB', 'PWD_LOGIN', 'a_web_4d066e9a55731f04bb860826cad4275d', NULL, 1380405525467897857, '2024-12-14 00:01:32', '2024-12-13 12:01:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419555270246401, 'WEB', 'PWD_LOGIN', 'a_web_30e66641af3c76a7ae53ef406c651731', NULL, 1380405525467897857, '2024-12-14 00:01:32', '2024-12-13 12:01:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419558369837057, 'WEB', 'PWD_LOGIN', 'a_web_0ac29aabda1d28628870155bee261b5a', NULL, 1380405525467897857, '2024-12-14 00:01:32', '2024-12-13 12:01:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419558378225665, 'WEB', 'PWD_LOGIN', 'a_web_e7c630aee426142ed2276fe8cb2510db', NULL, 1380405525467897857, '2024-12-14 00:01:32', '2024-12-13 12:01:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419559829454850, 'WEB', 'PWD_LOGIN', 'a_web_f758754fdb3956d4ccb0d810e43c63fd', NULL, 1380405525467897857, '2024-12-14 00:01:33', '2024-12-13 12:01:33', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419559842037761, 'WEB', 'PWD_LOGIN', 'a_web_645963daf455a00dac07d4ac729b8e60', NULL, 1380405525467897857, '2024-12-14 00:01:33', '2024-12-13 12:01:33', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419563302338561, 'WEB', 'PWD_LOGIN', 'a_web_c31c6eb25f4d6e17b044e5a495ccce2e', NULL, 1380405525467897857, '2024-12-14 00:01:34', '2024-12-13 12:01:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419565026197506, 'WEB', 'PWD_LOGIN', 'a_web_3271446823d28062124d988046a64090', NULL, 1380405525467897857, '2024-12-14 00:01:34', '2024-12-13 12:01:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419568826236930, 'WEB', 'PWD_LOGIN', 'a_web_bb5140b11711943dd7313910daccd05f', NULL, 1380405525467897857, '2024-12-14 00:01:35', '2024-12-13 12:01:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419568838819842, 'WEB', 'PWD_LOGIN', 'a_web_9a3591b868a9622d4fa355e56bad7b42', NULL, 1380405525467897857, '2024-12-14 00:01:35', '2024-12-13 12:01:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419568851402754, 'WEB', 'PWD_LOGIN', 'a_web_01329b43bd01dd5b32232fcec9d95791', NULL, 1380405525467897857, '2024-12-14 00:01:35', '2024-12-13 12:01:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419569199529985, 'WEB', 'PWD_LOGIN', 'a_web_496a1db49dbef4f83b46e71278eaad57', NULL, 1380405525467897857, '2024-12-14 00:01:35', '2024-12-13 12:01:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419569199529986, 'WEB', 'PWD_LOGIN', 'a_web_07f005427ae172eee0f23f7222c8b9b0', NULL, 1380405525467897857, '2024-12-14 00:01:35', '2024-12-13 12:01:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419577038684162, 'WEB', 'PWD_LOGIN', 'a_web_81186482a249d8f89295d1a4b3b46326', NULL, 1380405525467897857, '2024-12-14 00:01:37', '2024-12-13 12:01:37', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419577063849986, 'WEB', 'PWD_LOGIN', 'a_web_cf46197c8480d50e12badeb15ae74516', NULL, 1380405525467897857, '2024-12-14 00:01:37', '2024-12-13 12:01:37', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419577252593666, 'WEB', 'PWD_LOGIN', 'a_web_2eb91435c5c85482d904eb8f7f31f377', NULL, 1380405525467897857, '2024-12-14 00:01:37', '2024-12-13 12:01:37', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419577286148097, 'WEB', 'PWD_LOGIN', 'a_web_3ebb3faca64a4cabba257ecc009375ae', NULL, 1380405525467897857, '2024-12-14 00:01:37', '2024-12-13 12:01:37', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419580423487490, 'WEB', 'PWD_LOGIN', 'a_web_58079d90a83a80ab12b6d2ea08044669', NULL, 1380405525467897857, '2024-12-14 00:01:38', '2024-12-13 12:01:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419583212699650, 'WEB', 'PWD_LOGIN', 'a_web_8a59f189f6048cf821894fe502a93d8a', NULL, 1380405525467897857, '2024-12-14 00:01:38', '2024-12-13 12:01:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419583225282561, 'WEB', 'PWD_LOGIN', 'a_web_06cc013a0aa0cb6298dfca81cc0ee082', NULL, 1380405525467897857, '2024-12-14 00:01:38', '2024-12-13 12:01:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419583376277506, 'WEB', 'PWD_LOGIN', 'a_web_52a11c157de95eaa38af78ba5dc4f3a2', NULL, 1380405525467897857, '2024-12-14 00:01:38', '2024-12-13 12:01:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419583418220546, 'WEB', 'PWD_LOGIN', 'a_web_68df259bcbe198729faa867390730775', NULL, 1380405525467897857, '2024-12-14 00:01:38', '2024-12-13 12:01:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419583443386369, 'WEB', 'PWD_LOGIN', 'a_web_c71e268803d7d3440deec8fc705a3a74', NULL, 1380405525467897857, '2024-12-14 00:01:38', '2024-12-13 12:01:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419584882032641, 'WEB', 'PWD_LOGIN', 'a_web_69c49f11e73ac52df81f4f1ed3b553f7', NULL, 1380405525467897857, '2024-12-14 00:01:39', '2024-12-13 12:01:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419584919781377, 'WEB', 'PWD_LOGIN', 'a_web_558899a3476e43b72f3d610fa7ab8596', NULL, 1380405525467897857, '2024-12-14 00:01:39', '2024-12-13 12:01:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419585217576961, 'WEB', 'PWD_LOGIN', 'a_web_58c8feefcfb09a5ce89e620d6690035c', NULL, 1380405525467897857, '2024-12-14 00:01:39', '2024-12-13 12:01:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419587964846082, 'WEB', 'PWD_LOGIN', 'a_web_17f56e8614da3974746db32cf603f550', NULL, 1380405525467897857, '2024-12-14 00:01:39', '2024-12-13 12:01:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419588036149250, 'WEB', 'PWD_LOGIN', 'a_web_3f3bd7e492eaebaabcba37f564dee35d', NULL, 1380405525467897857, '2024-12-14 00:01:39', '2024-12-13 12:01:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419589327994882, 'WEB', 'PWD_LOGIN', 'a_web_c2abad3b182913373f3d4240e4a4b980', NULL, 1380405525467897857, '2024-12-14 00:01:40', '2024-12-13 12:01:40', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419589332189185, 'WEB', 'PWD_LOGIN', 'a_web_773980be0ef215955bbeba9234dd1cd3', NULL, 1380405525467897857, '2024-12-14 00:01:40', '2024-12-13 12:01:40', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419591794245634, 'WEB', 'PWD_LOGIN', 'a_web_80cc8e5e43d14b965c195a151687bb43', NULL, 1380405525467897857, '2024-12-14 00:01:40', '2024-12-13 12:01:40', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419593161588737, 'WEB', 'PWD_LOGIN', 'a_web_2184d49d1693469a19d5396c72c34e58', NULL, 1380405525467897857, '2024-12-14 00:01:41', '2024-12-13 12:01:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419593182560258, 'WEB', 'PWD_LOGIN', 'a_web_ba9b1165ac97e4552c44eeb96c37bbab', NULL, 1380405525467897857, '2024-12-14 00:01:41', '2024-12-13 12:01:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419596252790786, 'WEB', 'PWD_LOGIN', 'a_web_ea0ab6d444231344d3ee8594168bd324', NULL, 1380405525467897857, '2024-12-14 00:01:41', '2024-12-13 12:01:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419598425440257, 'WEB', 'PWD_LOGIN', 'a_web_27bb898170ae69eba10dddbbb01b8bb9', NULL, 1380405525467897857, '2024-12-14 00:01:42', '2024-12-13 12:01:42', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419598500937730, 'WEB', 'PWD_LOGIN', 'a_web_75252a674d34a131570797a8cb935b61', NULL, 1380405525467897857, '2024-12-14 00:01:42', '2024-12-13 12:01:42', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419598563852289, 'WEB', 'PWD_LOGIN', 'a_web_89ec06227ed5fdefeff9a8032fca8d32', NULL, 1380405525467897857, '2024-12-14 00:01:42', '2024-12-13 12:01:42', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419599276883970, 'WEB', 'PWD_LOGIN', 'a_web_fb06cedfe6344d454ea6445bf5994117', NULL, 1380405525467897857, '2024-12-14 00:01:42', '2024-12-13 12:01:42', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419599297855489, 'WEB', 'PWD_LOGIN', 'a_web_b0d204e258df4d184d1b16a636372921', NULL, 1380405525467897857, '2024-12-14 00:01:42', '2024-12-13 12:01:42', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419604184219649, 'WEB', 'PWD_LOGIN', 'a_web_1c5675524cb6f44ecdc4b5c0771e3503', NULL, 1380405525467897857, '2024-12-14 00:01:43', '2024-12-13 12:01:43', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419604381351938, 'WEB', 'PWD_LOGIN', 'a_web_4e437b261c4cb2eb74cfa4b005086d92', NULL, 1380405525467897857, '2024-12-14 00:01:43', '2024-12-13 12:01:43', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419604402323458, 'WEB', 'PWD_LOGIN', 'a_web_7b23e076239e3c6eff2b6eda44af1595', NULL, 1380405525467897857, '2024-12-14 00:01:43', '2024-12-13 12:01:43', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419604825948161, 'WEB', 'PWD_LOGIN', 'a_web_8a99667ce44baf55f3f5410c1a423fcb', NULL, 1380405525467897857, '2024-12-14 00:01:43', '2024-12-13 12:01:43', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419607141203970, 'WEB', 'PWD_LOGIN', 'a_web_93cc8a47c3c6d157931b8ab3ee11096a', NULL, 1380405525467897857, '2024-12-14 00:01:44', '2024-12-13 12:01:44', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419608957337601, 'WEB', 'PWD_LOGIN', 'a_web_f38f6d2ebbdd8f30ea7d849ed1c12953', NULL, 1380405525467897857, '2024-12-14 00:01:44', '2024-12-13 12:01:44', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419609037029377, 'WEB', 'PWD_LOGIN', 'a_web_8ba1e69657ce7c2ed5dbad5b2760bc19', NULL, 1380405525467897857, '2024-12-14 00:01:44', '2024-12-13 12:01:44', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419611893350402, 'WEB', 'PWD_LOGIN', 'a_web_b0fab901ca6af79958a568c69fd274cc', NULL, 1380405525467897857, '2024-12-14 00:01:45', '2024-12-13 12:01:45', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419612891594753, 'WEB', 'PWD_LOGIN', 'a_web_76b8ce560252c61f993ce276b6da3c66', NULL, 1380405525467897857, '2024-12-14 00:01:45', '2024-12-13 12:01:45', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419612891594754, 'WEB', 'PWD_LOGIN', 'a_web_241776cbff555278bb4b6947505b08b8', NULL, 1380405525467897857, '2024-12-14 00:01:45', '2024-12-13 12:01:45', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419612925149186, 'WEB', 'PWD_LOGIN', 'a_web_27402368384d45e7ef59cfc6c5b7ac33', NULL, 1380405525467897857, '2024-12-14 00:01:45', '2024-12-13 12:01:45', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419616481918978, 'WEB', 'PWD_LOGIN', 'a_web_c6c522c1d0943bc2660b4408b0467548', NULL, 1380405525467897857, '2024-12-14 00:01:46', '2024-12-13 12:01:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419616507084801, 'WEB', 'PWD_LOGIN', 'a_web_b7b147c894f4a71c78f9b2558ca4b48e', NULL, 1380405525467897857, '2024-12-14 00:01:46', '2024-12-13 12:01:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419616532250626, 'WEB', 'PWD_LOGIN', 'a_web_0579ca5fe72ff52a9da9e2597af4dcca', NULL, 1380405525467897857, '2024-12-14 00:01:46', '2024-12-13 12:01:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419618734260226, 'WEB', 'PWD_LOGIN', 'a_web_db007e4e4ea3af9a7fde3b83c61d03dc', NULL, 1380405525467897857, '2024-12-14 00:01:47', '2024-12-13 12:01:47', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419618742648834, 'WEB', 'PWD_LOGIN', 'a_web_3cdd9a835c1b1dd282c61b544d2b3411', NULL, 1380405525467897857, '2024-12-14 00:01:47', '2024-12-13 12:01:47', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419618985918465, 'WEB', 'PWD_LOGIN', 'a_web_89a09f81b813837aff1beaa07cdced90', NULL, 1380405525467897857, '2024-12-14 00:01:47', '2024-12-13 12:01:47', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419631170371585, 'WEB', 'PWD_LOGIN', 'a_web_e337caaa920b7045eaece1e72cefcb4e', NULL, 1380405525467897857, '2024-12-14 00:01:50', '2024-12-13 12:01:50', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419632814538753, 'WEB', 'PWD_LOGIN', 'a_web_830155a808effd0344fc39c9b4572889', NULL, 1380405525467897857, '2024-12-14 00:01:50', '2024-12-13 12:01:50', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419632852287490, 'WEB', 'PWD_LOGIN', 'a_web_d9f4a34c68e2cf11ca9dd260d5dfc880', NULL, 1380405525467897857, '2024-12-14 00:01:50', '2024-12-13 12:01:50', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419635213680642, 'WEB', 'PWD_LOGIN', 'a_web_b7a451dcd1cab18afe4dae89a0ced598', NULL, 1380405525467897857, '2024-12-14 00:01:51', '2024-12-13 12:01:51', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419635243040770, 'WEB', 'PWD_LOGIN', 'a_web_1818228f7b30caa48fce14283ab1bbe7', NULL, 1380405525467897857, '2024-12-14 00:01:51', '2024-12-13 12:01:51', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419635255623682, 'WEB', 'PWD_LOGIN', 'a_web_62a40b97abf8d62bfb9d307f362dddf9', NULL, 1380405525467897857, '2024-12-14 00:01:51', '2024-12-13 12:01:51', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419639441539073, 'WEB', 'PWD_LOGIN', 'a_web_e095207151d5bac293a9cb2d480df474', NULL, 1380405525467897857, '2024-12-14 00:01:52', '2024-12-13 12:01:52', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419644466315265, 'WEB', 'PWD_LOGIN', 'a_web_db77c6aba99fa7529a57300d7ebb459c', NULL, 1380405525467897857, '2024-12-14 00:01:53', '2024-12-13 12:01:53', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419644483092481, 'WEB', 'PWD_LOGIN', 'a_web_59fa8a9057303ae5ad3109e79004ba1f', NULL, 1380405525467897857, '2024-12-14 00:01:53', '2024-12-13 12:01:53', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419644491481089, 'WEB', 'PWD_LOGIN', 'a_web_124a6989457c5e1af3058577383e2697', NULL, 1380405525467897857, '2024-12-14 00:01:53', '2024-12-13 12:01:53', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419646357946370, 'WEB', 'PWD_LOGIN', 'a_web_fe9f7af6f8c9463a87d19ada230f8628', NULL, 1380405525467897857, '2024-12-14 00:01:53', '2024-12-13 12:01:53', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419646399889410, 'WEB', 'PWD_LOGIN', 'a_web_2752adcd3f264bceea248354a4fcf1df', NULL, 1380405525467897857, '2024-12-14 00:01:53', '2024-12-13 12:01:53', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419649583366145, 'WEB', 'PWD_LOGIN', 'a_web_021209b10d1e4b808adfb3129dd11ba0', NULL, 1380405525467897857, '2024-12-14 00:01:54', '2024-12-13 12:01:54', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419650896183297, 'WEB', 'PWD_LOGIN', 'a_web_e260af4eddf499acec73a8c4ab4dbd28', NULL, 1380405525467897857, '2024-12-14 00:01:54', '2024-12-13 12:01:54', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419659762941953, 'WEB', 'PWD_LOGIN', 'a_web_1bb6272c42a21ef1ab8836514b2be33b', NULL, 1380405525467897857, '2024-12-14 00:01:57', '2024-12-13 12:01:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419659783913473, 'WEB', 'PWD_LOGIN', 'a_web_fb421bfd010c25430ebc1a9dbf512ea1', NULL, 1380405525467897857, '2024-12-14 00:01:57', '2024-12-13 12:01:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419660887015426, 'WEB', 'PWD_LOGIN', 'a_web_b6e0d868ec1f0b13146c775fa9171c15', NULL, 1380405525467897857, '2024-12-14 00:01:57', '2024-12-13 12:01:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419660907986946, 'WEB', 'PWD_LOGIN', 'a_web_9cd61f3d8a19be7c970b0e38942def58', NULL, 1380405525467897857, '2024-12-14 00:01:57', '2024-12-13 12:01:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419665156816898, 'WEB', 'PWD_LOGIN', 'a_web_4bc885e29ba987864fafac48e3fdca4d', NULL, 1380405525467897857, '2024-12-14 00:01:58', '2024-12-13 12:01:58', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419665165205506, 'WEB', 'PWD_LOGIN', 'a_web_6824c8938be5b458a6b5e69f5f444a09', NULL, 1380405525467897857, '2024-12-14 00:01:58', '2024-12-13 12:01:58', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419665198759937, 'WEB', 'PWD_LOGIN', 'a_web_e8566dc2bdebc02de31c9ceecf1deb4c', NULL, 1380405525467897857, '2024-12-14 00:01:58', '2024-12-13 12:01:58', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419665312006146, 'WEB', 'PWD_LOGIN', 'a_web_38574f2f2ea671d788e0230000b39e5b', NULL, 1380405525467897857, '2024-12-14 00:01:58', '2024-12-13 12:01:58', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419666977144834, 'WEB', 'PWD_LOGIN', 'a_web_5eb7e37ba161006461e4b4eafb5e5f1e', NULL, 1380405525467897857, '2024-12-14 00:01:58', '2024-12-13 12:01:58', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419669216903170, 'WEB', 'PWD_LOGIN', 'a_web_207f3dc2f7dd972a369a2f88465b4a83', NULL, 1380405525467897857, '2024-12-14 00:01:59', '2024-12-13 12:01:59', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419676632432641, 'WEB', 'PWD_LOGIN', 'a_web_8713f184bdb8faa13376abf29254b7fc', NULL, 1380405525467897857, '2024-12-14 00:02:01', '2024-12-13 12:02:01', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419676632432642, 'WEB', 'PWD_LOGIN', 'a_web_f007801554d3782a82dd95e5faf4452c', NULL, 1380405525467897857, '2024-12-14 00:02:01', '2024-12-13 12:02:01', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419680583467009, 'WEB', 'PWD_LOGIN', 'a_web_e4c61dc1d82942f6f1b6b8e30fb73b50', NULL, 1380405525467897857, '2024-12-14 00:02:01', '2024-12-13 12:02:01', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419680621215745, 'WEB', 'PWD_LOGIN', 'a_web_331decb381e5ec3a5f99c64924eb29a2', NULL, 1380405525467897857, '2024-12-14 00:02:01', '2024-12-13 12:02:01', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419680998703105, 'WEB', 'PWD_LOGIN', 'a_web_541b1514cb04f4b8ff8c0b97bc84ab9f', NULL, 1380405525467897857, '2024-12-14 00:02:02', '2024-12-13 12:02:02', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419682508652545, 'WEB', 'PWD_LOGIN', 'a_web_f17765e7028369405a0d03695f05b71e', NULL, 1380405525467897857, '2024-12-14 00:02:02', '2024-12-13 12:02:02', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419682567372801, 'WEB', 'PWD_LOGIN', 'a_web_f659f92257afadc4808467be15eeed11', NULL, 1380405525467897857, '2024-12-14 00:02:02', '2024-12-13 12:02:02', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419682571567105, 'WEB', 'PWD_LOGIN', 'a_web_47f1565223ad20cc42d6ab721f132f31', NULL, 1380405525467897857, '2024-12-14 00:02:02', '2024-12-13 12:02:02', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419685058789378, 'WEB', 'PWD_LOGIN', 'a_web_fa431e281109eeee4f59c7a5d798def5', NULL, 1380405525467897857, '2024-12-14 00:02:03', '2024-12-13 12:02:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419686187057154, 'WEB', 'PWD_LOGIN', 'a_web_05f4b3ca14cfbe6a2436269582a15c9d', NULL, 1380405525467897857, '2024-12-14 00:02:03', '2024-12-13 12:02:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419686187057155, 'WEB', 'PWD_LOGIN', 'a_web_6203e13f35ed9103d8168b594c3abd52', NULL, 1380405525467897857, '2024-12-14 00:02:03', '2024-12-13 12:02:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419688753971201, 'WEB', 'PWD_LOGIN', 'a_web_21bc740f6edd2c36d667b8fda765a10c', NULL, 1380405525467897857, '2024-12-14 00:02:03', '2024-12-13 12:02:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419689689300994, 'WEB', 'PWD_LOGIN', 'a_web_5b8e41e3f27958a1d29460e6298d0880', NULL, 1380405525467897857, '2024-12-14 00:02:04', '2024-12-13 12:02:04', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419689722855426, 'WEB', 'PWD_LOGIN', 'a_web_cb9317e6a643b700d282fa6b4926b947', NULL, 1380405525467897857, '2024-12-14 00:02:04', '2024-12-13 12:02:04', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419695372582913, 'WEB', 'PWD_LOGIN', 'a_web_f1622051070eb57e2903052dcb0c1b51', NULL, 1380405525467897857, '2024-12-14 00:02:05', '2024-12-13 12:02:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419702905552897, 'WEB', 'PWD_LOGIN', 'a_web_f54bf0414557067e7308532e629fa112', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419702913941506, 'WEB', 'PWD_LOGIN', 'a_web_6f350eb1db657cebe50e0ae1bf4f3719', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419702913941507, 'WEB', 'PWD_LOGIN', 'a_web_e895956f85ba71ed23225c37f32e0198', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419702968467458, 'WEB', 'PWD_LOGIN', 'a_web_477189537c04daeb1759469030e1fce7', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419704772018177, 'WEB', 'PWD_LOGIN', 'a_web_17be7889aa30b310538e7d4fb7c86021', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419705346637825, 'WEB', 'PWD_LOGIN', 'a_web_1ebeea39cdd461398bdd3cac7e7ad446', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419705355026434, 'WEB', 'PWD_LOGIN', 'a_web_2d82f8e18bc1868568f48f479fb701d2', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419705359220737, 'WEB', 'PWD_LOGIN', 'a_web_f0a5283caf87be00e5f3706cfe2ae867', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419705367609346, 'WEB', 'PWD_LOGIN', 'a_web_0c77efaf0a58c1d7774f8fb3512e6c63', NULL, 1380405525467897857, '2024-12-14 00:02:07', '2024-12-13 12:02:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419706839810050, 'WEB', 'PWD_LOGIN', 'a_web_eebbbc49e23d5a184bac7603ef971d9c', NULL, 1380405525467897857, '2024-12-14 00:02:08', '2024-12-13 12:02:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419708991488001, 'WEB', 'PWD_LOGIN', 'a_web_f477c3e80c0e7bf42c47f786e0a8e2ac', NULL, 1380405525467897857, '2024-12-14 00:02:08', '2024-12-13 12:02:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419709008265217, 'WEB', 'PWD_LOGIN', 'a_web_6e97b84e4d8378509fca348b78c5d4c3', NULL, 1380405525467897857, '2024-12-14 00:02:08', '2024-12-13 12:02:08', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419711088640002, 'WEB', 'PWD_LOGIN', 'a_web_f6c2b54095d08afeaf5b20221133240d', NULL, 1380405525467897857, '2024-12-14 00:02:09', '2024-12-13 12:02:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419713466810370, 'WEB', 'PWD_LOGIN', 'a_web_8350e387e9a1bf532583a3922622416f', NULL, 1380405525467897857, '2024-12-14 00:02:09', '2024-12-13 12:02:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419713500364802, 'WEB', 'PWD_LOGIN', 'a_web_5add9948df84902d0726578f8f7430e1', NULL, 1380405525467897857, '2024-12-14 00:02:09', '2024-12-13 12:02:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419715828203521, 'WEB', 'PWD_LOGIN', 'a_web_97d089842303db9bbdaaa7947d38cb42', NULL, 1380405525467897857, '2024-12-14 00:02:10', '2024-12-13 12:02:10', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419715836592130, 'WEB', 'PWD_LOGIN', 'a_web_1281e89bdd3887b85e8e0d9cca1942b6', NULL, 1380405525467897857, '2024-12-14 00:02:10', '2024-12-13 12:02:10', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419718726467585, 'WEB', 'PWD_LOGIN', 'a_web_6abf3f3dbe46454e047f5e637ef8ad57', NULL, 1380405525467897857, '2024-12-14 00:02:11', '2024-12-13 12:02:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419719489830913, 'WEB', 'PWD_LOGIN', 'a_web_5d79fc2f2e6ee6d09b593fbb03a4baca', NULL, 1380405525467897857, '2024-12-14 00:02:11', '2024-12-13 12:02:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419722560061441, 'WEB', 'PWD_LOGIN', 'a_web_ab7c9684781196f8bd9a1d72e01339e8', NULL, 1380405525467897857, '2024-12-14 00:02:11', '2024-12-13 12:02:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419722564255745, 'WEB', 'PWD_LOGIN', 'a_web_bfc09d4a52d3c3778193a83213f93f91', NULL, 1380405525467897857, '2024-12-14 00:02:11', '2024-12-13 12:02:11', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419723847712770, 'WEB', 'PWD_LOGIN', 'a_web_d5e1b45bb76a06cafa6553ad1902b42a', NULL, 1380405525467897857, '2024-12-14 00:02:12', '2024-12-13 12:02:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419723860295681, 'WEB', 'PWD_LOGIN', 'a_web_d0b87422906fdbff764ec3d1850054e6', NULL, 1380405525467897857, '2024-12-14 00:02:12', '2024-12-13 12:02:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419723868684289, 'WEB', 'PWD_LOGIN', 'a_web_9b75cd9a6e3112f9f6dd35933699c15b', NULL, 1380405525467897857, '2024-12-14 00:02:12', '2024-12-13 12:02:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419723973541889, 'WEB', 'PWD_LOGIN', 'a_web_aae834a587808060e19e8f0debedb9fb', NULL, 1380405525467897857, '2024-12-14 00:02:12', '2024-12-13 12:02:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419724451692546, 'WEB', 'PWD_LOGIN', 'a_web_afd7a97cdbe5d183f5540183149c226a', NULL, 1380405525467897857, '2024-12-14 00:02:12', '2024-12-13 12:02:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419725475102721, 'WEB', 'PWD_LOGIN', 'a_web_04c27b05f9151799ae8aeed8c02c05e9', NULL, 1380405525467897857, '2024-12-14 00:02:12', '2024-12-13 12:02:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419725525434370, 'WEB', 'PWD_LOGIN', 'a_web_8c7fe24a4d31708deefbbd0fb407a5c5', NULL, 1380405525467897857, '2024-12-14 00:02:12', '2024-12-13 12:02:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419729740709890, 'WEB', 'PWD_LOGIN', 'a_web_7f3e256fbbb4dd6d2094c56920c730a4', NULL, 1380405525467897857, '2024-12-14 00:02:13', '2024-12-13 12:02:13', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419729744904193, 'WEB', 'PWD_LOGIN', 'a_web_d82391f76a4a53250bf8818ded90bcfa', NULL, 1380405525467897857, '2024-12-14 00:02:13', '2024-12-13 12:02:13', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419732106297346, 'WEB', 'PWD_LOGIN', 'a_web_6ffbfe8a121a68aedec32ab9ba080078', NULL, 1380405525467897857, '2024-12-14 00:02:14', '2024-12-13 12:02:14', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419737022021634, 'WEB', 'PWD_LOGIN', 'a_web_fd48d8bff1b12522f591fb429ed35624', NULL, 1380405525467897857, '2024-12-14 00:02:15', '2024-12-13 12:02:15', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419737089130498, 'WEB', 'PWD_LOGIN', 'a_web_810f796e0940e6879f5307254f44e572', NULL, 1380405525467897857, '2024-12-14 00:02:15', '2024-12-13 12:02:15', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419737114296322, 'WEB', 'PWD_LOGIN', 'a_web_4e65f932e1cc3a331e4e7c8c6c17cf76', NULL, 1380405525467897857, '2024-12-14 00:02:15', '2024-12-13 12:02:15', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419738641022978, 'WEB', 'PWD_LOGIN', 'a_web_31355c2e61833e5de4e6040d04f51dde', NULL, 1380405525467897857, '2024-12-14 00:02:15', '2024-12-13 12:02:15', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419744978616322, 'WEB', 'PWD_LOGIN', 'a_web_bcd1890b0fc81c332e92c1d44e53d5bf', NULL, 1380405525467897857, '2024-12-14 00:02:17', '2024-12-13 12:02:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419745016365058, 'WEB', 'PWD_LOGIN', 'a_web_827c43d2a277d1f415b87d1262a394ac', NULL, 1380405525467897857, '2024-12-14 00:02:17', '2024-12-13 12:02:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419749562990594, 'WEB', 'PWD_LOGIN', 'a_web_a876fc69c2b64829cb618957f469d2be', NULL, 1380405525467897857, '2024-12-14 00:02:18', '2024-12-13 12:02:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419753157509121, 'WEB', 'PWD_LOGIN', 'a_web_0a9c785039c68333c1addadff04a027e', NULL, 1380405525467897857, '2024-12-14 00:02:19', '2024-12-13 12:02:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419753375612930, 'WEB', 'PWD_LOGIN', 'a_web_a94316e13c1e67da44399ec882ddd585', NULL, 1380405525467897857, '2024-12-14 00:02:19', '2024-12-13 12:02:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419754852007937, 'WEB', 'PWD_LOGIN', 'a_web_dfa70bd7c04620c3215a732986b58874', NULL, 1380405525467897857, '2024-12-14 00:02:19', '2024-12-13 12:02:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419761650974722, 'WEB', 'PWD_LOGIN', 'a_web_732b095a7412e101e7659f504645d2e6', NULL, 1380405525467897857, '2024-12-14 00:02:21', '2024-12-13 12:02:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419761655169026, 'WEB', 'PWD_LOGIN', 'a_web_d8d1b562cd40cb960d2ec162e36095be', NULL, 1380405525467897857, '2024-12-14 00:02:21', '2024-12-13 12:02:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419765077721090, 'WEB', 'PWD_LOGIN', 'a_web_890c75323a520d4e3751b3b086c3a0cb', NULL, 1380405525467897857, '2024-12-14 00:02:22', '2024-12-13 12:02:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419771901853698, 'WEB', 'PWD_LOGIN', 'a_web_2773a350822445f6fe0053fcb8194f56', NULL, 1380405525467897857, '2024-12-14 00:02:23', '2024-12-13 12:02:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419771952185346, 'WEB', 'PWD_LOGIN', 'a_web_ea589acc8d38ab925e8f5fb7cf5c9be0', NULL, 1380405525467897857, '2024-12-14 00:02:23', '2024-12-13 12:02:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419774800117761, 'WEB', 'PWD_LOGIN', 'a_web_d5387261e854a4dc1a4db22d43df8414', NULL, 1380405525467897857, '2024-12-14 00:02:24', '2024-12-13 12:02:24', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419779229302786, 'WEB', 'PWD_LOGIN', 'a_web_c88b11475a966578c5665f898392a8bb', NULL, 1380405525467897857, '2024-12-14 00:02:25', '2024-12-13 12:02:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419779292217346, 'WEB', 'PWD_LOGIN', 'a_web_9c368caf3278b91d86d15cca06b7d0e8', NULL, 1380405525467897857, '2024-12-14 00:02:25', '2024-12-13 12:02:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419782383419394, 'WEB', 'PWD_LOGIN', 'a_web_0885d7af79e3fa596ad70686d79fdabc', NULL, 1380405525467897857, '2024-12-14 00:02:26', '2024-12-13 12:02:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419782559580161, 'WEB', 'PWD_LOGIN', 'a_web_316424556191c73e29026aa1640f09a3', NULL, 1380405525467897857, '2024-12-14 00:02:26', '2024-12-13 12:02:26', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419786447699970, 'WEB', 'PWD_LOGIN', 'a_web_e9f02ea8cd4acc5b8f927f3a07c6a9cd', NULL, 1380405525467897857, '2024-12-14 00:02:27', '2024-12-13 12:02:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419793187946497, 'WEB', 'PWD_LOGIN', 'a_web_42cbff7c8335bfc5e635f3ec458278e4', NULL, 1380405525467897857, '2024-12-14 00:02:28', '2024-12-13 12:02:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419793192140802, 'WEB', 'PWD_LOGIN', 'a_web_515ec847422214f9345aafaaef60ec2b', NULL, 1380405525467897857, '2024-12-14 00:02:28', '2024-12-13 12:02:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419793192140803, 'WEB', 'PWD_LOGIN', 'a_web_e59f9f4c5aadb42054c5015fe2bd384c', NULL, 1380405525467897857, '2024-12-14 00:02:28', '2024-12-13 12:02:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419793196335105, 'WEB', 'PWD_LOGIN', 'a_web_2f5f0f3e621eac83fe0c955e0a9ef998', NULL, 1380405525467897857, '2024-12-14 00:02:28', '2024-12-13 12:02:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419794123276289, 'WEB', 'PWD_LOGIN', 'a_web_b881097ab4a43c17e3ffee4e6b28ceac', NULL, 1380405525467897857, '2024-12-14 00:02:29', '2024-12-13 12:02:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419794140053506, 'WEB', 'PWD_LOGIN', 'a_web_ed7ef907785be53e043c4996197c7f08', NULL, 1380405525467897857, '2024-12-14 00:02:29', '2024-12-13 12:02:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419797734572033, 'WEB', 'PWD_LOGIN', 'a_web_bf60123c3ce340dfc3d09f81862739eb', NULL, 1380405525467897857, '2024-12-14 00:02:29', '2024-12-13 12:02:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419804776808450, 'WEB', 'PWD_LOGIN', 'a_web_e54797950ceaf0df858bdc6862a440ec', NULL, 1380405525467897857, '2024-12-14 00:02:31', '2024-12-13 12:02:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419807243059202, 'WEB', 'PWD_LOGIN', 'a_web_bfbcf9d38d57a9d2372035c3db00795e', NULL, 1380405525467897857, '2024-12-14 00:02:32', '2024-12-13 12:02:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419807930925057, 'WEB', 'PWD_LOGIN', 'a_web_de0c95a752d71bc2864864ed076314ca', NULL, 1380405525467897857, '2024-12-14 00:02:32', '2024-12-13 12:02:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419808526516225, 'WEB', 'PWD_LOGIN', 'a_web_35429a90c7782bae0583bb8f7e25151f', NULL, 1380405525467897857, '2024-12-14 00:02:32', '2024-12-13 12:02:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419819490426882, 'WEB', 'PWD_LOGIN', 'a_web_d0408af27d28e30efd8938254a198b2e', NULL, 1380405525467897857, '2024-12-14 00:02:35', '2024-12-13 12:02:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419819540758529, 'WEB', 'PWD_LOGIN', 'a_web_fea2730c581e4a3d66ef05f7048b8cfe', NULL, 1380405525467897857, '2024-12-14 00:02:35', '2024-12-13 12:02:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419819695947778, 'WEB', 'PWD_LOGIN', 'a_web_43c2005ce6a54dd384b96c868e378a61', NULL, 1380405525467897857, '2024-12-14 00:02:35', '2024-12-13 12:02:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419835923709953, 'WEB', 'PWD_LOGIN', 'a_web_0f7105502c0a1cddfd69e3014b569381', NULL, 1380405525467897857, '2024-12-14 00:02:39', '2024-12-13 12:02:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419835932098561, 'WEB', 'PWD_LOGIN', 'a_web_2a9f4dc817089aa1180ad8508ace68cf', NULL, 1380405525467897857, '2024-12-14 00:02:39', '2024-12-13 12:02:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419835961458689, 'WEB', 'PWD_LOGIN', 'a_web_c18ce64120d5915a9a29ad3903afcbdc', NULL, 1380405525467897857, '2024-12-14 00:02:39', '2024-12-13 12:02:39', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419846644350978, 'WEB', 'PWD_LOGIN', 'a_web_9100e75ca76747d1cdb91cb8760b2ea7', NULL, 1380405525467897857, '2024-12-14 00:02:41', '2024-12-13 12:02:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419846719848449, 'WEB', 'PWD_LOGIN', 'a_web_c14b19ca548023c0faeb144f49649a58', NULL, 1380405525467897857, '2024-12-14 00:02:41', '2024-12-13 12:02:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419846728237058, 'WEB', 'PWD_LOGIN', 'a_web_9bc76f6a8b864e424672b86e431b921b', NULL, 1380405525467897857, '2024-12-14 00:02:41', '2024-12-13 12:02:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419847051198465, 'WEB', 'PWD_LOGIN', 'a_web_f85beb17db1cb1b4d5ba2f0550c43c89', NULL, 1380405525467897857, '2024-12-14 00:02:41', '2024-12-13 12:02:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419847122501634, 'WEB', 'PWD_LOGIN', 'a_web_cb1182c34c31e2e0fb52e7ebb029a323', NULL, 1380405525467897857, '2024-12-14 00:02:41', '2024-12-13 12:02:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419857469849602, 'WEB', 'PWD_LOGIN', 'a_web_6f80eb44fd19940eedff028df6061444', NULL, 1380405525467897857, '2024-12-14 00:02:44', '2024-12-13 12:02:44', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419857524375554, 'WEB', 'PWD_LOGIN', 'a_web_1f0a35cd5dc0d8955e26322017e49536', NULL, 1380405525467897857, '2024-12-14 00:02:44', '2024-12-13 12:02:44', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419857650204673, 'WEB', 'PWD_LOGIN', 'a_web_d631973b4be42638738b3a823c8180ce', NULL, 1380405525467897857, '2024-12-14 00:02:44', '2024-12-13 12:02:44', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419858950438914, 'WEB', 'PWD_LOGIN', 'a_web_ccb3e17c589dacf9ccfe796d227c6292', NULL, 1380405525467897857, '2024-12-14 00:02:44', '2024-12-13 12:02:44', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419866282082306, 'WEB', 'PWD_LOGIN', 'a_web_3e63b347ded4a89e7658d7ccb0a8d9fc', NULL, 1380405525467897857, '2024-12-14 00:02:46', '2024-12-13 12:02:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419866797981697, 'WEB', 'PWD_LOGIN', 'a_web_24c7fa4a5e2a77501118101243c9b7c5', NULL, 1380405525467897857, '2024-12-14 00:02:46', '2024-12-13 12:02:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419867297103873, 'WEB', 'PWD_LOGIN', 'a_web_f602196297e930ea2e2ddfa3b7cd203f', NULL, 1380405525467897857, '2024-12-14 00:02:46', '2024-12-13 12:02:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419869041934337, 'WEB', 'PWD_LOGIN', 'a_web_c3a730e57583256f80ee55db3de3eb74', NULL, 1380405525467897857, '2024-12-14 00:02:46', '2024-12-13 12:02:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419869201317889, 'WEB', 'PWD_LOGIN', 'a_web_4bffdf085e33df9912012e5547b1b324', NULL, 1380405525467897857, '2024-12-14 00:02:46', '2024-12-13 12:02:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419869201317890, 'WEB', 'PWD_LOGIN', 'a_web_5c71064e528cf8a7db83cd54c67f3edd', NULL, 1380405525467897857, '2024-12-14 00:02:46', '2024-12-13 12:02:46', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419869754966018, 'WEB', 'PWD_LOGIN', 'a_web_4e4987819b03f46e07dc68fe5dbe20c8', NULL, 1380405525467897857, '2024-12-14 00:02:47', '2024-12-13 12:02:47', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419875551494146, 'WEB', 'PWD_LOGIN', 'a_web_e162d70a745d374ab4bb4324dc2b5c35', NULL, 1380405525467897857, '2024-12-14 00:02:48', '2024-12-13 12:02:48', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419875585048578, 'WEB', 'PWD_LOGIN', 'a_web_057233aa0b95c0dc01f6dcf59c1f75f7', NULL, 1380405525467897857, '2024-12-14 00:02:48', '2024-12-13 12:02:48', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419880458829825, 'WEB', 'PWD_LOGIN', 'a_web_567c448929a4e337af882bd5b7ede2d1', NULL, 1380405525467897857, '2024-12-14 00:02:49', '2024-12-13 12:02:49', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419885085147137, 'WEB', 'PWD_LOGIN', 'a_web_abcc1d7a8cffd0e5e8efdfb6e5d3c47f', NULL, 1380405525467897857, '2024-12-14 00:02:50', '2024-12-13 12:02:50', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419885089341441, 'WEB', 'PWD_LOGIN', 'a_web_e43f5c368256acd5fadb2e6ee4b4f79a', NULL, 1380405525467897857, '2024-12-14 00:02:50', '2024-12-13 12:02:50', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419890080563201, 'WEB', 'PWD_LOGIN', 'a_web_872d1ff24e63f56f8e3035843bc0391a', NULL, 1380405525467897857, '2024-12-14 00:02:51', '2024-12-13 12:02:51', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419891095584769, 'WEB', 'PWD_LOGIN', 'a_web_198063eec3b17be0b73bf05d7451f8c4', NULL, 1380405525467897857, '2024-12-14 00:02:52', '2024-12-13 12:02:52', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419891565346817, 'WEB', 'PWD_LOGIN', 'a_web_a8e92630b77ab9d4b16ac1c3c210c272', NULL, 1380405525467897857, '2024-12-14 00:02:52', '2024-12-13 12:02:52', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419898779549698, 'WEB', 'PWD_LOGIN', 'a_web_d8d903d3d186fe90d5bc0ad8d9c89543', NULL, 1380405525467897857, '2024-12-14 00:02:54', '2024-12-13 12:02:54', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419898804715521, 'WEB', 'PWD_LOGIN', 'a_web_6bc8cfbaf9dabc2a69fd115e72f4a232', NULL, 1380405525467897857, '2024-12-14 00:02:54', '2024-12-13 12:02:54', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419903724634114, 'WEB', 'PWD_LOGIN', 'a_web_7f5b6af0d85f5d956a63c91216321b81', NULL, 1380405525467897857, '2024-12-14 00:02:55', '2024-12-13 12:02:55', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419905003896833, 'WEB', 'PWD_LOGIN', 'a_web_6cdc7687b0def741b62e2394e8ea1b25', NULL, 1380405525467897857, '2024-12-14 00:02:55', '2024-12-13 12:02:55', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419905008091138, 'WEB', 'PWD_LOGIN', 'a_web_d11698bddaeb29dec16ae1d9e55e88b7', NULL, 1380405525467897857, '2024-12-14 00:02:55', '2024-12-13 12:02:55', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419907612753921, 'WEB', 'PWD_LOGIN', 'a_web_e14872c215740b03ca172ddb52d26be0', NULL, 1380405525467897857, '2024-12-14 00:02:56', '2024-12-13 12:02:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419908501946370, 'WEB', 'PWD_LOGIN', 'a_web_669e068c73cd53ecab1444c37a149d5d', NULL, 1380405525467897857, '2024-12-14 00:02:56', '2024-12-13 12:02:56', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419914768236545, 'WEB', 'PWD_LOGIN', 'a_web_2a9db3a7a5e63695a082a118c88f77a0', NULL, 1380405525467897857, '2024-12-14 00:02:57', '2024-12-13 12:02:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419915137335298, 'WEB', 'PWD_LOGIN', 'a_web_5f580e37422b2269f2ebf2c07c1060c1', NULL, 1380405525467897857, '2024-12-14 00:02:57', '2024-12-13 12:02:57', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419916898942978, 'WEB', 'PWD_LOGIN', 'a_web_54f56b939b981d1ec76408e19a88424a', NULL, 1380405525467897857, '2024-12-14 00:02:58', '2024-12-13 12:02:58', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419924079591425, 'WEB', 'PWD_LOGIN', 'a_web_aa8704c5ded1457cf5236f968248d87b', NULL, 1380405525467897857, '2024-12-14 00:03:00', '2024-12-13 12:03:00', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419924096368642, 'WEB', 'PWD_LOGIN', 'a_web_f0e9815a2971baf49215078924361235', NULL, 1380405525467897857, '2024-12-14 00:03:00', '2024-12-13 12:03:00', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419927799939073, 'WEB', 'PWD_LOGIN', 'a_web_072f7e76d5494cb009ebb10872c0f995', NULL, 1380405525467897857, '2024-12-14 00:03:00', '2024-12-13 12:03:00', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419935139971073, 'WEB', 'PWD_LOGIN', 'a_web_c0a777a6d7efd835d1958eb09c96c50c', NULL, 1380405525467897857, '2024-12-14 00:03:02', '2024-12-13 12:03:02', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419935139971074, 'WEB', 'PWD_LOGIN', 'a_web_21071699b2d1cf7d3fe2080898827324', NULL, 1380405525467897857, '2024-12-14 00:03:02', '2024-12-13 12:03:02', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419938147287041, 'WEB', 'PWD_LOGIN', 'a_web_ed8ac795936727549f9afae71e8fa825', NULL, 1380405525467897857, '2024-12-14 00:03:03', '2024-12-13 12:03:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419939833397249, 'WEB', 'PWD_LOGIN', 'a_web_33e1a872ab667c4c2cf190737d41f3d5', NULL, 1380405525467897857, '2024-12-14 00:03:03', '2024-12-13 12:03:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419940533846017, 'WEB', 'PWD_LOGIN', 'a_web_4e7705c7838d35aa7f0c41d486cd55f8', NULL, 1380405525467897857, '2024-12-14 00:03:03', '2024-12-13 12:03:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419941020385282, 'WEB', 'PWD_LOGIN', 'a_web_d8cee4d51170ee101c4809732ace9902', NULL, 1380405525467897857, '2024-12-14 00:03:04', '2024-12-13 12:03:04', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419942710689793, 'WEB', 'PWD_LOGIN', 'a_web_ce3b2ee16a5906a383c2142b85de4a81', NULL, 1380405525467897857, '2024-12-14 00:03:04', '2024-12-13 12:03:04', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419943075594241, 'WEB', 'PWD_LOGIN', 'a_web_1f40b2c0f6824b090fd7e56f3001ac47', NULL, 1380405525467897857, '2024-12-14 00:03:04', '2024-12-13 12:03:04', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419944828813314, 'WEB', 'PWD_LOGIN', 'a_web_5f8e7ee99878f2582a2a7e459c60762f', NULL, 1380405525467897857, '2024-12-14 00:03:04', '2024-12-13 12:03:04', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419946540089345, 'WEB', 'PWD_LOGIN', 'a_web_b2523eb3879d88aba1efa0982c67b478', NULL, 1380405525467897857, '2024-12-14 00:03:05', '2024-12-13 12:03:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419948633047042, 'WEB', 'PWD_LOGIN', 'a_web_d5293b9f318cd732b9a8a8ff1e19af24', NULL, 1380405525467897857, '2024-12-14 00:03:05', '2024-12-13 12:03:05', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419949497073665, 'WEB', 'PWD_LOGIN', 'a_web_1526d90dff8e053e9442d0547f097bfa', NULL, 1380405525467897857, '2024-12-14 00:03:06', '2024-12-13 12:03:06', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419950931525633, 'WEB', 'PWD_LOGIN', 'a_web_33b23ab0a38187a03cf7351fe70a7af5', NULL, 1380405525467897857, '2024-12-14 00:03:06', '2024-12-13 12:03:06', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419954224054274, 'WEB', 'PWD_LOGIN', 'a_web_affb45bc1616fd005ba4cb24c0e1ba4a', NULL, 1380405525467897857, '2024-12-14 00:03:07', '2024-12-13 12:03:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419954505072642, 'WEB', 'PWD_LOGIN', 'a_web_5c31934f68ceba2423640ac081f061b7', NULL, 1380405525467897857, '2024-12-14 00:03:07', '2024-12-13 12:03:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419956321206274, 'WEB', 'PWD_LOGIN', 'a_web_8c5bdeaf39d25677d70e8cacc0d4a267', NULL, 1380405525467897857, '2024-12-14 00:03:07', '2024-12-13 12:03:07', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419963229224962, 'WEB', 'PWD_LOGIN', 'a_web_1717ea743686635abbfd0492e67bc74e', NULL, 1380405525467897857, '2024-12-14 00:03:09', '2024-12-13 12:03:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419963325693953, 'WEB', 'PWD_LOGIN', 'a_web_fad87a036c717b51737876967537c7d1', NULL, 1380405525467897857, '2024-12-14 00:03:09', '2024-12-13 12:03:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419963350859778, 'WEB', 'PWD_LOGIN', 'a_web_4ed7fe738bb01f21fb9fd5266aba06cc', NULL, 1380405525467897857, '2024-12-14 00:03:09', '2024-12-13 12:03:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419964873392130, 'WEB', 'PWD_LOGIN', 'a_web_7123f4aaa32f64339f56c442f898e639', NULL, 1380405525467897857, '2024-12-14 00:03:09', '2024-12-13 12:03:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419965691281410, 'WEB', 'PWD_LOGIN', 'a_web_971a3138371da6eef131a5b587451f87', NULL, 1380405525467897857, '2024-12-14 00:03:09', '2024-12-13 12:03:09', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419974365102081, 'WEB', 'PWD_LOGIN', 'a_web_198479559490325fb7eb5935b8ad3f16', NULL, 1380405525467897857, '2024-12-14 00:03:12', '2024-12-13 12:03:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419975673724929, 'WEB', 'PWD_LOGIN', 'a_web_82c9db48022bb8daa15fb626116d1260', NULL, 1380405525467897857, '2024-12-14 00:03:12', '2024-12-13 12:03:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419976655192066, 'WEB', 'PWD_LOGIN', 'a_web_c936bc763952245d86011de67aaa160d', NULL, 1380405525467897857, '2024-12-14 00:03:12', '2024-12-13 12:03:12', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419981809991681, 'WEB', 'PWD_LOGIN', 'a_web_06eea65b6070bf4419c90b38a9dcd0e2', NULL, 1380405525467897857, '2024-12-14 00:03:13', '2024-12-13 12:03:13', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419986490834945, 'WEB', 'PWD_LOGIN', 'a_web_19e4a7e0fc1503fffc09dd7e16415188', NULL, 1380405525467897857, '2024-12-14 00:03:14', '2024-12-13 12:03:14', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419986889293825, 'WEB', 'PWD_LOGIN', 'a_web_d858990abca14ea8f6879e23b399f427', NULL, 1380405525467897857, '2024-12-14 00:03:15', '2024-12-13 12:03:15', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419992849399809, 'WEB', 'PWD_LOGIN', 'a_web_d9b6cdbca48ebb9f753ce8f283f2bcad', NULL, 1380405525467897857, '2024-12-14 00:03:16', '2024-12-13 12:03:16', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419997995810818, 'WEB', 'PWD_LOGIN', 'a_web_896d6c95315a1db0ac23db450361e105', NULL, 1380405525467897857, '2024-12-14 00:03:17', '2024-12-13 12:03:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419997995810819, 'WEB', 'PWD_LOGIN', 'a_web_23408e6d06b04d0154396d5767cb2c00', NULL, 1380405525467897857, '2024-12-14 00:03:17', '2024-12-13 12:03:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419997995810820, 'WEB', 'PWD_LOGIN', 'a_web_de20cd982b53b026cd67c396bea13da0', NULL, 1380405525467897857, '2024-12-14 00:03:17', '2024-12-13 12:03:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867419998062919682, 'WEB', 'PWD_LOGIN', 'a_web_1a633c6de8ac7426e7f1d3eb8db772ac', NULL, 1380405525467897857, '2024-12-14 00:03:17', '2024-12-13 12:03:17', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420001099595778, 'WEB', 'PWD_LOGIN', 'a_web_16cba80e7d45354c4386a0d7ca911d8f', NULL, 1380405525467897857, '2024-12-14 00:03:18', '2024-12-13 12:03:18', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420005658804225, 'WEB', 'PWD_LOGIN', 'a_web_dd9331e8cfa4c50a8ec85ba19443d92e', NULL, 1380405525467897857, '2024-12-14 00:03:19', '2024-12-13 12:03:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420005751078914, 'WEB', 'PWD_LOGIN', 'a_web_7b7144aa444ba7d8a7f92ff475b91de9', NULL, 1380405525467897857, '2024-12-14 00:03:19', '2024-12-13 12:03:19', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420013376323585, 'WEB', 'PWD_LOGIN', 'a_web_8c33fd5e86f132abab75dd289bc9662f', NULL, 1380405525467897857, '2024-12-14 00:03:21', '2024-12-13 12:03:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420013439238145, 'WEB', 'PWD_LOGIN', 'a_web_3684321be31422ea89b7bc994c0c4357', NULL, 1380405525467897857, '2024-12-14 00:03:21', '2024-12-13 12:03:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420014068383745, 'WEB', 'PWD_LOGIN', 'a_web_2c72fc2dc2f66e91056ceb5f09c3df75', NULL, 1380405525467897857, '2024-12-14 00:03:21', '2024-12-13 12:03:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420014148075522, 'WEB', 'PWD_LOGIN', 'a_web_df7d1a78d3c3f65fd02a1a082acb8cad', NULL, 1380405525467897857, '2024-12-14 00:03:21', '2024-12-13 12:03:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420016022929409, 'WEB', 'PWD_LOGIN', 'a_web_79e0ef460a1407ad15bc27e5b9f12a00', NULL, 1380405525467897857, '2024-12-14 00:03:21', '2024-12-13 12:03:21', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420018585649154, 'WEB', 'PWD_LOGIN', 'a_web_957b1f7596fdab580ef92e3bb668103c', NULL, 1380405525467897857, '2024-12-14 00:03:22', '2024-12-13 12:03:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420018594037762, 'WEB', 'PWD_LOGIN', 'a_web_25a73a4cba3deaec214af85b63c8215f', NULL, 1380405525467897857, '2024-12-14 00:03:22', '2024-12-13 12:03:22', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420023115497473, 'WEB', 'PWD_LOGIN', 'a_web_979c258b1b19d31a6afc652e540ebbc9', NULL, 1380405525467897857, '2024-12-14 00:03:23', '2024-12-13 12:03:23', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420029432119298, 'WEB', 'PWD_LOGIN', 'a_web_835cb859dd4334787c432b21d6e06aa3', NULL, 1380405525467897857, '2024-12-14 00:03:25', '2024-12-13 12:03:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420029432119299, 'WEB', 'PWD_LOGIN', 'a_web_4a9726dfcc810a49b038e9352d7b7304', NULL, 1380405525467897857, '2024-12-14 00:03:25', '2024-12-13 12:03:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420029490839554, 'WEB', 'PWD_LOGIN', 'a_web_d1c80ae587335bc73d3822a843c084e5', NULL, 1380405525467897857, '2024-12-14 00:03:25', '2024-12-13 12:03:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420032665927682, 'WEB', 'PWD_LOGIN', 'a_web_9621f4c1eb3957557fff10330331e627', NULL, 1380405525467897857, '2024-12-14 00:03:25', '2024-12-13 12:03:25', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420041159393281, 'WEB', 'PWD_LOGIN', 'a_web_284cffb1a082829fdcdbac65f320a637', NULL, 1380405525467897857, '2024-12-14 00:03:27', '2024-12-13 12:03:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420041159393282, 'WEB', 'PWD_LOGIN', 'a_web_3fdeb0d8599b2b65bb13feaf6e6b47d6', NULL, 1380405525467897857, '2024-12-14 00:03:27', '2024-12-13 12:03:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420041243279362, 'WEB', 'PWD_LOGIN', 'a_web_1a830bb1ea6fd96add55917c11e4a2d8', NULL, 1380405525467897857, '2024-12-14 00:03:27', '2024-12-13 12:03:27', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420041616572418, 'WEB', 'PWD_LOGIN', 'a_web_dc186c2dc851e96c47135909430d93f2', NULL, 1380405525467897857, '2024-12-14 00:03:28', '2024-12-13 12:03:28', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420048109355009, 'WEB', 'PWD_LOGIN', 'a_web_aecf7e830a51118a5b0b12f74d573718', NULL, 1380405525467897857, '2024-12-14 00:03:29', '2024-12-13 12:03:29', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420051963920385, 'WEB', 'PWD_LOGIN', 'a_web_87d559f35015435ec7f964a9388ae62e', NULL, 1380405525467897857, '2024-12-14 00:03:30', '2024-12-13 12:03:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420053343846402, 'WEB', 'PWD_LOGIN', 'a_web_a8d810f3eb23e81879425c8b7c8015f2', NULL, 1380405525467897857, '2024-12-14 00:03:30', '2024-12-13 12:03:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420053356429313, 'WEB', 'PWD_LOGIN', 'a_web_0d9d40ebd51fe9da3e7809020c525c3f', NULL, 1380405525467897857, '2024-12-14 00:03:30', '2024-12-13 12:03:30', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420056497963010, 'WEB', 'PWD_LOGIN', 'a_web_6a7d55931eb2d5dabf301c428322832f', NULL, 1380405525467897857, '2024-12-14 00:03:31', '2024-12-13 12:03:31', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420060356722690, 'WEB', 'PWD_LOGIN', 'a_web_b7d239dd0861332994825c3f40b95304', NULL, 1380405525467897857, '2024-12-14 00:03:32', '2024-12-13 12:03:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420060394471425, 'WEB', 'PWD_LOGIN', 'a_web_252e7f656454024d710c2bd910ee5f0d', NULL, 1380405525467897857, '2024-12-14 00:03:32', '2024-12-13 12:03:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420060759375873, 'WEB', 'PWD_LOGIN', 'a_web_280baaa3ee16f4022649bd73e47215a7', NULL, 1380405525467897857, '2024-12-14 00:03:32', '2024-12-13 12:03:32', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420067126329346, 'WEB', 'PWD_LOGIN', 'a_web_9b7525b28e1ac6befcad7f2c4f0c8c92', NULL, 1380405525467897857, '2024-12-14 00:03:34', '2024-12-13 12:03:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420067331850242, 'WEB', 'PWD_LOGIN', 'a_web_f4c107b051576e5065493228d62dc264', NULL, 1380405525467897857, '2024-12-14 00:03:34', '2024-12-13 12:03:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420067331850243, 'WEB', 'PWD_LOGIN', 'a_web_ac8d9aadbdd74c3e4d4630ff7434f75a', NULL, 1380405525467897857, '2024-12-14 00:03:34', '2024-12-13 12:03:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420067956801538, 'WEB', 'PWD_LOGIN', 'a_web_99c3b1506465424b582b3bd07bfc6641', NULL, 1380405525467897857, '2024-12-14 00:03:34', '2024-12-13 12:03:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420069999427586, 'WEB', 'PWD_LOGIN', 'a_web_43f9d5df0a640cb4912cf1b6bd9ce8ef', NULL, 1380405525467897857, '2024-12-14 00:03:34', '2024-12-13 12:03:34', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420072310489090, 'WEB', 'PWD_LOGIN', 'a_web_040a8761d3d05274fceff066ccd06955', NULL, 1380405525467897857, '2024-12-14 00:03:35', '2024-12-13 12:03:35', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420078312538113, 'WEB', 'PWD_LOGIN', 'a_web_a43b2ac6b035df286cc97382e8a3b298', NULL, 1380405525467897857, '2024-12-14 00:03:36', '2024-12-13 12:03:36', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420083706413058, 'WEB', 'PWD_LOGIN', 'a_web_4cf713ec4c7096f35dfb8ab854b4dcd8', NULL, 1380405525467897857, '2024-12-14 00:03:38', '2024-12-13 12:03:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420083727384578, 'WEB', 'PWD_LOGIN', 'a_web_9562296ea99c359ac13dec0b11e8bbaa', NULL, 1380405525467897857, '2024-12-14 00:03:38', '2024-12-13 12:03:38', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420091751088130, 'WEB', 'PWD_LOGIN', 'a_web_e596ad8bb29e697b9cf3b6a272cf9757', NULL, 1380405525467897857, '2024-12-14 00:03:40', '2024-12-13 12:03:40', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420098235482114, 'WEB', 'PWD_LOGIN', 'a_web_ac0ddbbc7267728dcb7183d30789e993', NULL, 1380405525467897857, '2024-12-14 00:03:41', '2024-12-13 12:03:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420098424225794, 'WEB', 'PWD_LOGIN', 'a_web_24ab968b547c761f780343226df6312a', NULL, 1380405525467897857, '2024-12-14 00:03:41', '2024-12-13 12:03:41', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (1867420106716364801, 'WEB', 'PWD_LOGIN', 'a_web_d22b4e7e6dddf2c972bc958815d97049', NULL, 1380405525467897857, '2024-12-14 00:03:43', '2024-12-13 12:03:43', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (2045076505092833281, 'WEB', 'PWD_LOGIN', 'u_web_e028d8535ff416b0feb8a1b25de3f630', NULL, 1216914154267365377, '2026-04-18 05:46:49', '2026-04-17 17:46:49', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_token` (`id`, `type`, `way`, `token`, `device_id`, `account_id`, `expires_time`, `last_fresh_time`, `status`, `remarks`, `created_by`, `created_at`, `updated_by`, `updated_at`) VALUES (2045076562844205058, 'WEB', 'PWD_LOGIN', 'u_web_ebae608575a445a28d6a9ab296b8e13a', NULL, 1216914154267365377, '2026-04-18 05:47:03', '2026-04-17 17:47:03', 'NORMAL', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_tripartite_account
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_tripartite_account`;
CREATE TABLE `t_sys_tripartite_account` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `app_code` varchar(64) DEFAULT NULL COMMENT 'appCode',
  `app_id` varchar(64) DEFAULT NULL COMMENT 'appId',
  `open_id` varchar(255) DEFAULT NULL COMMENT 'open_id',
  `union_id` varchar(255) DEFAULT NULL COMMENT 'union_id',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `gender` varchar(64) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `country` varchar(64) DEFAULT NULL COMMENT '国家',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账号id',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='三方绑定信息信息';

-- ----------------------------
-- Records of t_sys_tripartite_account
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_tripartite_account` (`id`, `type`, `app_code`, `app_id`, `open_id`, `union_id`, `mobile`, `nickname`, `gender`, `avatar`, `country`, `province`, `city`, `account_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1925841482791530497, 'WE_CHAT', '113', 'wxed7d36e4b5fd4209', 'o54WF4tvJxB8xpdzYTKxZ6Gl1Tbo', 'o1ud753lB_8XWTdtBnt0KfbAomzg', NULL, '微信用户', 'unknown', 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', '', '', '', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_tripartite_app
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_tripartite_app`;
CREATE TABLE `t_sys_tripartite_app` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `app_code` varchar(64) NOT NULL COMMENT 'appCode',
  `type` varchar(64) NOT NULL COMMENT 'WX',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `app_id` varchar(64) NOT NULL COMMENT 'appId',
  `app_secret` varchar(64) NOT NULL COMMENT 'appSecret',
  `mch_id` varchar(64) NOT NULL COMMENT '商户appId',
  `mch_secret` varchar(255) NOT NULL COMMENT '商户appSecret',
  `is_platform` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开放平台',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用';

-- ----------------------------
-- Records of t_sys_tripartite_app
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_tripartite_app` (`id`, `app_code`, `type`, `name`, `app_id`, `app_secret`, `mch_id`, `mch_secret`, `is_platform`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, '110', 'WE_CHAT', '好又买', 'wx4603d0257c43d9c4', 'b677315a2b2c51ecd73e9c7b92e9b1e9', '1562599161', 'wxlxcbkjysgszxrmq20180601xh93412', 0, '2020-05-06 17:14:37', NULL, '2020-05-06 17:14:39', NULL);
INSERT INTO `t_sys_tripartite_app` (`id`, `app_code`, `type`, `name`, `app_id`, `app_secret`, `mch_id`, `mch_secret`, `is_platform`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, '111', 'WE_CHAT', '超境', 'wx72092eb6ced420de', 'd8cca1a9a44d26305841cc45284c1bd6', '1608049181', 'b677315a2b2c51ecd73e9c7b92e9b1e9', 0, '2021-04-12 11:00:15', NULL, '2021-04-12 11:00:18', NULL);
INSERT INTO `t_sys_tripartite_app` (`id`, `app_code`, `type`, `name`, `app_id`, `app_secret`, `mch_id`, `mch_secret`, `is_platform`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (3, '112', 'WE_CHAT', '电动瓦力', 'wx535d7e2b3e0421cc', '69e19032972e25da7bfc9da7fcc89348', '122121', '22112', 0, '2021-04-12 11:00:15', NULL, '2021-04-12 11:00:15', NULL);
INSERT INTO `t_sys_tripartite_app` (`id`, `app_code`, `type`, `name`, `app_id`, `app_secret`, `mch_id`, `mch_secret`, `is_platform`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (4, '113', 'WE_CHAT', '来年', 'wxed7d36e4b5fd4209', 'fc732205ce2c5a7d7cd9a9d06ec39030', '2', '2', 0, '2025-05-23 16:36:29', '1', '2025-05-23 16:36:32', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
