-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bidding_app_schema
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `adress` varchar(100) NOT NULL,
  `zipcode` int(6) NOT NULL,
  `city` varchar(100) NOT NULL,
  `phone` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `account_user_fk_idx` (`user_id`),
  CONSTRAINT `account_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'2635 Simons Hollow Road',10001,'New York',0),(2,11,'1444 S. Alameda Street Los Angeles, California',90021,'Los Angeles, California',213765310);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bidding`
--

DROP TABLE IF EXISTS `bidding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bidding` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `value` float NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `item_bid_fk_idx` (`item_id`),
  KEY `user_bid_fk` (`user_id`),
  CONSTRAINT `item_bid_fk` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_bid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidding`
--

LOCK TABLES `bidding` WRITE;
/*!40000 ALTER TABLE `bidding` DISABLE KEYS */;
INSERT INTO `bidding` VALUES (14,11,20,750,'2017-05-26 09:29:11'),(16,11,47,250,'2017-05-26 10:29:28'),(18,11,51,300,'2017-05-28 10:31:10'),(20,11,48,245,'2017-05-29 04:42:44'),(21,11,52,1430,'2017-05-28 13:52:14'),(22,11,1,100,'2017-05-29 04:10:40'),(23,11,43,350,'2017-05-30 06:13:48'),(25,11,53,320,'2017-06-06 10:44:43');
/*!40000 ALTER TABLE `bidding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `parent_id` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `categories_fk_idx` (`parent_id`),
  CONSTRAINT `categories_fk` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'root','description',NULL),(2,'Desktop PC','description',1),(3,'PC components','description',1),(4,'Motherboards','description',3),(5,'CPU\'s','description',3),(6,'AMD','description update',5),(7,'Intel','description',5),(8,'Storage','description',3),(9,'RAM','description',3),(10,'Laptops','description',1),(73,'Consoles','consoles description',1),(75,'Xbox One','Xbox one console',73),(76,'PS4','Playstation 4',73),(77,'Nitendo','consoles description',73),(78,'GPU\'s','description',3),(79,'AMD Radeon','description',78),(81,'Accesoriess','accesories description',1);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `price` float NOT NULL,
  `opening_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `closing_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('OPEN','CLOSED','ABANDONED','NOT YET OPENED') NOT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `winner_id` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT 'resources\\img\\no_image.png',
  `image` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_fk_idx` (`category_id`),
  KEY `user_fk_idx` (`seller_id`),
  KEY `seller_fk_idx` (`seller_id`),
  KEY `winner_fk_idx` (`winner_id`),
  CONSTRAINT `category_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `seller_fk` FOREIGN KEY (`seller_id`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `winner_fk` FOREIGN KEY (`winner_id`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Intel i3 7100',5,110,'2017-05-31 07:01:11','2017-06-26 07:56:28','OPEN',11,NULL,NULL,'resources\\img\\intel_i3_7100.jpg'),(3,'Intel i3 7300',7,100,'2017-05-31 07:01:11','2017-05-31 07:01:11','CLOSED',11,1,NULL,'resources\\img\\missing.png'),(18,'Intel i3 9561',5,300,'2017-05-31 07:01:11','2017-06-26 08:01:49','OPEN',11,NULL,NULL,'resources\\img\\missing.png'),(20,'Intel Core i7-4930K',5,800,'2017-05-31 07:01:11','2017-05-31 07:01:11','CLOSED',11,13,'Socket: LGA2011, Clockspeed: 3.4 GHz, Turbo Speed: 3.9 GHz, No of Cores: 6 , Typical TDP: 130 W','resources/img/i7-4930K.jpg'),(21,'AMD FX-9590',5,200,'2017-05-31 07:01:11','2017-06-26 07:49:21','OPEN',11,NULL,NULL,'resources\\img\\AMD_FX-9590.jpg'),(22,'AMD FX-8150 ',5,340,'2017-05-31 08:35:06','2017-05-31 08:35:06','CLOSED',11,13,NULL,'resources\\img\\missing.png'),(43,'AMD Phenom II X6',5,400,'2017-05-31 07:01:11','2017-05-31 07:01:11','CLOSED',11,15,'Multi-Core: Six-core, Operating Frequency: 2.8GHz, Socket AM3, 3 MB L2 cache, 6 MB L3 cache ,Offers 64 bit support','resources\\img\\AMD_Phenom_X6.jpg'),(44,'AMD Opteron 6234',5,200,'2017-05-31 07:01:11','2017-06-26 08:01:49','OPEN',11,NULL,NULL,'resources\\img\\missing.png'),(45,'Intel Core i7-4960X ',5,1300,'2017-05-31 06:53:41','2017-05-31 06:53:41','CLOSED',11,1,'Socket: LGA2011,Clockspeed: 3.6 GHz, Turbo Speed: 4.0 GHz, No of Cores: 6 (2 logical cores per physical), Typical TDP: 130 W','resources\\img\\IntelCorei7_4960X.jpg'),(46,'Intel Core i3-2310M',5,1200,'2017-05-31 07:01:11','2017-06-26 08:01:49','OPEN',11,NULL,NULL,'resources\\img\\missing.png'),(47,'AMD Phenom II X3 B75',7,350,'2017-06-10 08:01:49','2017-06-26 08:01:49','NOT YET OPENED',11,NULL,NULL,'resources\\img\\missing.png'),(48,'XBOX ONE 500GB',75,250,'2017-05-31 07:01:11','2017-05-31 07:01:11','CLOSED',11,1,'Includes Xbox One console, wireless controller, 500GB hard drive, chat headset and HDMI cable.','resources\\img\\xbox_one.jpg'),(49,'Asus ROG STRIX GTX 1080 O8G GAMING',78,700,'2017-05-31 06:53:41','2017-05-31 06:53:41','CLOSED',11,13,'1936 MHz boost clock in OC mode for outstanding performance and gaming experience. DirectCU III with Patented Wing-Blade Fans delivers 30% cooler and 3X quieter performance. ASUS FanConnect features 4-pin GPU-controlled headers connected to system fans for optimal thermal performance. Industry Only Auto-Extreme Technology with Super Alloy Power II delivers premium quality and best reliability.','resources\\img\\Asus_ROG_STRIX_GTX_1080_O8G.jpg'),(50,'AMD Radeon HD 7970 PCIE 3G GDDR5',79,260,'2017-05-31 06:53:41','2017-05-31 06:53:41','CLOSED',11,15,'Play the latest PC games supporting DirectX 11.1\nWork and Productivity Application\nVideo playback and editing\nHD video playback and editing\nGCN Architecture','resources\\img\\missing.png'),(51,'Sony PlayStation4 1TB',76,300,'2017-05-31 06:53:41','2017-05-31 06:53:41','CLOSED',11,1,'Features powerful graphics and speed, deeply integrated social capabilities, connected gaming, intelligent personalization, innovative second-screen features and more.\n\nThe substantial 1TB hard drive lets you store several blockbuster games, plenty of apps, indie titles, recorded game footage and more.','resources\\img\\ps4.jpg'),(52,'Apple MacBookPro 15\'',10,1500,'2017-05-31 07:01:11','2017-05-31 07:01:11','CLOSED',11,13,'The new MacBook Pro is faster and more powerful than before, yet remarkably thinner and lighter.¹ It has the brightest, most colorful display ever on a Mac notebook.\nBrilliant Retina display\nDual-core Intel Core i5\nIntel Iris Graphics 540\nUltrafast SSD\nTwo Thunderbolt 3 ports','resources\\img\\MacBookPro.jpg'),(53,'Xbox One S 1TB Battlefield™ 1 Edition',75,300,'2017-05-31 07:01:11','2017-05-31 07:01:11','CLOSED',11,1,'Own the Xbox One S Battlefield™ 1 Special Edition Bundle (1TB), featuring a military green console with matching stand and controller, 4K Ultra HD Blu-ray, High Dynamic Range, and one month of EA Access. Includes a full game download of Battlefield™ 1 Early Enlister Deluxe Edition','resources\\img\\xbox_one_s_battlefield.jpg'),(55,'Samsung 850 EVO 500GB Solid State Drive',8,200,'2017-06-06 12:06:57','2017-06-06 12:06:57','CLOSED',1,NULL,NULL,NULL),(58,'Intel i3 7300',77,65,'2017-06-28 09:08:30','2017-06-14 09:10:03','NOT YET OPENED',1,NULL,'Hesus',NULL);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registrationdetails`
--

DROP TABLE IF EXISTS `registrationdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registrationdetails` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `activation_expiry` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  `activation_key` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `user_fk_idx` (`user_id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrationdetails`
--

LOCK TABLES `registrationdetails` WRITE;
/*!40000 ALTER TABLE `registrationdetails` DISABLE KEYS */;
INSERT INTO `registrationdetails` VALUES (5,'2017-04-20 12:29:53',6,'6e33b12d-a7a7-4846-8c42-c229dce90b69'),(6,'2017-05-05 10:01:34',12,'6a144b9b-af94-45b6-99b3-b429389dd028'),(7,'2017-05-05 11:27:08',13,'9b460fd7-f421-411a-aa26-712ee397688c'),(8,'2017-05-05 11:29:13',14,'ea52fc4e-6a25-443b-9353-11b7105ad1a0'),(9,'2017-05-05 11:30:05',15,'68f0458b-400b-4434-84f6-18b3c393feda');
/*!40000 ALTER TABLE `registrationdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `account_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `is_activated` tinyint(1) DEFAULT '0',
  `roles` enum('USER','ADMIN') NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `Email_UNIQUE` (`email`),
  UNIQUE KEY `Account name_UNIQUE` (`account_name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Bob','Jones','bobbyjones@gmail.com','Bobby17','iambob',1,'USER'),(6,'aaaaaa','aaaaa','aaaaaa','aaaaaaa','aaaaaaa',0,'USER'),(11,'Lucian','Arustei','luci.arustei@gmail.com','Lucian','lucian',1,'ADMIN'),(12,'Adrian','Butnaru','adrian.butnaru4@gmail.com','AdrianB','55555555',0,'USER'),(13,'James','Smith','asdasdsa@sdf.com','Jamessss','sajkdsfldfsd',1,'USER'),(14,'Jamessa','Smith','asdasa@sdf.com','Jamess','sadsadsadasd',0,'USER'),(15,'Paul','Prothos','puscasu494@gmail.com','Prothos','asddsadsa',0,'USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-07 10:08:35
