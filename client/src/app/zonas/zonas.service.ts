import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { Zona } from '../../assets/aves/zona'

import { environment } from '../../environments/environment'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ZonasService {
 
  constructor( private http: HttpClient ) {
  }

  getZonas(): Observable<HttpResponse<Array<Zona>>> {
    return this.http.get<Array<Zona>>(environment.apiZonasHost, { observe: 'response'});
  }

}
