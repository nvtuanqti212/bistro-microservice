CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_category_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `food` (
  `id` binary(16) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `status` int NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_food_name` (`name`),
  KEY `key_food_category` (`category_id`),
  CONSTRAINT `fk_food_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- INSERT  DB
-- Category -- 
-- Drink, Appetizer, Main Course, Dessert
INSERT INTO `bistro-food-service`.`category`
(`id`, `name`) VALUES (1, "Appetizer");
INSERT INTO `bistro-food-service`.`category`
(`id`, `name`) VALUES (2, "Main Course");
INSERT INTO `bistro-food-service`.`category`
(`id`, `name`) VALUES (3, "Dessert");
INSERT INTO `bistro-food-service`.`category`
(`id`, `name`) VALUES (4, "Drink");

-- insert food --
-- appetizer --
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("30420338-8127-11", "Bánh mì nướng với Phô Mai Camembert", 1, "Bánh mì nướng giòn với lớp vỏ ngoài và phô mai Camembert nướng tan chảy bên trong. Được kèmtheo mứt dâu và hạt dẻ.", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777758/appetizer1_qmtpcx.jpg", 1, 40000);

INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("30420586-8127-11", "Salad Cà Chua Caprese", 1, "Cà chua tươi ngon, phô mai Mozzarella cắt lát mỏng, và lá bạc hà, tất cả được chế biến cùng với dầu ô-liu và nước cốt chanh", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777758/appetizer2_yukeyl.jpg", 1, 35000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("304206b2-8127-11", "Bánh Mì Sandwich Phô Mai Gruyère và Prosciutto", 1, "Bánh mì sandwich với lớp phô mai Gruyère thơm ngon và lớp Prosciutto mỏng, nướng vàng giòn.", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777758/appetizer3_ahte6i.jpg", 1, 49000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("304207d4", "Bánh mì nướng với Phô Mai Ricotta và Mứt Dâu", 1, "Bánh mì nướng với phô mai Ricotta mềm mịn và mứt dâu ngọt ngào", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777758/appetizer4_fylmuj.jpg", 1, 49000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("30420e46-8127-11", "Hộp Súp Hành Phô Mai", 1, "Súp hành trắng thơm ngon, phủ lớp phô mai Swiss nướng trên mặt", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777758/appetizer5_tq88yg.jpg", 1, 49000);
-- main course --
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("82a4522e-8128-11", "Bánh Mì Burger Phô Mai", 2, "Burger bò mỹ phẩm với phô mai Cheddar, hành tây, bơ rút, và rau sống trên bánh mì hấp", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/pizza1_ul8uos.jpg", 1, 109000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("82a4551c-8128-11", "Pizza Quattro Formaggi", 2, "Pizza với tổ hợp bốn loại phô mai: Mozzarella, Gorgonzola, Parmesan và Ricotta", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/pizza2_ffelxl.jpg", 1, 129000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("82a45652-8128-11", "Ravioli Phô Mai với Sốt Nấm Trắng", 2, "Ravioli nhân phô mai Ricotta, được phủ lớp sốt nấm trắng kem ngon", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777760/pizza3_kbwhai.jpg", 1, 139000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("82a45c10-8128-11", "Lasagna Bolognese với Phô Mai Parmesan", 2, "Lasagna cổ điển với lớp bò bolognese, mousse phô mai Ricotta và phủ lớp phô mai Parmesan nướng", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777760/pizza4_kj9l4r.jpg", 1, 139000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("82a45da0-8128-11", "Quiche Lorraine với Phô Mai Gruyère", 2, "Quiche với nhân bánh béo ngậy, bacon và phô mai Gruyère", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777758/pizza5_i1jtmq.jpg", 1, 139000);
-- INSERT DESSERT
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("31279a4a-8129-11", "Bánh Phô Mai New York Cheesecake", 3, "Cheesecake New York đậm đà với lớp phô mai kem trên đỉnh, được kết hợp với sốt dâu tây", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/dessert1_ow1xpt.jpg", 1, 79000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("31279e3c-8129-11", "Mousse Phô Mai với Hương Vị Cam", 3, "Mousse phô mai mềm mịn với hương vị cam tự nhiên và lớp kem sữa tươi", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/dessert2_citu5i.jpg", 1, 79000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("31279fa4-8129-11", "Bánh Roulade Phô Mai với Sốt Sô-cô-la", 3, "Bánh cuốn mềm mịn với nhân phô mai và phủ lớp sốt sô-cô-la đắng", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/dessert3_qthtc3.jpg", 1, 79000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("3127a0bc-8129-11", "Panna Cotta vị Phô Mai với Quả Dâu", 3, "Panna Cotta đặc biệt với hương vị phô mai, kèm theo quả dâu tươi ngon.", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/dessert4_xjvsg4.jpg", 1, 49000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("3127a22e-8129-11", "Tiramisu với Lớp Phô Mai Mascarpone", 3, "Tiramisu truyền thống với lớp phô mai Mascarpone thơm ngon và bột cacao", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/dessert5_qczscm.jpg", 1, 49000);
-- INSERT DRINK
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("25334440-812a-11", "Martini Phô Mai", 4, "Một phiên bản độc đáo của Martini với phô mai Blue Cheese và một lát hành vàng", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/drink1_nkrxcv.jpg", 1, 39000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("253346f2-812a-11", "Wine & Cheese Pairing", 4, "Menu chọn món ăn kèm với các loại rượu và phô mai, bao gồm Chardonnay và Gouda, Merlot và Brie, và Shiraz và Gorgonzola", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/drink2_gozuq1.jpg", 1, 39000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("25334800-812a-11", "Cappuccino Phô Mai", 4, "Cappuccino béo ngậy với lớp bọt sữa phô mai và bột cacao", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/drink3_m3yvt3.jpg", 1, 39000);

INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("25334d3c-812a-11", "Sữa Phô Mai Dâu", 4, "Sữa dâu thơm ngon kèm theo lớp phô mai cream trên đỉnh", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/drink4_yci0mm.jpg", 1, 39000);
INSERT INTO `bistro-food-service`.`food`
(`id`, `name`, `category_id`, `description`, `image`, `status`, `price`)
VALUES
("25334e7c-812a-11", "Espresso Affogato", 4, "Espresso đen và nóng với một viên cà phê kem và lớp phô mai Mascarpone", "https://res.cloudinary.com/dc6io9an2/image/upload/v1699777759/drink5_ltn4zp.jpg", 1, 39000);

