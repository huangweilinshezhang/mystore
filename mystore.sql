/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : mystore

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2022-04-08 00:27:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cate_gory`
-- ----------------------------
DROP TABLE IF EXISTS `cate_gory`;
CREATE TABLE `cate_gory` (
  `cate_id` int(100) NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(100) DEFAULT NULL,
  `cate_parent_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cate_gory
-- ----------------------------
INSERT INTO `cate_gory` VALUES ('1', '书', '0');
INSERT INTO `cate_gory` VALUES ('2', 'JavaSE图书', '0');
INSERT INTO `cate_gory` VALUES ('4', 'Java定义图书', '0');
INSERT INTO `cate_gory` VALUES ('5', 'PHP高级', '0');
INSERT INTO `cate_gory` VALUES ('6', 'JavaSE图书下的目录', '2');
INSERT INTO `cate_gory` VALUES ('7', 'springboot框架', '0');
INSERT INTO `cate_gory` VALUES ('8', 'springboot-dubbo', '7');
INSERT INTO `cate_gory` VALUES ('15', 'springboot框架分支1', '7');
INSERT INTO `cate_gory` VALUES ('16', '书2', '1');
INSERT INTO `cate_gory` VALUES ('17', '字3', '1');

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
-- Table structure for `orderuser`
-- ----------------------------
DROP TABLE IF EXISTS `orderuser`;
CREATE TABLE `orderuser` (
  `order_id` int(100) NOT NULL AUTO_INCREMENT,
  `order_state` int(100) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `user_id` int(100) DEFAULT NULL,
  `product_id` int(100) DEFAULT NULL,
  `product_number` int(100) DEFAULT NULL,
  `express_id` int(100) DEFAULT NULL,
  `evaluate` varchar(100) DEFAULT NULL,
  `total` int(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=465 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderuser
-- ----------------------------
INSERT INTO `orderuser` VALUES ('458', '2', '2022-04-06 13:05:58', '1', '14', '1', '1', '添加评价', '158');
INSERT INTO `orderuser` VALUES ('459', '2', '2022-04-06 13:06:02', '1', '14', '1', '1', '添加评价', '158');
INSERT INTO `orderuser` VALUES ('460', '1', '2022-04-07 16:31:06', '1', '2', '2', '1', '添加评价', '16');
INSERT INTO `orderuser` VALUES ('461', '1', '2022-04-07 16:32:00', '46', '2', '1', '1', '添加评价', '8');
INSERT INTO `orderuser` VALUES ('462', '1', '2022-04-07 16:32:00', '46', '15', '2', '1', '添加评价', '316');
INSERT INTO `orderuser` VALUES ('463', '2', '2022-04-07 18:43:29', '47', '2', '1', '1', '添加评价', '8');
INSERT INTO `orderuser` VALUES ('464', '2', '2022-04-07 18:43:29', '47', '14', '1', '1', '添加评价', '158');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) DEFAULT NULL,
  `product_cid` int(40) DEFAULT NULL,
  `product_fid` int(40) DEFAULT NULL,
  `product_biaoqian` varchar(100) DEFAULT NULL,
  `product_detail` varchar(1000) DEFAULT NULL,
  `product_tupian` varchar(9) DEFAULT NULL,
  `product_number` int(100) DEFAULT NULL,
  `product_price` int(100) DEFAULT NULL,
  `product_address` varchar(100) DEFAULT NULL,
  `product_kuaidi` varchar(100) DEFAULT NULL,
  `seller_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('2', '【一本书】', '16', '1', '4', '5', '2', '7', '8', '9', '10', '1');
INSERT INTO `product` VALUES ('8', 'java图书', '6', '2', '3', '3', '8', '3', '3', '3', '3', '3');
INSERT INTO `product` VALUES ('14', 'Java从入门到精通', '0', '4', '1', '1', '14', '1', '158', '1', '1', '1');
INSERT INTO `product` VALUES ('15', 'springboot基础框架+dubbo', '8', '7', 'springboot基础框架+dubbo', 'springboot基础框架+dubbo', '15', '1', '158', '2', '3', '1');
INSERT INTO `product` VALUES ('18', 'Java从入门到精通', '0', '4', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `product` VALUES ('19', 'Java从入门到精通', '6', '4', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `product` VALUES ('20', 'springboot基础框架+dubbo进阶版', '8', '7', '1', '2', '3', '4', '5', '6', '7', '1');
INSERT INTO `product` VALUES ('29', 'Java从入门到精通', '0', '4', '1', '', null, null, null, '', '', '1');
INSERT INTO `product` VALUES ('30', 'springboot基础框架分支1', '15', '7', '1', '4', '44', '4', '4', '4', '4', '1');
INSERT INTO `product` VALUES ('31', '2022322', '16', '1', '2022322', '2022322', null, '2022322', '2022322', '2022322', '2022322', '1');
INSERT INTO `product` VALUES ('32', '202222', '6', '2', '202222', '202222', null, '202222', '202222', '202222', '202222', '1');
INSERT INTO `product` VALUES ('104', 'Java从入门到精通', '0', '4', '1', '1', '104', '3', '2', '2', '2', '1');

-- ----------------------------
-- Table structure for `product_img`
-- ----------------------------
DROP TABLE IF EXISTS `product_img`;
CREATE TABLE `product_img` (
  `img_id` int(100) NOT NULL AUTO_INCREMENT,
  `user_id` int(100) NOT NULL,
  `img_number` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` int(100) NOT NULL,
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_img
-- ----------------------------
INSERT INTO `product_img` VALUES ('80', '11', '46df8699-64b3-4a26-91ea-0775fa58b864.jpg', '2');
INSERT INTO `product_img` VALUES ('81', '11', '92cc7e3b-3bed-4e52-9aa2-7a860145ac34.jpg', '2');
INSERT INTO `product_img` VALUES ('82', '11', '0ced4ebf-f477-470a-86dd-059853ca1bf3.jpg', '2');
INSERT INTO `product_img` VALUES ('83', '3', '50d1703f-4d99-4c66-8257-ee4d0ecfe8e3.jpg', '8');
INSERT INTO `product_img` VALUES ('84', '3', '737b1087-8671-4c39-883b-f0d83adf4f56.jpg', '8');
INSERT INTO `product_img` VALUES ('85', '3', '466fdc9e-536b-42fe-90d5-6f458fe42f32.jpg', '8');
INSERT INTO `product_img` VALUES ('86', '1', '66519b73-6739-4cfa-97f9-6c108fcd0bc5.jpg', '14');
INSERT INTO `product_img` VALUES ('87', '1', 'b4ef9caa-2066-4c59-ade9-be7c1fc69d50.jpg', '14');
INSERT INTO `product_img` VALUES ('88', '1', 'c3dd190f-4ce2-42f1-936d-a35cfd6e0afe.jpg', '14');
INSERT INTO `product_img` VALUES ('89', '1', 'd916f7c3-f833-46f8-9e2a-e0813fb0629e.JPG', '15');
INSERT INTO `product_img` VALUES ('90', '1', '8c5e1ac9-f70b-43cc-9071-9f470c967a37.JPG', '15');
INSERT INTO `product_img` VALUES ('91', '1', '5685aff1-c445-4a68-bb4f-d67039cfd064.JPG', '15');
INSERT INTO `product_img` VALUES ('92', '1', '93117c50-9e0e-443a-ad1e-80bb2c210e7d.jpg', '2');
INSERT INTO `product_img` VALUES ('93', '1', '5d6962c2-4d2a-4a6e-aaf0-a239b22d35a6.jpg', '2');
INSERT INTO `product_img` VALUES ('94', '1', 'a1b87e53-f1fb-4975-a44a-3c3730a554cc.jpg', '2');

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
  `product_id` int(100) NOT NULL,
  `seller_id` int(100) DEFAULT NULL,
  `shopping_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_number` int(100) NOT NULL,
  PRIMARY KEY (`shopping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=629 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopping_cat
-- ----------------------------
INSERT INTO `shopping_cat` VALUES ('1', '2', '11', '622', '2');
INSERT INTO `shopping_cat` VALUES ('46', '2', '11', '624', '1');
INSERT INTO `shopping_cat` VALUES ('46', '15', '1', '625', '2');

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
  `user_number` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_address_id` int(100) DEFAULT NULL,
  `user_stat` int(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', 'huangweilin', '1', '1', '1', '1', '90300f3b-76a9-4249-a429-e3a0cfb95556.jpg', '13724136866', '1', '1');
INSERT INTO `user` VALUES ('0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000047', 'admin_hwl', '2', '14', '1', '1', 'd7891f8e-ab58-4880-8665-04d2d46419ab.jpg', '1', '5', '0');

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
  `user_tel` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES ('1', '1', 'ok', '1', '1', '13724136866');
INSERT INTO `user_address` VALUES ('4', '46', 'ok', '北京', '1', '13724136866');
INSERT INTO `user_address` VALUES ('5', '47', 'ok', '5', '1', '1');
