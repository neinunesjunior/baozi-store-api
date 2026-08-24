-- Inserir dados de exemplo APÓS as tabelas serem criadas
INSERT INTO clientes (nome, cliente_desde) VALUES 
('Claudilei5158446', '2026-08-15'),
('Maria Silva', '2026-08-15'),
('Joao Pereira', '2026-08-15');

INSERT INTO produtos (nome, preco, estoque) VALUES 
('Pão de Porco Baozi', 8.50, true),
('Pão de Carne Baozi', 9.50, true),
('Pão de Frango Baozi', 7.50, false),
('Pão Vegetariano Baozi', 6.50, true);

INSERT INTO pedidos (cliente_id, produto_id, quantidade) VALUES 
(1, 1, 12),
(2, 1, 5),
(3, 4, 3);
