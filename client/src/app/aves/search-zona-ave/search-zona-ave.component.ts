import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { AvesService } from '../aves.service';
import { ZonasService } from '../../zonas/zonas.service';
import { AvePais } from '../../../assets/aves/avePais';
import { AdvancedSearch } from '../../../assets/aves/advancedSearch';
import { Zona } from 'src/assets/aves/Zona';

@Component({
  selector: 'app-search-zona-ave',
  templateUrl: './search-zona-ave.component.html'
})
export class SearchZonaAveComponent implements OnInit {

  zonas: Array<Zona>;
  searchForm: FormGroup;
  avesPais: Array<AvePais>;

  constructor(private aR: ActivatedRoute, private router: Router, private avesService: AvesService, private zonaService: ZonasService) { }

  ngOnInit() {
    this.zonaService.getZonas()
      .subscribe( data => {
        this.zonas = data.body;
      });

      this.searchForm = this.getFormGroup();
  }

  getFormGroup() {
    return new FormGroup({
      nombreAve: new FormControl(null, [Validators.required,  Validators.maxLength(50)]),
      zona: new FormControl(null)
    });
  }

  get nombreAve() { return this.searchForm.get('nombreAve'); }
  get zona() { return this.searchForm.get('zona'); }

  search(advancedSearch: AdvancedSearch) {
    this.avesPais = [];
    this.avesService.getAvesByNameAndZone(advancedSearch)
      .subscribe( data => {
        data.status === 404 ? this.avesPais = [] : this.avesPais = data.body;
      });
  }
}
