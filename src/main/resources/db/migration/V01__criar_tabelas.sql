CREATE TABLE categoria (
   id_categoria serial PRIMARY KEY,
   nome varchar(30) NOT NULL UNIQUE,
   descricao varchar(200)
);

CREATE TABLE produto (
  id_produto serial PRIMARY KEY,
  nome varchar(30) NOT NULL UNIQUE,
  descricao varchar(200),
  qtd_estoque INT,
  data_cadastro DATE,
  valor_unitario REAL NOT NULL,
  imagem oid,
  id_categoria int REFERENCES categoria(id_categoria)
);

CREATE TABLE endereco (
   id_endereco serial PRIMARY KEY,
   cep varchar(8) NOT NULL,
   rua varchar(80) NOT NULL,
   numero varchar(20) NOT NULL,
   complemento varchar(80), 
   bairro varchar(50) NOT NULL,
   cidade varchar(80) NOT NULL,
   uf varchar(2) NOT NULL
);

CREATE TABLE cliente (
  id_cliente serial PRIMARY KEY,
  nome_completo varchar(50) NOT NULL,
  email varchar(80) NOT NULL UNIQUE,
  cpf varchar(11) NOT NULL UNIQUE,
  telefone varchar(40) NOT NULL,
  data_nascimento DATE,
  id_endereco int REFERENCES endereco(id_endereco)
);

CREATE TABLE pedido (
  id_pedido serial PRIMARY KEY,
  data_pedido DATE NOT NULL,
  data_entrega DATE,
  data_envio DATE,
  status varchar(1) NOT NULL,
  valor_total REAL NOT NULL,
  id_cliente int REFERENCES cliente(id_cliente)
);

CREATE TABLE item_pedido (
id_item_pedido serial PRIMARY KEY,
	quantidade INT NOT NULL,
	preco_venda REAL NOT NULL,
	percentual_desconto REAL NOT NULL,
	valor_bruto REAL NOT NULL,
	valor_liquido REAL NOT NULL,
	id_produto int REFERENCES produto(id_produto),
	id_pedido int REFERENCES pedido(id_pedido)
);

CREATE TABLE usuario (
   id serial PRIMARY key,
   nome varchar(60),
   email varchar(60),
   senha varchar(255)
);

CREATE TABLE perfil (
   id_perfil serial PRIMARY KEY,
   nome varchar(40)
);

CREATE TABLE usuario_perfil (
	id_usuario int REFERENCES usuario(id),
	id_perfil int REFERENCES perfil(id_perfil),
	data_criacao date,
	CONSTRAINT pk_usuario_perfil PRIMARY KEY (id_usuario, id_perfil)
);






