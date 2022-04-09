
CREATE DATABASE IF NOT EXISTS farmtads;
USE farmtads;

DROP TABLE IF EXISTS `pessoa`;
CREATE TABLE `pessoa` (
  `idPessoa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `pessoa_fisica`;
CREATE TABLE `pessoa_fisica` (
  `pessoa_idPessoa` int(10) unsigned NOT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `rg` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`pessoa_idPessoa`),
  CONSTRAINT `fk_Pessoa_Fisica_Pessoa1` FOREIGN KEY (`pessoa_idPessoa`) REFERENCES `pessoa` (`idPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `pessoa_juridica`;
CREATE TABLE `pessoa_juridica` (
  `pessoa_idPessoa` int(10) unsigned NOT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  `razaoSocial` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pessoa_idPessoa`),
  CONSTRAINT `fk_Pessoa_Juridica_Pessoa1` FOREIGN KEY (`pessoa_idPessoa`) REFERENCES `pessoa` (`idPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `IdCliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pontos` int(10) unsigned NOT NULL,
  `Pessoa_Fisica_Pessoa_idPessoa` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IdCliente`),
  KEY `fk_Cliente_Pessoa_Fisica1` (`Pessoa_Fisica_Pessoa_idPessoa`),
  CONSTRAINT `fk_Cliente_Pessoa_Fisica1` FOREIGN KEY (`Pessoa_Fisica_Pessoa_idPessoa`) REFERENCES `pessoa_fisica` (`pessoa_idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fornecedor`;
CREATE TABLE `fornecedor` (
  `idfornecedor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contato` varchar(45) DEFAULT NULL,
  `Pessoa_Juridica_Pessoa_IdPessoa` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idfornecedor`),
  KEY `fk_Fornecedor_Pessoa_Juridica1` (`Pessoa_Juridica_Pessoa_IdPessoa`),
  CONSTRAINT `fk_Fornecedor_Pessoa_Juridica1` FOREIGN KEY (`Pessoa_Juridica_Pessoa_IdPessoa`) REFERENCES `pessoa_juridica` (`pessoa_idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE `funcionario` (
  `idFuncionario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `endereco` varchar(45) DEFAULT NULL,
  `senha` varchar(8) NOT NULL,
  `Pessoa_Fisica_Pessoa_IdPessoa` int(10) unsigned NOT NULL,
  `administrador` tinyint(1) NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  KEY `fk_Funcionario_Pessoa_Fisica1` (`Pessoa_Fisica_Pessoa_IdPessoa`),
  CONSTRAINT `fk_Funcionario_Pessoa_Fisica1` FOREIGN KEY (`Pessoa_Fisica_Pessoa_IdPessoa`) REFERENCES `pessoa_fisica` (`pessoa_idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `IdProduto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `laboratorio` varchar(45) NOT NULL,
  `quantidade` int(10) unsigned NOT NULL,
  `valor_venda` double NOT NULL,
  PRIMARY KEY (`IdProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `entrada`;
CREATE TABLE `entrada` (
  `idEntrada` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data` varchar(10) NOT NULL,
  `Fornecedor_IdFornecedor` int(10) unsigned NOT NULL,
  `Funcionario_IdFuncionario` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idEntrada`),
  KEY `fk_Entrada_Fornecedor1` (`Fornecedor_IdFornecedor`),
  KEY `fk_Entrada_Funcionario1` (`Funcionario_IdFuncionario`),
  CONSTRAINT `fk_Entrada_Fornecedor1` FOREIGN KEY (`Fornecedor_IdFornecedor`) REFERENCES `fornecedor` (`idfornecedor`),
  CONSTRAINT `fk_Entrada_Funcionario1` FOREIGN KEY (`Funcionario_IdFuncionario`) REFERENCES `funcionario` (`idFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `venda`;
CREATE TABLE `venda` (
  `idVenda` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data` varchar(10) NOT NULL,
  `formaPagto` varchar(45) NOT NULL,
  `desconto` double NOT NULL,
  `cliente_Id_Cliente` int(10) unsigned NOT NULL,
  `funcionario_IdFuncionario` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idVenda`),
  KEY `fk_Venda_Cliente1` (`cliente_Id_Cliente`),
  KEY `fk_Venda_Funcionario1` (`funcionario_IdFuncionario`),
  CONSTRAINT `fk_Venda_Cliente1` FOREIGN KEY (`cliente_Id_Cliente`) REFERENCES `cliente` (`IdCliente`),
  CONSTRAINT `fk_Venda_Funcionario1` FOREIGN KEY (`funcionario_IdFuncionario`) REFERENCES `funcionario` (`idFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `item_entrada`;
CREATE TABLE `item_entrada` (
  `Entrada_IdEntrada` int(10) unsigned NOT NULL,
  `Produto_IdProduto` int(10) unsigned NOT NULL,
  `quantidade` int(10) unsigned NOT NULL,
  `valor_compra` double NOT NULL,
  PRIMARY KEY (`Entrada_IdEntrada`,`Produto_IdProduto`),
  KEY `fk_Entrada_has_Produto_Produto1` (`Produto_IdProduto`),
  CONSTRAINT `fk_Entrada_has_Produto_Entrada1` FOREIGN KEY (`Entrada_IdEntrada`) REFERENCES `entrada` (`idEntrada`),
  CONSTRAINT `fk_Entrada_has_Produto_Produto1` FOREIGN KEY (`Produto_IdProduto`) REFERENCES `produto` (`IdProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `item_venda`;
CREATE TABLE `item_venda` (
  `IdProduto` int(10) unsigned NOT NULL,
  `IdVenda` int(10) unsigned NOT NULL,
  `quantidade` int(10) unsigned NOT NULL,
  `valor_venda` double NOT NULL,
  PRIMARY KEY (`IdProduto`,`IdVenda`),
  KEY `fk_Produto_has_Venda_Venda1` (`IdVenda`),
  CONSTRAINT `fk_Produto_has_Venda_Produto1` FOREIGN KEY (`IdProduto`) REFERENCES `produto` (`IdProduto`),
  CONSTRAINT `fk_Produto_has_Venda_Venda1` FOREIGN KEY (`IdVenda`) REFERENCES `venda` (`idVenda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;