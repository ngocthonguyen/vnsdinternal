import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IPerson, Person } from 'app/shared/model/person.model';
import { PersonService } from './person.service';
import { IOrganization } from 'app/shared/model/organization.model';
import { OrganizationService } from 'app/entities/organization';
import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';
import { OrganizationEmployeeService } from 'app/entities/organization-employee';

@Component({
  selector: 'jhi-person-update',
  templateUrl: './person-update.component.html'
})
export class PersonUpdateComponent implements OnInit {
  isSaving: boolean;

  organizations: IOrganization[];

  organizationemployees: IOrganizationEmployee[];

  editForm = this.fb.group({
    id: [],
    uuid: [null, [Validators.required]],
    permalink: [null, [Validators.required]],
    firstname: [null, [Validators.required]],
    lastname: [null, [Validators.required]],
    alsoknownas: [],
    bio: [],
    profileimageid: [],
    roleinvestor: [],
    bornon: [],
    bornontrustcode: [],
    diedon: [],
    diedontrustcode: [],
    createdat: [],
    updatedat: [],
    permalinkaliases: [],
    gender: [],
    rank: [],
    primaryaffiliationid: [],
    primarylocationid: [],
    primaryimageid: [],
    title: [],
    homepageurl: [],
    facebookurl: [],
    twitterurl: [],
    linkedinurl: [],
    cityname: [],
    regionname: [],
    countrycode: [],
    organization: [],
    organizationEmployee: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected personService: PersonService,
    protected organizationService: OrganizationService,
    protected organizationEmployeeService: OrganizationEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ person }) => {
      this.updateForm(person);
    });
    this.organizationService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrganization[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrganization[]>) => response.body)
      )
      .subscribe((res: IOrganization[]) => (this.organizations = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.organizationEmployeeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrganizationEmployee[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrganizationEmployee[]>) => response.body)
      )
      .subscribe(
        (res: IOrganizationEmployee[]) => (this.organizationemployees = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(person: IPerson) {
    this.editForm.patchValue({
      id: person.id,
      uuid: person.uuid,
      permalink: person.permalink,
      firstname: person.firstname,
      lastname: person.lastname,
      alsoknownas: person.alsoknownas,
      bio: person.bio,
      profileimageid: person.profileimageid,
      roleinvestor: person.roleinvestor,
      bornon: person.bornon != null ? person.bornon.format(DATE_TIME_FORMAT) : null,
      bornontrustcode: person.bornontrustcode,
      diedon: person.diedon != null ? person.diedon.format(DATE_TIME_FORMAT) : null,
      diedontrustcode: person.diedontrustcode,
      createdat: person.createdat != null ? person.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: person.updatedat != null ? person.updatedat.format(DATE_TIME_FORMAT) : null,
      permalinkaliases: person.permalinkaliases,
      gender: person.gender,
      rank: person.rank,
      primaryaffiliationid: person.primaryaffiliationid,
      primarylocationid: person.primarylocationid,
      primaryimageid: person.primaryimageid,
      title: person.title,
      homepageurl: person.homepageurl,
      facebookurl: person.facebookurl,
      twitterurl: person.twitterurl,
      linkedinurl: person.linkedinurl,
      cityname: person.cityname,
      regionname: person.regionname,
      countrycode: person.countrycode,
      organization: person.organization,
      organizationEmployee: person.organizationEmployee
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const person = this.createFromForm();
    if (person.id !== undefined) {
      this.subscribeToSaveResponse(this.personService.update(person));
    } else {
      this.subscribeToSaveResponse(this.personService.create(person));
    }
  }

  private createFromForm(): IPerson {
    return {
      ...new Person(),
      id: this.editForm.get(['id']).value,
      uuid: this.editForm.get(['uuid']).value,
      permalink: this.editForm.get(['permalink']).value,
      firstname: this.editForm.get(['firstname']).value,
      lastname: this.editForm.get(['lastname']).value,
      alsoknownas: this.editForm.get(['alsoknownas']).value,
      bio: this.editForm.get(['bio']).value,
      profileimageid: this.editForm.get(['profileimageid']).value,
      roleinvestor: this.editForm.get(['roleinvestor']).value,
      bornon: this.editForm.get(['bornon']).value != null ? moment(this.editForm.get(['bornon']).value, DATE_TIME_FORMAT) : undefined,
      bornontrustcode: this.editForm.get(['bornontrustcode']).value,
      diedon: this.editForm.get(['diedon']).value != null ? moment(this.editForm.get(['diedon']).value, DATE_TIME_FORMAT) : undefined,
      diedontrustcode: this.editForm.get(['diedontrustcode']).value,
      createdat:
        this.editForm.get(['createdat']).value != null ? moment(this.editForm.get(['createdat']).value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat']).value != null ? moment(this.editForm.get(['updatedat']).value, DATE_TIME_FORMAT) : undefined,
      permalinkaliases: this.editForm.get(['permalinkaliases']).value,
      gender: this.editForm.get(['gender']).value,
      rank: this.editForm.get(['rank']).value,
      primaryaffiliationid: this.editForm.get(['primaryaffiliationid']).value,
      primarylocationid: this.editForm.get(['primarylocationid']).value,
      primaryimageid: this.editForm.get(['primaryimageid']).value,
      title: this.editForm.get(['title']).value,
      homepageurl: this.editForm.get(['homepageurl']).value,
      facebookurl: this.editForm.get(['facebookurl']).value,
      twitterurl: this.editForm.get(['twitterurl']).value,
      linkedinurl: this.editForm.get(['linkedinurl']).value,
      cityname: this.editForm.get(['cityname']).value,
      regionname: this.editForm.get(['regionname']).value,
      countrycode: this.editForm.get(['countrycode']).value,
      organization: this.editForm.get(['organization']).value,
      organizationEmployee: this.editForm.get(['organizationEmployee']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerson>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackOrganizationById(index: number, item: IOrganization) {
    return item.id;
  }

  trackOrganizationEmployeeById(index: number, item: IOrganizationEmployee) {
    return item.id;
  }
}
