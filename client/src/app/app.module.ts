import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AvesComponent } from './aves/aves.component';
import { HeaderComponent } from './header/header.component';
import { CreateAveComponent } from './aves/create-ave/create-ave.component';
import { ReadAveComponent } from './aves/read-ave/read-ave.component';
import { SearchZonaAveComponent } from './aves/search-zona-ave/search-zona-ave.component';

@NgModule({
  declarations: [
    AppComponent,
    AvesComponent,
    HeaderComponent,
    CreateAveComponent,
    ReadAveComponent,
    SearchZonaAveComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
