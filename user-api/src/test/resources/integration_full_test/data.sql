delete from users.user where 1 = 1;
delete from users.endereco where 1 = 1;

insert into users.endereco(id, cep, rua, numero, complemento, bairro, cidade, estado) values
(1, '00000000', 'Rua Teste', '123', 'Apto 123', 'Centro', 'São Paulo', 'SP');
insert into users.endereco(id, cep, rua, numero, complemento, bairro, cidade, estado) values
(2, '11111111', 'Rua Teste 2', '456', 'Apto 456', 'Centro', 'São Paulo', 'SP');
insert into users.endereco(id, cep, rua, numero, complemento, bairro, cidade, estado) values
(3, '22222222', 'Rua Teste 3', '789', 'Apto 789', 'Centro', 'São Paulo', 'SP');


insert into users.user(id, nome, senha, cpf, email, telefone, endereco, data_cadastro) values
(1, 'Teste', '123456', '267.380.310-68', 'email@email.com', '(11) 1111-1111', 1, '2021-01-01 00:00');

insert into users.user(id, nome, senha, cpf, email, telefone, endereco, data_cadastro) values
(2, 'Teste 2', '1234567', '560.213.750-50', 'email2@email.com', '(11) 1111-1111', 2, '2021-01-01 00:00');

insert into users.user(id, nome, senha, cpf, email, telefone, endereco, data_cadastro) values
(3, 'Teste 3', '12345678', '480.329.540-90', 'email3@email.com', '(11) 1111-1111', 1, '2021-01-01 00:00');

insert into users.user(id, nome, senha, cpf, email, telefone, endereco, data_cadastro) values
(4, 'Teste 4', '12345678', '963.035.030-04', 'email4@email.com', '(11) 1111-1111', 3, '2021-01-01 00:00');