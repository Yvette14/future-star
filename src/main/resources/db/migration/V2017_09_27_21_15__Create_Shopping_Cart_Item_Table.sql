CREATE TABLE `shopping_cart_items` (
  `shopping_cart_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `item_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `FKrgk6gtv1mgc41bdsrmcp0vkbo` (`item_id`),
  KEY `FKn4ocuqbcv64d0pvyhv863l1y5` (`shopping_cart_id`),
  CONSTRAINT `FKn4ocuqbcv64d0pvyhv863l1y5` FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_cart` (`id`),
  CONSTRAINT `FKrgk6gtv1mgc41bdsrmcp0vkbo` FOREIGN KEY (`item_id`) REFERENCES `t_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;