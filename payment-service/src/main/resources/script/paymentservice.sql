CREATE TABLE `discount` (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_date` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `type` tinyint NOT NULL,
  `uses_cnt` int NOT NULL,
  `uses_max` int NOT NULL,
  `value` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `discount_chk_1` CHECK ((`type` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transfer_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `acc_holder_name` varchar(255) NOT NULL,
  `acc_num` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `method_name` varchar(255) NOT NULL,
  `method_type` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `transfer_method_chk_1` CHECK ((`method_type` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payment` (
  `id` binary(16) NOT NULL,
  `cus_nme` varchar(255) NOT NULL,
  `type` tinyint NOT NULL,
  `phone_num` varchar(255) NOT NULL,
  `method_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_payment_transfer_method` (`method_id`),
  CONSTRAINT `fk_payment_transfer_method` FOREIGN KEY (`method_id`) REFERENCES `transfer_method` (`id`),
  CONSTRAINT `payment_chk_1` CHECK ((`type` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bill` (
  `id` binary(16) NOT NULL,
  `change_paid` decimal(38,2) DEFAULT NULL,
  `cus_in` datetime(6) DEFAULT NULL,
  `cus_out` datetime(6) DEFAULT NULL,
  `order_id` binary(16) DEFAULT NULL,
  `paid` decimal(38,2) DEFAULT NULL,
  `sub_total` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `discount_id` int DEFAULT NULL,
  `pay_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_bill_discount` (`discount_id`),
  KEY `key_bill_payment` (`pay_id`),
  CONSTRAINT `fk_bill_payment` FOREIGN KEY (`pay_id`) REFERENCES `payment` (`id`),
  CONSTRAINT `fk_bill_discount` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
