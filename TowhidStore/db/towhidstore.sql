-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: towhidstore
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `unitPrice` float(8,2) NOT NULL,
  `quantity` float(8,2) NOT NULL,
  `totalPrice` float(8,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'Keybord',200.00,50.00,10000.00),(4,'Laptop',70000.00,10.00,700000.00),(5,'monitor',4000.00,80.00,320000.00),(7,'Personal Computer',40000.00,2.00,80000.00),(8,'RAM',1800.00,20.00,36000.00),(9,'Hardisk',2700.00,50.00,135000.00),(10,'Converter',70.00,100.00,7000.00),(11,'tggvbyh',400.00,5.00,2000.00),(12,'Mobile',12000.00,5.00,60000.00),(13,'Hardware',7200.00,5.00,36000.00),(14,'qguyhui8',785.00,5.00,3925.00),(16,'habijabi',400.00,10.00,4000.00),(17,'rdubjh',452.00,10.00,4520.00),(18,'Mobile',12000.00,6.00,72000.00),(19,'Hardware',7200.00,10.00,72000.00),(20,'lkl',100.00,2.00,200.00),(21,'kkkk',10.00,100.00,1000.00),(22,'sasa',10.00,20.00,200.00),(23,'Mobile',12000.00,15.00,180000.00),(24,'mobile',12000.00,3.00,36000.00),(25,'mobile',12000.00,2.00,24000.00),(26,'Hardisk',10.00,2700.00,27000.00),(27,'Hardisk',5.00,2700.00,27000.00),(28,'hardisk',2700.00,5.00,13500.00),(29,'Hardisk',2700.00,12.00,32400.00),(30,'mobile',12000.00,5.00,60000.00),(31,'Hardware',7200.00,10.00,72000.00),(32,'Hardware',7200.00,10.00,72000.00),(33,'Habijabi',400.00,10.00,4000.00),(34,'sasa',10.00,110.00,1100.00);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `salesUnitPrice` float(8,2) NOT NULL,
  `salesQuantity` float(8,2) NOT NULL,
  `salesTotalPrice` float(8,2) NOT NULL,
  `salesDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,'Keybord',200.00,5.00,1000.00,'2024-05-28'),(2,'Converter',70.00,20.00,1400.00,'2024-05-29'),(3,'Personal Computer',40000.00,3.00,120000.00,'2024-05-29'),(4,'tggvbyh',400.00,5.00,2000.00,'2024-05-29'),(5,'RAM',1800.00,9.00,16200.00,'2024-05-29'),(6,'mobile',12000.00,10.00,120000.00,'2024-05-30'),(7,'Hardware',7200.00,19.00,136800.00,'2024-05-30'),(8,'sasa',10.00,115.00,1150.00,'2024-05-30'),(9,'sasa',10.00,12.00,120.00,'2024-05-30');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `quantity` float(8,2) NOT NULL,
  `unitPrice` float(8,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,'Mobile',5.00,12000.00),(2,'Hardware',6.00,7200.00),(3,'qguyhui8',5.00,785.00),(4,'dftfdt',78.00,8.00),(5,'habijabi',20.00,400.00),(6,'rdubjh',10.00,452.00),(7,'Mobile',6.00,12000.00),(8,'Hardware',11.00,7200.00),(9,'sasa',3.00,10.00),(10,'Mobile',15.00,12000.00),(11,'Hardisk',5417.00,10.00);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-30 10:19:34
