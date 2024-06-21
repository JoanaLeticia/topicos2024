-- Inserindo usuários (administradores)
insert into usuario(login, senha, perfil) values('joanaleticia@skinstore.br', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==', 1); -- 123
insert into usuario(login, senha, perfil) values('ronaldocosta@skinstore.br', '2jqHB2Uf9imuz2oRVlzQCEMTCOoHPgbnPCwXCm100JmUzMNhlZFMjcXoeWp9T91TTCruG2sL5JNYRvt6wtw2Ew==', 1); -- 321
insert into usuario(login, senha, perfil) values('luisvictor@skinstore.br', 'tZYRBf5GvY1a50GXJECowW55ttUHZwdYuOL2f34tfdzXOYpGWD5rBr5+4bpeRCUFAhf+vw4CpiFWmF1Bb8FcBw==', 1); -- senha987

-- Inserindo usuários (clientes)
insert into usuario(login, senha, perfil) values('lucassilva@unitins.br', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==', 2); -- 123
insert into usuario(login, senha, perfil) values('carollopes@unitins.br', '2jqHB2Uf9imuz2oRVlzQCEMTCOoHPgbnPCwXCm100JmUzMNhlZFMjcXoeWp9T91TTCruG2sL5JNYRvt6wtw2Ew==', 2); -- 321
insert into usuario(login, senha, perfil) values('nicolasg@unitins.br', 'tZYRBf5GvY1a50GXJECowW55ttUHZwdYuOL2f34tfdzXOYpGWD5rBr5+4bpeRCUFAhf+vw4CpiFWmF1Bb8FcBw==', 2); -- senha987

-- Inserindo pessoas
insert into pessoa(nome, cpf, id_usuario) values('Joana Letícia', '123.456.789-00', 1);
insert into pessoa(nome, cpf, id_usuario) values('Ronaldo Costa', '987.654.321-00', 2);
insert into pessoa(nome, cpf, id_usuario) values('Luis Victor', '324.531.456-93', 3);
insert into pessoa(nome, cpf, id_usuario) values('Lucas Silva', '853.324.654-32', 4);
insert into pessoa(nome, cpf, id_usuario) values('Carol Lopes','231.534.765-12', 5);
insert into pessoa(nome, cpf, id_usuario) values('Nicolás Giacomelli','435.321.765-34', 6);

-- Inserindo telefones
insert into telefone (codigoArea, numero) values ('11', '123456789');
insert into telefone (codigoArea, numero) values ('63', '984039523');
insert into telefone (codigoArea, numero) values ('63', '987654321');

-- Inserindo estados
insert into estado (nome, sigla) values('Tocantins', 'TO');
insert into estado (nome, sigla) values('Maranhão', 'MA');
insert into estado (nome, sigla) values('Goiás', 'GO');

-- Inserindo cidades
insert into cidade (nome, id_estado) values('Palmas', 1);
insert into cidade (nome, id_estado) values('São Luís', 2);
insert into cidade (nome, id_estado) values('Goiania', 3);

-- Inserindo endereços
insert into endereco (logradouro, numero, complemento, bairro, cep, cidade_id)
values('Rua Pereira da Costa', '2351', 'Próximo a distribuidora Camarote', '114 Norte', '74542-321', 1); 
insert into endereco (logradouro, numero, complemento, bairro, cep, cidade_id)
values('Rua Três', '2-106', 'Próximo a praça', 'PiraPora', '65137-000', 2);
insert into endereco (logradouro, numero, complemento, bairro, cep, cidade_id)
values('9ª avenida', '229', 'Próximo a PUC-GO', 'St. Leste Vila Nova', '74643-080', 3);

-- Inserindo administrador
insert into administrador(matricula, id_pessoa) values (3242341, 1);
insert into administrador(matricula, id_pessoa) values (3457654, 2);
insert into administrador(matricula, id_pessoa) values (5643457, 3);

-- Inserindo cliente
insert into cliente (dataNascimento, id_pessoa) values ('2003-05-05', 4);
insert into cliente (dataNascimento, id_pessoa) values ('2000-06-19', 5);
insert into cliente (dataNascimento, id_pessoa) values ('1998-06-23', 6);


-- Inserindo telefone ao cliente
insert into cliente_telefone (id_cliente, id_telefone) values(1, 1);
insert into cliente_telefone (id_cliente, id_telefone) values(2, 2);
insert into cliente_telefone (id_cliente, id_telefone) values(3, 3);

-- Inserindo endereço ao cliente
insert into cliente_endereco (id_cliente, id_endereco) values(1, 1);
insert into cliente_endereco (id_cliente, id_endereco) values(2, 2);
insert into cliente_endereco (id_cliente, id_endereco) values(3, 3);

-- Pix
insert into pix (chavePix) values('luisvictor@hotmail.com');
insert into pix (chavePix) values('11992342311');

-- Boleto Bancario
insert into boletoBancario (banco, numeroBoleto, dataVencimento) 
values('Banco do Brasil', '3543564421', '2024-09-01');
insert into boletoBancario (banco, numeroBoleto, dataVencimento) 
values('Inter', '2132153345', '2026-05-04');
insert into boletoBancario (banco, numeroBoleto, dataVencimento) 
values('Nubank', '3452353453', '2025-02-05');

-- Cartão de Crédito
insert into cartaoCredito (bandeira, numeroCartao, codigoSeguranca, dataVencimento) 
values('Visa', '4543523412432534', '432', '2024-03-08');
insert into cartaoCredito (bandeira, numeroCartao, codigoSeguranca, dataVencimento) 
values('MasterCard', '3243254364534213', '124', '2024-03-08');
insert into cartaoCredito (bandeira, numeroCartao, codigoSeguranca, dataVencimento) 
values('Elo', '35436437547657', '654', '2024-03-08');

-- Inserindo produtos
insert into produto (nome,  linkSteam, valor, quantEstoque, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('AK-47 | Neon Rider', 'https://steamcommunity.com/market/listings/730/AK-47%20%7C%20Neon%20Rider%20%28Battle-Scarred%29', 138.97, 1, 'RIFLE', 'AK47', 'BS', 0.073495, 368, 'PRONTAENTREGA');
insert into produto (nome,  linkSteam, valor, quantEstoque, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('StatTrak™ Glock-18 | Vogue', 'https://steamcommunity.com/market/listings/730/StatTrak%E2%84%A2%20Glock-18%20%7C%20Vogue%20%28Well-Worn%29', 39.35, 1, 'PISTOLA', 'GLOCK', 'WW', 0.0023901, 991, 'TRES');
insert into produto (nome,  linkSteam, valor, quantEstoque, tipo, arma, exterior, numeroFloat, pattern, disponibilidade) values ('★ Bayonet | Doppler', 'https://steamcommunity.com/market/listings/730/%E2%98%85%20Bayonet%20%7C%20Doppler%20%28Factory%20New%29', 4513.71, 1, 'FACA', 'BAIONETA', 'FN', 0.00023133, 761, 'SETE');

-- Inserindo pedidos
insert into pedido (dataHora, valorTotal, id_cliente, id_endereco) values ('2024-06-14 10:00:00', 150.0, 1, 1);

-- Inserindo itens pedidos
insert into itemPedido (valor, quantidade, id_produto, id_pedido) values (138.79, 1, 1, 1);
