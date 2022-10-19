CREATE TABLE Categoria (
  id_categoria serial,
  nome varchar(30) not null unique,
  descricao varchar(200)
);


