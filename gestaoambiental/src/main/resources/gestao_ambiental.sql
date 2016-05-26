/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : gestao_ambiental

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-05-26 20:01:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `acesso`
-- ----------------------------
DROP TABLE IF EXISTS `acesso`;
CREATE TABLE `acesso` (
  `aces_id` int(11) NOT NULL AUTO_INCREMENT,
  `aces_descricao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`aces_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of acesso
-- ----------------------------
INSERT INTO `acesso` VALUES ('1', 'Testando1');

-- ----------------------------
-- Table structure for `anexo`
-- ----------------------------
DROP TABLE IF EXISTS `anexo`;
CREATE TABLE `anexo` (
  `anex_id` int(11) NOT NULL AUTO_INCREMENT,
  `anex_caminho` varchar(100) DEFAULT NULL,
  `anex_id_documento` int(11) DEFAULT NULL,
  `anex_nome_arquivo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`anex_id`),
  KEY `fk_anex_id_documento_idx` (`anex_id_documento`),
  CONSTRAINT `fk_anex_id_documento` FOREIGN KEY (`anex_id_documento`) REFERENCES `documento` (`docu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of anexo
-- ----------------------------
INSERT INTO `anexo` VALUES ('1', 'C:\\sga\\teste\\estrutura fatura vivo.docx', '5', 'estrutura fatura vivo.docx');

-- ----------------------------
-- Table structure for `documento`
-- ----------------------------
DROP TABLE IF EXISTS `documento`;
CREATE TABLE `documento` (
  `docu_id` int(11) NOT NULL AUTO_INCREMENT,
  `docu_data_manutencao` date DEFAULT NULL,
  `docu_identificacao` varchar(100) DEFAULT NULL,
  `docu_id_local_origem` int(11) DEFAULT NULL,
  `docu_local_guarda` int(11) DEFAULT NULL,
  `docu_id_acesso` int(11) DEFAULT NULL,
  `docu_mod_recuperacao` varchar(50) DEFAULT NULL,
  `docu_tempo_guarda` varchar(50) DEFAULT NULL,
  `docu_tempo_guarda_arquivo_morto` varchar(50) DEFAULT NULL,
  `docu_forma_protecao` varchar(50) DEFAULT NULL,
  `docu_id_usuario` int(11) DEFAULT NULL,
  `docu_obs` varchar(500) DEFAULT NULL,
  `docu_data_cadastro` date DEFAULT NULL,
  `docu_chave` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`docu_id`),
  KEY `fk_docu_id_local_origem_idx` (`docu_id_local_origem`),
  KEY `fk_docu_local_guarda_idx` (`docu_local_guarda`),
  KEY `fk_docu_id_acesso_idx` (`docu_id_acesso`),
  KEY `fk_docu_id_usuario_idx` (`docu_id_usuario`),
  CONSTRAINT `fk_docu_id_acesso` FOREIGN KEY (`docu_id_acesso`) REFERENCES `acesso` (`aces_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docu_id_local_origem` FOREIGN KEY (`docu_id_local_origem`) REFERENCES `local_origem` (`loor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docu_id_usuario` FOREIGN KEY (`docu_id_usuario`) REFERENCES `usuario` (`usua_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docu_local_guarda` FOREIGN KEY (`docu_local_guarda`) REFERENCES `local_origem` (`loor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of documento
-- ----------------------------
INSERT INTO `documento` VALUES ('5', '2016-05-26', 'teste', '1', '1', '1', 'AALP', '1 ano', '1 ano', 'Cronologica', '1', null, null, null);

-- ----------------------------
-- Table structure for `empresa`
-- ----------------------------
DROP TABLE IF EXISTS `empresa`;
CREATE TABLE `empresa` (
  `empr_id` int(11) NOT NULL AUTO_INCREMENT,
  `empr_nome` varchar(45) DEFAULT NULL,
  `empr_garagem` varchar(45) DEFAULT NULL,
  `empr_setor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`empr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of empresa
-- ----------------------------
INSERT INTO `empresa` VALUES ('1', 'Teste LTDA', 'teste', 'teste');
INSERT INTO `empresa` VALUES ('2', 'Nova LTDA', 'teste', 'teste');

-- ----------------------------
-- Table structure for `filial`
-- ----------------------------
DROP TABLE IF EXISTS `filial`;
CREATE TABLE `filial` (
  `fili_id` int(11) NOT NULL AUTO_INCREMENT,
  `fili_nome` varchar(45) DEFAULT NULL,
  `fili_id_empresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`fili_id`),
  KEY `fili_id_empresa_idx` (`fili_id_empresa`),
  CONSTRAINT `fili_id_empresa` FOREIGN KEY (`fili_id_empresa`) REFERENCES `empresa` (`empr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filial
-- ----------------------------
INSERT INTO `filial` VALUES ('1', 'g', '1');
INSERT INTO `filial` VALUES ('2', 'Teste', '1');
INSERT INTO `filial` VALUES ('3', 'fsdffsd', '1');

-- ----------------------------
-- Table structure for `local_origem`
-- ----------------------------
DROP TABLE IF EXISTS `local_origem`;
CREATE TABLE `local_origem` (
  `loor_id` int(11) NOT NULL AUTO_INCREMENT,
  `loor_descricao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`loor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of local_origem
-- ----------------------------
INSERT INTO `local_origem` VALUES ('1', 'Testando');

-- ----------------------------
-- Table structure for `usuario`
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `usua_id` int(11) NOT NULL AUTO_INCREMENT,
  `usua_nome` varchar(45) DEFAULT NULL,
  `usua_usuario` varchar(45) DEFAULT NULL,
  `usua_senha` varchar(45) DEFAULT NULL,
  `usua_id_empresa` int(11) DEFAULT NULL,
  `usua_nivel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`usua_id`),
  KEY `fk_usua_id_empresa_idx` (`usua_id_empresa`),
  CONSTRAINT `fk_usua_id_empresa` FOREIGN KEY (`usua_id_empresa`) REFERENCES `empresa` (`empr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('1', 'Fernando', 'fernando', '123', '1', 'ROLE_ADMIN');
INSERT INTO `usuario` VALUES ('2', 'Novo', 'novo', '123', '1', 'ROLE_ADMIN');
