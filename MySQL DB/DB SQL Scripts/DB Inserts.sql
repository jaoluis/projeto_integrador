/*FORNECEDOR*/
insert into Fornecedor (nome_fornecedor, cnpj_fornecedor) values ('Rodrigo de Assunção', '49.475.142/0001-02');
insert into Fornecedor (nome_fornecedor, cnpj_fornecedor) values ('Irineu da Silva', '80.422.773/0001-03');
insert into Fornecedor (nome_fornecedor, cnpj_fornecedor) values ('Giovanna Mendes', '10.923.150/0001-20');
insert into Fornecedor (nome_fornecedor, cnpj_fornecedor) values ('Louças & Cerâmicas Silva', '71.985.765/0001-76');
insert into Fornecedor (nome_fornecedor, cnpj_fornecedor) values ('Jabuti Cerâmicas', '71.985.765/0001-76');

/*ENDEREÇO FORNECEDOR*/
insert into Fornecedor_Endereco values (1, 'João Pessoa', 'Santo Augusto', 'Vereador José Gomes', 564, 1);
insert into Fornecedor_Endereco values (2, 'Blumenau', 'Vila Nova', 'Liberdade', 224, 2);
insert into Fornecedor_Endereco values (3, 'Gaspar', 'Gaspar Alto', 'Romero Batista', 6969, 3);
insert into Fornecedor_Endereco values (4, 'João Pessoa', 'Laranjeira', 'Prefeito Fernando Mezadri', 420, 4);
insert into Fornecedor_Endereco values (5, 'Navegantes', 'Pedreira', '200-3', 253, 5);

/*CONTATO FORNECEDOR*/
insert into Fornecedor_Contato values (1, 'rodrigo.assuncao2006@email.com', '(47)99785-2345', 1);
insert into Fornecedor_Contato values (2, 'irineusilva.contato@email.com', '(41)99833-3415',2);
insert into Fornecedor_Contato values (3, 'GiovanaMendes@rotmail.com', '(47)94886-3266',3);
insert into Fornecedor_Contato values (4, 'loucas.ceramicas.silva.contato@email.com', '(47) 9865-1253',4);
insert into Fornecedor_Contato values (5, 'jabuti.ceramicas.contato@email.com', '(47) 99896-2356', 5);


/*TABELA HISTÓRICO PRODUTO*/
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Garfo Inox', 50, 'Inox', '15x4x2');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Cinzeiro de Vidro', 13, 'Vidro', '10x10x6');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Kit 10 Xícaras Comemorativas Natal', 10, 'Porcelana', '7x7x6');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Jogo de Pratos', 18, 'Vidro', '22x22x3');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Escorredor', 15, 'Inox', '45x25x20');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Concha', 50, 'Inox', '25x8x8');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Petisqueira', 25, 'Madeira', '25x30x4');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Bule', 25, 'Porcelana', '30x20x26');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Jogo Talheres', 50, 'Inox', '15x4x2');
insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto) values ('Saleiro', 2, 'Vidro', '5x5x10');

/*TABELA PRODUTO*/
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Garfo Inox', 50, 'Inox', '15x4x2', 1);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Cinzeiro de Vidro', 13, 'Vidro', '10x10x6', 2);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Kit 10 Xícaras Comemorativas Natal', 10, 'Porcelana', '7x7x6', 3);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Jogo de Pratos', 18, 'Vidro', '22x22x3', 4);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Escorredor', 15, 'Inox', '45x25x20', 5);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Concha', 50, 'Inox', '25x8x8', 6);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Petisqueira', 25, 'Madeira', '25x30x4', 7);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Bule', 25, 'Porcelana', '30x20x26', 8);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Jogo Talheres', 50, 'Inox', '15x4x2', 9);
insert into Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto) values ('Saleiro', 2, 'Vidro', '5x5x10', 10);

/*PRECO PRODUTO*/
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (12.00, 5.00, '2022-06-16',1);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (35.00, 15.00, '2022-07-25', 2);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (85.00, 35.00, '2022-04-30', 3);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (100.00, 50.00, '2022-06-07', 4);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (55.00, 25.00, '2022-06-30', 5);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (15.00, 7.00, '2022-06-16', 6);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (35.00, 15.00, '2022-06-30', 7);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (135.00, 55.00, '2022-07-25', 8);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (87.00, 45.00, '2022-06-16', 9);
insert into Preco (preco_venda, preco_custo, data_alteracao, fk_id_historico_produto_preco) values (35.00, 13.00, '2022-06-07', 10);

/*CONTROLE ENTRADA*/
insert into Controle_Entrada values (1, '2022-06-11', 60, 1);
insert into Controle_Entrada values (2, '2022-07-20', 20, 2);
insert into Controle_Entrada values (3, '2022-04-25', 15, 3);
insert into Controle_Entrada values (4, '2022-06-02', 20, 4);
insert into Controle_Entrada values (5, '2022-04-25', 15, 5);
insert into Controle_Entrada values (6, '2022-06-11', 60, 6);
insert into Controle_Entrada values (7, '2022-04-25', 30, 7);
insert into Controle_Entrada values (8, '2022-07-20', 15, 8);
insert into Controle_Entrada values (9, '2022-06-11', 60, 9);
insert into Controle_Entrada values (10,'2022-06-02', 20, 10);

/*PRODUTO DE VENDA*/

/*RELACAO PRODUTO E VENDA*/

/*VENDA*/

/*TABELA USUARIO*/
insert into Usuario (cargo_usuario, email_usuario, senha_usuario, cpf_usuario, nome_usuario, nascimento_usuario) values ('vendedor', 'vendedor1@email.com', 'senha1234', '451.073.880-35', 'Rodrigo Pereira', '1996-09-26');
insert into Usuario (cargo_usuario, email_usuario, senha_usuario, cpf_usuario, nome_usuario, nascimento_usuario) values ('vendedor', 'vendedor2@email.com', 'senha1234', '229.100.990-75', 'Marcela Vieira', '1991-08-31');
insert into Usuario (cargo_usuario, email_usuario, senha_usuario, cpf_usuario, nome_usuario, nascimento_usuario) values ('administrador', 'admin1@email.com', 'senha1234', '700.022.730-39', 'Roberta Morais', '1992-03-05');

/*USUARIO CONTATO*/
insert into Usuario_Contato values (1, 'vendedor1@email', '(47) 93325-8945', 1);
insert into Usuario_Contato values (2, 'vendedor2@email', '(47) 94231-0073', 2);
insert into Usuario_Contato values (3, 'admin1@email', '(47) 95684-1036', 3);

/*USUARIO ENDERECO*/
insert into Usuario_Endereco values (1, 'SC', 'Brusque', 'Braço Elza', 'Teixo Babtista', 890, 1);
insert into Usuario_Endereco values (2, 'SC', 'Brusque', 'Laranjeiras', 'Rua dos Loucos', 969, 2);
insert into Usuario_Endereco values (3, 'SC', 'Brusque', 'Braço Elza', 'Teixo Babtista', 890, 3);

/*RELACAO FORNECEDOR PRODUTO*/
insert into Relacao_Fornecedor_Produto values (1,4);
insert into Relacao_Fornecedor_Produto values (2,3);
insert into Relacao_Fornecedor_Produto values (3,5);
insert into Relacao_Fornecedor_Produto values (4,1);
insert into Relacao_Fornecedor_Produto values (5,2);
insert into Relacao_Fornecedor_Produto values (6,4);
insert into Relacao_Fornecedor_Produto values (7,2);
insert into Relacao_Fornecedor_Produto values (8,3);
insert into Relacao_Fornecedor_Produto values (9,4);
insert into Relacao_Fornecedor_Produto values (10,1);
