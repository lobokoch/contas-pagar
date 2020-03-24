-- Alteracao altamente destrutiva
alter table conta_pagar
	add column conta_paga boolean not null default false;
	
update conta_pagar set
	conta_paga = ((data_pagamento is not null) and (valor_pago is not null));