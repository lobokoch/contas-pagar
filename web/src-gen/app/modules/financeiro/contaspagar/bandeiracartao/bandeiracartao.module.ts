/**********************************************************************************************
Code generated with MKL Plug-in version: 3.6.2
Code generated at time stamp: 2019-06-05T06:43:06.721
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { PanelModule } from 'primeng/panel';
import { InputSwitchModule } from 'primeng/inputswitch';
import { AccordionModule } from 'primeng/accordion';
import { SpinnerModule } from 'primeng/spinner';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';

// Kerubin - BEGIN
import { FinanceiroContasPagarTranslationService } from './../i18n/./../i18n/financeiro-contaspagar-translation.service';
import { BandeiraCartaoService } from './bandeiracartao.service';
import { BandeiraCartaoListComponent } from './list-bandeiracartao.component';
import { BandeiraCartaoComponent } from './crud-bandeiracartao.component';
import { BandeiraCartaoRoutingModule } from './bandeiracartao-routing.module';
// Kerubin - END

@NgModule({

  imports: [
    // PrimeNG
    CommonModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    InputTextareaModule,
    TableModule,
    TooltipModule,
    ToastModule,
    ConfirmDialogModule,
    AutoCompleteModule,
    PanelModule,
    InputSwitchModule,
    AccordionModule,
    SpinnerModule,
    DialogModule,
    DropdownModule,

    // Kerubin
    BandeiraCartaoRoutingModule

  ],

  declarations: [
    BandeiraCartaoComponent,
    BandeiraCartaoListComponent
  ],

  exports: [

  ],

  providers: [
    BandeiraCartaoService,
    FinanceiroContasPagarTranslationService
  ]

})

export class BandeiraCartaoModule { }
