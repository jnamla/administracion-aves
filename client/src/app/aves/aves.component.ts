import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms'

import { AvesService } from './aves.service'
import { Ave } from '../../assets/aves/ave'
import { BasicSearch } from '../../assets/aves/basicSearch'

@Component({
  selector: 'app-aves',
  templateUrl: './aves.component.html'
})
export class AvesComponent implements OnInit {

  ave: Ave;
  searchForm: FormGroup;
  aves: Array<Ave>;
  
  constructor(private aR: ActivatedRoute, private router: Router, private avesService: AvesService) {
  }

  ngOnInit(): void {
    this.showAves()
    this.searchForm = this.getFormGroup();
  }

  getFormGroup() {
    return new FormGroup({
      nombre: new FormControl("")
    });
  }

  get nombre() { return this.searchForm.get('nombre'); }

  showAves() {
    this.avesService.getAves()
      .subscribe( data => {
        data.status === 404 ? this.aves = [] : this.aves = data.body;
      })
  }

  search(basicSearch: BasicSearch) {
    
    if (basicSearch === undefined || basicSearch.nombre === ""){
      this.showAves();
    } else {
      this.avesService.getAvesByName(basicSearch)
        .subscribe( data => {
          data.status === 404 ? this.aves = [] : this.aves = data.body;
        });
    }
      
  }
}
