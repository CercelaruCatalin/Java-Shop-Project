create table produse
  (`name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `produse`
  ADD PRIMARY KEY (`id`);
  
  ALTER TABLE `produse`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
  
  Insert into produse (`name`, `price`, `image`) VALUES
('7up', 2.5, 'img/7up.png'),
('biscuiti', 4.15, 'img/biscuiti.png'),
('joe', 5.25, 'img/joe.png'),
('knoppers', 3.50, 'img/knoppers.png'),
('lays', 6.25, 'img/lays.png'),
('milka', 4, 'img/milka.png'),
('mirinda', 2.5, 'img/7mirinda.png'),
('mms', 5.75, 'img/mms.png'),
('mtn dew', 2.5, 'img/mtn dew.png'),
('oreo', 4.35, 'img/oreo.png'),
('pepsi', 2.5, 'img/pepsi.png'),
('salatini', 4.5, 'img/salatini.png'),
('snickers', 2.25, 'img/snickers.png'),
('twix', 2.25, 'img/twix.png'),
('rom', 1.5, 'img/rom.png');

SELECT * FROM p3.produse;

CREATE TABLE `cart` (
  `id` int(100) NOT NULL,
  `user_id` int(100) NOT NULL,
  `pid` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(10) NOT NULL,
  `image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `cart` (`id`, `user_id`, `pid`, `name`, `price`, `quantity`, `image`) VALUES
(2, 2, 0, 'Mouse Gaming LOGITECH G502 HERO', 252, 3, 'discounts/product-1.png');


--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(100) NOT NULL,
  `user_id` int(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `number` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `method` varchar(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `total_products` varchar(1000) NOT NULL,
  `total_price` double NOT NULL,
  `placed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` varchar(20) NOT NULL DEFAULT "pending"
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_form`
--

CREATE TABLE `users` (
  `id` int(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_form`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(3, 'Shingen3', 'test02@gmail.com', '1234');


--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);
  

--
-- Indexes for table `user_form`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `cart`
--
INSERT INTO `users`(name, email, password, NR_TEL, address) VALUES('name','mail','pass','tel','adress');

select * from users;

select * from cart;

Delete from cart where user_id>4;

select * from produse;

ALTER TABLE `cart`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;

-
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
  
  
