/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.876
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { ConfirmationService } from 'primeng/components/common/api';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule, LOCALE_ID } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';
import localeExtraPT from '@angular/common/locales/extra/pt';

// PrimeNG
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {CalendarModule} from 'primeng/calendar';
import {TableModule} from 'primeng/table';
import {TooltipModule} from 'primeng/tooltip';
import {ToastModule} from 'primeng/toast';
import {MessageService} from 'primeng/api';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {PanelModule} from 'primeng/panel';
import {InputSwitchModule} from 'primeng/inputswitch';
import {AccordionModule} from 'primeng/accordion';
import {SpinnerModule} from 'primeng/spinner';
import {SelectButtonModule} from 'primeng/selectbutton';
import {DialogModule} from 'primeng/dialog';
import {DropdownModule} from 'primeng/dropdown';
import {CardModule} from 'primeng/card';


// CurrencyMask
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { CurrencyMaskConfig, CURRENCY_MASK_CONFIG } from 'ng2-currency-mask/src/currency-mask.config';

// Rotas
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './security/auth.guard';

// Kerubin begin

import { ContaPagarComponent } from './financeiro/contaspagar/contapagar/crud-contapagar.component';
import { ContaPagarListComponent } from './financeiro/contaspagar/contapagar/list-contapagar.component';
import { ContaPagarService } from './financeiro/contaspagar/contapagar/contapagar.service';

import { FornecedorComponent } from './financeiro/contaspagar/fornecedor/crud-fornecedor.component';
import { FornecedorListComponent } from './financeiro/contaspagar/fornecedor/list-fornecedor.component';
import { FornecedorService } from './financeiro/contaspagar/fornecedor/fornecedor.service';

import { BancoComponent } from './financeiro/contaspagar/banco/crud-banco.component';
import { BancoListComponent } from './financeiro/contaspagar/banco/list-banco.component';
import { BancoService } from './financeiro/contaspagar/banco/banco.service';

import { AgenciaBancariaComponent } from './financeiro/contaspagar/agenciabancaria/crud-agenciabancaria.component';
import { AgenciaBancariaListComponent } from './financeiro/contaspagar/agenciabancaria/list-agenciabancaria.component';
import { AgenciaBancariaService } from './financeiro/contaspagar/agenciabancaria/agenciabancaria.service';

import { BandeiraCartaoComponent } from './financeiro/contaspagar/bandeiracartao/crud-bandeiracartao.component';
import { BandeiraCartaoListComponent } from './financeiro/contaspagar/bandeiracartao/list-bandeiracartao.component';
import { BandeiraCartaoService } from './financeiro/contaspagar/bandeiracartao/bandeiracartao.service';

import { ContaBancariaComponent } from './financeiro/contaspagar/contabancaria/crud-contabancaria.component';
import { ContaBancariaListComponent } from './financeiro/contaspagar/contabancaria/list-contabancaria.component';
import { ContaBancariaService } from './financeiro/contaspagar/contabancaria/contabancaria.service';

import { CartaoCreditoComponent } from './financeiro/contaspagar/cartaocredito/crud-cartaocredito.component';
import { CartaoCreditoListComponent } from './financeiro/contaspagar/cartaocredito/list-cartaocredito.component';
import { CartaoCreditoService } from './financeiro/contaspagar/cartaocredito/cartaocredito.service';

import { PlanoContaComponent } from './financeiro/contaspagar/planoconta/crud-planoconta.component';
import { PlanoContaListComponent } from './financeiro/contaspagar/planoconta/list-planoconta.component';
import { PlanoContaService } from './financeiro/contaspagar/planoconta/planoconta.service';
import { FinanceiroContasPagarTranslationService } from './financeiro/contaspagar/i18n/financeiro-contaspagar-translation.service';

import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './security/login/login.component';
import { SecurityModule } from './security/security.module';
import { CoreModule } from './core/core.module';
import { NewAccountComponent } from './account/newaccount/newaccount.component';
import { ConfirmAccountComponent } from './account/confirmaccount/confirmaccount.component';
import { ConfigNewAccountComponent } from './account/confignewaccount/confignewaccount.component';
import { UserAccountService } from './account/useraccount.service';
import { FocusDirective } from './directive/focus.directive';

// Kerubin end

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

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  
	// Kerubin Begin
	
	{ path: 'contapagar/novo', component: ContaPagarComponent, canActivate: [AuthGuard] },
	{ path: 'contapagar/:id', component: ContaPagarComponent, canActivate: [AuthGuard] },
	{ path: 'contapagar', component: ContaPagarListComponent, canActivate: [AuthGuard] },
	
	{ path: 'fornecedor/novo', component: FornecedorComponent, canActivate: [AuthGuard] },
	{ path: 'fornecedor/:id', component: FornecedorComponent, canActivate: [AuthGuard] },
	{ path: 'fornecedor', component: FornecedorListComponent, canActivate: [AuthGuard] },
	
	{ path: 'banco/novo', component: BancoComponent, canActivate: [AuthGuard] },
	{ path: 'banco/:id', component: BancoComponent, canActivate: [AuthGuard] },
	{ path: 'banco', component: BancoListComponent, canActivate: [AuthGuard] },
	
	{ path: 'agenciabancaria/novo', component: AgenciaBancariaComponent, canActivate: [AuthGuard] },
	{ path: 'agenciabancaria/:id', component: AgenciaBancariaComponent, canActivate: [AuthGuard] },
	{ path: 'agenciabancaria', component: AgenciaBancariaListComponent, canActivate: [AuthGuard] },
	
	{ path: 'bandeiracartao/novo', component: BandeiraCartaoComponent, canActivate: [AuthGuard] },
	{ path: 'bandeiracartao/:id', component: BandeiraCartaoComponent, canActivate: [AuthGuard] },
	{ path: 'bandeiracartao', component: BandeiraCartaoListComponent, canActivate: [AuthGuard] },
	
	{ path: 'contabancaria/novo', component: ContaBancariaComponent, canActivate: [AuthGuard] },
	{ path: 'contabancaria/:id', component: ContaBancariaComponent, canActivate: [AuthGuard] },
	{ path: 'contabancaria', component: ContaBancariaListComponent, canActivate: [AuthGuard] },
	
	{ path: 'cartaocredito/novo', component: CartaoCreditoComponent, canActivate: [AuthGuard] },
	{ path: 'cartaocredito/:id', component: CartaoCreditoComponent, canActivate: [AuthGuard] },
	{ path: 'cartaocredito', component: CartaoCreditoListComponent, canActivate: [AuthGuard] },
	
	{ path: 'planoconta/novo', component: PlanoContaComponent, canActivate: [AuthGuard] },
	{ path: 'planoconta/:id', component: PlanoContaComponent, canActivate: [AuthGuard] },
	{ path: 'planoconta', component: PlanoContaListComponent, canActivate: [AuthGuard] },
	// Kerubin Begin
  
  { path: 'mainmenu', component: ContaPagarListComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'confignewaccount', component: ConfigNewAccountComponent },
  { path: 'newaccount', component: NewAccountComponent },
  { path: 'confirmaccount', component: ConfirmAccountComponent }
];



@NgModule({
  declarations: [
    // Kerubin Begin
    
    ContaPagarComponent,
    ContaPagarListComponent,
    
    FornecedorComponent,
    FornecedorListComponent,
    
    BancoComponent,
    BancoListComponent,
    
    AgenciaBancariaComponent,
    AgenciaBancariaListComponent,
    
    BandeiraCartaoComponent,
    BandeiraCartaoListComponent,
    
    ContaBancariaComponent,
    ContaBancariaListComponent,
    
    CartaoCreditoComponent,
    CartaoCreditoListComponent,
    
    PlanoContaComponent,
    PlanoContaListComponent,
    NavbarComponent,
    LoginComponent,
    NewAccountComponent,
    ConfirmAccountComponent,
    ConfigNewAccountComponent,
    FocusDirective,
    // Kerubin End
    
    AppComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,

    CurrencyMaskModule,

    // PrimeNG
    InputTextModule,
    ButtonModule,
    InputTextareaModule,
    CalendarModule,
    TableModule,
    TooltipModule,
    ToastModule,
    ConfirmDialogModule,
    AutoCompleteModule,
    PanelModule,
    InputSwitchModule,
    AccordionModule,
    SpinnerModule,
    SelectButtonModule,
    DialogModule,
    DropdownModule,
    CardModule,
    
    CoreModule,
    SecurityModule
  ],
  providers: [
  	// Kerubin Begin
  	ContaPagarService,
  	FornecedorService,
  	BancoService,
  	AgenciaBancariaService,
  	BandeiraCartaoService,
  	ContaBancariaService,
  	CartaoCreditoService,
  	PlanoContaService,
  	FinanceiroContasPagarTranslationService,
  	UserAccountService,
  	// Kerubin End
  	
    MessageService,
    ConfirmationService,
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig }
  ],
  bootstrap: [AppComponent]
})

export class AppModule {

}

