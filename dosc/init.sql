CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(64) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `order_state` tinyint(4) DEFAULT NULL,
  `pay_way` varchar(255) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `amount` decimal(8,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_t_order_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

