alter table conta_pagar
	add column tipo_pagamento VARCHAR(255) default 'SINGLE';
	
alter table conta_pagar
	add column conta_pagar_pai UUID;
