insert into administrador(id, nome, login, senha, inscricao) values (1, 'Joana Letícia', 'joanaleticia@unitins.br', '123456', 3242341);
insert into administrador(id, nome, login, senha, inscricao) values (2, 'Ronaldo Costa', 'ronaldocosta@unitins.br', '543421', 3457654);
insert into administrador(id, nome, login, senha, inscricao) values (3, 'Luis Victor', 'luisvictor@unitins.br', '676594', 5643457);

insert into estado (nome, sigla) values('Tocantins', 'TO');
insert into estado (nome, sigla) values('Maranhão', 'MA');
insert into estado (nome, sigla) values('Goiás', 'GO');

insert into cidade (nome, id_estado) values('Palmas', 1);
insert into cidade (nome, id_estado) values('São Luís', 2);
insert into cidade (nome, id_estado) values('Goiania', 3);

insert into cliente (id, nome, login, senha, cpf) values (4, 'Lucas Silva', 'lucassilva@unitins.br', '12485382', '219038121323');
insert into cliente (id, nome, login, senha, cpf) values (5, 'Carol Lopes', 'carollopes@unitins.br', '43028403', '1298031912');
insert into cliente (id, nome, login, senha, cpf) values (6, 'Nicolás Giacomelli', 'nicolasgiacomelli@unitins.br', '203192', '92138029143');

insert into produto (nome,  linkSteam, valor, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('AK-47 | Neon Rider', 'https://steamcommunity.com/market/listings/730/AK-47%20%7C%20Neon%20Rider%20%28Battle-Scarred%29', 138.97, 'RIFLE', 'AK47', 'BS', 0.073495, 368, 'PRONTAENTREGA');
insert into produto (nome,  linkSteam, valor, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('StatTrak™ Glock-18 | Vogue', 'https://steamcommunity.com/market/listings/730/StatTrak%E2%84%A2%20Glock-18%20%7C%20Vogue%20%28Well-Worn%29', 39.35, 'PISTOLA', 'GLOCK', 'WW', 0.0023901, 991, 'TRES');
insert into produto (nome,  linkSteam, valor, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('★ Bayonet | Doppler', 'https://steamcommunity.com/market/listings/730/%E2%98%85%20Bayonet%20%7C%20Doppler%20%28Factory%20New%29', 4513.71, 'FACA', 'BAIONETA', 'FN', 0.00023133, 761, 'SETE');