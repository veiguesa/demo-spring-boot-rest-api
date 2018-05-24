CREATE DATABASE  IF NOT EXISTS `market-database` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `market-database`;
-- MySQL dump 10.13  Distrib 5.5.59, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: market-database
-- ------------------------------------------------------
-- Server version	5.5.59-0ubuntu0.14.04.1

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
-- Table structure for table `condition_survey`
--

DROP TABLE IF EXISTS `condition_survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `condition_survey` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `availability` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `condition_survey`
--

LOCK TABLES `condition_survey` WRITE;
/*!40000 ALTER TABLE `condition_survey` DISABLE KEYS */;
INSERT INTO `condition_survey` VALUES (1,0,'Condition 1'),(2,2,'Condition 2'),(3,0,'Condition 1'),(4,2,'Condition 2'),(5,0,'Condition 1'),(6,2,'Condition 2');
/*!40000 ALTER TABLE `condition_survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES ('AR'),('ES'),('FR'),('IT');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market_survey`
--

DROP TABLE IF EXISTS `market_survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `market_survey` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `work_end` datetime DEFAULT NULL,
  `work_init` datetime DEFAULT NULL,
  `method` int(11) DEFAULT NULL,
  `organisation` varchar(255) DEFAULT NULL,
  `registration_type` int(11) DEFAULT NULL,
  `sample_size` int(11) DEFAULT NULL,
  `target_group_description` varchar(255) DEFAULT NULL,
  `time_series` bit(1) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `provider_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKomi9aw4nrn26lvg8n3j0oonp8` (`provider_id`),
  CONSTRAINT `FKomi9aw4nrn26lvg8n3j0oonp8` FOREIGN KEY (`provider_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market_survey`
--

LOCK TABLES `market_survey` WRITE;
/*!40000 ALTER TABLE `market_survey` DISABLE KEYS */;
INSERT INTO `market_survey` VALUES (1,3,'2018-05-24 09:32:01','market survey description 1','2018-01-01 00:00:00','2018-01-01 00:00:00',1,'Organization 1',6,1,'Target description 1','',3,NULL,1),(2,3,'2018-05-24 09:44:34','market survey description POST (updated)','2018-01-01 00:00:00','2018-01-01 00:00:00',1,'Organization post',6,1,'Target description 1','',3,'2018-05-24 11:48:07',NULL),(3,3,'2018-05-24 09:44:50','market survey description 3','2018-01-01 00:00:00','2018-01-01 00:00:00',1,'Organization 1',6,1,'Target description 3','',3,NULL,1);
/*!40000 ALTER TABLE `market_survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market_survey_conditions`
--

DROP TABLE IF EXISTS `market_survey_conditions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `market_survey_conditions` (
  `market_survey_id` bigint(20) NOT NULL,
  `conditions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`market_survey_id`,`conditions_id`),
  UNIQUE KEY `UK_1l15odropvc60s67auvqc4667` (`conditions_id`),
  CONSTRAINT `FK3qrgornfbgqchnnffn9erp5cw` FOREIGN KEY (`market_survey_id`) REFERENCES `market_survey` (`id`),
  CONSTRAINT `FKg84xw92dwk7usxrljqwes93x3` FOREIGN KEY (`conditions_id`) REFERENCES `condition_survey` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market_survey_conditions`
--

LOCK TABLES `market_survey_conditions` WRITE;
/*!40000 ALTER TABLE `market_survey_conditions` DISABLE KEYS */;
INSERT INTO `market_survey_conditions` VALUES (1,1),(1,2),(3,5),(3,6);
/*!40000 ALTER TABLE `market_survey_conditions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market_survey_countries`
--

DROP TABLE IF EXISTS `market_survey_countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `market_survey_countries` (
  `market_survey_id` bigint(20) NOT NULL,
  `countries_code` varchar(255) NOT NULL,
  PRIMARY KEY (`market_survey_id`,`countries_code`),
  UNIQUE KEY `UK_frj231fx7onn8do6vf2ckhln1` (`countries_code`),
  CONSTRAINT `FK24iiog9enlus2cs5la9e5ouj5` FOREIGN KEY (`market_survey_id`) REFERENCES `market_survey` (`id`),
  CONSTRAINT `FKhp5iljv1me65a871jnfl8lfai` FOREIGN KEY (`countries_code`) REFERENCES `country` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market_survey_countries`
--

LOCK TABLES `market_survey_countries` WRITE;
/*!40000 ALTER TABLE `market_survey_countries` DISABLE KEYS */;
INSERT INTO `market_survey_countries` VALUES (1,'ES'),(1,'IT');
/*!40000 ALTER TABLE `market_survey_countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market_survey_requesters`
--

DROP TABLE IF EXISTS `market_survey_requesters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `market_survey_requesters` (
  `market_survey_id` bigint(20) NOT NULL,
  `requesters_id` bigint(20) NOT NULL,
  PRIMARY KEY (`market_survey_id`,`requesters_id`),
  UNIQUE KEY `UK_ej7jjt20g0x4n7nm9ttrvut0s` (`requesters_id`),
  CONSTRAINT `FKlr8tp8g7tpue2n2tcsxkwxq0d` FOREIGN KEY (`market_survey_id`) REFERENCES `market_survey` (`id`),
  CONSTRAINT `FKasxowx22vvntl3gjgeits1725` FOREIGN KEY (`requesters_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market_survey_requesters`
--

LOCK TABLES `market_survey_requesters` WRITE;
/*!40000 ALTER TABLE `market_survey_requesters` DISABLE KEYS */;
INSERT INTO `market_survey_requesters` VALUES (2,1),(1,2);
/*!40000 ALTER TABLE `market_survey_requesters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `party`
--

DROP TABLE IF EXISTS `party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `party` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `party`
--

LOCK TABLES `party` WRITE;
/*!40000 ALTER TABLE `party` DISABLE KEYS */;
INSERT INTO `party` VALUES (1,'adress 1','2018-01-01 00:00:00','user1','$2a$10$fvf2FH7AFIZB1HgSEonUmeA/ao5riBL708gUToaQPNyQKiBXhisF.','2018-01-01 00:00:00'),(2,'adress 2','2018-01-01 00:00:00','user2','$2a$10$fvf2FH7AFIZB1HgSEonUmeA/ao5riBL708gUToaQPNyQKiBXhisF.','2018-01-01 00:00:00'),(3,'adress 3','2018-01-01 00:00:00','user3','$2a$10$fvf2FH7AFIZB1HgSEonUmeA/ao5riBL708gUToaQPNyQKiBXhisF.','2018-01-01 00:00:00');
/*!40000 ALTER TABLE `party` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `party_role`
--

DROP TABLE IF EXISTS `party_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `party_role` (
  `party_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`party_id`,`role_id`),
  KEY `FKha3kwn54bhn5bcmcwgbq21v87` (`role_id`),
  CONSTRAINT `FKl2wdhtgceql7lj645k23crm3w` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FKha3kwn54bhn5bcmcwgbq21v87` FOREIGN KEY (`role_id`) REFERENCES `role_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `party_role`
--

LOCK TABLES `party_role` WRITE;
/*!40000 ALTER TABLE `party_role` DISABLE KEYS */;
INSERT INTO `party_role` VALUES (1,1),(2,2),(3,2);
/*!40000 ALTER TABLE `party_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_app`
--

DROP TABLE IF EXISTS `role_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_app`
--

LOCK TABLES `role_app` WRITE;
/*!40000 ALTER TABLE `role_app` DISABLE KEYS */;
INSERT INTO `role_app` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role_app` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-24 14:02:48

