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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidding`
--

LOCK TABLES `bidding` WRITE;
/*!40000 ALTER TABLE `bidding` DISABLE KEYS */;
INSERT INTO `bidding` VALUES (14,11,20,750,'2017-05-26 09:29:11'),(15,11,51,300,'2017-05-26 09:34:04'),(16,11,47,250,'2017-05-26 10:29:28');
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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'root','description',NULL),(2,'Desktop PC','description',1),(3,'PC components','description',1),(4,'Motherboards','description',3),(5,'CPU\'s','description',3),(6,'AMD','description update',5),(7,'Intel','description',5),(8,'Storage','description',3),(9,'RAM','description',3),(10,'Laptops','description',1),(11,'Accesorries','accesories description',1),(73,'Consoles','consoles description',1),(75,'Xbox One','Xbox one console',73),(76,'PS4','Playstation 4',73),(77,'Nitendo','consoles description',73),(78,'GPU\'s','description',3),(79,'AMD Radeon','description',78);
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
  `best_bid` float DEFAULT NULL,
  `bids` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Intel i3 7100',5,110,100,2,'2017-05-26 07:56:28','2017-05-26 07:56:28','OPEN',11,NULL,NULL,'resources\\img\\intel_i3_7100.jpg'),(3,'Intel i3 7300',7,100,0,0,'2017-05-26 08:01:49','2017-05-26 08:01:49','OPEN',11,NULL,NULL,'resources\\img\\missing.png'),(18,'Intel i3 9561',5,300,0,0,'2017-05-26 08:01:49','2017-05-26 08:01:49','OPEN',11,NULL,NULL,'resources\\img\\missing.png'),(20,'Intel Core i7-4930K',5,800,0,0,'2017-05-26 08:11:12','2017-05-26 08:11:12','OPEN',11,NULL,'Socket: LGA2011, Clockspeed: 3.4 GHz, Turbo Speed: 3.9 GHz, No of Cores: 6 , Typical TDP: 130 W','resources/img/i7-4930K.jpg'),(21,'AMD FX-9590',5,200,0,0,'2017-05-26 07:49:21','2017-05-26 07:49:21','OPEN',11,NULL,NULL,'resources\\img\\AMD_FX-9590.jpg'),(22,'AMD FX-8150 ',5,340,0,0,'2017-05-26 08:01:49','2017-05-26 08:01:49','ABANDONED',11,NULL,NULL,'resources\\img\\missing.png'),(43,'AMD Phenom II X6',8,400,0,0,'2017-05-26 08:01:49','2017-05-26 08:01:49','OPEN',11,NULL,NULL,'resources\\img\\missing.png'),(44,'AMD Opteron 6234',5,200,0,0,'2017-05-26 08:01:49','2017-05-26 08:01:49','NOT YET OPENED',11,NULL,NULL,'resources\\img\\missing.png'),(45,'Intel Core i7-4960X ',5,1300,0,0,'2017-05-26 08:11:12','2017-05-26 08:11:12','CLOSED',11,NULL,'Socket: LGA2011,Clockspeed: 3.6 GHz, Turbo Speed: 4.0 GHz, No of Cores: 6 (2 logical cores per physical), Typical TDP: 130 W','resources\\img\\missing.png'),(46,'Intel Core i3-2310M',5,1200,0,0,'2017-05-26 08:01:49','2017-05-26 08:01:49','NOT YET OPENED',11,NULL,NULL,'resources\\img\\missing.png'),(47,'AMD Phenom II X3 B75',7,350,0,0,'2017-05-26 08:01:49','2017-05-26 08:01:49','NOT YET OPENED',11,NULL,NULL,'resources\\img\\missing.png'),(48,'XBOX ONE 500GB',75,250,0,0,'2017-05-26 08:11:12','2017-05-26 08:11:12','NOT YET OPENED',11,NULL,'Includes Xbox One console, wireless controller, 500GB hard drive, chat headset and HDMI cable.','resources\\img\\xbox_one.jpg'),(49,'Asus ROG STRIX GTX 1080 O8G GAMING',78,700,0,0,'2017-05-26 07:56:28','2017-05-26 07:56:28','CLOSED',11,NULL,NULL,'resources\\img\\Asus_ROG_STRIX_GTX_1080_O8G.jpg'),(50,'AMD Radeon HD 7970 PCIE 3G GDDR5',79,260,0,0,'2017-05-26 08:01:49','2017-05-26 08:01:49','CLOSED',11,NULL,'Play the latest PC games supporting DirectX 11.1\nWork and Productivity Application\nVideo playback and editing\nHD video playback and editing\nGCN Architecture','resources\\img\\missing.png'),(51,'Sony PlayStation4 1TB',76,300,0,0,'2017-05-26 08:11:12','2017-05-26 08:11:12','CLOSED',11,NULL,'Features powerful graphics and speed, deeply integrated social capabilities, connected gaming, intelligent personalization, innovative second-screen features and more.\n\nThe substantial 1TB hard drive lets you store several blockbuster games, plenty of apps, indie titles, recorded game footage and more.','resources\\img\\ps4.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Bob','Jones','bobbyjones@gmail.com','Bobby17','iambob',0,'USER'),(6,'aaaaaa','aaaaa','aaaaaa','aaaaaaa','aaaaaaa',0,'USER'),(11,'Lucian','Arustei','luci.arustei@gmail.com','Lucian','lucian',1,'USER'),(12,'Adrian','Butnaru','adrian.butnaru4@gmail.com','AdrianB','55555555',0,'USER'),(13,'James','Smith','asdasdsa@sdf.com','Jamessss','sajkdsfldfsd',0,'USER'),(14,'Jamessa','Smith','asdasa@sdf.com','Jamess','sadsadsadasd',0,'USER'),(15,'Paul','Prothos','puscasu494@gmail.com','Prothos','asddsadsa',0,'USER');
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

-- Dump completed on 2017-05-26 17:36:27
