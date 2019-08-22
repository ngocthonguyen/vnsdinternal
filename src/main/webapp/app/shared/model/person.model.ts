import { Moment } from 'moment';
import { IOrganization } from 'app/shared/model/organization.model';
import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';

export interface IPerson {
  id?: number;
  uuid?: string;
  permalink?: string;
  firstname?: string;
  lastname?: string;
  alsoknownas?: string;
  bio?: string;
  profileimageid?: number;
  roleinvestor?: boolean;
  bornon?: Moment;
  bornontrustcode?: number;
  diedon?: Moment;
  diedontrustcode?: number;
  createdat?: Moment;
  updatedat?: Moment;
  permalinkaliases?: string;
  gender?: string;
  rank?: number;
  primaryaffiliationid?: number;
  primarylocationid?: number;
  primaryimageid?: number;
  title?: string;
  homepageurl?: string;
  facebookurl?: string;
  twitterurl?: string;
  linkedinurl?: string;
  cityname?: string;
  regionname?: string;
  countrycode?: string;
  organization?: IOrganization;
  organizationEmployee?: IOrganizationEmployee;
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public uuid?: string,
    public permalink?: string,
    public firstname?: string,
    public lastname?: string,
    public alsoknownas?: string,
    public bio?: string,
    public profileimageid?: number,
    public roleinvestor?: boolean,
    public bornon?: Moment,
    public bornontrustcode?: number,
    public diedon?: Moment,
    public diedontrustcode?: number,
    public createdat?: Moment,
    public updatedat?: Moment,
    public permalinkaliases?: string,
    public gender?: string,
    public rank?: number,
    public primaryaffiliationid?: number,
    public primarylocationid?: number,
    public primaryimageid?: number,
    public title?: string,
    public homepageurl?: string,
    public facebookurl?: string,
    public twitterurl?: string,
    public linkedinurl?: string,
    public cityname?: string,
    public regionname?: string,
    public countrycode?: string,
    public organization?: IOrganization,
    public organizationEmployee?: IOrganizationEmployee
  ) {
    this.roleinvestor = this.roleinvestor || false;
  }
}
