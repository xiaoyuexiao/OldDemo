/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3307
Source Database       : outfood

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-29 12:23:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `evalutelist`
-- ----------------------------
DROP TABLE IF EXISTS `evalutelist`;
CREATE TABLE `evalutelist` (
  `evaluatetime` char(100) DEFAULT '',
  `evalutecontent` char(100) DEFAULT '',
  `orderid` char(100) DEFAULT '',
  `shopid` char(100) DEFAULT '',
  `userid` char(100) DEFAULT '',
  `evaluatetype` char(100) DEFAULT '',
  `evaluater` char(100) DEFAULT '',
  `evaluatenum` char(100) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evalutelist
-- ----------------------------

-- ----------------------------
-- Table structure for `eva_star`
-- ----------------------------
DROP TABLE IF EXISTS `eva_star`;
CREATE TABLE `eva_star` (
  `Flavor` char(255) DEFAULT '',
  `Packing` char(255) DEFAULT '',
  `Distribution` char(255) DEFAULT NULL,
  `Orderid` char(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`Orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eva_star
-- ----------------------------

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodid` char(100) NOT NULL DEFAULT '',
  `goodname` char(100) DEFAULT '',
  `goodtype` char(100) DEFAULT '',
  `shopphone` char(100) DEFAULT '',
  `goodimage` char(100) DEFAULT '',
  `goodprice` char(100) DEFAULT '',
  PRIMARY KEY (`goodid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('good001', '煎蛋', '2', '13440146584', '1.jpg', '15');
INSERT INTO `goods` VALUES ('good002', '可乐', '1', '13440146584', '2.jpg', '20');

-- ----------------------------
-- Table structure for `goodtype`
-- ----------------------------
DROP TABLE IF EXISTS `goodtype`;
CREATE TABLE `goodtype` (
  `typeid` char(100) NOT NULL DEFAULT '',
  `typename` char(100) DEFAULT '',
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodtype
-- ----------------------------

-- ----------------------------
-- Table structure for `good_order_middle`
-- ----------------------------
DROP TABLE IF EXISTS `good_order_middle`;
CREATE TABLE `good_order_middle` (
  `goodnum` int(20) NOT NULL,
  `orderId` char(100) NOT NULL DEFAULT '',
  `goodId` char(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`goodId`,`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_order_middle
-- ----------------------------
INSERT INTO `good_order_middle` VALUES ('1', 'orderd5a96c629a', 'good001');
INSERT INTO `good_order_middle` VALUES ('1', 'orderd5a96c629a', 'good002');

-- ----------------------------
-- Table structure for `images`
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `imageurl` char(100) NOT NULL DEFAULT '',
  `imagetoken` char(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`imageurl`,`imagetoken`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of images
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `orderid` char(100) NOT NULL DEFAULT '',
  `carid` char(100) DEFAULT '',
  `ordertype` char(100) DEFAULT '',
  `orderstarttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderendtime` char(100) DEFAULT '',
  `ordermoney` char(100) DEFAULT '',
  `orderstatus` char(100) DEFAULT '',
  `detailaddress` char(100) DEFAULT '',
  `userphone` char(100) DEFAULT '',
  `shopphone` char(100) DEFAULT '',
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('orderd5a96c629a', '', '', '2018-06-28 15:38:01', '', '35', '', 'df', '13778246098', '13440146584');

-- ----------------------------
-- Table structure for `school`
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `schoolId` char(100) NOT NULL DEFAULT '',
  `schoolName` char(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`schoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('qhdx', '清华大学');
INSERT INTO `school` VALUES ('xnkjdx', '西南科技大学');

-- ----------------------------
-- Table structure for `sellermoneylist`
-- ----------------------------
DROP TABLE IF EXISTS `sellermoneylist`;
CREATE TABLE `sellermoneylist` (
  `payid` char(100) NOT NULL DEFAULT '',
  `payuserphone` char(100) DEFAULT '',
  `paymoney` char(100) DEFAULT '',
  `paytype` char(100) DEFAULT '',
  `shopid` char(100) DEFAULT '',
  `paytime` char(100) DEFAULT '',
  `payflag` char(100) DEFAULT '',
  PRIMARY KEY (`payid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellermoneylist
-- ----------------------------

-- ----------------------------
-- Table structure for `shopadminstor`
-- ----------------------------
DROP TABLE IF EXISTS `shopadminstor`;
CREATE TABLE `shopadminstor` (
  `shoppwd` char(100) DEFAULT '',
  `shopname` char(100) DEFAULT '',
  `schoolid` char(100) DEFAULT '',
  `shopphone` char(100) NOT NULL DEFAULT '',
  `shophousepicurl` char(100) DEFAULT '',
  `shopstatus` char(100) DEFAULT '',
  `shopaddress` char(100) DEFAULT '',
  `shoptype` char(100) DEFAULT '',
  `shopfee` char(100) DEFAULT '',
  `shopstartprice` char(100) DEFAULT '',
  PRIMARY KEY (`shopphone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopadminstor
-- ----------------------------
INSERT INTO `shopadminstor` VALUES ('123456', '美蛙鱼头', 'qhdx', '13440146582', '1.jpg', '1', '', '1', '1', '11');
INSERT INTO `shopadminstor` VALUES ('asdsads', '麦克多', 'xnkjdx', '13440146584', '2.jpg', '1', '西南科技大学', '1', '1', '15');
INSERT INTO `shopadminstor` VALUES ('123123', '汉使烤肉', 'qhdx', '13678657435', '3.jpg', '1', '', '', '', '');
INSERT INTO `shopadminstor` VALUES ('123456', '鸭血粉丝汤', 'xnkjdx', '13778246098', '4.jpg', '1', 'xnkjdx', '2', '1', '11');
INSERT INTO `shopadminstor` VALUES ('888888', '颜铁板烧烤', 'xnkjdx', '13788888555', '5.jpg', '1', 'xnkjdx', '2', '1', '12');
INSERT INTO `shopadminstor` VALUES ('123456789', '粥公粥婆', 'qhdx', '15800218161', '6.jpg', '1', 'qhdx', '1', '1', '20');
INSERT INTO `shopadminstor` VALUES ('456546456', '牛x小吃', 'xnkjdx', '18754555555', '5.jpg', '0', '西南科技大学', '1', '1', '13');

-- ----------------------------
-- Table structure for `shoptype`
-- ----------------------------
DROP TABLE IF EXISTS `shoptype`;
CREATE TABLE `shoptype` (
  `shopTypeName` char(100) NOT NULL DEFAULT '',
  `shopTypeId` char(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`shopTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoptype
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userstatus` char(100) DEFAULT '0',
  `schoolid` char(100) DEFAULT '',
  `username` char(100) DEFAULT '',
  `phone` char(11) NOT NULL DEFAULT '',
  `userid` char(100) DEFAULT '',
  `userHeadPicUrl` char(100) DEFAULT '',
  `password` char(100) DEFAULT '',
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', 'qhdx', '', '13054879512', '', '1.jpg', '123456');
INSERT INTO `user` VALUES ('0', 'xnkjdx', '', '13123125485', '', '2.jpg', '123123');
INSERT INTO `user` VALUES ('0', 'xnkjdx', '', '13778246098', '', '3.jpg', '666666');
INSERT INTO `user` VALUES ('0', 'qhdx', '', '15196235249', '', '4.jpg', '123456');
