import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';
import { AccountService } from 'app/core';
import { OrganizationEmployeeService } from './organization-employee.service';

@Component({
  selector: 'jhi-organization-employee',
  templateUrl: './organization-employee.component.html'
})
export class OrganizationEmployeeComponent implements OnInit, OnDestroy {
  organizationEmployees: IOrganizationEmployee[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected organizationEmployeeService: OrganizationEmployeeService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.organizationEmployeeService
      .query()
      .pipe(
        filter((res: HttpResponse<IOrganizationEmployee[]>) => res.ok),
        map((res: HttpResponse<IOrganizationEmployee[]>) => res.body)
      )
      .subscribe(
        (res: IOrganizationEmployee[]) => {
          this.organizationEmployees = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInOrganizationEmployees();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IOrganizationEmployee) {
    return item.id;
  }

  registerChangeInOrganizationEmployees() {
    this.eventSubscriber = this.eventManager.subscribe('organizationEmployeeListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
