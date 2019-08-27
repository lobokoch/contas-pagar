-- Registros de inicialização do plano de contas pré-cadastrado.

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('282d5860-aca6-4f23-87b3-d508ab8e8d81','2','PASSIVO','DESPESA',NULL,NULL,true)
,('5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5','3','DESPESAS','DESPESA',NULL,NULL,true)
,('4b657c0d-35c0-4745-b643-61752711822f','2.1','PASSIVO CIRCULANTE','DESPESA',NULL,'282d5860-aca6-4f23-87b3-d508ab8e8d81',true)
,('ed6494a0-032c-4bf8-a0b2-479e286447d0','2.1.1','Impostos e Contribuições a Recolher','DESPESA',NULL,'4b657c0d-35c0-4745-b643-61752711822f',true)
,('9875368b-1d62-4760-bc4b-2aefb4bdb04c','2.1.1.02','INSS','DESPESA',NULL,'ed6494a0-032c-4bf8-a0b2-479e286447d0',true)
,('9493372c-9628-4cff-9488-47b5111abb39','2.1.1.03','FGTS','DESPESA',NULL,'ed6494a0-032c-4bf8-a0b2-479e286447d0',true)
,('e6ba5596-0f5f-4228-a553-a4f456ff84f3','2.1.2','Contas a Pagar','DESPESA',NULL,'4b657c0d-35c0-4745-b643-61752711822f',true)
,('45362ea3-0098-4527-81b3-bf41a40b7419','2.1.2.07','Empréstimos','DESPESA',NULL,'e6ba5596-0f5f-4228-a553-a4f456ff84f3',true)
,('bc20bccc-e5c9-4eaa-ae34-f8ae10da17cc','2.1.2.08','Financiamentos','DESPESA',NULL,'e6ba5596-0f5f-4228-a553-a4f456ff84f3',true)
,('4d810c74-a708-4d2c-a8ec-7a9986b76c9b','2.2','PASSIVO NÃO CIRCULANTE','DESPESA',NULL,'282d5860-aca6-4f23-87b3-d508ab8e8d81',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('ffaa28fa-fd11-4493-941a-da99015237d5','2.2.1','Empréstimos Bancários','DESPESA',NULL,'4d810c74-a708-4d2c-a8ec-7a9986b76c9b',true)
,('0cc44ace-43fb-423f-9db0-ba3560813717','2.1.1.01','Simples a Recolher','DESPESA',NULL,'ed6494a0-032c-4bf8-a0b2-479e286447d0',true)
,('3961b834-605b-4816-8c7e-56178d4d830f','2.3','PATRIMÔNIO LÍQUIDO','DESPESA',NULL,'282d5860-aca6-4f23-87b3-d508ab8e8d81',true)
,('cb0056cf-24e4-42fb-811b-e7be3ff8fc93','2.3.1','Capital Social','DESPESA',NULL,'3961b834-605b-4816-8c7e-56178d4d830f',true)
,('a1471e3f-8603-4c32-a7de-f5a4c0e987ce','2.3.1.01','Capital Social Subscrito','DESPESA',NULL,'cb0056cf-24e4-42fb-811b-e7be3ff8fc93',true)
,('e966a2f3-702a-4395-8e8e-7412111a54c9','2.3.1.02','Capital Social a Integralizar','DESPESA',NULL,'cb0056cf-24e4-42fb-811b-e7be3ff8fc93',true)
,('9886ca57-e030-4f75-af34-6392b975cc42','2.3.2','Reservas','DESPESA',NULL,'3961b834-605b-4816-8c7e-56178d4d830f',true)
,('633ff70f-af39-47b7-818d-69d37ac99fe0','2.3.2.01','Reserva de Capital','DESPESA',NULL,'9886ca57-e030-4f75-af34-6392b975cc42',true)
,('b9ee55e8-f3be-4d30-81b7-f1904625e305','2.3.2.02','Reserva de Lucros','DESPESA',NULL,'9886ca57-e030-4f75-af34-6392b975cc42',true)
,('cf2646c9-38e6-4546-8e07-6a56e73becce','2.3.3','Prejuízos Acumulados','DESPESA',NULL,'3961b834-605b-4816-8c7e-56178d4d830f',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('05b907f0-dd35-4bbb-a6a1-2d49cfb53f45','2.3.3.01','Prejuízos Acumulados de Exercícios Anteriores','DESPESA',NULL,'cf2646c9-38e6-4546-8e07-6a56e73becce',true)
,('7049c3a4-781b-4128-b0da-51a1fb0dc559','2.3.3.02','Prejuízos do Exercício Atual','DESPESA',NULL,'cf2646c9-38e6-4546-8e07-6a56e73becce',true)
,('a349a8ca-2baa-412d-ab11-b0a52d5ab602','3.1','DESPESAS OPERACIONAIS','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true)
,('24a4acf9-815d-4098-a34e-1ccfd4545265','3.1.1','Despesas Com Vendas','DESPESA',NULL,'a349a8ca-2baa-412d-ab11-b0a52d5ab602',true)
,('27f047af-9506-45af-a2f0-745b2d099030','3.1.1.01','Comissão Sobre Vendas','DESPESA',NULL,'24a4acf9-815d-4098-a34e-1ccfd4545265',true)
,('c78076a5-d21d-4c75-b9a7-e7974c51256f','3.1.1.02','Fretes','DESPESA',NULL,'24a4acf9-815d-4098-a34e-1ccfd4545265',true)
,('fde1bf29-bc19-470c-8760-958699263fae','3.1.1.03','Material de Embalagem','DESPESA',NULL,'24a4acf9-815d-4098-a34e-1ccfd4545265',true)
,('bf1025bb-6875-49d5-b9f4-66987241521c','3.1.1.04','Publicidade e Propaganda','DESPESA',NULL,'24a4acf9-815d-4098-a34e-1ccfd4545265',true)
,('a0ee2334-a4c9-47d6-95e1-f449416e7b5e','3.1.2','Despesas Administrativas','DESPESA',NULL,'a349a8ca-2baa-412d-ab11-b0a52d5ab602',true)
,('bf53e9ff-f7f4-46e8-9deb-fee0de4c84aa','3.1.2.01','Aluguel','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('0a51cddb-3f96-462b-b754-90b407c204e9','3.1.2.02','Energia Elétrica','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('7b59c68b-835f-487e-85fa-9dc56ea89750','3.1.2.03','Água e Esgoto','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('985194a7-3575-4df4-b566-d56ea6137813','3.1.2.04','Internet','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('ca93c04d-ebcb-414c-9ba6-5214c2a53a94','3.1.2.05','Salários','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('e418fdb0-d2a4-4de8-9cc4-8f5a9667eba4','3.1.2.06','Telefonia Fixa','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('741f9e93-6388-42e4-9ee9-25098108ae96','3.1.2.07','Telefonia Móvel','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('63264b8a-a99c-45b8-a82a-656b4d49f2db','3.1.2.08','Impostos e Taxas','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('6b81c32a-af18-45fc-b969-3457ca15d01c','3.1.2.09','Material de Expediente','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('c0606a93-bd5a-4273-ae41-011591c7092d','3.1.2.10','Férias','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('774428f1-b0ee-4680-8590-9b9e6b2a443f','3.1.2.11','Décimo Terceiro Salário','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('3a83d411-0d27-4e9c-8d63-994d3bf7f42a','3.1.2.12','Encargos Sociais','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('cb9ba534-2f46-412a-baf9-4e94a9ad3986','3.1.3','Despesas Financeiras','DESPESA',NULL,'a349a8ca-2baa-412d-ab11-b0a52d5ab602',true)
,('3b48de74-4f0a-4fff-8d6c-70203e0660ee','3.1.3.01','Despesas Bancárias','DESPESA',NULL,'cb9ba534-2f46-412a-baf9-4e94a9ad3986',true)
,('cf329499-3256-4b6a-abf6-4e6ae2adaa33','3.1.2.13','Alimentação','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('c2acc4a3-c54a-4f30-a0ee-b9b4b2a129c5','3.1.2.14','Material de Limpeza','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('8f025ca3-c51a-432c-b0cc-5e834b1311e4','3.1.2.15','Plano de Saúde','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('b77122e7-af12-4eba-8762-aa6e59e3cd1f','3.1.2.16','Plano Odontológico','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('773c3e76-3a10-4933-86fd-5376679c0599','3.1.2.17','Vales','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('1992f25b-1bc9-4e71-970a-d16ff64dcfb4','3.1.2.18','Despesas Diversas','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
,('735cbc91-5d52-48cc-9296-23c431993833','3.1.2.19','Manutenções','DESPESA',NULL,'a0ee2334-a4c9-47d6-95e1-f449416e7b5e',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;
