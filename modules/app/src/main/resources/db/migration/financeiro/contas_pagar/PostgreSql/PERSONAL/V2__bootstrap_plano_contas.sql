INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5','1','DESPESAS','DESPESA',NULL,NULL,true,false)
,('4d405422-53e1-48ad-9335-f57cece3563a','1.1','HABITAÇÃO','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('e80d6a4b-75cc-4155-9463-b71ac3e65600','1.1.1','Aluguel','DESPESA','FIXO','4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('773a2908-3a4d-4626-ad20-307039bc8e64','1.1.2','Condomínio','DESPESA','FIXO','4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('f3561fe4-53ea-4949-8bf3-47e73320ab3b','1.1.3','Financiamento','DESPESA','FIXO','4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('4de857e2-f964-4d60-ac9f-2554d9d28bab','1.1.4','Seguro','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('b5e086fb-7def-4c31-9caf-759d040cc65a','1.1.5','Luz','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('85508fd5-5243-458d-bf69-1157e0026d1e','1.1.6','Água','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('0769679a-c149-4cde-984d-a3d4c2fc35ba','1.1.7','Gás','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('625cabca-8296-48bd-8cfa-4b5d054ab5c3','1.1.8','IPTU','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('20455e6c-b917-460d-b957-a94a8d68e871','1.1.9','Manutenção','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('05ed638b-770f-4fed-b553-c1ae6161fa9e','1.1.10','Construção','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('9a97b0be-d78c-4e3b-9463-6d453ac347bc','1.1.11','Reforma','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('f4048192-acd9-45e6-b4b3-1cb30f1ba4ca','1.1.12','Telefone Fixo','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('0d4b36ff-44e7-4e11-8b27-c705ea7c7d80','1.1.13','Internet','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('ff82282a-3f6a-4772-a921-4e96415002ee','1.1.14','Netflix','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('e3762012-e618-4d3b-8185-d00f33f06999','1.1.15','Assinatura TV','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('58f3021f-b45d-4df0-8f51-0fb47ddf0d99','1.2','ALIMENTAÇÃO','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('85b1cc1d-e808-48d5-bd1b-84702d11d0c0','1.1.16','Diversos','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('055c85a0-3c8e-47b2-8eed-10a1640ea868','1.2.1','Supermercado','DESPESA',NULL,'58f3021f-b45d-4df0-8f51-0fb47ddf0d99',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('98e6eea9-4d99-4c8a-96f4-4e9bf22fb78b','1.2.2','Refeição','DESPESA',NULL,'58f3021f-b45d-4df0-8f51-0fb47ddf0d99',true,false)
,('58997062-6666-4eec-b262-5434d2eec1db','1.2.3','Padaria','DESPESA',NULL,'58f3021f-b45d-4df0-8f51-0fb47ddf0d99',true,false)
,('0817c1d7-a89d-451e-a8e5-f5da9f0379ed','1.2.4','Açougue','DESPESA',NULL,'58f3021f-b45d-4df0-8f51-0fb47ddf0d99',true,false)
,('f56eb5ec-44c2-4500-b5c9-f04d66441c40','1.2.5','Verdureira','DESPESA',NULL,'58f3021f-b45d-4df0-8f51-0fb47ddf0d99',true,false)
,('be26fe80-4785-4c57-b7a8-7a3002b55e49','1.2.6','Peixaria','DESPESA',NULL,'58f3021f-b45d-4df0-8f51-0fb47ddf0d99',true,false)
,('a00efd87-2ed0-455a-98d7-7b61681fcbd7','1.2.7','Diversos','DESPESA',NULL,'58f3021f-b45d-4df0-8f51-0fb47ddf0d99',true,false)
,('b2389da7-e107-455a-a654-e27ce5b91150','1.3','SAÚDE','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('b3d4d38a-f459-4ab5-8902-cde0f70332c5','1.3.1','Plano de Saúde','DESPESA',NULL,'b2389da7-e107-455a-a654-e27ce5b91150',true,false)
,('c5a03f56-31d1-4728-ad96-059d6b774254','1.3.2','Farmácia','DESPESA',NULL,'b2389da7-e107-455a-a654-e27ce5b91150',true,false)
,('f169397b-4834-4c99-8ec5-19e23432ae6e','1.3.3','Hospital','DESPESA',NULL,'b2389da7-e107-455a-a654-e27ce5b91150',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('8f4eb4c8-ac86-4b56-b434-b109abb2857e','1.3.4','Seguro de Vida','DESPESA',NULL,'b2389da7-e107-455a-a654-e27ce5b91150',true,false)
,('040ee4a4-baa5-4c06-866e-17448a108768','1.3.5','Acessórios','DESPESA',NULL,'b2389da7-e107-455a-a654-e27ce5b91150',true,false)
,('4f42bdec-50f6-4c7a-abaf-656c7cf32a71','1.4','AUTOMÓVEIS','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('c66c7f4e-7e96-4576-84e4-93c53e427191','1.4.1','Compra','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('ce400245-b602-4954-b9d5-f378fe604646','1.4.2','Combustível','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('387e243e-5f25-433f-af06-deb65ec2f1de','1.4.3','Estacionamento','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('b9fb9b51-8e80-4e02-87a4-3a914eb60a66','1.4.4','Revisão','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('307aa33d-a879-462f-8f2a-87f424943847','1.4.5','Mecânica','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('b8856dac-78a9-45f2-b3dc-51e91504c232','1.4.6','IPVA','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('939ebe91-6448-4f61-9b0a-b39983164f61','1.4.7','Multa','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('81dde4f7-6954-4569-8b7e-996e8f9bd179','1.4.8','Seguro','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('b6020e13-80e6-41bf-a0f2-f86ba28b1f36','1.4.9','Pedágio','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('169bb575-c3f6-452c-ac7c-a2383372ec0e','1.4.10','Diversos','DESPESA',NULL,'4f42bdec-50f6-4c7a-abaf-656c7cf32a71',true,false)
,('591c77cd-de73-4a05-967c-1fd6302fddbd','1.5','CELULARES','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('8450ba34-592f-436d-8255-2501613b2a02','1.5.1','Mensalidade','DESPESA',NULL,'591c77cd-de73-4a05-967c-1fd6302fddbd',true,false)
,('6d324956-5432-4e99-b8e1-cb0fab1995e7','1.5.2','Manutenção','DESPESA',NULL,'591c77cd-de73-4a05-967c-1fd6302fddbd',true,false)
,('013e8f83-db0a-468f-bccc-f277ca121db6','1.5.3','Acessórios','DESPESA',NULL,'591c77cd-de73-4a05-967c-1fd6302fddbd',true,false)
,('20f9782b-cb30-49e2-ab99-68aa480aa311','1.5.4','Diversos','DESPESA',NULL,'591c77cd-de73-4a05-967c-1fd6302fddbd',true,false)
,('6dfc8963-d84a-4a37-bc81-06289c773105','1.6','INVESTIMENTOS','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('a8100167-2b91-4781-9c28-a7e764b70810','1.6.1','Poupança','DESPESA',NULL,'6dfc8963-d84a-4a37-bc81-06289c773105',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('ed59f26e-28ee-4df0-a769-cf810b534487','1.6.2','Previdência Privada','DESPESA',NULL,'6dfc8963-d84a-4a37-bc81-06289c773105',true,false)
,('ca590814-8576-47fc-917c-0db012773560','1.6.3','Tesouro Direto','DESPESA',NULL,'6dfc8963-d84a-4a37-bc81-06289c773105',true,false)
,('fbc135be-b11d-4de2-a1af-f07e147586ca','1.6.4','CDB','DESPESA',NULL,'6dfc8963-d84a-4a37-bc81-06289c773105',true,false)
,('6f933e80-073b-4a59-98bc-4adb3d0c76ec','1.6.5','Bolsa de Ações','DESPESA',NULL,'6dfc8963-d84a-4a37-bc81-06289c773105',true,false)
,('df5003ac-4a2e-4b02-810d-c76ad039d940','1.6.6','Diversos','DESPESA',NULL,'6dfc8963-d84a-4a37-bc81-06289c773105',true,false)
,('5337768e-f7e8-423a-b0dd-4f47c32b8db6','1.7','BANCO','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('c288dd54-db53-4794-b5bc-d5a2e81d36ae','1.7.4','Taxas','DESPESA',NULL,'5337768e-f7e8-423a-b0dd-4f47c32b8db6',true,false)
,('d0d0c60b-d23c-4f44-a22b-b14a90f5ca15','1.7.5','Tarifas','DESPESA',NULL,'5337768e-f7e8-423a-b0dd-4f47c32b8db6',true,false)
,('44ceb55d-2cd4-4f5d-80fb-b84fd6b9535d','1.7.3','Financiamento','DESPESA',NULL,'5337768e-f7e8-423a-b0dd-4f47c32b8db6',true,false)
,('4f34b5bb-e0bf-40a8-b434-5f91cea88039','1.8','EDUCAÇÃO','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('79913e3e-ed71-49b2-bac1-50bb579bdfb3','1.8.1','Escola','DESPESA',NULL,'4f34b5bb-e0bf-40a8-b434-5f91cea88039',true,false)
,('fe833363-85f9-4d54-afae-d4658acffe62','1.8.2','Cursos','DESPESA',NULL,'4f34b5bb-e0bf-40a8-b434-5f91cea88039',true,false)
,('119463eb-6930-4a45-8f50-a2acd6e6b1a0','1.8.3','Materias','DESPESA',NULL,'4f34b5bb-e0bf-40a8-b434-5f91cea88039',true,false)
,('7b504f62-86c6-4d42-b29e-6df6ab2d48af','1.8.4','Transporte','DESPESA',NULL,'4f34b5bb-e0bf-40a8-b434-5f91cea88039',true,false)
,('51f19f60-3523-42a6-a729-6cfebf0f4829','1.8.5','Diversos','DESPESA',NULL,'4f34b5bb-e0bf-40a8-b434-5f91cea88039',true,false)
,('ab289880-6982-46d2-ab2c-543d2d5c7253','1.9','VESTUÁRIO','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('d03014a2-4601-4532-bef1-430d5b65a8a3','1.9.1','Roupas','DESPESA',NULL,'ab289880-6982-46d2-ab2c-543d2d5c7253',true,false)
,('4d29b952-03ea-4a63-86ff-7a88c11f7485','1.9.2','Calçados','DESPESA',NULL,'ab289880-6982-46d2-ab2c-543d2d5c7253',true,false)
,('5123d8c5-b5ca-48af-9692-4466fe362cea','1.9.3','Acessórios','DESPESA',NULL,'ab289880-6982-46d2-ab2c-543d2d5c7253',true,false)
,('34fe2efb-5f3e-4de0-ba70-d09e76ee35a2','1.9.4','Diversos','DESPESA',NULL,'ab289880-6982-46d2-ab2c-543d2d5c7253',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('2d2077c2-239d-48bf-9b2e-29f15e6a714f','1.10','TRANSPORTE','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('44da1429-e2c2-4496-bde6-e68d591a486a','1.10.1','Ônibus','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
,('fc3143c2-1c42-4e47-87dc-1052e686e58e','1.10.2','Topique','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
,('c4ed3976-44be-47dc-b4cc-d474b08489dc','1.10.3','Uber','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
,('aed3a589-836a-4cb6-b016-969930869bc2','1.10.4','Taxi','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
,('ce490284-2399-454d-9ac9-0935864c5ced','1.10.5','Carona','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
,('3be895ab-15fe-4697-9345-a284c21c3f70','1.10.6','Metrô','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
,('30df7e7f-c208-4572-aa4e-6fbff315ffa8','1.10.7','Avião','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
,('52454684-cd63-4816-bd69-bb1dde645f28','1.10.8','Trem','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
,('cf03c5d3-ffe8-40ab-9069-a6b529267af4','1.10.9','Diversos','DESPESA',NULL,'2d2077c2-239d-48bf-9b2e-29f15e6a714f',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('a0dfc59c-43fc-49d1-ad05-0ad3fc38b063','1.11','LAZER','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('4c1ebad0-d117-465e-a047-48c5e802c6b4','1.11.1','Cinema','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
,('d7c72cd8-e7ef-4a57-b601-481a2ae7f82d','1.11.2','Restaurante','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
,('aeece084-7c14-4b0a-97cb-fb014b6fd26b','1.11.3','Pizzaria','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
,('d88e9343-4ec3-4f1c-b350-3b205525cb4d','1.11.4','Festa','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
,('e8be3a28-a8a8-4806-87b9-d6ea52351a8b','1.11.5','Viagem','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
,('26d24960-a8ae-481d-b480-a3932a121282','1.11.6','Bar','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
,('39f60039-018e-4f85-8ed9-96661bdc8dfc','1.11.7','Parque','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
,('9f5dc698-a7d4-40b5-bf06-4127df15673f','1.11.8','Passeio','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
,('8cbe9eb1-9da1-43e8-b8ad-6739a0ce19a1','1.11.9','Diversos','DESPESA',NULL,'a0dfc59c-43fc-49d1-ad05-0ad3fc38b063',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('e237f583-a771-4838-841d-5806f94b79d0','1.12','FITNESS','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('cb899d34-af0b-4c75-a3de-886a07761ffd','1.12.1','Acadêmia','DESPESA',NULL,'e237f583-a771-4838-841d-5806f94b79d0',true,false)
,('c9056789-35e7-4e4a-aece-446c7d435cdf','1.12.2','Esportes','DESPESA',NULL,'e237f583-a771-4838-841d-5806f94b79d0',true,false)
,('50a58c44-9e92-4862-bb0a-15c7cf816622','1.12.3','Pilates','DESPESA',NULL,'e237f583-a771-4838-841d-5806f94b79d0',true,false)
,('3520863a-84a0-4ae8-8455-4984c2729ef3','1.12.4','Nutrição','DESPESA',NULL,'e237f583-a771-4838-841d-5806f94b79d0',true,false)
,('d69364bc-b7e1-4d53-8665-0245b2c2d94b','1.12.5','Acessórios','DESPESA',NULL,'e237f583-a771-4838-841d-5806f94b79d0',true,false)
,('dcaa60ac-d469-4754-937b-892c5993ead8','1.12.6','Diversos','DESPESA',NULL,'e237f583-a771-4838-841d-5806f94b79d0',true,false)
,('8ee9d849-e95d-48a1-bb92-8941a8bda4ac','1.13','PET','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('b5b94cb2-1f39-4a36-b7b4-b5c9e27e89c5','1.13.1','Pet Shop','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
,('ee22acd0-ab85-44e0-82cd-652d5b4ac92d','1.13.2','Veterinário','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('cf467e0b-cf75-4035-90a9-dd00ecd0596d','1.13.3','Vacinas','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
,('41135481-0754-48fd-8383-38cb2a3a3249','1.13.4','Alimentação','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
,('9988feb2-1b58-4eb7-bf60-47188996c3a2','1.13.5','Medicamentos','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
,('e63f2dbc-c394-41a8-8333-1f1021ce33ee','1.13.6','Produtos Para Higiene','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
,('8556f29b-3d7b-4c5b-b26d-fc16018789f1','1.13.7','Acessórios','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
,('88bd414f-a38f-474e-8d36-cf62a8bbc6bc','1.13.8','Hotel','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
,('1703ca34-2dfc-470e-85dc-5b6e7fe69a33','1.13.9','Diversos','DESPESA',NULL,'8ee9d849-e95d-48a1-bb92-8941a8bda4ac',true,false)
,('d07164ce-a5b2-41e8-8d7f-d60bd6df9103','1.14','JARDINAGEM','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('ab91d943-7132-4a76-8738-a659076e5285','1.14.1','Plantas','DESPESA',NULL,'d07164ce-a5b2-41e8-8d7f-d60bd6df9103',true,false)
,('f2eb5969-5c2f-4e87-843e-e34fb2cc1547','1.14.2','Flores','DESPESA',NULL,'d07164ce-a5b2-41e8-8d7f-d60bd6df9103',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('f0ad1aff-098d-4c32-bd4c-a6e32ac290c3','1.14.3','Adubos','DESPESA',NULL,'d07164ce-a5b2-41e8-8d7f-d60bd6df9103',true,false)
,('999cdcba-68cf-4ea6-bf32-909956c623d4','1.14.4','Inseticidas','DESPESA',NULL,'d07164ce-a5b2-41e8-8d7f-d60bd6df9103',true,false)
,('baa81af1-41fc-4cbf-b113-52049951666c','1.14.5','Equipamentos','DESPESA',NULL,'d07164ce-a5b2-41e8-8d7f-d60bd6df9103',true,false)
,('621ae932-1031-4206-bcd7-66475edfcab9','1.14.6','Acessórios','DESPESA',NULL,'d07164ce-a5b2-41e8-8d7f-d60bd6df9103',true,false)
,('cd2ea036-38de-4dec-9a14-032d1c3a9f27','1.14.7','Diversos','DESPESA',NULL,'d07164ce-a5b2-41e8-8d7f-d60bd6df9103',true,false)
,('3aa5de5b-4e3c-49a4-9c64-3fdef967c2e0','1.15','MESADAS','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('81001f87-15f7-4c3c-a9ac-1f04cf796fa6','1.15.1','Filhos','DESPESA',NULL,'3aa5de5b-4e3c-49a4-9c64-3fdef967c2e0',true,false)
,('bcd520d9-564c-4973-886f-11a41a70cb7d','1.15.2','Conjugue','DESPESA',NULL,'3aa5de5b-4e3c-49a4-9c64-3fdef967c2e0',true,false)
,('b1b30f63-e9b8-4fa0-8594-af4a331fe8b8','1.15.3','Pais','DESPESA',NULL,'3aa5de5b-4e3c-49a4-9c64-3fdef967c2e0',true,false)
,('8befebfe-c735-489b-88b5-95626096b822','1.16','DOAÇÕES','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('6ad68b3a-6ad2-4660-b0c0-42b598f81380','1.16.1','Igreja','DESPESA',NULL,'8befebfe-c735-489b-88b5-95626096b822',true,false)
,('6925cd3a-5d26-456d-bbcb-f613804195ef','1.16.2','Escola','DESPESA',NULL,'8befebfe-c735-489b-88b5-95626096b822',true,false)
,('1ad1d145-8053-4eb4-993c-89b381d97a8c','1.16.3','ONGs','DESPESA',NULL,'8befebfe-c735-489b-88b5-95626096b822',true,false)
,('7de9db62-f894-4bd4-a4f1-b2bbe4f02aa7','1.16.4','Diversos','DESPESA',NULL,'8befebfe-c735-489b-88b5-95626096b822',true,false)
,('361736d5-d8c3-44e9-a71e-c28cf8dede6f','1.17','DESPESAS DIVERSAS','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('2b31b74b-45b9-46ff-be55-abdadf631198','1.17.1','Rifas','DESPESA',NULL,'361736d5-d8c3-44e9-a71e-c28cf8dede6f',true,false)
,('b8dd0dd3-414b-4ee4-a1c8-2f5bde922a99','1.18','IMPOSTO RENDA','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('f7637499-a447-45ee-a4c0-1928293554fb','1.18.1','IRPF','DESPESA',NULL,'b8dd0dd3-414b-4ee4-a1c8-2f5bde922a99',true,false)
,('919f3251-98a0-4e4e-b964-9a4db902daf3','1.17.2','Diversos','DESPESA',NULL,'361736d5-d8c3-44e9-a71e-c28cf8dede6f',true,false)
,('ecaa5b47-590b-40fc-b041-a7d49e84d9ac','1.3.6','Diversos','DESPESA',NULL,'b2389da7-e107-455a-a654-e27ce5b91150',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('f965ee15-df2b-4e82-a348-6d862671ad14','1.7.1','Saque','DESPESA','VARIAVEL','5337768e-f7e8-423a-b0dd-4f47c32b8db6',true,false)
,('5645b648-1d79-49b7-ae1d-87d0a9c75854','1.7.6','Diversos','DESPESA',NULL,'5337768e-f7e8-423a-b0dd-4f47c32b8db6',true,false)
,('3ad7a43d-2766-4b22-bd0c-045888ec22f4','1.1.17','Lavanderia','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('25b25559-6221-4215-96e3-13dd28f1830c','1.19','BELEZA','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('da8a9ce8-70a7-4d00-a0d4-fc275c373c5f','1.19.1','Cabelo','DESPESA',NULL,'25b25559-6221-4215-96e3-13dd28f1830c',true,false)
,('29c1de3f-5790-41d5-add9-ff58c73d6bf9','1.19.2','Maquiagem','DESPESA',NULL,'25b25559-6221-4215-96e3-13dd28f1830c',true,false)
,('0832d3be-19a3-496d-8ead-a9b0ec8de95d','1.19.3','Depilação','DESPESA',NULL,'25b25559-6221-4215-96e3-13dd28f1830c',true,false)
,('21dbd1b6-d04c-4ea0-bf0f-abcabb96c2be','1.19.4','Limpeza','DESPESA',NULL,'25b25559-6221-4215-96e3-13dd28f1830c',true,false)
,('452b47c3-08ea-4091-beab-ccc16e509e71','1.19.5','Unhas','DESPESA',NULL,'25b25559-6221-4215-96e3-13dd28f1830c',true,false)
,('b759d996-0174-4ecd-a388-e0d19ae9b210','1.19.6','Barba/bigode','DESPESA',NULL,'25b25559-6221-4215-96e3-13dd28f1830c',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('3bfb55f9-d85b-4197-897e-28ccda7aade7','1.7.2','Transferência','DESPESA',NULL,'5337768e-f7e8-423a-b0dd-4f47c32b8db6',true,false)
,('238b6101-dd5d-4b99-9967-1edf9dbb6f4b','1.19.7','Completo','DESPESA',NULL,'25b25559-6221-4215-96e3-13dd28f1830c',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('ebb32268-5a82-4da6-8db5-2b3843097cf5','1.1.18','Acessórios','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('1824cff8-ceea-41e4-85f9-31fac02d8a79','1.1.19','Produtos Limpeza','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false)
,('07fa03bd-7c7f-4b0e-9a33-ab584e776d17','1.17.3','Presentes','DESPESA',NULL,'361736d5-d8c3-44e9-a71e-c28cf8dede6f',true,false)
,('ffd33323-f1af-4d67-b105-2327e6511a5a','1.19.8','Higiene Pessoal','DESPESA',NULL,'25b25559-6221-4215-96e3-13dd28f1830c',true,false)
,('87057271-2858-445a-9ebe-fd08684bb487','1.3.7','Dentista','DESPESA',NULL,'b2389da7-e107-455a-a654-e27ce5b91150',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

