/*
Navicat MySQL Data Transfer

Source Server         : sample-ku
Source Server Version : 50643
Source Host           : 127.0.0.1:3306
Source Database       : bank

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2021-05-23 11:18:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bankcard
-- ----------------------------
DROP TABLE IF EXISTS `bankcard`;
CREATE TABLE `bankcard` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bankcard_number` varchar(50) NOT NULL COMMENT '银行卡号',
  `uid` bigint(20) NOT NULL COMMENT '用户Id',
  `money` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '钱',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bankcardnumber` (`bankcard_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for transfer_record
-- ----------------------------
DROP TABLE IF EXISTS `transfer_record`;
CREATE TABLE `transfer_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transfer_card_id` bigint(20) NOT NULL COMMENT '转账卡id',
  `accept_card_id` bigint(20) NOT NULL COMMENT '接受卡id',
  `transfer_money` decimal(18,2) NOT NULL COMMENT '转账金额',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '姓名',
  `idcardnum` varchar(18) NOT NULL COMMENT '身份证号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idcardnum` (`idcardnum`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
