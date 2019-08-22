import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrganizationEmployee } from 'app/shared/model/organization-employee.model';
import { OrganizationEmployeeService } from './organization-employee.service';
import { OrganizationEmployeeComponent } from './organization-employee.component';
import { OrganizationEmployeeDetailComponent } from './organization-employee-detail.component';
import { OrganizationEmployeeUpdateComponent } from './organization-employee-update.component';
import { OrganizationEmployeeDeletePopupComponent } from './organization-employee-delete-dialog.component';
import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';

@Injectable({ providedIn: 'root' })
export class OrganizationEmployeeResolve implements Resolve<IOrganizationEmployee> {
  constructor(private service: OrganizationEmployeeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOrganizationEmployee> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OrganizationEmployee>) => response.ok),
        map((organizationEmployee: HttpResponse<OrganizationEmployee>) => organizationEmployee.body)
      );
    }
    return of(new OrganizationEmployee());
  }
}

export const organizationEmployeeRoute: Routes = [
  {
    path: '',
    component: OrganizationEmployeeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'vnsdinternalApp.organizationEmployee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrganizationEmployeeDetailComponent,
    resolve: {
      organizationEmployee: OrganizationEmployeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'vnsdinternalApp.organizationEmployee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrganizationEmployeeUpdateComponent,
    resolve: {
      organizationEmployee: OrganizationEmployeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'vnsdinternalApp.organizationEmployee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrganizationEmployeeUpdateComponent,
    resolve: {
      organizationEmployee: OrganizationEmployeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'vnsdinternalApp.organizationEmployee.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const organizationEmployeePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OrganizationEmployeeDeletePopupComponent,
    resolve: {
      organizationEmployee: OrganizationEmployeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'vnsdinternalApp.organizationEmployee.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
