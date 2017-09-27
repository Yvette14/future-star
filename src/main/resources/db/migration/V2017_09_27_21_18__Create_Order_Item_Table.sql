CREATE TABLE `order_items` (
  `order_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `item_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `FKhsb4w1q1wcgrqlt55t9pc5h2i` (`item_id`),
  KEY `FKrr42tb47afmaep0ke3uwu37w4` (`order_id`),
  CONSTRAINT `FKhsb4w1q1wcgrqlt55t9pc5h2i` FOREIGN KEY (`item_id`) REFERENCES `t_item` (`id`),
  CONSTRAINT `FKrr42tb47afmaep0ke3uwu37w4` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;