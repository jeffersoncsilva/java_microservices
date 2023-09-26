create schema if not exists users;

create table users.endereco(
    id bigserial primary key,
    cep varchar(8) not null,
    rua varchar(100) not null,
    numero varchar(10) not null,
    complemento varchar(100),
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    estado varchar(2) not null
);

create table users.user(
    id bigserial primary key,
    nome varchar(100) not null,
    senha varchar(256) not null,
    cpf varchar(14) not null unique,
    email varchar(100) not null unique,
    telefone varchar(15) not null,
    endereco bigint references users.endereco(id),
    data_cadastro timestamp not null
);