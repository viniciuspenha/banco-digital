DROP TABLE IF EXISTS `conta`;

CREATE TABLE `conta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dataNascimento` DATE NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `cep` varchar(9),
  `rua` varchar(50),
  `bairro` varchar(50),
  `complemento` varchar(50),
  `cidade` varchar(50),
  `estado` varchar(50),
  PRIMARY KEY (`id`)
);