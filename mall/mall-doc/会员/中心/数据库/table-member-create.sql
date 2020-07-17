CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(11) NOT NULL COMMENT '手机号码',
  `level` tinyint(1) DEFAULT '0' COMMENT '等级',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_person` varchar(50) DEFAULT NULL,
  `update_person` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT '0',
  `tenant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_idx_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='会员信息表';


