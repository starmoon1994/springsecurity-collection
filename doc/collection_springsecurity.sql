/*
Navicat MySQL Data Transfer

Source Server         : 本地localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : collection_springsecurity

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-23 16:16:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sec_menu
-- ----------------------------
DROP TABLE IF EXISTS `sec_menu`;
CREATE TABLE `sec_menu` (
  `id` int(11) NOT NULL,
  `title` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT '#',
  `path` varchar(128) DEFAULT NULL,
  `component` varchar(32) DEFAULT NULL,
  `ico` varchar(16) DEFAULT 'fa-tags',
  `url` varchar(64) DEFAULT '#',
  `orderNo` tinyint(4) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '0显示 1不显示',
  `pid` int(11) DEFAULT '0' COMMENT '父模块id',
  `resourceType` varchar(255) DEFAULT NULL COMMENT '资源类型， 菜单 1 |接口 2',
  `permission` text COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_menu
-- ----------------------------
INSERT INTO `sec_menu` VALUES ('1', '表格', 'tables', '/tables', 'Main', 'ios-grid-view', '#', '1', '0', '0', '1', '[\"user:add1\"]');
INSERT INTO `sec_menu` VALUES ('2', '高级路由', 'advanced-router', '/advanced-router', 'Main', 'ios-infinite', '#', '2', '0', '0', '1', '[\"user:add1\"]');
INSERT INTO `sec_menu` VALUES ('3', '可拖拽排序', 'dragable-table', 'dragableTable', 'tables/dragable-table', 'arrow-move', '#', '1', '0', '1', '1', '[\"user:add1\"]');
INSERT INTO `sec_menu` VALUES ('4', '可编辑表格', 'editable-table', 'editableTable', 'tables/editable-table', 'edit', '#', '2', '0', '1', '1', '[\"user:add1\"]');
INSERT INTO `sec_menu` VALUES ('5', '可搜索表格', 'searchable-table', 'searchableTable', 'tables/searchable-table', 'search', '#', '3', '0', '1', '1', '[\"user:add1\"]');
INSERT INTO `sec_menu` VALUES ('6', '表格导出数据', 'exportable-table', 'exportableTable', 'tables/exportable-table', 'code-download', '#', '4', '0', '1', '1', '[\"user:add1\"]');
INSERT INTO `sec_menu` VALUES ('7', '表格转图片', 'table-to-image', 'table2image', 'tables/table-to-image', 'images', '#', '5', '0', '1', '1', '[\"user:add1\"]');
INSERT INTO `sec_menu` VALUES ('8', '动态路由', 'mutative-router', 'mutative-router', 'advanced-router/mutative-router', 'link', '#', '1', '0', '2', '1', '[\"user:add1\"]');
INSERT INTO `sec_menu` VALUES ('9', '带参页面', 'argument-page', 'argument-page', 'advanced-router/argument-page', 'android-send', '#', '2', '0', '2', '1', '[\"user:add1\",\"user:list1\"]');
INSERT INTO `sec_menu` VALUES ('20', '用户管理', 'UserManage', '/userManage', 'Main', 'ios-people', '#', '20', '0', '0', null, null);
INSERT INTO `sec_menu` VALUES ('2001', '用户列表', 'userList', 'userList', 'userList', 'ios-people', '#', '1', '0', '20', null, null);
INSERT INTO `sec_menu` VALUES ('2002', '角色列表', 'roleList', 'roleList', 'roleList', 'ios-people', '#', '2', '0', '20', null, null);
INSERT INTO `sec_menu` VALUES ('2003', '菜单列表', 'menuList', 'menuList', 'menuList', 'ios-people', '#', '3', '0', '20', null, null);
INSERT INTO `sec_menu` VALUES ('2004', '角色授权（跳转页）', 'roleAcl', 'roleAcl', 'roleAcl', 'ios-people', '#', '4', '1', '20', null, null);

-- ----------------------------
-- Table structure for sec_relation_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sec_relation_role_menu`;
CREATE TABLE `sec_relation_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `acl` int(4) DEFAULT '15' COMMENT '权限值  二进制 1111=15',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='关系映射-角色-菜单';

-- ----------------------------
-- Records of sec_relation_role_menu
-- ----------------------------
INSERT INTO `sec_relation_role_menu` VALUES ('1', '1', '1', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('2', '1', '2', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('3', '1', '3', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('4', '1', '4', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('5', '1', '5', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('6', '1', '6', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('7', '1', '7', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('8', '1', '8', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('9', '1', '9', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('10', '1', '20', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('11', '1', '111', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('12', '1', '112', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('13', '1', '113', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('14', '1', '114', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('15', '1', '2001', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('16', '1', '2002', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('17', '1', '2003', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('18', '1', '2004', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('19', '2', '1', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('20', '2', '101', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('21', '2', '102', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('22', '3', '1', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('23', '3', '3', '0');
INSERT INTO `sec_relation_role_menu` VALUES ('24', '3', '4', '0');
INSERT INTO `sec_relation_role_menu` VALUES ('25', '3', '5', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('26', '3', '6', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('27', '3', '7', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('28', '3', '2001', '1');
INSERT INTO `sec_relation_role_menu` VALUES ('29', '3', '20', '1');

-- ----------------------------
-- Table structure for sec_relation_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_relation_user_role`;
CREATE TABLE `sec_relation_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色-关系映射';

-- ----------------------------
-- Records of sec_relation_user_role
-- ----------------------------
INSERT INTO `sec_relation_user_role` VALUES ('1', '1');
INSERT INTO `sec_relation_user_role` VALUES ('2', '2');
INSERT INTO `sec_relation_user_role` VALUES ('3', '3');
INSERT INTO `sec_relation_user_role` VALUES ('4', '2');

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '角色英文名',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sec_role
-- ----------------------------
INSERT INTO `sec_role` VALUES ('1', '管理员', 'ROLE_ADMIN', '管理员');
INSERT INTO `sec_role` VALUES ('2', '开发经理', 'ROLE_DEVM', '开发经理');
INSERT INTO `sec_role` VALUES ('3', 'test3', 'ROLE_test3', 'test3');
INSERT INTO `sec_role` VALUES ('4', 'test4', 'ROLE_test4', 'test4');
INSERT INTO `sec_role` VALUES ('5', 'test5', 'test5', 'test5');
INSERT INTO `sec_role` VALUES ('6', '商务', 'ROLE_BD', '商务');
INSERT INTO `sec_role` VALUES ('7', 'test7', 'test7', 'test7');
INSERT INTO `sec_role` VALUES ('8', 'test8', 'test8', 'test8');
INSERT INTO `sec_role` VALUES ('9', 'test9', 'test9', 'test9');

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `roleIds` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `menuIds` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '授权菜单信息（单独授权）',
  `expireTime` datetime DEFAULT NULL COMMENT '有效时间',
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sec_user
-- ----------------------------
INSERT INTO `sec_user` VALUES ('1', 'admin', '$2a$10$4xsCi5TV1reUHKDjT1vTCe7PJM1/x8tlqvU7l7XSmX5WuMdK94ByC', '管理员', '', '[1,6]', '1', '[3,4,5,6,8,9]', '2020-03-04 16:07:55', '2018-06-16 14:43:54', '2018-07-05 12:13:39');
INSERT INTO `sec_user` VALUES ('2', 'user2', '$2a$10$4xsCi5TV1reUHKDjT1vTCe7PJM1/x8tlqvU7l7XSmX5WuMdK94ByC', '用户2', null, '[3,5]', '1', '[1,2,3,4,5,6,7,8,9,101,102,111,112,113,114]', '2020-06-01 00:00:00', '2018-06-25 12:09:45', '2018-07-02 16:20:03');
INSERT INTO `sec_user` VALUES ('3', 'user3', '$2a$10$4xsCi5TV1reUHKDjT1vTCe7PJM1/x8tlqvU7l7XSmX5WuMdK94ByC', '用户3', null, '[6]', '1', '[1]', '2018-10-26 16:07:55', '2018-06-25 12:09:47', '2018-07-02 15:24:59');
INSERT INTO `sec_user` VALUES ('4', 'user4', '$2a$10$4xsCi5TV1reUHKDjT1vTCe7PJM1/x8tlqvU7l7XSmX5WuMdK94ByC', '用户4', null, '[2]', '1', null, '2020-03-04 16:07:55', '2018-06-25 12:09:50', '2018-06-25 12:10:01');
INSERT INTO `sec_user` VALUES ('10', 'user6', '$2a$10$4xsCi5TV1reUHKDjT1vTCe7PJM1/x8tlqvU7l7XSmX5WuMdK94ByC', '66666', null, '[2]', '1', '[9,8]', '2020-03-04 16:07:55', '2018-06-30 11:41:42', '2018-06-30 11:41:42');
INSERT INTO `sec_user` VALUES ('11', 'user6', '$2a$10$4xsCi5TV1reUHKDjT1vTCe7PJM1/x8tlqvU7l7XSmX5WuMdK94ByC', '22222', null, '[2]', '1', '[9,8]', '2020-03-04 16:07:55', '2018-06-30 11:45:09', '2018-06-30 11:45:09');
INSERT INTO `sec_user` VALUES ('13', 'user6', '$2a$10$4xsCi5TV1reUHKDjT1vTCe7PJM1/x8tlqvU7l7XSmX5WuMdK94ByC', '22222', null, '[2]', '1', '[9,8]', '2020-03-04 16:07:55', '2018-06-30 11:49:39', '2018-06-30 11:49:39');
