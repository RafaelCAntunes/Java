CREATE TABLE cliente_poo(
    cpf      	 VARCHAR2    (11)    CONSTRAINT pk_cliente_poo PRIMARY KEY,
    nome         VARCHAR2    (50),
    endereco     VARCHAR2    (100),
	cidade       VARCHAR2    (50),
	uf           VARCHAR2    (2),
	cep          VARCHAR2    (8),
	ddd          VARCHAR2    (2),
	telefone     VARCHAR2    (9),
	limite_cred  NUMBER      (12,2),
	limite_disp  NUMBER      (12,2)
);

CREATE TABLE vendedor_poo(
    cpf      	   VARCHAR2    (11)    CONSTRAINT pk_vendedor_poo PRIMARY KEY,
    nome           VARCHAR2    (50),
    endereco       VARCHAR2    (100),
	cidade         VARCHAR2    (50),
	uf             VARCHAR2    (2),
	cep            VARCHAR2    (8),
	ddd            VARCHAR2    (2),
	telefone       VARCHAR2    (9),
	salario_base   NUMBER      (12,2),
	taxa_comissao  NUMBER      (12,2)
);

CREATE TABLE produto_poo(
	codigo			VARCHAR2	(20)	CONSTRAINT pk_produto_poo PRIMARY KEY,
	descricao		VARCHAR2	(50),
	qtde_estoque	NUMBER		(10),
	unidade_medida	VARCHAR2	(20),
	preco			NUMBER		(12,2),
	estoque_minimo	NUMBER		(10)
);

CREATE TABLE pedido_poo(
	numero			VARCHAR2	(20) CONSTRAINT pk_pedido_poo PRIMARY KEY,
	cpf_cliente		VARCHAR2	(11) REFERENCES cliente_poo,
	cpf_vendedor	VARCHAR2	(11) REFERENCES vendedor_poo,
	data_emissao	DATE,
	data_pagto		DATE,
	forma_pagto		NUMBER		(1) NOT NULL,
	situacao		NUMBER		(1) NOT NULL
);

CREATE TABLE item_pedido_poo(
	sequencia		NUMBER		(20) CONSTRAINT pk_item_pedido_poo PRIMARY KEY,
	cod_produto		VARCHAR2	(20) REFERENCES produto_poo,
	num_pedido		VARCHAR2	(20) REFERENCES pedido_poo,
	qtde_vendida	NUMBER		(10)
);