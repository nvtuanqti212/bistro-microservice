CREATE TABLE `orders` (
  `id` binary(16) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `phone_cus` varchar(255) DEFAULT NULL,
  `staff_id` varchar(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `total_price` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`, `created_at`),
  CONSTRAINT `orders_chk_1` CHECK ((`status` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_line` (
  `id` binary(16) NOT NULL,
  `food_id` binary(16) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` binary(16) DEFAULT NULL,
  `price` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `key_order_id` (`order_id`),
  CONSTRAINT `fk_line_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert Bill Trigger --
DROP TRIGGER IF EXISTS trg_after_order_line_insert;
DELIMITER //
CREATE TRIGGER trg_after_order_line_insert
AFTER INSERT ON order_line
FOR EACH ROW
BEGIN
        -- Update existing record
        UPDATE orders
        SET `total_price` = `total_price` + NEW.price
        where `id` = NEW.order_id;
END //

DELIMITER ;