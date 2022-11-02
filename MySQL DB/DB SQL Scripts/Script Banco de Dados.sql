CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `controle_entrada`
--

DROP TABLE IF EXISTS `controle_entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `controle_entrada` (
  `id_controle_entrada` int NOT NULL,
  `data_entrada` date DEFAULT NULL,
  `quantidade_entrada` int DEFAULT NULL,
  `fk_id_historico_produto_entrada` int NOT NULL,
  PRIMARY KEY (`id_controle_entrada`),
  KEY `fk_id_produto_entrada_idx` (`fk_id_historico_produto_entrada`),
  CONSTRAINT `fk_id_produto_entrada` FOREIGN KEY (`fk_id_historico_produto_entrada`) REFERENCES `historico_produto` (`id_historico_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controle_entrada`
--

LOCK TABLES `controle_entrada` WRITE;
/*!40000 ALTER TABLE `controle_entrada` DISABLE KEYS */;
INSERT INTO `controle_entrada` VALUES (1,'2022-06-11',60,1),(2,'2022-07-20',20,2),(3,'2022-04-25',15,3),(4,'2022-06-02',20,4),(5,'2022-04-25',15,5),(6,'2022-06-11',60,6),(7,'2022-04-25',30,7),(8,'2022-07-20',15,8),(9,'2022-06-11',60,9),(10,'2022-06-02',20,10);
/*!40000 ALTER TABLE `controle_entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `id_fornecedor` int NOT NULL AUTO_INCREMENT,
  `nome_fornecedor` varchar(50) DEFAULT NULL,
  `cnpj_fornecedor` char(18) DEFAULT NULL,
  PRIMARY KEY (`id_fornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'Rodrigo de Assunção','49.475.142/0001-02'),(2,'Irineu da Silva','80.422.773/0001-03'),(3,'Giovanna Mendes','10.923.150/0001-20'),(4,'Louças & Cerâmicas Silva','71.985.765/0001-76'),(5,'Jabuti Cerâmicas','71.985.765/0001-76');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor_contato`
--

DROP TABLE IF EXISTS `fornecedor_contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor_contato` (
  `id_fornecedor_contato` int NOT NULL,
  `email` varchar(75) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `fk_id_fornecedor` int NOT NULL,
  PRIMARY KEY (`id_fornecedor_contato`),
  KEY `fk_id_fornecedor_contato_idx` (`fk_id_fornecedor`),
  CONSTRAINT `fk_id_fornecedor` FOREIGN KEY (`fk_id_fornecedor`) REFERENCES `fornecedor` (`id_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor_contato`
--

LOCK TABLES `fornecedor_contato` WRITE;
/*!40000 ALTER TABLE `fornecedor_contato` DISABLE KEYS */;
INSERT INTO `fornecedor_contato` VALUES (1,'rodrigo.assuncao2006@email.com','(47)99785-2345',1),(2,'irineusilva.contato@email.com','(41)99833-3415',2),(3,'GiovanaMendes@rotmail.com','(47)94886-3266',3),(4,'loucas.ceramicas.silva.contato@email.com','(47) 9865-1253',4),(5,'jabuti.ceramicas.contato@email.com','(47) 99896-2356',5);
/*!40000 ALTER TABLE `fornecedor_contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor_endereco`
--

DROP TABLE IF EXISTS `fornecedor_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor_endereco` (
  `id_fornecedor_endereco` int NOT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `rua` varchar(70) DEFAULT NULL,
  `numero` int DEFAULT NULL,
  `fk_id_fornecedor` int NOT NULL,
  PRIMARY KEY (`id_fornecedor_endereco`),
  KEY `fk_id_fornecedor_endereco_idx` (`fk_id_fornecedor`),
  CONSTRAINT `fk_id_fornecedor_endereco` FOREIGN KEY (`fk_id_fornecedor`) REFERENCES `fornecedor` (`id_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor_endereco`
--

LOCK TABLES `fornecedor_endereco` WRITE;
/*!40000 ALTER TABLE `fornecedor_endereco` DISABLE KEYS */;
INSERT INTO `fornecedor_endereco` VALUES (1,'João Pessoa','Santo Augusto','Vereador José Gomes',564,1),(2,'Blumenau','Vila Nova','Liberdade',224,2),(3,'Gaspar','Gaspar Alto','Romero Batista',6969,3),(4,'João Pessoa','Laranjeira','Prefeito Fernando Mezadri',420,4),(5,'Navegantes','Pedreira','200-3',253,5);
/*!40000 ALTER TABLE `fornecedor_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico_produto`
--

DROP TABLE IF EXISTS `historico_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historico_produto` (
  `id_historico_produto` int NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(45) DEFAULT NULL,
  `estoque_produto` int DEFAULT NULL,
  `material_produto` varchar(45) DEFAULT NULL,
  `dimensoes_produto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_historico_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico_produto`
--

LOCK TABLES `historico_produto` WRITE;
/*!40000 ALTER TABLE `historico_produto` DISABLE KEYS */;
INSERT INTO `historico_produto` VALUES (1,'Garfo Inox',50,'Inox','15x4x2'),(2,'Escorredor',15,'Inox','45x25x20'),(3,'Kit 10 Xícaras Comemorativas Natal',10,'Porcelana','7x7x6'),(4,'Jogo de Pratos',18,'Vidro','22x22x3'),(5,'Escorredor',15,'Inox','45x25x20'),(6,'Concha',50,'Inox','25x8x8'),(7,'Petisqueira',25,'Madeira','25x30x4'),(8,'Bule',25,'Porcelana','30x20x26'),(9,'Jogo Talheres',50,'Inox','15x4x2'),(10,'Saleiro',2,'Vidro','5x5x10'),(11,'asd',12,'21','12'),(12,'asd',12,'21','12'),(13,'asd',12,'sadsda','121212'),(14,'asd',121,'21','12'),(15,'as',12,'12','12'),(16,'a',1,'1','1'),(17,'1',1,'1','1'),(18,'asd',12,'12','121212'),(19,'asdasddasd',12,'sa','12x12x12');
/*!40000 ALTER TABLE `historico_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preco`
--

DROP TABLE IF EXISTS `preco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preco` (
  `id_preco` int NOT NULL AUTO_INCREMENT,
  `preco_venda` float DEFAULT NULL,
  `preco_custo` float DEFAULT NULL,
  `data_alteracao` date DEFAULT NULL,
  `fk_id_historico_produto_preco` int NOT NULL,
  PRIMARY KEY (`id_preco`),
  KEY `fk_id_produto_preco_idx` (`fk_id_historico_produto_preco`),
  CONSTRAINT `fk_id_produto_preco` FOREIGN KEY (`fk_id_historico_produto_preco`) REFERENCES `historico_produto` (`id_historico_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preco`
--

LOCK TABLES `preco` WRITE;
/*!40000 ALTER TABLE `preco` DISABLE KEYS */;
INSERT INTO `preco` VALUES (1,12,5,'2022-06-16',1),(2,35,15,'2022-07-25',2),(3,85,35,'2022-04-30',3),(4,100,50,'2022-06-07',4),(5,55,25,'2022-06-30',5),(6,15,7,'2022-06-16',6),(7,35,15,'2022-06-30',7),(8,135,55,'2022-07-25',8),(9,87,45,'2022-06-16',9),(10,35,13,'2022-06-07',10),(11,13,21,'2022-11-02',11),(12,13,21,'2022-11-02',12),(13,12,12,'2022-11-02',13),(14,12,12,'2022-11-02',14),(15,12,12,'2022-11-02',15),(16,1,1,'2022-11-02',16),(17,1,1,'2022-11-02',17),(18,12,12,'2022-11-02',18),(19,11,11,'2022-11-02',19);
/*!40000 ALTER TABLE `preco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id_produto` int NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(45) DEFAULT NULL,
  `estoque_produto` varchar(45) DEFAULT NULL,
  `material_produto` varchar(45) DEFAULT NULL,
  `dimensoes_produto` varchar(45) DEFAULT NULL,
  `fk_id_historico_produto` int NOT NULL,
  `fk_id_fornecedor_id` int DEFAULT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `fk_id_historico_produto_idx` (`fk_id_historico_produto`),
  KEY `fk_id_fornecedor_idx` (`fk_id_fornecedor_id`),
  CONSTRAINT `fk_id_fornecedor_id` FOREIGN KEY (`fk_id_fornecedor_id`) REFERENCES `fornecedor` (`id_fornecedor`),
  CONSTRAINT `fk_id_historico_produto` FOREIGN KEY (`fk_id_historico_produto`) REFERENCES `historico_produto` (`id_historico_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (2,'teste','4','asd','12',2,2),(3,'Kit 10 Xícaras Comemorativas Natal','10','Porcelana','7x7x6',3,NULL),(4,'Jogo de Pratos','18','Vidro','22x22x3',4,NULL),(5,'Escorredor','15','Inox','45x25x20',5,NULL),(6,'Concha','50','Inox','25x8x8',6,2),(7,'Petisqueira','25','Madeira','25x30x4',7,NULL),(8,'Bule','25','Porcelana','30x20x26',8,NULL),(9,'Jogo Talheres','50','Inox','15x4x2',9,NULL),(10,'Saleiro','2','Vidro','5x5x10',10,NULL),(11,'ad','2','asdasd','5x23x23',4,2),(13,'asd','12','sadsda','121212',13,2),(16,'a','1','1','1',16,NULL),(17,'1','1','1','1',17,2),(18,'asd','12','12','121212',18,2),(19,'asdasddasd','12','sa','12x12x12',19,2);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_venda`
--

DROP TABLE IF EXISTS `produto_venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto_venda` (
  `id_produto_venda` int NOT NULL AUTO_INCREMENT,
  `quantidade` int DEFAULT NULL,
  `fk_id_produto` int NOT NULL,
  `fk_id_venda` int NOT NULL,
  PRIMARY KEY (`id_produto_venda`),
  KEY `fk_id_produto_idx` (`fk_id_produto`),
  CONSTRAINT `fk_id_produto` FOREIGN KEY (`fk_id_produto`) REFERENCES `historico_produto` (`id_historico_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_venda`
--

LOCK TABLES `produto_venda` WRITE;
/*!40000 ALTER TABLE `produto_venda` DISABLE KEYS */;
INSERT INTO `produto_venda` VALUES (1,3,2,0),(2,3,2,0),(3,3,2,0),(4,3,2,0),(5,4,2,0),(6,4,2,0),(7,4,2,0),(8,4,2,0),(9,4,2,0),(10,3,3,0),(11,3,3,0),(12,3,3,0),(13,3,3,0),(14,3,4,0),(15,3,4,0),(16,3,4,0),(17,3,4,0),(18,3,5,0),(19,3,5,0),(20,3,5,0),(21,2,8,0),(22,2,8,0),(23,2,8,0),(24,2,8,0),(25,2,2,0),(26,2,2,0),(27,3,4,0),(28,3,4,0),(29,3,4,0),(30,3,4,0),(31,3,4,0),(32,1,2,0),(33,1,4,0),(34,1,4,0),(35,1,3,0),(36,1,3,0),(37,1,5,0),(38,1,5,0),(39,1,2,13),(40,1,2,13),(41,1,2,13),(42,1,2,13);
/*!40000 ALTER TABLE `produto_venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relacao_fornecedor_produto`
--

DROP TABLE IF EXISTS `relacao_fornecedor_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relacao_fornecedor_produto` (
  `fk_id_historico_produto` int NOT NULL,
  `fk_id_fornecedor` int NOT NULL,
  KEY `fk_id_fornecedor_produto_fornecedor_idx` (`fk_id_fornecedor`),
  KEY `fk_id_produto_fornecedor_produto_idx` (`fk_id_historico_produto`),
  CONSTRAINT `fk_id_fornecedor_produto_fornecedor` FOREIGN KEY (`fk_id_fornecedor`) REFERENCES `fornecedor` (`id_fornecedor`),
  CONSTRAINT `fk_id_produto_fornecedor_produto` FOREIGN KEY (`fk_id_historico_produto`) REFERENCES `historico_produto` (`id_historico_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relacao_fornecedor_produto`
--

LOCK TABLES `relacao_fornecedor_produto` WRITE;
/*!40000 ALTER TABLE `relacao_fornecedor_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `relacao_fornecedor_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `cargo_usuario` varchar(20) DEFAULT NULL,
  `email_usuario` varchar(45) NOT NULL,
  `senha_usuario` varchar(45) NOT NULL,
  `cpf_usuario` char(14) DEFAULT NULL,
  `nome_usuario` varchar(50) DEFAULT NULL,
  `nascimento_usuario` date DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'vendedor','gsad.asd@asda.ad','xasd','118.468.069-84','ad','1111-11-11'),(2,'vendedor','gsad.asd@asda.ad','xasd','118.468.069-84','ad','1111-11-11'),(3,'administrador','gustavo.s07@aluno.com','1234','118.468.069-84','Gustavo Alves','2004-07-05'),(4,'administrador','sa.13@dad.cad','1','118.468.069-84','1','1111-11-11'),(5,'administrador','asdds.asd@dada.ada','1111111','118.468.069-84','11111','1111-11-11'),(6,'vendedor','vendedor1@email.com','senha1234','451.073.880-35','Rodrigo Pereira','1996-09-26'),(7,'vendedor','vendedor2@email.com','senha1234','229.100.990-75','Marcela Vieira','1991-08-31'),(8,'administrador','admin1@email.com','senha1234','700.022.730-39','Roberta Morais','1992-03-05');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_contato`
--

DROP TABLE IF EXISTS `usuario_contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_contato` (
  `id_usuario_contato` int NOT NULL AUTO_INCREMENT,
  `email` varchar(70) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `fk_id_usuario_contato` int NOT NULL,
  PRIMARY KEY (`id_usuario_contato`),
  KEY `fk_id_usuario_contato_idx` (`fk_id_usuario_contato`),
  CONSTRAINT `fk_id_usuario_contato` FOREIGN KEY (`fk_id_usuario_contato`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_contato`
--

LOCK TABLES `usuario_contato` WRITE;
/*!40000 ALTER TABLE `usuario_contato` DISABLE KEYS */;
INSERT INTO `usuario_contato` VALUES (1,'asdad.asd@dasda.as','12313131',2),(2,'asdad.asd@dasda.as','12313131',3),(3,'gustavo.s07@aluno.ifsc.edu.br','(47) 99243-4138',3),(4,'11111','111111',4),(5,'11111','111111',5);
/*!40000 ALTER TABLE `usuario_contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_endereco`
--

DROP TABLE IF EXISTS `usuario_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_endereco` (
  `id_usuario_endereco` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `rua` varchar(70) DEFAULT NULL,
  `numero` int DEFAULT NULL,
  `fk_id_usuario_endereco` int NOT NULL,
  PRIMARY KEY (`id_usuario_endereco`),
  KEY `fk_id_usuario_endereco_idx` (`fk_id_usuario_endereco`),
  CONSTRAINT `fk_id_usuario_endereco` FOREIGN KEY (`fk_id_usuario_endereco`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_endereco`
--

LOCK TABLES `usuario_endereco` WRITE;
/*!40000 ALTER TABLE `usuario_endereco` DISABLE KEYS */;
INSERT INTO `usuario_endereco` VALUES (1,NULL,'weqeq','eqweq','qeq',131122,2),(2,NULL,'weqeq','eqweq','qeq',131122,2),(3,NULL,'weqeq','eqweq','qeq',131122,3),(4,NULL,'Blumenau','vostardt','Pedro Krauss Senior',152,3),(5,NULL,'weqeq','eqweq','qeq',131122,3),(6,NULL,'Blumenau','vostardt','Pedro Krauss Senior',152,3),(7,NULL,'111111','1111','1111',11111111,4),(8,NULL,'111111','1111','1111',11111111,4),(9,NULL,'11','111','11',11,5);
/*!40000 ALTER TABLE `usuario_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda` (
  `id_venda` int NOT NULL AUTO_INCREMENT,
  `data_venda` date NOT NULL,
  `pagamento` enum('cartao','dinheiro') NOT NULL,
  `preco_total_venda` float DEFAULT NULL,
  `fk_id_usuario_venda` int NOT NULL,
  PRIMARY KEY (`id_venda`),
  KEY `fk_id_usuario_venda_idx` (`fk_id_usuario_venda`),
  CONSTRAINT `fk_id_usuario_venda` FOREIGN KEY (`fk_id_usuario_venda`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (1,'2022-11-01','dinheiro',105,1),(2,'2022-11-01','dinheiro',105,1),(3,'2022-11-02','dinheiro',105,1),(4,'2022-11-02','dinheiro',140,1),(5,'2022-11-02','dinheiro',255,1),(6,'2022-11-02','dinheiro',300,1),(7,'2022-11-02','dinheiro',165,1),(8,'2022-11-02','dinheiro',340,1),(9,'2022-11-02','dinheiro',70,1),(10,'2022-11-02','dinheiro',555,1),(11,'2022-11-02','dinheiro',270,1),(12,'2022-11-02','dinheiro',280,1),(13,'2022-11-02','dinheiro',140,1),(14,'2022-11-02','dinheiro',140,1);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-02 16:20:37
