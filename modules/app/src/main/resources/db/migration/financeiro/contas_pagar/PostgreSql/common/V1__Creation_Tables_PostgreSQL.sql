/**************** WARNING WILL DELETE ALL TABLES *********
DROP TABLE IF EXISTS conta_pagar CASCADE;
DROP TABLE IF EXISTS fornecedor CASCADE;
DROP TABLE IF EXISTS banco CASCADE;
DROP TABLE IF EXISTS agencia_bancaria CASCADE;
DROP TABLE IF EXISTS bandeira_cartao CASCADE;
DROP TABLE IF EXISTS conta_bancaria CASCADE;
DROP TABLE IF EXISTS cartao_credito CASCADE;
DROP TABLE IF EXISTS plano_conta CASCADE;
**********************************************************/

CREATE TABLE conta_pagar /* ContaPagar */  (
	id UUID NOT NULL,
	plano_contas UUID NOT NULL /* planoContas */,
	descricao VARCHAR(255) NOT NULL,
	data_vencimento DATE NOT NULL /* dataVencimento */,
	valor DECIMAL NOT NULL,
	forma_pagamento VARCHAR(255) NOT NULL /* formaPagamento */,
	conta_bancaria UUID /* contaBancaria */,
	cartao_credito UUID /* cartaoCredito */,
	outros_descricao VARCHAR(255) /* outrosDescricao */,
	data_pagamento DATE /* dataPagamento */,
	valor_desconto DECIMAL /* valorDesconto */,
	valor_multa DECIMAL /* valorMulta */,
	valor_juros DECIMAL /* valorJuros */,
	valor_acrescimos DECIMAL /* valorAcrescimos */,
	valor_pago DECIMAL /* valorPago */,
	fornecedor UUID,
	num_documento VARCHAR(255) /* numDocumento */,
	observacoes VARCHAR(1000),
	agrupador VARCHAR(255),
	created_by VARCHAR(255) /* createdBy */,
	created_date TIMESTAMP /* createdDate */,
	last_modified_by VARCHAR(255) /* lastModifiedBy */,
	last_modified_date TIMESTAMP /* lastModifiedDate */
);

CREATE TABLE fornecedor /* Fornecedor */  (
	id UUID NOT NULL,
	tipo_pessoa VARCHAR(255) NOT NULL /* tipoPessoa */,
	nome VARCHAR(255) NOT NULL,
	cnpj_cpf VARCHAR(255) /* cnpjCPF */,
	deleted BOOLEAN DEFAULT false
);

CREATE TABLE banco /* Banco */  (
	id UUID NOT NULL,
	numero VARCHAR(20) NOT NULL,
	nome VARCHAR(255) NOT NULL,
	deleted BOOLEAN DEFAULT false
);

CREATE TABLE agencia_bancaria /* AgenciaBancaria */  (
	id UUID NOT NULL,
	banco UUID NOT NULL,
	numero_agencia VARCHAR(50) NOT NULL /* numeroAgencia */,
	digito_agencia VARCHAR(10) NOT NULL /* digitoAgencia */,
	endereco VARCHAR(255),
	deleted BOOLEAN DEFAULT false
);

CREATE TABLE bandeira_cartao /* BandeiraCartao */  (
	id UUID NOT NULL,
	nome_bandeira VARCHAR(255) NOT NULL /* nomeBandeira */,
	deleted BOOLEAN DEFAULT false
);

CREATE TABLE conta_bancaria /* ContaBancaria */  (
	id UUID NOT NULL,
	nome_titular VARCHAR(255) NOT NULL /* nomeTitular */,
	agencia UUID NOT NULL,
	tipo_conta_bancaria VARCHAR(255) NOT NULL /* tipoContaBancaria */,
	numero_conta VARCHAR(30) NOT NULL /* numeroConta */,
	digito VARCHAR(10),
	data_validade DATE /* dataValidade */,
	ativo BOOLEAN NOT NULL DEFAULT true,
	deleted BOOLEAN DEFAULT false
);

CREATE TABLE cartao_credito /* CartaoCredito */  (
	id UUID NOT NULL,
	banco UUID NOT NULL,
	nome_titular VARCHAR(255) NOT NULL /* nomeTitular */,
	numero_cartao VARCHAR(50) NOT NULL /* numeroCartao */,
	validade DATE,
	valor_limite DECIMAL /* valorLimite */,
	bandeira_cartao UUID NOT NULL /* bandeiraCartao */,
	ativo BOOLEAN NOT NULL DEFAULT true,
	deleted BOOLEAN DEFAULT false
);

CREATE TABLE plano_conta /* PlanoConta */  (
	id UUID NOT NULL,
	codigo VARCHAR(255) NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	tipo_financeiro VARCHAR(255) NOT NULL /* tipoFinanceiro */,
	tipo_receita_despesa VARCHAR(255) /* tipoReceitaDespesa */,
	plano_conta_pai UUID /* planoContaPai */,
	ativo BOOLEAN NOT NULL DEFAULT true,
	deleted BOOLEAN DEFAULT false
);

/* PRIMARY KEYS */
ALTER TABLE conta_pagar ADD CONSTRAINT pk_conta_pagar_id PRIMARY KEY (id);
ALTER TABLE fornecedor ADD CONSTRAINT pk_fornecedor_id PRIMARY KEY (id);
ALTER TABLE banco ADD CONSTRAINT pk_banco_id PRIMARY KEY (id);
ALTER TABLE agencia_bancaria ADD CONSTRAINT pk_agencia_bancaria_id PRIMARY KEY (id);
ALTER TABLE bandeira_cartao ADD CONSTRAINT pk_bandeira_cartao_id PRIMARY KEY (id);
ALTER TABLE conta_bancaria ADD CONSTRAINT pk_conta_bancaria_id PRIMARY KEY (id);
ALTER TABLE cartao_credito ADD CONSTRAINT pk_cartao_credito_id PRIMARY KEY (id);
ALTER TABLE plano_conta ADD CONSTRAINT pk_plano_conta_id PRIMARY KEY (id);

/* FOREIGN KEYS */
ALTER TABLE conta_pagar ADD CONSTRAINT fk_conta_pagar_plano_contas FOREIGN KEY (plano_contas) REFERENCES plano_conta (id);
ALTER TABLE conta_pagar ADD CONSTRAINT fk_conta_pagar_conta_bancaria FOREIGN KEY (conta_bancaria) REFERENCES conta_bancaria (id);
ALTER TABLE conta_pagar ADD CONSTRAINT fk_conta_pagar_cartao_credito FOREIGN KEY (cartao_credito) REFERENCES cartao_credito (id);
ALTER TABLE conta_pagar ADD CONSTRAINT fk_conta_pagar_fornecedor FOREIGN KEY (fornecedor) REFERENCES fornecedor (id);
ALTER TABLE agencia_bancaria ADD CONSTRAINT fk_agencia_bancaria_banco FOREIGN KEY (banco) REFERENCES banco (id);
ALTER TABLE conta_bancaria ADD CONSTRAINT fk_conta_bancaria_agencia FOREIGN KEY (agencia) REFERENCES agencia_bancaria (id);
ALTER TABLE cartao_credito ADD CONSTRAINT fk_cartao_credito_banco FOREIGN KEY (banco) REFERENCES banco (id);
ALTER TABLE cartao_credito ADD CONSTRAINT fk_cartao_credito_bandeira_cartao FOREIGN KEY (bandeira_cartao) REFERENCES bandeira_cartao (id);
ALTER TABLE plano_conta ADD CONSTRAINT fk_plano_conta_plano_conta_pai FOREIGN KEY (plano_conta_pai) REFERENCES plano_conta (id);

