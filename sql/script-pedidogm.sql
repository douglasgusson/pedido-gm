CREATE TABLE usuario (
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
  apelido_motorista VARCHAR(80),
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
  apelido           VARCHAR(80),
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


CREATE TABLE pedido (
  id_pedido         SERIAL          NOT NULL,
  id_cliente        INTEGER         NOT NULL,
  valor_pedido      NUMERIC(12,2)   NOT NULL,
  placa_veiculo     VARCHAR(8),
  nome_motorista    VARCHAR(80),
  observacoes       VARCHAR(400),
  data_carregamento DATE            NOT NULL,
  data_criacao      TIMESTAMP       NOT NULL,
  data_atualizacao  TIMESTAMP       NOT NULL,
  id_usuario        INTEGER         NOT NULL, 
  CONSTRAINT pk_pedido
    PRIMARY KEY (id_pedido),
  CONSTRAINT fk_pedido_cliente
    FOREIGN KEY (id_cliente)
    REFERENCES cliente (id_cliente),
  CONSTRAINT fk_pedido_usuario
    FOREIGN KEY (id_usuario)
    REFERENCES usuario (id_usuario)
);


CREATE TABLE tipo_item (
  id_tipo_item       SERIAL      NOT NULL,
  descricao          VARCHAR(40) NOT NULL,
  referencia_calculo INTEGER     NOT NULL,
  data_criacao       TIMESTAMP   NOT NULL,
  data_atualizacao   TIMESTAMP   NOT NULL,
  CONSTRAINT pk_tipo_item
    PRIMARY KEY (id_tipo_item)
);


CREATE TABLE item_pedido (
  id_item_pedido  SERIAL        NOT NULL,
  id_material     INTEGER       NOT NULL,
  id_pedido       INTEGER       NOT NULL,
  id_tipo_item    INTEGER       NOT NULL,
  quantidade      INTEGER       NOT NULL,
  comprimento_br  NUMERIC(3,2)  NOT NULL,
  altura_br       NUMERIC(3,2)  NOT NULL,
  largura_br      NUMERIC(3,2)  NOT NULL,
  comprimento_liq NUMERIC(3,2)  NOT NULL,
  altura_liq      NUMERIC(3,2)  NOT NULL,
  largura_liq     NUMERIC(3,2)  NOT NULL,
  acabamento      VARCHAR(30)   NOT NULL,
  metragem        NUMERIC(12,3) NOT NULL,
  valor_unitario  NUMERIC(12,2) NOT NULL,
  desconto        NUMERIC(12,2) NOT NULL,
  valor_total     NUMERIC(12,2) NOT NULL, 
  CONSTRAINT pk_item_pedido
    PRIMARY KEY (id_item_pedido, id_material, id_pedido),
  CONSTRAINT fk_item_pedido_material
    FOREIGN KEY (id_material)
    REFERENCES material (id_material),
  CONSTRAINT fk_item_pedido_pedido
    FOREIGN KEY (id_pedido)
    REFERENCES pedido (id_pedido),
  CONSTRAINT fk_item_pedido_tipo_item
    FOREIGN KEY (id_tipo_item)
    REFERENCES tipo_item (id_tipo_item)
);
