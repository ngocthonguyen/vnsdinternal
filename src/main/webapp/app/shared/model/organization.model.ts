import { Moment } from 'moment';
import { IPerson } from 'app/shared/model/person.model';
import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';

export interface IOrganization {
  id?: number;
  uuid?: string;
  permalink?: string;
  name?: string;
  alsoknownas?: string;
  shortdescription?: string;
  description?: string;
  profileimageid?: number;
  primaryrole?: string;
  rolecompany?: boolean;
  roleinvestor?: boolean;
  rolegroup?: boolean;
  roleschool?: boolean;
  foundedon?: Moment;
  foundedontrustcode?: number;
  closedon?: Moment;
  closedontrustcode?: number;
  numemployeesmin?: number;
  numemployeesmax?: number;
  totalfundingusd?: number;
  totalfundingvnd?: number;
  stockexchange?: string;
  stocksymbol?: string;
  numberofinvestments?: number;
  createdat?: Moment;
  updatedat?: Moment;
  permalinkaliases?: string;
  investortype?: string;
  contactemail?: string;
  phonenumber?: string;
  rank?: number;
  primaryimageid?: number;
  ownedbyid?: number;
  headquartersid?: number;
  acquiredbyid?: number;
  ipoid?: number;
  domain?: string;
  homepageurl?: string;
  facebookurl?: string;
  twitterurl?: string;
  linkedinurl?: string;
  cityname?: string;
  regionname?: string;
  countrycode?: string;
  owners?: IPerson[];
  organizationEmployee?: IOrganizationEmployee;
}

export class Organization implements IOrganization {
  constructor(
    public id?: number,
    public uuid?: string,
    public permalink?: string,
    public name?: string,
    public alsoknownas?: string,
    public shortdescription?: string,
    public description?: string,
    public profileimageid?: number,
    public primaryrole?: string,
    public rolecompany?: boolean,
    public roleinvestor?: boolean,
    public rolegroup?: boolean,
    public roleschool?: boolean,
    public foundedon?: Moment,
    public foundedontrustcode?: number,
    public closedon?: Moment,
    public closedontrustcode?: number,
    public numemployeesmin?: number,
    public numemployeesmax?: number,
    public totalfundingusd?: number,
    public totalfundingvnd?: number,
    public stockexchange?: string,
    public stocksymbol?: string,
    public numberofinvestments?: number,
    public createdat?: Moment,
    public updatedat?: Moment,
    public permalinkaliases?: string,
    public investortype?: string,
    public contactemail?: string,
    public phonenumber?: string,
    public rank?: number,
    public primaryimageid?: number,
    public ownedbyid?: number,
    public headquartersid?: number,
    public acquiredbyid?: number,
    public ipoid?: number,
    public domain?: string,
    public homepageurl?: string,
    public facebookurl?: string,
    public twitterurl?: string,
    public linkedinurl?: string,
    public cityname?: string,
    public regionname?: string,
    public countrycode?: string,
    public owners?: IPerson[],
    public organizationEmployee?: IOrganizationEmployee
  ) {
    this.rolecompany = this.rolecompany || false;
    this.roleinvestor = this.roleinvestor || false;
    this.rolegroup = this.rolegroup || false;
    this.roleschool = this.roleschool || false;
  }
}
