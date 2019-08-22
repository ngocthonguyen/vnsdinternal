import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IOrganizationEmployee, OrganizationEmployee } from 'app/shared/model/organization-employee.model';
import { OrganizationEmployeeService } from './organization-employee.service';

@Component({
  selector: 'jhi-organization-employee-update',
  templateUrl: './organization-employee-update.component.html'
})
export class OrganizationEmployeeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    role: []
  });

  constructor(
    protected organizationEmployeeService: OrganizationEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ organizationEmployee }) => {
      this.updateForm(organizationEmployee);
    });
  }

  updateForm(organizationEmployee: IOrganizationEmployee) {
    this.editForm.patchValue({
      id: organizationEmployee.id,
      role: organizationEmployee.role
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const organizationEmployee = this.createFromForm();
    if (organizationEmployee.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationEmployeeService.update(organizationEmployee));
    } else {
      this.subscribeToSaveResponse(this.organizationEmployeeService.create(organizationEmployee));
    }
  }

  private createFromForm(): IOrganizationEmployee {
    return {
      ...new OrganizationEmployee(),
      id: this.editForm.get(['id']).value,
      role: this.editForm.get(['role']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizationEmployee>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
