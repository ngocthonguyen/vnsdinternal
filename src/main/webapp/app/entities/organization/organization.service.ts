import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrganization } from 'app/shared/model/organization.model';

type EntityResponseType = HttpResponse<IOrganization>;
type EntityArrayResponseType = HttpResponse<IOrganization[]>;

@Injectable({ providedIn: 'root' })
export class OrganizationService {
  public resourceUrl = SERVER_API_URL + 'api/organizations';

  constructor(protected http: HttpClient) {}

  create(organization: IOrganization): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(organization);
    return this.http
      .post<IOrganization>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(organization: IOrganization): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(organization);
    return this.http
      .put<IOrganization>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOrganization>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrganization[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(organization: IOrganization): IOrganization {
    const copy: IOrganization = Object.assign({}, organization, {
      foundedon: organization.foundedon != null && organization.foundedon.isValid() ? organization.foundedon.toJSON() : null,
      closedon: organization.closedon != null && organization.closedon.isValid() ? organization.closedon.toJSON() : null,
      createdat: organization.createdat != null && organization.createdat.isValid() ? organization.createdat.toJSON() : null,
      updatedat: organization.updatedat != null && organization.updatedat.isValid() ? organization.updatedat.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.foundedon = res.body.foundedon != null ? moment(res.body.foundedon) : null;
      res.body.closedon = res.body.closedon != null ? moment(res.body.closedon) : null;
      res.body.createdat = res.body.createdat != null ? moment(res.body.createdat) : null;
      res.body.updatedat = res.body.updatedat != null ? moment(res.body.updatedat) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((organization: IOrganization) => {
        organization.foundedon = organization.foundedon != null ? moment(organization.foundedon) : null;
        organization.closedon = organization.closedon != null ? moment(organization.closedon) : null;
        organization.createdat = organization.createdat != null ? moment(organization.createdat) : null;
        organization.updatedat = organization.updatedat != null ? moment(organization.updatedat) : null;
      });
    }
    return res;
  }
}
