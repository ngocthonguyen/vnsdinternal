import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';

@Component({
  selector: 'jhi-organization-employee-detail',
  templateUrl: './organization-employee-detail.component.html'
})
export class OrganizationEmployeeDetailComponent implements OnInit {
  organizationEmployee: IOrganizationEmployee;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ organizationEmployee }) => {
      this.organizationEmployee = organizationEmployee;
    });
  }

  previousState() {
    window.history.back();
  }
}
