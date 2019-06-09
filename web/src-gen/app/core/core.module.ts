/**********************************************************************************************
Code generated with MKL Plug-in version: 3.6.2
Code generated at time stamp: 2019-06-05T06:43:06.721
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

// Angular
import { HttpClientModule } from '@angular/common/http';
import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import { RouterModule } from '@angular/router';
import localePt from '@angular/common/locales/pt';
import localeExtraPT from '@angular/common/locales/extra/pt';

// PrimeNG
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessageService, ConfirmationService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';

// CurrencyMask
import { CurrencyMaskConfig, CURRENCY_MASK_CONFIG } from 'ng2-currency-mask/src/currency-mask.config';

// auth0
import { JwtHelperService } from '@auth0/angular-jwt';

// Kerubin - BEGIN
import { NavbarComponent } from './../navbar/navbar.component';
import { KerubinMenuModule } from './../menu/kerubin-menu.module';
import { FocusDirective } from './../directive/focus.directive';
import { MessageHandlerService } from './message-handler.service';
import { HttpClientWithToken } from '../security/http-client-token';
import { UserAccountService } from '../account/useraccount.service';
import { SecurityModule } from './../security/security.module';
import { KerubinAccountModule } from './../account/kerubin-account.module';
// Kerubin - END


registerLocaleData(localePt, 'pt', localeExtraPT);

export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: 'right',
  allowNegative: true,
  decimal: ',',
  precision: 2,
  // prefix: 'R$ ',
  prefix: '',
  suffix: '',
  thousands: '.'
};

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    ConfirmDialogModule,
    KerubinMenuModule,
    KerubinAccountModule,
    SecurityModule,
  ],

  declarations: [
    NavbarComponent,
    FocusDirective
  ],

  exports: [
    NavbarComponent,
    ToastModule,
    KerubinMenuModule,
    ConfirmDialogModule
  ],

  providers: [
    UserAccountService,
    MessageHandlerService,
    HttpClientWithToken,
    JwtHelperService,
    MessageService,
    ConfirmationService,
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig }
  ]
})
export class CoreModule { }

