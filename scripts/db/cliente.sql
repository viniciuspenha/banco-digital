DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `cep` varchar(9),
  `rua` varchar(50),
  `bairro` varchar(50),
  `complemento` varchar(50),
  `cidade` varchar(50),
  `estado` varchar(50),
  `url_cpf_foto` varchar(100),
  `data_criacao` DATETIME,
  `data_atualizacao` DATETIME,
  PRIMARY KEY (`id`)
);