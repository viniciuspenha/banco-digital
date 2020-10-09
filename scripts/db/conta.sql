DROP TABLE IF EXISTS `conta`;

CREATE TABLE `conta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nascimento` DATE NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
);