/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-07-25 17:41:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for operate_logger
-- ----------------------------
DROP TABLE IF EXISTS `operate_logger`;
CREATE TABLE `operate_logger` (
  `num_id` varchar(80) DEFAULT NULL COMMENT '记录标识',
  `reg_id` varchar(80) DEFAULT NULL COMMENT '应用系统/资源库标识',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户标识',
  `organization` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `organization_id` varchar(80) DEFAULT NULL COMMENT '单位机构代码',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `operate_time` varchar(80) DEFAULT NULL COMMENT '操作时间',
  `terminal_id` varchar(80) DEFAULT NULL COMMENT '终端标识',
  `operate_type` int(11) DEFAULT NULL COMMENT '操作类型（0：登录；1：查询；2：新增；3：修改；4：删除；5：导出）',
  `operate_result` varchar(255) DEFAULT NULL COMMENT '操作结果（1：成功；0：失败）',
  `error_code` varchar(255) DEFAULT NULL COMMENT '失败原因代码',
  `operate_name` varchar(255) DEFAULT NULL COMMENT '功能模块名称',
  `operate_condition` varchar(255) DEFAULT NULL COMMENT '操作条件',
  `operate_number` int(11) DEFAULT NULL COMMENT '操作返回条目数',
  `operate_table` varchar(255) DEFAULT NULL COMMENT '被操作数据表',
  `operate_key` varchar(255) DEFAULT NULL COMMENT '被操作数据的主键标识',
  `msg` varchar(255) DEFAULT NULL COMMENT '异常操作信息',
  `operatenape` varchar(255) DEFAULT NULL COMMENT '操作项',
  `path` varchar(255) DEFAULT NULL COMMENT '请求地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
