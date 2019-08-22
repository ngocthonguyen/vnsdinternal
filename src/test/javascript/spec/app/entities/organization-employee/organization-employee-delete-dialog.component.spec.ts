/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { VnsdinternalTestModule } from '../../../test.module';
import { OrganizationEmployeeDeleteDialogComponent } from 'app/entities/organization-employee/organization-employee-delete-dialog.component';
import { OrganizationEmployeeService } from 'app/entities/organization-employee/organization-employee.service';

describe('Component Tests', () => {
  describe('OrganizationEmployee Management Delete Component', () => {
    let comp: OrganizationEmployeeDeleteDialogComponent;
    let fixture: ComponentFixture<OrganizationEmployeeDeleteDialogComponent>;
    let service: OrganizationEmployeeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [VnsdinternalTestModule],
        declarations: [OrganizationEmployeeDeleteDialogComponent]
      })
        .overrideTemplate(OrganizationEmployeeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrganizationEmployeeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganizationEmployeeService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
