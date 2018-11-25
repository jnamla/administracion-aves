import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AvesComponent } from './aves/aves.component';
import { CreateAveComponent } from './aves/create-ave/create-ave.component';
import { ReadAveComponent } from './aves/read-ave/read-ave.component';
import { SearchZonaAveComponent } from './aves/search-zona-ave/search-zona-ave.component';

const routes: Routes = [
  {
    path:'aves', component: AvesComponent
  },
  {
    path:'aves/:id', component: ReadAveComponent
  },
  {
    path:'aves-crear', component: CreateAveComponent
  },
  {
    path:'aves-editar/:id', component: CreateAveComponent
  },
  {
    path:'aves-busqueda-especializada', component: SearchZonaAveComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
