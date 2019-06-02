/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.876
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { JwtHelperService } from '@auth0/angular-jwt';

// Angular
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// Kerubin
import { MessageHandlerService } from './message-handler.service';
import { HttpClientWithToken } from '../security/http-client-token';



@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
  ],
  exports: [ // app.module precisa desses modulos
  ],
  providers: [
    MessageHandlerService,
    HttpClientWithToken,
  	// Kerubin End
    JwtHelperService
  ]
})
export class CoreModule { }

