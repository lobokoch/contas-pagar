CREATE TABLE conta_pagar_multiple /* ContaPagarMultiple */  (
	id UUID NOT NULL,
	data_pagamento DATE NOT NULL /* dataPagamento */,
	valor_pago DECIMAL NOT NULL /* valorPago */,
	fornecedor UUID,
	descricao VARCHAR(255) NOT NULL,
	forma_pagamento VARCHAR(255) NOT NULL /* formaPagamento */,
	conta_bancaria UUID /* contaBancaria */,
	cartao_credito UUID /* cartaoCredito */,
	outros_descricao VARCHAR(255) /* outrosDescricao */,
	plano_contas UUID NOT NULL /* planoContas */,
	conta_pagar UUID NOT NULL /* contaPagar */,
	created_by VARCHAR(255) /* createdBy */,
	created_date TIMESTAMP /* createdDate */,
	last_modified_by VARCHAR(255) /* lastModifiedBy */,
	last_modified_date TIMESTAMP /* lastModifiedDate */
);

/* PRIMARY KEYS */
ALTER TABLE conta_pagar_multiple ADD CONSTRAINT pk_conta_pagar_multiple_id PRIMARY KEY (id);

/* FOREIGN KEYS */
ALTER TABLE conta_pagar_multiple ADD CONSTRAINT fk_conta_pagar_multiple_fornecedor FOREIGN KEY (fornecedor) REFERENCES fornecedor (id);
ALTER TABLE conta_pagar_multiple ADD CONSTRAINT fk_conta_pagar_multiple_conta_bancaria FOREIGN KEY (conta_bancaria) REFERENCES conta_bancaria (id);
ALTER TABLE conta_pagar_multiple ADD CONSTRAINT fk_conta_pagar_multiple_cartao_credito FOREIGN KEY (cartao_credito) REFERENCES cartao_credito (id);
ALTER TABLE conta_pagar_multiple ADD CONSTRAINT fk_conta_pagar_multiple_plano_contas FOREIGN KEY (plano_contas) REFERENCES plano_conta (id);
ALTER TABLE conta_pagar_multiple ADD CONSTRAINT fk_conta_pagar_multiple_conta_pagar FOREIGN KEY (conta_pagar) REFERENCES conta_pagar (id);