/**********************************************************************************************
Code generated with MKL Plug-in version: 3.6.2
Code generated at time stamp: 2019-06-05T06:43:06.721
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


export class SortField {
  field: string;
  order: number;

  constructor(field: string, order: number) {
    this.field = field;
    this.order = order;
  }
}

export class PaginationFilter {
  pageNumber: number;
  pageSize: number;
  sortField: SortField;

  constructor() {
    this.pageNumber = 0;
    this.pageSize = 10;
  }
}

export class UserAccountListFilter extends PaginationFilter {

}

export class UserAccount {
	name: string;
	email: string;
	password: string;
	accountType: string;
}

export class UserAccountAutoComplete {
	name: string;
}

export class UserAccountSumFields {
}

export class AccountCreatedDTO {
	text: string;
}

export class SysUser {
  id: string;
  name: string;
  email: string;
  accountType: string;
}

