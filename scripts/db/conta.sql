DROP TABLE IF EXISTS `conta`;

CREATE TABLE `conta` (
  `cliente_id` bigint NOT NULL,
  `agencia` varchar(4) NOT NULL,
  `conta` varchar(8) NOT NULL,
  `codigo_banco` varchar(3) NOT NULL,
  `saldo` decimal NOT NULL default 0.0,
  PRIMARY KEY (`agencia`, `conta`)
);