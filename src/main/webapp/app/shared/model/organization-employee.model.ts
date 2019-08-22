import { IOrganization } from 'app/shared/model/organization.model';
import { IPerson } from 'app/shared/model/person.model';

export interface IOrganizationEmployee {
  id?: number;
  role?: string;
  organizations?: IOrganization[];
  people?: IPerson[];
}

export class OrganizationEmployee implements IOrganizationEmployee {
  constructor(public id?: number, public role?: string, public organizations?: IOrganization[], public people?: IPerson[]) {}
}
