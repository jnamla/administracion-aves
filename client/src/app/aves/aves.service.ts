import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Ave } from '../../assets/aves/ave'
import { AvePais } from 'src/assets/aves/avePais';

import { environment } from '../../environments/environment'
import { BasicSearch } from 'src/assets/aves/basicSearch';
import { AdvancedSearch } from 'src/assets/aves/advancedSearch';
import { Observable } from 'rxjs';
import { TagPlaceholder } from '@angular/compiler/src/i18n/i18n_ast';

@Injectable({
  providedIn: 'root'
})
export class AvesService {
 
  constructor( private http: HttpClient ) {
  }

  getAves(): Observable<HttpResponse<Array<Ave>>> {
    return this.http.get<Array<Ave>>(environment.apiAvesHost, { observe: 'response'});
  }

  getAvesByName(basicSearch: BasicSearch): Observable<HttpResponse<Array<Ave>>> {
      return this.http.get<Array<Ave>>(environment.apiAvesHost + 'nombre/' + basicSearch.nombre, { observe: 'response'});
  }
    

  getAvesByNameAndZone(search?: AdvancedSearch): Observable<HttpResponse<Array<AvePais>>> {

    let urlParcial = '/';

    if(search.nombreAve) {
      urlParcial += `nombre/${search.nombreAve}/`
    }

    if(search.zona) {
      urlParcial += `zona/${search.zona}`
    }

    return this.http.get<Array<AvePais>>(`${environment.apiAvesHost}busqueda-avanzada${urlParcial}`, { observe: 'response'});
  }

  getAvesId(id: string): Observable<HttpResponse<Ave>> {
    return this.http.get<Ave>(environment.apiAvesHost + id, { observe: 'response'});
  }

  insertAve(post: Ave): Observable<HttpResponse<Ave>> {
    const httpHeaders = new HttpHeaders({
        'Content-Type': 'application/json'
    });
      return this.http.post<Ave>(environment.apiAvesHost, post, { headers:httpHeaders, observe: 'response'});
  }

  updateAve(aveId: string, post: Ave): Observable<HttpResponse<Ave>>  {
    const httpHeaders = new HttpHeaders({
        'Content-Type': 'application/json'
    });
  
    return this.http.put<Ave>(environment.apiAvesHost + aveId, post, { headers:httpHeaders, observe: 'response'});
  }

  deleteAve(aveId: string): Observable<HttpResponse<Object>>  {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.delete(environment.apiAvesHost + aveId, { headers:httpHeaders, observe: 'response'});
  }

}
