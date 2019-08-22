/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { VnsdinternalTestModule } from '../../../test.module';
import { OrganizationEmployeeDetailComponent } from 'app/entities/organization-employee/organization-employee-detail.component';
import { OrganizationEmployee } from 'app/shared/model/organization-employee.model';

describe('Component Tests', () => {
  describe('OrganizationEmployee Management Detail Component', () => {
    let comp: OrganizationEmployeeDetailComponent;
    let fixture: ComponentFixture<OrganizationEmployeeDetailComponent>;
    const route = ({ data: of({ organizationEmployee: new OrganizationEmployee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [VnsdinternalTestModule],
        declarations: [OrganizationEmployeeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrganizationEmployeeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrganizationEmployeeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.organizationEmployee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
