CREATE TABLE categoria (
  id_categoria serial primary key,
  nome varchar(30) not null unique,
  descricao varchar(200)
);

CREATE TABLE endereco (
  id_endereco serial primary key,
  cep varchar(8) not null,
  rua varchar(80)not null,
  bairro varchar(50) not null,
  cidade  varchar(80) not null,
  numero varchar(20) not null,
  complemento varchar(80),
  uf varchar(2)not null
);
-- CREATE INDEX pk ON  Categoria (id_categoria);

CREATE TABLE cliente (
  id_cliente serial primary key,
  nome_completo varchar(50) not null,
  email varchar(80) not null unique,
  cpf varchar(11) not null unique,
  telefone varchar(40) not null,
  data_nascimento date,
  id_endereco int references endereco(id_endereco)
);

-- CREATE INDEX pk ON  Cliente (id_cliente);

-- CREATE INDEX fk ON  Cliente (id_endereco);


-- -- CREATE INDEX pk ON  Endereco (id_endereco);

CREATE TABLE produto (
  id_produto serial PRIMARY KEY,
  nome varchar(30) not null unique,
  descricao varchar(200),
  qtd_estoque int4,
  data_cadastro date,
  valor_unitario real not null,
  imagem bytea,
  id_categoria int references categoria(id_categoria)
  -- id_categoria int4,
  -- -- CONSTRAINT FK_Produto.id_categoria
  -- --   FOREIGN KEY (id_categoria)
  -- --     REFERENCES categoria(id_categoria)
);

-- -- CREATE INDEX pk ON  Produto (id_produto);

-- -- CREATE INDEX fk ON  Produto (id_categoria);

CREATE TABLE pedido (
  id_pedido serial PRIMARY KEY,
  data_pedido date not null,
  data_entrega date ,
  data_envio date,
  status varchar(1) not null,
  valor_total real not null,
  id_cliente int REFERENCES cliente(id_cliente)
  -- id_cliente  int4 not null,
  -- CONSTRAINT FK_Pedido.id_cliente 
  --   FOREIGN KEY (id_cliente )
  --     REFERENCES Cliente(id_cliente)
);

-- -- CREATE INDEX pk ON  Pedido (id_pedido);

-- -- CREATE INDEX fk ON  Pedido (id_cliente );

CREATE TABLE item_pedido (
  id_item_pedido serial PRIMARY KEY,
  quantidade int4 not null,
  preco_venda real not null,
  percentual_desconto real not null,
  valor_bruto real not null,
  valor_liquido real not null,
  id_produto int REFERENCES produto(id_produto),
  id_pedido int REFERENCES pedido(id_pedido)
  -- id_produto int4 not null,
  -- id_pedido int4 not null,
  -- CONSTRAINT FK_Item_pedido.id_produto
  --   FOREIGN KEY (id_produto)
  --     REFERENCES Produto(id_produto),
  -- CONSTRAINT FK_Item_pedido.id_pedido
  --   FOREIGN KEY (id_pedido)
  --     REFERENCES Pedido(id_pedido)
);

-- CREATE INDEX pk ON  Item_pedido (id_item_pedido);

-- CREATE INDEX fk ON  Item_pedido (id_produto, id_pedido);



