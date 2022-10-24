INSERT INTO categoria (nome, descricao)
VALUES
('INFORMATICA','produtos de informática'),
('ESCRITORIO','cadeiras, mesas, e materiais de escritório'),
('LIVRARIA','livros, revistas, quadrinhos'),
('CARTAS RPG','Pokemon, D&D, Magic');

INSERT INTO produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria, imagem)
VALUES
('Cadeira bx9', 'Cadeira ergonomica confortavel',3,'2019-10-01',850.00,3,null),
('Badesh Branca','Escrivaninha para computador',4,'2019-10-01',480.00,3,null),
('Do Inferno', 'Quadrinho do Alan More',2,'2019-10-01',150.00,2,null),
('Use a Cabeca Java','Livro principal para Java',10,'2019-10-01',75.00,2,null),
('Tablet Samsung','Tablet 10 polegadas',2,'2019-10-01',3850.00,1,null),
('Mouse Logitec','Mouse com 3 botoes e uma rodinha',2,'2019-10-01',50.00,3,null),
('Fita Crepe','Fita crepe simples',33,'2019-10-01',1.30,1,null),
('Mousepad','Mousepad estilizado com foto',13,'2019-10-01',25.00,1,null),
('A Batalha do Apocalipse', 'Melhor livro que voce deve ler',55,'2019-10-01',42.00,2,null);

INSERT INTO endereco (cep,rua,numero,bairro,cidade,complemento,uf)
VALUES
('29190380','Avenida Florestal','549','Jardins','Aracruz','','ES'),
('77808684','Rua Brusque','940','Residencial Camargo', 'Araguaína','','TO'),
('96420260','Rua Cinqüenta e Sete','575','Esplanada', 'Bagé','','RS'),
('35164072','Rua Lausanne','861','SinBethânia','Ipatinga','','MG'),
('70673510','Quadra SQSW','626','Setor Sudoeste','Brasília','Bloco J','AL');

INSERT INTO cliente (nome_completo,cpf,email,telefone,data_nascimento,id_endereco)
VALUES
('Ester Peixoto','60530582031','ester@email.com','551234456','1992-02-01',1),
('Cauê Victor Souza','59456286037','caue@email.com','551234456','1993-04-06',2),
('Catarina Aragão','31804568090','catarina@email.com','551234458','1991-08-13',3),
('Gustavo Teixeira','58005641087','gustavo@email.com','551234459','2002-03-21',4),
('Emanuelly Rosa','77352233075','rosa@email.com','551234450','2001-07-11',5);


INSERT INTO pedido (data_pedido, data_entrega, data_envio, status, valor_total, id_cliente)
VALUES
('2020-01-01', '2020-01-02', '2020-02-03','3' ,850.00, 1);

INSERT INTO item_pedido (quantidade, preco_venda, percentual_desconto, valor_bruto, valor_liquido, id_produto, id_pedido)
VALUES
(1, 850.00, 0, 850.00, 850.00, 1, 1);

INSERT INTO perfil (nome) VALUES
('ADMIN'),
('USER');
 
INSERT INTO usuario (nome, email, senha) VALUES
('Maria Joao da Silva', 'mariajoao@email.com', '$2a$12$sPPV9up/RlaZGUBA1AU7ju66f4o.eNSGhhCaWUdr4rnvDZ.QjaMtK'),
('Jose Maria Santos', 'josemaria@email.com', '$2a$12$G7ibc/sJRL0BWCpVCBcRxudHZ2aV8uHbMhHbu/Y6Zpz3Dw1X4.B2S');

INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES
( (SELECT id FROM usuario WHERE email='mariajoao@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='ADMIN') ),
( (SELECT id FROM usuario WHERE email='mariajoao@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='USER') ),
( (SELECT id FROM usuario WHERE email='josemaria@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='USER') );

