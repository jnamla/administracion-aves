import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms'

import { AvesService } from '../aves.service'
import { Ave } from '../../../assets/aves/ave'

@Component({
  selector: 'app-create-ave',
  templateUrl: './create-ave.component.html'
})
export class CreateAveComponent implements OnInit {

  ave: Ave;
  aveForm: FormGroup;
  aves: Array<Ave>;

  constructor(private avesService: AvesService, private aR: ActivatedRoute,  private router: Router) { }

  ngOnInit() {
    this.getAves();

    this.aR.params.subscribe((params) => {
      if(params['id']) {
        this.avesService.getAvesId(params['id'])
          .subscribe( data => {
            this.ave = data.body;
            this.aveForm = this.getFormGroup(this.ave);
          })
      } else {
        this.aveForm = this.getFormGroup();
      }
    });
  }

  getFormGroup(ave?: Ave) {
    return new FormGroup({
      cdAve: new FormControl(ave? ave.cdAve: null, [Validators.required, Validators.minLength(3),  Validators.maxLength(5)]),
      dsNombreComun: new FormControl(ave? ave.dsNombreComun: null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]),
      dsNombreCientifico: new FormControl(ave? ave.dsNombreCientifico: null,[Validators.required, Validators.minLength(1), Validators.maxLength(200)])
    });
  }

  get cdAve() { return this.aveForm.get('cdAve'); }
  get dsNombreComun() { return this.aveForm.get('dsNombreComun'); }
  get dsNombreCientifico() { return this.aveForm.get('dsNombreCientifico'); }

  getAves() {
    this.avesService.getAves()
      .subscribe( data => {
        this.aves = data.body;
      });
  }

  addAve(aveId: Ave, ave: Ave) {
    if (aveId) {
      this.avesService.updateAve(aveId.cdAve,ave)
        .subscribe( data => {
          this.router.navigateByUrl("/aves")
        });
    } else {
      this.avesService.insertAve(ave)
      .subscribe( data => {
        this.aves.push(data.body);
        this.router.navigateByUrl("/aves")
      });
    }
  }
}
