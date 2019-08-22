import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';
import { OrganizationEmployeeService } from './organization-employee.service';

@Component({
  selector: 'jhi-organization-employee-delete-dialog',
  templateUrl: './organization-employee-delete-dialog.component.html'
})
export class OrganizationEmployeeDeleteDialogComponent {
  organizationEmployee: IOrganizationEmployee;

  constructor(
    protected organizationEmployeeService: OrganizationEmployeeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.organizationEmployeeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'organizationEmployeeListModification',
        content: 'Deleted an organizationEmployee'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-organization-employee-delete-popup',
  template: ''
})
export class OrganizationEmployeeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ organizationEmployee }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OrganizationEmployeeDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.organizationEmployee = organizationEmployee;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/organization-employee', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/organization-employee', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
