CREATE DATABASE  IF NOT EXISTS `amazonia2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `amazonia2`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: amazonia2
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('admin@email.net','ROLE_ADMIN'),('javier','ROLE_ADMIN'),('juan','ROLE_USER'),('pepe','ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `numero` char(8) NOT NULL,
  `cliente_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tmmdejxot9pde0r7k9w5nwoan` (`numero`),
  KEY `FK7m3iwpqcq2spnlv9gggnbgupn` (`cliente_id`),
  CONSTRAINT `FK7m3iwpqcq2spnlv9gggnbgupn` FOREIGN KEY (`cliente_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (8,'2024-01-09','20240001',2),(9,'2024-01-09','20240002',1),(10,'2024-01-09','20240003',1),(11,'2024-01-09','20240004',1),(12,'2024-01-09','20240005',1),(13,'2024-01-09','20240006',1);
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_barras` char(13) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(38,2) NOT NULL,
  `unidades` int NOT NULL,
  `factura_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo7ejo0fh854wy6xcvdr5oyphr` (`factura_id`),
  CONSTRAINT `FKo7ejo0fh854wy6xcvdr5oyphr` FOREIGN KEY (`factura_id`) REFERENCES `facturas` (`id`),
  CONSTRAINT `pedidos_chk_1` CHECK ((`precio` >= 0)),
  CONSTRAINT `pedidos_chk_2` CHECK ((`unidades` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'1234567890982','Modificadofgdsfgasdfasd',12.37,1,8),(2,'4006381333931','asdfasdgasdhgasdh',1234.00,2,8),(3,'5901234123457','Mogollón',1234.00,2,9),(4,'5051893155020','Nuevo',1234.00,1,9),(5,'1234567890982','Modificadofgdsfgasdfasd',12.37,1,9),(6,'4006381333931','asdfasdgasdhgasdh',1234.00,2,9),(7,'4006381333931','asdfasdgasdhgasdh',1234.00,1,13);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_barras` char(13) NOT NULL,
  `fecha_caducidad` date DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(38,2) NOT NULL,
  `unidades` int NOT NULL,
  `version` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b2vi4382tjm9b8q2sgfiox2ec` (`codigo_barras`),
  UNIQUE KEY `UK_mlgw7js72hh2xtd4mvpdqfsbe` (`nombre`),
  CONSTRAINT `productos_chk_1` CHECK ((`precio` >= 0)),
  CONSTRAINT `productos_chk_2` CHECK ((`unidades` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (6,'1234567890982','2025-01-01','Modificado',12.37,3,NULL),(31,'5901234123457',NULL,'Mogollón',1234.00,65,NULL),(33,'5051893155020','2023-12-30','Nuevo',1234.00,4,NULL),(43,'9780141036144','2024-12-12','Novísimo',4321.00,12,NULL);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ldv0v52e0udsh2h1rs0r0gw1n` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'CLIENTE'),(3,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin@email.net','admin',1),('javier','$2a$12$VrzIXrjakP3fgVGCGGEY2O94lPEYegtqRt0TNoH2VlfLXSCbvERTm',1),('juan','$2a$12$bu/I5Gj0x9vbBZI9kZhcWuzygs4NjgsxIYUV0Njyi.VmChGaKGOFm',1),('pepe','$2a$12$9MB8SLdln7G17w2jSFow.e19Pnf2TVyVo0MHFdTkcu8zZ0Ub76zAW',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `password` varchar(500) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `rol_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_ggd9d47p8x7m0ajavk1ayuyqs` (`dni`),
  KEY `FKqf5elo4jcq7qrt83oi0qmenjo` (`rol_id`),
  CONSTRAINT `FKqf5elo4jcq7qrt83oi0qmenjo` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('Cliente',1,'javier@email.net','Javier','$2a$12$IaeDbmhu24FW0aj3fzLhmeRzPgueKWDBcvw1g0c/5txyGk6e8IA4O','12345679J',1),('Cliente',2,'pepe@email.net','Pepe','$2a$12$Gyp3xJ0xYLuAz3eE72HAz.tl9/ErsoB9NXmd9zO5oqy59c45We86u','12345678Z',2),('Cliente',3,'juan@email.net','Juan','juan','87654321X',2),('Usuario',4,'pedro@email.net','Pedro','pedro',NULL,3),('Cliente',5,'luis@email.net','Luis','luis',NULL,2),('Usuario',21,'jon@email.net','Jon','$2a$12$KTKFX4sVnPMACRUTZZ/0TuMSVSwc3AbRcH4DC2GEK2PoX.nIGQMrG',NULL,3),('Usuario',22,'juana@email.net','Juana','$2a$12$errOmRbtDIFScAjQqSx2T.DS2/EvGiDt9ZbzSC4D8UqWVBM34fI.y',NULL,3),('Usuario',24,'manuela@email.net','Manuela','$2a$12$D3gncf.ZMbKUMkGb7GUwC.o2B23GIV6AqVTguCXIMVPloYNVUGaei',NULL,3),('Cliente',25,'pepa@email.net','Pepa','$2a$12$wf3UQJforvjs71o0dA35te8j6Kn5tHgjBgTtAIUQwpx3h5X1DTox2','11111111H',2),('Usuario',26,'nuevo@email.net','Nuevo','$2a$12$wygHjm6Fy1exj6jY/dhMrOC0wyKekS/nT1NC1BEsfjthr1z3UtORS',NULL,3),('Usuario',28,'juanito@email.net','Juanito','$2a$12$jhI7wmfhcaZXvK9yCKwR2uZGUvh5G0DKthYXP2iOKsdfzvnN06Tk.',NULL,3),('Usuario',29,'pp@email.net','PP','$2a$12$xv1w9cweBNtCJtfi.J5D5.ssHNRLyyB800UKL8gqywxDFjDvjQC1i',NULL,3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'amazonia2'
--

--
-- Dumping routines for database 'amazonia2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-12  9:50:56
