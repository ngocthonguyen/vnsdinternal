import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IOrganization, Organization } from 'app/shared/model/organization.model';
import { OrganizationService } from './organization.service';
import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';
import { OrganizationEmployeeService } from 'app/entities/organization-employee';

@Component({
  selector: 'jhi-organization-update',
  templateUrl: './organization-update.component.html'
})
export class OrganizationUpdateComponent implements OnInit {
  isSaving: boolean;

  organizationemployees: IOrganizationEmployee[];

  editForm = this.fb.group({
    id: [],
    uuid: [null, [Validators.required]],
    permalink: [null, [Validators.required]],
    name: [null, [Validators.required]],
    alsoknownas: [],
    shortdescription: [],
    description: [],
    profileimageid: [],
    primaryrole: [],
    rolecompany: [],
    roleinvestor: [],
    rolegroup: [],
    roleschool: [],
    foundedon: [],
    foundedontrustcode: [],
    closedon: [],
    closedontrustcode: [],
    numemployeesmin: [],
    numemployeesmax: [],
    totalfundingusd: [],
    totalfundingvnd: [],
    stockexchange: [],
    stocksymbol: [],
    numberofinvestments: [],
    createdat: [],
    updatedat: [],
    permalinkaliases: [],
    investortype: [],
    contactemail: [],
    phonenumber: [],
    rank: [],
    primaryimageid: [],
    ownedbyid: [],
    headquartersid: [],
    acquiredbyid: [],
    ipoid: [],
    domain: [],
    homepageurl: [],
    facebookurl: [],
    twitterurl: [],
    linkedinurl: [],
    cityname: [],
    regionname: [],
    countrycode: [],
    organizationEmployee: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected organizationService: OrganizationService,
    protected organizationEmployeeService: OrganizationEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ organization }) => {
      this.updateForm(organization);
    });
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

  updateForm(organization: IOrganization) {
    this.editForm.patchValue({
      id: organization.id,
      uuid: organization.uuid,
      permalink: organization.permalink,
      name: organization.name,
      alsoknownas: organization.alsoknownas,
      shortdescription: organization.shortdescription,
      description: organization.description,
      profileimageid: organization.profileimageid,
      primaryrole: organization.primaryrole,
      rolecompany: organization.rolecompany,
      roleinvestor: organization.roleinvestor,
      rolegroup: organization.rolegroup,
      roleschool: organization.roleschool,
      foundedon: organization.foundedon != null ? organization.foundedon.format(DATE_TIME_FORMAT) : null,
      foundedontrustcode: organization.foundedontrustcode,
      closedon: organization.closedon != null ? organization.closedon.format(DATE_TIME_FORMAT) : null,
      closedontrustcode: organization.closedontrustcode,
      numemployeesmin: organization.numemployeesmin,
      numemployeesmax: organization.numemployeesmax,
      totalfundingusd: organization.totalfundingusd,
      totalfundingvnd: organization.totalfundingvnd,
      stockexchange: organization.stockexchange,
      stocksymbol: organization.stocksymbol,
      numberofinvestments: organization.numberofinvestments,
      createdat: organization.createdat != null ? organization.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: organization.updatedat != null ? organization.updatedat.format(DATE_TIME_FORMAT) : null,
      permalinkaliases: organization.permalinkaliases,
      investortype: organization.investortype,
      contactemail: organization.contactemail,
      phonenumber: organization.phonenumber,
      rank: organization.rank,
      primaryimageid: organization.primaryimageid,
      ownedbyid: organization.ownedbyid,
      headquartersid: organization.headquartersid,
      acquiredbyid: organization.acquiredbyid,
      ipoid: organization.ipoid,
      domain: organization.domain,
      homepageurl: organization.homepageurl,
      facebookurl: organization.facebookurl,
      twitterurl: organization.twitterurl,
      linkedinurl: organization.linkedinurl,
      cityname: organization.cityname,
      regionname: organization.regionname,
      countrycode: organization.countrycode,
      organizationEmployee: organization.organizationEmployee
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const organization = this.createFromForm();
    if (organization.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationService.update(organization));
    } else {
      this.subscribeToSaveResponse(this.organizationService.create(organization));
    }
  }

  private createFromForm(): IOrganization {
    return {
      ...new Organization(),
      id: this.editForm.get(['id']).value,
      uuid: this.editForm.get(['uuid']).value,
      permalink: this.editForm.get(['permalink']).value,
      name: this.editForm.get(['name']).value,
      alsoknownas: this.editForm.get(['alsoknownas']).value,
      shortdescription: this.editForm.get(['shortdescription']).value,
      description: this.editForm.get(['description']).value,
      profileimageid: this.editForm.get(['profileimageid']).value,
      primaryrole: this.editForm.get(['primaryrole']).value,
      rolecompany: this.editForm.get(['rolecompany']).value,
      roleinvestor: this.editForm.get(['roleinvestor']).value,
      rolegroup: this.editForm.get(['rolegroup']).value,
      roleschool: this.editForm.get(['roleschool']).value,
      foundedon:
        this.editForm.get(['foundedon']).value != null ? moment(this.editForm.get(['foundedon']).value, DATE_TIME_FORMAT) : undefined,
      foundedontrustcode: this.editForm.get(['foundedontrustcode']).value,
      closedon: this.editForm.get(['closedon']).value != null ? moment(this.editForm.get(['closedon']).value, DATE_TIME_FORMAT) : undefined,
      closedontrustcode: this.editForm.get(['closedontrustcode']).value,
      numemployeesmin: this.editForm.get(['numemployeesmin']).value,
      numemployeesmax: this.editForm.get(['numemployeesmax']).value,
      totalfundingusd: this.editForm.get(['totalfundingusd']).value,
      totalfundingvnd: this.editForm.get(['totalfundingvnd']).value,
      stockexchange: this.editForm.get(['stockexchange']).value,
      stocksymbol: this.editForm.get(['stocksymbol']).value,
      numberofinvestments: this.editForm.get(['numberofinvestments']).value,
      createdat:
        this.editForm.get(['createdat']).value != null ? moment(this.editForm.get(['createdat']).value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat']).value != null ? moment(this.editForm.get(['updatedat']).value, DATE_TIME_FORMAT) : undefined,
      permalinkaliases: this.editForm.get(['permalinkaliases']).value,
      investortype: this.editForm.get(['investortype']).value,
      contactemail: this.editForm.get(['contactemail']).value,
      phonenumber: this.editForm.get(['phonenumber']).value,
      rank: this.editForm.get(['rank']).value,
      primaryimageid: this.editForm.get(['primaryimageid']).value,
      ownedbyid: this.editForm.get(['ownedbyid']).value,
      headquartersid: this.editForm.get(['headquartersid']).value,
      acquiredbyid: this.editForm.get(['acquiredbyid']).value,
      ipoid: this.editForm.get(['ipoid']).value,
      domain: this.editForm.get(['domain']).value,
      homepageurl: this.editForm.get(['homepageurl']).value,
      facebookurl: this.editForm.get(['facebookurl']).value,
      twitterurl: this.editForm.get(['twitterurl']).value,
      linkedinurl: this.editForm.get(['linkedinurl']).value,
      cityname: this.editForm.get(['cityname']).value,
      regionname: this.editForm.get(['regionname']).value,
      countrycode: this.editForm.get(['countrycode']).value,
      organizationEmployee: this.editForm.get(['organizationEmployee']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganization>>) {
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

  trackOrganizationEmployeeById(index: number, item: IOrganizationEmployee) {
    return item.id;
  }
}
