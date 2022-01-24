CREATE TABLE `sales`(
    `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `salesperson_id` BIGINT UNSIGNED NOT NULL,
    `total_price` BIGINT UNSIGNED NOT NULL,
    CONSTRAINT `fk_sale_items_salespeople` FOREIGN KEY (`salesperson_id`) REFERENCES `salespeople`(`id`)
);

CREATE TABLE `sale_items`(
    `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `sale_id` BIGINT UNSIGNED NULL,
    `product_id` BIGINT UNSIGNED NOT NULL,
    `quantity` INT UNSIGNED NOT NULL,
    `price` BIGINT UNSIGNED NOT NULL,
    CONSTRAINT `fk_sale_items_sales` FOREIGN KEY (`sale_id`) REFERENCES `sales`(`id`),
    CONSTRAINT `fk_sale_items_products` FOREIGN KEY (`product_id`) REFERENCES `products`(`id`)
);