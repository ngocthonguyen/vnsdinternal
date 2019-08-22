import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrganizationEmployee } from 'app/shared/model/organization-employee.model';

type EntityResponseType = HttpResponse<IOrganizationEmployee>;
type EntityArrayResponseType = HttpResponse<IOrganizationEmployee[]>;

@Injectable({ providedIn: 'root' })
export class OrganizationEmployeeService {
  public resourceUrl = SERVER_API_URL + 'api/organization-employees';

  constructor(protected http: HttpClient) {}

  create(organizationEmployee: IOrganizationEmployee): Observable<EntityResponseType> {
    return this.http.post<IOrganizationEmployee>(this.resourceUrl, organizationEmployee, { observe: 'response' });
  }

  update(organizationEmployee: IOrganizationEmployee): Observable<EntityResponseType> {
    return this.http.put<IOrganizationEmployee>(this.resourceUrl, organizationEmployee, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrganizationEmployee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrganizationEmployee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
