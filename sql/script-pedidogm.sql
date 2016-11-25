
﻿CREATE TABLE usuario (
  id_usuario    SERIAL      NOT NULL,
  nome_usuario  VARCHAR(60) NOT NULL,
  senha         CHAR(64)    NOT NULL,
  nome_completo VARCHAR(60) NOT NULL,
  email         VARCHAR(80) NOT NULL,
  ultimo_acesso TIMESTAMP   NOT NULL,
  nova_senha    BOOLEAN     NOT NULL,
  ativo         BOOLEAN     NOT NULL,
  administrador BOOLEAN     NOT NULL,
  CONSTRAINT pk_usuario
    PRIMARY KEY (id_usuario),
  CONSTRAINT uniq_nome
    UNIQUE (nome_usuario)
);


CREATE TABLE material (
  id_material      SERIAL      NOT NULL,
  nome_material    VARCHAR(80) NOT NULL,
  data_criacao     TIMESTAMP   NOT NULL,
  data_atualizacao TIMESTAMP   NOT NULL,
  CONSTRAINT pk_material
    PRIMARY KEY (id_material)
);


CREATE TABLE motorista (
  id_motorista      SERIAL      NOT NULL,
  nome_motorista    VARCHAR(80) NOT NULL,
  apelido_motorista VARCHAR(80) NOT NULL,
  placa_veiculo     VARCHAR(8)  NOT NULL,
  telefone          VARCHAR(15),
  celular           VARCHAR(15),
  observacoes       VARCHAR(400),
  data_criacao      TIMESTAMP   NOT NULL,
  data_atualizacao  TIMESTAMP   NOT NULL,
  CONSTRAINT pk_motorista
    PRIMARY KEY (id_motorista)
);


CREATE TABLE cliente (
  id_cliente        SERIAL      NOT NULL,
  nome_cliente      VARCHAR(80) NOT NULL,
  apelido           VARCHAR(80) NOT NULL,
  telefone          VARCHAR(15),
  celular           VARCHAR(15),
  email             VARCHAR(100),
  contato           VARCHAR(80),
  observacoes       VARCHAR(400),
  data_criacao      TIMESTAMP   NOT NULL,
  data_atualizacao  TIMESTAMP   NOT NULL,
  CONSTRAINT pk_cliente
    PRIMARY KEY (id_cliente)
);


