USE mercadinho;

-- 1. Inserir Clientes de Exemplo
INSERT INTO cliente (nome, email, telefone, cpf_cnpj, endereco) VALUES
('Ana Souza', 'ana.souza@email.com', '(11) 98765-4321', '123.456.789-01', 'Rua das Flores, 100 - São Paulo/SP'),
('Carlos Silva', 'carlos.silva@email.com', '(11) 91234-5678', '987.654.321-09', 'Av. Paulista, 1500 - São Paulo/SP');

-- 2. Inserir Vendedores de Exemplo
INSERT INTO vendedor (nome, email, telefone, percentual_comissao) VALUES
('Marcos Oliveira', 'marcos.vendedor@email.com', '(11) 99888-7766', 5.00),
('Beatriz Lima', 'beatriz.vendedor@email.com', '(11) 97777-6655', 4.50);

-- 3. Inserir Produtos de Exemplo
INSERT INTO produto (nome, descricao, preco, estoque) VALUES
('Notebook Gamer', 'Processador i7, 16GB RAM, SSD 512GB', 4500.00, 10),
('Mouse Sem Fio', 'Mouse óptico ergonômico com receptor USB', 80.00, 50),
('Teclado Mecânico', 'Teclado RGB switch blue', 250.00, 30);

-- 4. Inserir Pedidos de Exemplo
-- (Supondo que os IDs gerados para cliente e vendedor foram 1 e 2)
INSERT INTO pedido (id_cliente, id_vendedor, valor_total, status_pedido) VALUES
(1, 1, 4580.00, 'Aprovado'),
(2, 2, 250.00, 'Pendente');

-- 5. Inserir Itens do Pedido (Relacionamento Pedido <-> Produto)
INSERT INTO item_pedido (id_pedido, id_produto, quantidade, preco_unitario) VALUES
(1, 1, 1, 4500.00), -- 1 Notebook no Pedido 1
(1, 2, 1, 80.00),   -- 1 Mouse no Pedido 1
(2, 3, 1, 250.00);  -- 1 Teclado no Pedido 2

-- 6. Inserir Pagamentos (Para testar o seu CRUD de Pagamento)
INSERT INTO pagamento (id_pedido, valor_pago, forma_pagamento, status_pagamento) VALUES
(1, 4580.00, 'Cartão de Crédito', 'Aprovado'),
(2, 250.00, 'Pix', 'Aguardando');