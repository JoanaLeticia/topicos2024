-- Inserindo usuários
insert into usuario(login, senha) values('joanaleticia@unitins.br', '123456');
insert into usuario(login, senha) values('ronaldocosta@unitins.br', '543421');
insert into usuario(login, senha) values('luisvictor@unitins.br', '676594');
insert into usuario(login, senha) values('lucassilva@unitins.br', '12485382');
insert into usuario(login, senha) values('carollopes@unitins.br', '43028403');
insert into usuario(login, senha) values('nicolasgiacomelli@unitins.br', '203192');

-- Inserindo pessoas
insert into pessoa(nome, id_usuario) values('Joana Letícia', 1);
insert into pessoa(nome, id_usuario) values('Ronaldo Costa', 2);
insert into pessoa(nome, id_usuario) values('Luis Victor', 3);
insert into pessoa(nome, id_usuario) values('Lucas Silva', 4);
insert into pessoa(nome, id_usuario) values('Carol Lopes', 5);
insert into pessoa(nome, id_usuario) values('Nicolás Giacomelli', 6);

-- Inserindo administrador
insert into administrador(inscricao, id_pessoa) values (3242341, 1);
insert into administrador(inscricao, id_pessoa) values (3457654, 2);
insert into administrador(inscricao, id_pessoa) values (5643457, 3);

insert into estado (nome, sigla) values('Tocantins', 'TO');
insert into estado (nome, sigla) values('Maranhão', 'MA');
insert into estado (nome, sigla) values('Goiás', 'GO');

insert into cidade (nome, id_estado) values('Palmas', 1);
insert into cidade (nome, id_estado) values('São Luís', 2);
insert into cidade (nome, id_estado) values('Goiania', 3);

insert into cliente (cpf, id_pessoa) values ('219038121323', 4);
insert into cliente (cpf, id_pessoa) values ('1298031912', 5);
insert into cliente (cpf, id_pessoa) values ('92138029143', 6);

insert into produto (nome,  linkSteam, valor, quantEstoque, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('AK-47 | Neon Rider', 'https://steamcommunity.com/market/listings/730/AK-47%20%7C%20Neon%20Rider%20%28Battle-Scarred%29', 138.97, 1, 'RIFLE', 'AK47', 'BS', 0.073495, 368, 'PRONTAENTREGA');
insert into produto (nome,  linkSteam, valor, quantEstoque, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('StatTrak™ Glock-18 | Vogue', 'https://steamcommunity.com/market/listings/730/StatTrak%E2%84%A2%20Glock-18%20%7C%20Vogue%20%28Well-Worn%29', 39.35, 1, 'PISTOLA', 'GLOCK', 'WW', 0.0023901, 991, 'TRES');
insert into produto (nome,  linkSteam, valor, quantEstoque, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('★ Bayonet | Doppler', 'https://steamcommunity.com/market/listings/730/%E2%98%85%20Bayonet%20%7C%20Doppler%20%28Factory%20New%29', 4513.71, 1, 'FACA', 'BAIONETA', 'FN', 0.00023133, 761, 'SETE');