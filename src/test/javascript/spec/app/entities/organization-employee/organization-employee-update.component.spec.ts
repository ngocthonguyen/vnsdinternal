/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { VnsdinternalTestModule } from '../../../test.module';
import { OrganizationEmployeeUpdateComponent } from 'app/entities/organization-employee/organization-employee-update.component';
import { OrganizationEmployeeService } from 'app/entities/organization-employee/organization-employee.service';
import { OrganizationEmployee } from 'app/shared/model/organization-employee.model';

describe('Component Tests', () => {
  describe('OrganizationEmployee Management Update Component', () => {
    let comp: OrganizationEmployeeUpdateComponent;
    let fixture: ComponentFixture<OrganizationEmployeeUpdateComponent>;
    let service: OrganizationEmployeeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [VnsdinternalTestModule],
        declarations: [OrganizationEmployeeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrganizationEmployeeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganizationEmployeeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganizationEmployeeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrganizationEmployee(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrganizationEmployee();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
