/*
Navicat MySQL Data Transfer

Source Server         : sample-ku
Source Server Version : 50643
Source Host           : 127.0.0.1:3306
Source Database       : pay

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2021-05-23 16:46:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for business_record
-- ----------------------------
DROP TABLE IF EXISTS `business_record`;
CREATE TABLE `business_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expense_paynum` varchar(50) NOT NULL COMMENT '支出支付账号',
  `accept_paynum` varchar(50) NOT NULL COMMENT '接受支付账号',
  `business_money` decimal(18,2) NOT NULL COMMENT '交易金额',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '姓名',
  `idcardnum` varchar(18) NOT NULL COMMENT '身份证号',
  `paynum` varchar(50) NOT NULL COMMENT '支付账号',
  `money` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '钱',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idcardnum` (`idcardnum`) USING BTREE,
  UNIQUE KEY `paynum` (`paynum`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for transfer_record
-- ----------------------------
DROP TABLE IF EXISTS `transfer_record`;
CREATE TABLE `transfer_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transfer_num` varchar(50) NOT NULL COMMENT '转账账号',
  `accept_num` varchar(50) NOT NULL COMMENT '接受账号',
  `transfer_money` decimal(18,2) NOT NULL COMMENT '转账金额',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
