/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : mystore

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2021-11-06 01:13:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `collect`
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `user_id` int(100) NOT NULL,
  `product_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for `consignor_address`
-- ----------------------------
DROP TABLE IF EXISTS `consignor_address`;
CREATE TABLE `consignor_address` (
  `consignor_id` int(100) NOT NULL AUTO_INCREMENT,
  `consignor_address` varchar(100) DEFAULT NULL,
  `consignor_number` varchar(100) DEFAULT NULL,
  `consignor_staus` int(10) DEFAULT NULL,
  `seller_id` int(100) NOT NULL,
  PRIMARY KEY (`consignor_id`,`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consignor_address
-- ----------------------------

-- ----------------------------
-- Table structure for `express`
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `express_id` int(100) NOT NULL AUTO_INCREMENT,
  `express_name` varchar(100) DEFAULT NULL,
  `express_state` int(100) DEFAULT NULL,
  `express_time` datetime DEFAULT NULL,
  `express_adress` varchar(100) DEFAULT NULL,
  `express_persion` varchar(100) DEFAULT NULL,
  `express_line` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`express_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of express
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(100) NOT NULL AUTO_INCREMENT,
  `order_state` int(100) DEFAULT NULL,
  `order_start` datetime DEFAULT NULL,
  `order_end` datetime DEFAULT NULL,
  `user_id` int(100) DEFAULT NULL,
  `product_id` int(100) DEFAULT NULL,
  `product_number` int(100) DEFAULT NULL,
  `express_id` int(100) DEFAULT NULL,
  `evaluate` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) DEFAULT NULL,
  `product_fenlei` varchar(100) DEFAULT NULL,
  `product_biaoqian` varchar(100) DEFAULT NULL,
  `product_detail` varchar(1000) DEFAULT NULL,
  `product_tupian` mediumint(9) DEFAULT NULL,
  `product_number` int(100) DEFAULT NULL,
  `product_price` int(100) DEFAULT NULL,
  `product_address` varchar(100) DEFAULT NULL,
  `product_kuaidi` varchar(100) DEFAULT NULL,
  `seller_id` int(100) NOT NULL,
  PRIMARY KEY (`product_id`,`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for `seller`
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `seller_id` int(100) NOT NULL AUTO_INCREMENT,
  `seller_name` varchar(100) DEFAULT NULL,
  `seller_password` varchar(100) DEFAULT NULL,
  `seller_touxiang` mediumint(9) DEFAULT NULL,
  `seller_age` int(100) DEFAULT NULL,
  `seller_email` varchar(100) DEFAULT NULL,
  `seller_sex` int(10) DEFAULT NULL,
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller
-- ----------------------------

-- ----------------------------
-- Table structure for `shopping_cat`
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cat`;
CREATE TABLE `shopping_cat` (
  `user_id` int(100) DEFAULT NULL,
  `product_id` int(100) DEFAULT NULL,
  `seller_id` int(100) DEFAULT NULL,
  `shopping_id` int(100) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`shopping_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopping_cat
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(100) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `user_password` varchar(100) DEFAULT NULL,
  `user_age` int(100) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_sex` int(10) DEFAULT NULL,
  `user_touxiang` varchar(100) DEFAULT NULL,
  `user_number` int(100) DEFAULT NULL,
  `user_address_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', 'huangweilin', '1', '1', '1', '1', '5145050a-ff4a-444b-9b31-86fff6c46b37.jpg', '1', '1');
INSERT INTO `user` VALUES ('0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002', '2', '2', '2', '2', '2', '5145050a-ff4a-444b-9b31-86fff6c46b37.jpg', '2', '2');
INSERT INTO `user` VALUES ('0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003', '3', '3', '3', '3', '3', 'd3e3401e-9c73-49fb-8cb1-24479fd15c89.jpg', '3', '3');
INSERT INTO `user` VALUES ('0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000004', '4', '4', '4', '4', '4', '87a51ea3-c5fc-4367-9c87-564ab43d93d4.jpg', '4', '4');
INSERT INTO `user` VALUES ('0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000015', 'admin_hwl', '1', '1', '1', '1', '93cb1ab1-67ec-46f6-8e4a-4bc9661d14ac.jpg', '1', '1');

-- ----------------------------
-- Table structure for `user_address`
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `address_id` int(100) NOT NULL AUTO_INCREMENT,
  `user_id` int(100) DEFAULT NULL,
  `consignee` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `address_staus` int(10) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_address
-- ----------------------------
