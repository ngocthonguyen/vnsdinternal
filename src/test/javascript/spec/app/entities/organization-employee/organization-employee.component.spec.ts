/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { VnsdinternalTestModule } from '../../../test.module';
import { OrganizationEmployeeComponent } from 'app/entities/organization-employee/organization-employee.component';
import { OrganizationEmployeeService } from 'app/entities/organization-employee/organization-employee.service';
import { OrganizationEmployee } from 'app/shared/model/organization-employee.model';

describe('Component Tests', () => {
  describe('OrganizationEmployee Management Component', () => {
    let comp: OrganizationEmployeeComponent;
    let fixture: ComponentFixture<OrganizationEmployeeComponent>;
    let service: OrganizationEmployeeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [VnsdinternalTestModule],
        declarations: [OrganizationEmployeeComponent],
        providers: []
      })
        .overrideTemplate(OrganizationEmployeeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganizationEmployeeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganizationEmployeeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OrganizationEmployee(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.organizationEmployees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
