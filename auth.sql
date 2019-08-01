/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : auth

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-08-01 10:04:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_id` varchar(64) NOT NULL COMMENT '路由编号',
  `service_id` varchar(64) NOT NULL COMMENT '服务编号',
  `path` varchar(128) DEFAULT NULL COMMENT '路由的路径',
  `url` varchar(128) DEFAULT NULL COMMENT '映射到路由的完整物理路径',
  `strip_prefix` tinyint(4) NOT NULL DEFAULT '0' COMMENT '路由前缀标志(0:去掉 1:不去掉)',
  `retryable` tinyint(4) NOT NULL DEFAULT '0' COMMENT '重试标志(0:不重试 1:重试)',
  `enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '启用标志(0:不启用 1:启用)',
  `service_name` varchar(64) DEFAULT NULL COMMENT '服务名称',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8 COMMENT='网关路由表';

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
INSERT INTO `gateway_route` VALUES ('1001', 'auth', 'auth', '/uaa/**', null, '1', '0', '1', null, '2018-12-13 13:35:23', '2018-12-13 16:54:42');
INSERT INTO `gateway_route` VALUES ('1002', 'admin', 'admin', '/admin/**', null, '0', '0', '1', null, '2018-12-14 17:17:56', '2019-07-29 17:32:34');
INSERT INTO `gateway_route` VALUES ('1003', 'customer', 'customer', '/customer/**', null, '0', '0', '1', null, '2018-12-14 17:19:13', '2019-07-29 17:48:29');

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userid` varchar(256) DEFAULT NULL,
  `clientid` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresat` timestamp NULL DEFAULT NULL,
  `lastmodifiedat` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(45) DEFAULT 'true',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('api', null, '$2a$10$4RrpN5lQY0wjjNFQY6LH3OrXlNcrMCeHBHiUwX4VXpuIHFr5.vsdi', 'server-scope', 'authorization_code,password,refresh_token,client_credentials,ldap', '', 'role_client, role_trusted_client,ROLE_USER,ROLE_ADMIN', '30000', '30000', null, 'false');
INSERT INTO `oauth_client_details` VALUES ('ui', null, '$2a$10$4RrpN5lQY0wjjNFQY6LH3OrXlNcrMCeHBHiUwX4VXpuIHFr5.vsdi', 'ui-scope', 'authorization_code,password,refresh_token,client_credentials', '', 'role_client, role_trusted_client', '30000', '30000', null, 'false');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
