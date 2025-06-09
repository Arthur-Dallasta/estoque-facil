-- Criar o banco de dados
CREATE DATABASE estoque_facil;

-- Criar tabela de usuários
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    nivel_acesso VARCHAR(20) NOT NULL,
    ativo BOOLEAN DEFAULT true,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar tabela de produtos
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    quantidade INTEGER NOT NULL,
    categoria VARCHAR(50),
    ativo BOOLEAN DEFAULT true,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar tabela de movimentações
CREATE TABLE IF NOT EXISTS movimentacao (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(10) NOT NULL, -- 'ENTRADA' ou 'SAIDA'
    produto_id INTEGER NOT NULL REFERENCES produto(id),
    usuario_id INTEGER NOT NULL REFERENCES usuario(id),
    quantidade INTEGER NOT NULL,
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT
);

-- Inserir usuário administrador padrão
INSERT INTO usuario (nome, email, senha, telefone, nivel_acesso, ativo)
VALUES ('Administrador', 'admin@estoquefacil.com', 'admin123', '1234567890', 'ADMIN', true);

-- Inserir alguns produtos de exemplo
INSERT INTO produto (nome, descricao, preco, quantidade, categoria, ativo)
VALUES 
    ('Arroz 5kg', 'Arroz tipo 1', 25.90, 100, 'Alimentos', true),
    ('Feijão 1kg', 'Feijão carioca', 8.90, 150, 'Alimentos', true),
    ('Óleo de Soja 900ml', 'Óleo de soja refinado', 12.90, 80, 'Alimentos', true),
    ('Café 500g', 'Café em pó', 15.90, 120, 'Alimentos', true),
    ('Leite 1L', 'Leite integral', 4.90, 200, 'Laticínios', true);

-- Criar índices para melhorar a performance
CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_produto_nome ON produto(nome);
CREATE INDEX idx_produto_categoria ON produto(categoria); 