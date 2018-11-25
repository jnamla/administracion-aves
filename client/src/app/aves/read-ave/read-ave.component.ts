import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AvesService } from '../aves.service'
import { Ave } from '../../../assets/aves/ave'

@Component({
  selector: 'app-read-ave',
  templateUrl: './read-ave.component.html'
})
export class ReadAveComponent implements OnInit {

  ave: Ave;

  constructor(private avesService: AvesService, private aR: ActivatedRoute,  private router: Router) { }

  ngOnInit() {
    this.aR.params.subscribe((params) => {
      if(params['id']) {
        this.avesService.getAvesId(params['id'])
          .subscribe( data => {
            data.status === 404 ? this.ave = undefined : this.ave = data.body;
          })
      }
    });
  }

  deleteAve(aveId: string) {
    if (aveId) {
      this.avesService.deleteAve(aveId)
        .subscribe( newAve => {
          this.router.navigateByUrl("/")
        });
    }
  }
}
