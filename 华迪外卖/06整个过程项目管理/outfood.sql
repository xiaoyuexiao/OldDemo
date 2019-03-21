/*
Navicat MySQL Data Transfer

Source Server         : sada
Source Server Version : 50720
Source Host           : localhost:3307
Source Database       : outfood

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-20 14:16:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `evalutelist`
-- ----------------------------
DROP TABLE IF EXISTS `evalutelist`;
CREATE TABLE `evalutelist` (
  `evaluatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `evalutecontent` char(100) DEFAULT '',
  `orderid` char(100) NOT NULL DEFAULT '',
  `shopid` char(100) DEFAULT '',
  `userid` char(100) DEFAULT '',
  `evaluatetype` char(100) DEFAULT '',
  `evaluater` char(100) DEFAULT '',
  `evaluatenum` char(100) DEFAULT '',
  `evaluatestatus` char(100) DEFAULT '',
  PRIMARY KEY (`evaluatetime`,`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evalutelist
-- ----------------------------
INSERT INTO `evalutelist` VALUES ('2018-07-19 20:32:14', '666666', 'orderad7c654c4b', '13778246098', '18281524890', '', '0', '1', '');

-- ----------------------------
-- Table structure for `eva_star`
-- ----------------------------
DROP TABLE IF EXISTS `eva_star`;
CREATE TABLE `eva_star` (
  `Flavor` char(255) DEFAULT '',
  `Packing` char(255) DEFAULT '',
  `Distribution` char(255) DEFAULT NULL,
  `Orderid` char(255) NOT NULL DEFAULT '',
  `EvaluateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eva_star
-- ----------------------------
INSERT INTO `eva_star` VALUES ('1.5', '5', '\n							0\n						', 'order9160786d30', '2018-07-19 19:17:55');
INSERT INTO `eva_star` VALUES ('1.5', '5', '\n							0\n						', 'order9160786d30', '2018-07-19 19:18:03');
INSERT INTO `eva_star` VALUES ('3.5', '3', '2.5', 'orderad7c654c4b', '2018-07-19 20:32:14');

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
INSERT INTO `goods` VALUES ('good001', '煎蛋', '2', '13440146584', '1.jpg', '1.5');
INSERT INTO `goods` VALUES ('good002', '可乐', '1', '13440146584', '2.jpg', '5');
INSERT INTO `goods` VALUES ('good003', '炸鸡腿', '2', '13440146584', '3.jpg', '16');
INSERT INTO `goods` VALUES ('good004', '香辣鸡腿堡', '1', '13440146584', '4.jpg', '8');
INSERT INTO `goods` VALUES ('good005', '魔法汉堡', '1', '13440146584', '5.jpg', '7');
INSERT INTO `goods` VALUES ('good006', '水煮草鱼', '2', '13440146582', 'fish1.jpg', '50');
INSERT INTO `goods` VALUES ('good007', '绝品蛙鱼', '2', '13440146582', 'fish2.jpg', '55');
INSERT INTO `goods` VALUES ('good008', '水煮鱼片', '1', '13440146582', 'fish3.jpg', '45');
INSERT INTO `goods` VALUES ('good011', '简约烤肉', '2', '13678657435', 'meat3.jpg', '20');
INSERT INTO `goods` VALUES ('good012', '鸭汤面', '1', '13778246098', 'mian1.jpg', '9');
INSERT INTO `goods` VALUES ('good013', '鸭血粉丝', '2', '13778246098', 'mian2.jpg', '12');
INSERT INTO `goods` VALUES ('good014', '蘸饺', '1', '13778246098', 'mian3.jpg', '11');
INSERT INTO `goods` VALUES ('good3b30eb040', '撒大声地', '0', '18281524890', '1531995238320tuitui.jpg', '12000');
INSERT INTO `goods` VALUES ('good7faea8c64', '鸡腿', '0', '18281524892', '15320033088592.jpg', '20');

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
INSERT INTO `good_order_middle` VALUES ('1', 'order9160786d30', 'good012');
INSERT INTO `good_order_middle` VALUES ('1', 'orderad7c654c4b', 'good012');
INSERT INTO `good_order_middle` VALUES ('1', 'order9160786d30', 'good013');
INSERT INTO `good_order_middle` VALUES ('1', 'orderad7c654c4b', 'good013');
INSERT INTO `good_order_middle` VALUES ('1', 'orderad7c654c4b', 'good014');
INSERT INTO `good_order_middle` VALUES ('2', 'order86e5907b89', 'good3b30eb040');
INSERT INTO `good_order_middle` VALUES ('2', 'order359f2dcd28', 'good7faea8c64');

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
  `orderstatus` char(100) DEFAULT '0',
  `detailaddress` char(100) DEFAULT '',
  `userphone` char(100) DEFAULT '',
  `shopphone` char(100) DEFAULT '',
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('order359f2dcd28', '', '', '2018-07-19 20:31:19', '', '40', '3', '东一', '18281524890', '18281524892');
INSERT INTO `order` VALUES ('order86e5907b89', '', '', '2018-07-19 14:17:27', '', '24000', '3', '还会更丰富', '18281524890', '18281524890');
INSERT INTO `order` VALUES ('order9160786d30', '', '', '2018-07-19 15:16:17', '', '21', '2', '华迪', '13778246098', '13778246098');
INSERT INTO `order` VALUES ('orderad7c654c4b', '', '', '2018-07-19 16:07:22', '', '32', '2', '东一', '18281524890', '13778246098');

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
INSERT INTO `school` VALUES ('dzkjdx', '电子科技大学');
INSERT INTO `school` VALUES ('qhdx', '清华大学');
INSERT INTO `school` VALUES ('xncjdx', '西南财经大学');
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
  `paytime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `payflag` char(100) DEFAULT '',
  PRIMARY KEY (`payid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellermoneylist
-- ----------------------------
INSERT INTO `sellermoneylist` VALUES ('pay28b04f000', '18281524890', '32', '', '13778246098', '2018-07-19 17:07:22', '1');
INSERT INTO `sellermoneylist` VALUES ('pay506bb7eb5', '18281524890', '40', '', '18281524892', '2018-07-19 20:31:19', '1');
INSERT INTO `sellermoneylist` VALUES ('payc095dbc59', '18281524890', '24000', '', '18281524890', '2018-07-19 18:17:27', '1');
INSERT INTO `sellermoneylist` VALUES ('payc59173ea1', '13778246098', '21', '', '13778246098', '2018-07-19 19:16:17', '1');

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
  `shopstatus` char(100) DEFAULT '0',
  `shopaddress` char(100) DEFAULT '',
  `shoptype` char(100) DEFAULT '',
  `shopfee` char(100) DEFAULT '',
  `shopstartprice` char(100) DEFAULT '',
  PRIMARY KEY (`shopphone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopadminstor
-- ----------------------------
INSERT INTO `shopadminstor` VALUES ('123456', '美蛙鱼头', 'qhdx', '13440146582', '1.jpg', '1', '小铁门外科大美林转角处520号', '1', '1', '11');
INSERT INTO `shopadminstor` VALUES ('123123', '麦克多', 'xnkjdx', '13440146584', '2.jpg', '1', '小铁门外科大美林转角处520号', '1', '1', '15');
INSERT INTO `shopadminstor` VALUES ('999999999', '韩国烤肉', 'qhdx', '13678657435', '1531928887955tuitui.jpg', '0', '西南科技大学大铁门外青羊小区250号', '2', '25', '5');
INSERT INTO `shopadminstor` VALUES ('123456', '鸭血粉丝汤', 'xnkjdx', '13778246098', '4.jpg', '1', '小铁门外科大美林转角处520号', '2', '1', '11');
INSERT INTO `shopadminstor` VALUES ('888888', '颜铁板烧烤', 'xnkjdx', '13788888555', '5.jpg', '1', '小铁门外科大美林转角处520号', '2', '1', '12');
INSERT INTO `shopadminstor` VALUES ('123456789', '粥公粥婆', 'qhdx', '15800218161', '6.jpg', '0', '小铁门外科大美林转角处520号', '1', '1', '20');
INSERT INTO `shopadminstor` VALUES ('123456', '蜜雪冰城', 'xnkjdx', '18212346578', 'ice.jpg', '0', '小铁门外科大美林转角处520号', '3', '1', '2');
INSERT INTO `shopadminstor` VALUES ('123456', '肯德基', 'xnkjdx', '18281524892', '15320032030371.jpg', '1', '西南科技学院是是是', '', '10', '20');
INSERT INTO `shopadminstor` VALUES ('456546456', '牛x小吃', 'xnkjdx', '18754555555', '5.jpg', '0', '小铁门外科大美林转角处520号', '1', '1', '13');

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
INSERT INTO `user` VALUES ('0', 'qhdx', '', '13054879518', '', '', '123456');
INSERT INTO `user` VALUES ('0', 'xnkjdx', '', '13123125485', '', '2.jpg', '123123');
INSERT INTO `user` VALUES ('0', 'xnkjdx', '', '13189456787', '', '', '123456');
INSERT INTO `user` VALUES ('0', 'xnkjdx', '', '13778246098', '', '3.jpg', '666666');
INSERT INTO `user` VALUES ('0', 'qhdx', '', '15196235249', '', '4.jpg', '123456');
INSERT INTO `user` VALUES ('0', 'xnkjdx', '', '18281524890', '', '', '123456');
