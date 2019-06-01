/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:21.027
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { AuthService } from './../security/auth.service';
import { Component, OnInit } from '@angular/core';
import { LogoutService } from '../security/logout.service';
import { MessageHandlerService } from '../core/message-handler.service';
import { Router } from '@angular/router';

// import { FinanceiroContasPagarTranslationService } from './../i18n/financeiro-contaspagar-translation.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent implements OnInit {

  constructor(
	private authService: AuthService,
	private logoutService: LogoutService,
	private messageHandler: MessageHandlerService,
	private router: Router
  	// private financeiroContasPagarTranslationService: FinanceiroContasPagarTranslationService
  ) { }

  ngOnInit() {
  }
  
  /* 
  // TODO: temporário, só para testes.
  getTranslation(key: string): string {
  	const value = this.financeiroContasPagarTranslationService.getTranslation(key);
  	return value;
  	
  	// const result = key.substring(key.lastIndexOf('_') + 1);
  	// return result;
  }
  */
  
  getCurrentUserName() {
      if (this.authService.jwtPayload && this.authService.jwtPayload.name) {
        return this.authService.jwtPayload.name;
      } else {
        return '<Desconhecido>';
      }
  }
  
  logout() {
      this.logoutService.logout()
      .then(() => {
        this.router.navigate(['/login']);
      })
      .catch(error => {
        this.messageHandler.showError(error);
      });
  }

}

