--
-- Table structure for table `AUTHORITY`
--
DROP TABLE IF EXISTS `USER_AUTHORITY`;
DROP TABLE IF EXISTS `AUTHORITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AUTHORITY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_ana6i0racv9b9d5dx3mlwho9p` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTHORITY`
--

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');
INSERT INTO AUTHORITY (ID, NAME) VALUES (3, 'ROLE_DEPTMT');
