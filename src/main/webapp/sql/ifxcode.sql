CREATE DATABASE ifxcode DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use ifxcode;

-- ----------------------------
-- Table structure for about
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `about` varchar(15000) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for backup
-- ----------------------------
DROP TABLE IF EXISTS `backup`;
CREATE TABLE `backup` (
  `backid` int(10) NOT NULL AUTO_INCREMENT,
  `backname` varchar(100) NOT NULL,
  `backdir` varchar(255) NOT NULL,
  `createdate` datetime NOT NULL,
  PRIMARY KEY (`backid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blogclassify
-- ----------------------------
DROP TABLE IF EXISTS `blogclassify`;
CREATE TABLE `blogclassify` (
  `cid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `blogcount` int(10) NOT NULL,
  `isdelete` varchar(10) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blogreply
-- ----------------------------
DROP TABLE IF EXISTS `blogreply`;
CREATE TABLE `blogreply` (
  `bg_rid` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(5000) NOT NULL,
  `isdelete` varchar(10) NOT NULL,
  `createtime` datetime NOT NULL,
  `user_id` int(10) NOT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `blogtopic_id` int(10) NOT NULL,
  PRIMARY KEY (`bg_rid`),
  KEY `bg_reply_user_id` (`user_id`),
  KEY `bd_parent_id` (`parent_id`),
  KEY `bg_reply_blogtopic_id` (`blogtopic_id`),
  CONSTRAINT `bd_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `blogreply` (`bg_rid`),
  CONSTRAINT `bg_reply_blogtopic_id` FOREIGN KEY (`blogtopic_id`) REFERENCES `blogtopic` (`bid`),
  CONSTRAINT `bg_reply_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blogsign
-- ----------------------------
DROP TABLE IF EXISTS `blogsign`;
CREATE TABLE `blogsign` (
  `bsid` int(10) NOT NULL AUTO_INCREMENT,
  `bsname` varchar(20) NOT NULL,
  `createtime` datetime NOT NULL,
  `blog_id` int(10) NOT NULL,
  PRIMARY KEY (`bsid`),
  KEY `blog_sign_id` (`blog_id`),
  CONSTRAINT `blog_sign_id` FOREIGN KEY (`blog_id`) REFERENCES `blogtopic` (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blogtopic
-- ----------------------------
DROP TABLE IF EXISTS `blogtopic`;
CREATE TABLE `blogtopic` (
  `bid` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `viewcount` int(10) NOT NULL,
  `replycount` int(10) NOT NULL,
  `praisecount` int(10) NOT NULL,
  `notpraisecount` int(10) NOT NULL,
  `ishome` varchar(10) NOT NULL,
  `isdelete` varchar(10) NOT NULL,
  `createtime` datetime NOT NULL,
  `classify_id` int(10) NOT NULL,
  `createOrRepost` varchar(10) NOT NULL,
  PRIMARY KEY (`bid`),
  KEY `blog_classify_id` (`classify_id`),
  CONSTRAINT `blog_classify_id` FOREIGN KEY (`classify_id`) REFERENCES `blogclassify` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bookreply
-- ----------------------------
DROP TABLE IF EXISTS `bookreply`;
CREATE TABLE `bookreply` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  `replycount` int(10) NOT NULL,
  `isdelete` varchar(10) NOT NULL,
  `createtime` datetime NOT NULL,
  `user_id` int(10) NOT NULL,
  `parent_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_parent_id` (`parent_id`),
  KEY `book_user_id` (`user_id`),
  CONSTRAINT `book_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `bookreply` (`id`),
  CONSTRAINT `book_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dd
-- ----------------------------
DROP TABLE IF EXISTS `dd`;
CREATE TABLE `dd` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `ddesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ddl
-- ----------------------------
DROP TABLE IF EXISTS `ddl`;
CREATE TABLE `ddl` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `value` varchar(50) NOT NULL,
  `sign` int(1) NOT NULL,
  `dd_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dd_id` (`dd_id`),
  CONSTRAINT `dd_id` FOREIGN KEY (`dd_id`) REFERENCES `dd` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for doinglog
-- ----------------------------
DROP TABLE IF EXISTS `doinglog`;
CREATE TABLE `doinglog` (
  `dongid` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`dongid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for friendlink
-- ----------------------------
DROP TABLE IF EXISTS `friendlink`;
CREATE TABLE `friendlink` (
  `fid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `url` varchar(500) NOT NULL,
  `isdelete` varchar(10) NOT NULL,
  `createtime` datetime NOT NULL,
  `endtime` date NOT NULL,
  `customer` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `c_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for loginlog
-- ----------------------------
DROP TABLE IF EXISTS `loginlog`;
CREATE TABLE `loginlog` (
  `loginid` int(10) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) NOT NULL,
  `loginip` varchar(50) NOT NULL,
  `loginfrom` varchar(100) NOT NULL,
  `logintime` datetime NOT NULL,
  PRIMARY KEY (`loginid`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageid` int(10) NOT NULL AUTO_INCREMENT,
  `send_user_name` int(10) NOT NULL,
  `receive_user_name` int(10) NOT NULL,
  `message` varchar(500) NOT NULL,
  `status` int(1) NOT NULL,
  `sendtime` datetime NOT NULL,
  `viewtime` datetime DEFAULT NULL,
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mood
-- ----------------------------
DROP TABLE IF EXISTS `mood`;
CREATE TABLE `mood` (
  `mid` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for navigation
-- ----------------------------
DROP TABLE IF EXISTS `navigation`;
CREATE TABLE `navigation` (
  `nid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `url` varchar(255) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resid` int(10) NOT NULL AUTO_INCREMENT,
  `resname` varchar(20) CHARACTER SET utf8 NOT NULL,
  `resurl` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `resattr` varchar(100) CHARACTER SET utf8 NOT NULL,
  `ressign` varchar(100) CHARACTER SET utf8 NOT NULL,
  `resico` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `resdesc` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `resstatus` varchar(100) CHARACTER SET utf8 NOT NULL,
  `pid` int(10) DEFAULT NULL,
  `rescreatetime` datetime NOT NULL,
  PRIMARY KEY (`resid`),
  KEY `pid` (`pid`),
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `resource` (`resid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `roledesc` varchar(100) DEFAULT NULL,
  `role_status` varchar(10) NOT NULL,
  `role_createtime` datetime NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `res_role_id` int(10) NOT NULL AUTO_INCREMENT,
  `resource_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  PRIMARY KEY (`res_role_id`),
  KEY `resource_id` (`resource_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `resource_id` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resid`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) NOT NULL,
  `username` varchar(40) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `userimage` varchar(255) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `blogcount` int(10) NOT NULL,
  `replycount` int(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `isdelete` varchar(10) NOT NULL,
  `lastloginTime` datetime DEFAULT NULL,
  `lastloginip` varchar(20) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `createip` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `user_role_id` (`role_id`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
