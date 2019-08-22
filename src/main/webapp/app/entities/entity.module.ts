import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'organization',
        loadChildren: () => import('./organization/organization.module').then(m => m.VnsdinternalOrganizationModule)
      },
      {
        path: 'person',
        loadChildren: () => import('./person/person.module').then(m => m.VnsdinternalPersonModule)
      },
      {
        path: 'organization-employee',
        loadChildren: () =>
          import('./organization-employee/organization-employee.module').then(m => m.VnsdinternalOrganizationEmployeeModule)
      },
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.VnsdinternalRegionModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.VnsdinternalCountryModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.VnsdinternalLocationModule)
      },
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.VnsdinternalDepartmentModule)
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.VnsdinternalTaskModule)
      },
      {
        path: 'employee',
        loadChildren: () => import('./employee/employee.module').then(m => m.VnsdinternalEmployeeModule)
      },
      {
        path: 'job',
        loadChildren: () => import('./job/job.module').then(m => m.VnsdinternalJobModule)
      },
      {
        path: 'job-history',
        loadChildren: () => import('./job-history/job-history.module').then(m => m.VnsdinternalJobHistoryModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VnsdinternalEntityModule {}
