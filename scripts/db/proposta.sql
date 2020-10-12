DROP TABLE IF EXISTS `proposta`;

CREATE TABLE `proposta` (
  `cliente_id` bigint NOT NULL,
  `agencia` varchar(4),
  `conta` varchar(8),
  `status` varchar(50) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  `data_atualizacao` DATETIME,
  FOREIGN KEY (`cliente_id`) references cliente (`id`),
  FOREIGN KEY (`agencia`, `conta`) references conta (`agencia`, `conta`)
);