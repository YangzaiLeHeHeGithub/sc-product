/* 用户表DDL */

CREATE TABLE `sc_user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sc_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '姓名 ',
  `mobile_tel_no` varchar(20) DEFAULT NULL COMMENT '手机号',
  `certificate_type` varchar(2) DEFAULT NULL COMMENT '证件类型（1：身份证 , 2：营业执照编号）',
  `certificate_no` varchar(30) DEFAULT NULL COMMENT '证件号',
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  `properties` bigint(20) DEFAULT NULL COMMENT '属性',
  `cert_expire_time` date DEFAULT NULL COMMENT '证件时效时间',
  `address` varchar(512) DEFAULT NULL COMMENT '通讯地址',
  `mailbox` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `beneficiary_info` varchar(256) DEFAULT '' COMMENT '受益人信息',
  `identity_info` varchar(1024) DEFAULT NULL COMMENT '税收居民身份信息',
  `credit_type` int(10) DEFAULT NULL COMMENT '人征信记录是否保持良好 0 没选 1各项征信记录保持良好 2：有过逾期记录',
  `penates` decimal(16,2) DEFAULT NULL COMMENT '家庭资产（两位小数）',
  `bank_num` int(8) DEFAULT '0' COMMENT '银行卡个数',
  `investors_birthday` varchar(8) DEFAULT NULL COMMENT '投资人出生日期',
  `vocation_code` varchar(3) DEFAULT NULL COMMENT '投资人职业代码',
  `annual_income` bigint(8) DEFAULT NULL COMMENT '投资人年收入',
  `tel_no` varchar(22) DEFAULT NULL COMMENT '投资人电话号码',
  `status` int(10) DEFAULT NULL COMMENT '状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sc_user_id` (`sc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=315 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息表'


/**/

CREATE TABLE `sc_product_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '产品编号',
  `product_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '产品代码',
  `product_factory_code` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '厂家代码',
  `product_grade` varchar(2) DEFAULT NULL COMMENT '产品等级 01-食用级别 02-医用级别 03-工厂级别 04-a 05-b',
  `product_label` varchar(2) DEFAULT NULL COMMENT '产品标签',
  `product_status` varchar(2) DEFAULT NULL COMMENT '产品状态',
  `product_factory_status` varchar(2) DEFAULT NULL COMMENT '厂家状态0-待业 1-开业 2-停业',
  `product_creat_date` datetime DEFAULT NULL COMMENT '生产日期',
  `product_out_date` datetime DEFAULT NULL COMMENT '过期时间',
  `releas_release_date` datetime DEFAULT NULL COMMENT '产品上架时间',
  `product_revoke_date` datetime DEFAULT NULL COMMENT '产品下架时间',
  `release_management` varchar(1) DEFAULT NULL COMMENT '上架处理 0-待上架 1-正常上架',
  `product_sort` int(3) DEFAULT NULL COMMENT '商品排序',
  `is_hot_sale` varchar(1) DEFAULT NULL COMMENT '是否爆款 0-是 1-否',
  `is_delete` varchar(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `sale_sort` int(3) DEFAULT NULL COMMENT '爆款排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_no_01` (`product_no`),
  UNIQUE KEY `code_01` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='产品信息表'