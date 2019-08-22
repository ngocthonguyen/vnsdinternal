/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OrganizationService } from 'app/entities/organization/organization.service';
import { IOrganization, Organization } from 'app/shared/model/organization.model';

describe('Service Tests', () => {
  describe('Organization Service', () => {
    let injector: TestBed;
    let service: OrganizationService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrganization;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(OrganizationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Organization(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        false,
        false,
        false,
        false,
        currentDate,
        0,
        currentDate,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            foundedon: currentDate.format(DATE_TIME_FORMAT),
            closedon: currentDate.format(DATE_TIME_FORMAT),
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Organization', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            foundedon: currentDate.format(DATE_TIME_FORMAT),
            closedon: currentDate.format(DATE_TIME_FORMAT),
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            foundedon: currentDate,
            closedon: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );
        service
          .create(new Organization(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Organization', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            permalink: 'BBBBBB',
            name: 'BBBBBB',
            alsoknownas: 'BBBBBB',
            shortdescription: 'BBBBBB',
            description: 'BBBBBB',
            profileimageid: 1,
            primaryrole: 'BBBBBB',
            rolecompany: true,
            roleinvestor: true,
            rolegroup: true,
            roleschool: true,
            foundedon: currentDate.format(DATE_TIME_FORMAT),
            foundedontrustcode: 1,
            closedon: currentDate.format(DATE_TIME_FORMAT),
            closedontrustcode: 1,
            numemployeesmin: 1,
            numemployeesmax: 1,
            totalfundingusd: 1,
            totalfundingvnd: 1,
            stockexchange: 'BBBBBB',
            stocksymbol: 'BBBBBB',
            numberofinvestments: 1,
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT),
            permalinkaliases: 'BBBBBB',
            investortype: 'BBBBBB',
            contactemail: 'BBBBBB',
            phonenumber: 'BBBBBB',
            rank: 1,
            primaryimageid: 1,
            ownedbyid: 1,
            headquartersid: 1,
            acquiredbyid: 1,
            ipoid: 1,
            domain: 'BBBBBB',
            homepageurl: 'BBBBBB',
            facebookurl: 'BBBBBB',
            twitterurl: 'BBBBBB',
            linkedinurl: 'BBBBBB',
            cityname: 'BBBBBB',
            regionname: 'BBBBBB',
            countrycode: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            foundedon: currentDate,
            closedon: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Organization', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            permalink: 'BBBBBB',
            name: 'BBBBBB',
            alsoknownas: 'BBBBBB',
            shortdescription: 'BBBBBB',
            description: 'BBBBBB',
            profileimageid: 1,
            primaryrole: 'BBBBBB',
            rolecompany: true,
            roleinvestor: true,
            rolegroup: true,
            roleschool: true,
            foundedon: currentDate.format(DATE_TIME_FORMAT),
            foundedontrustcode: 1,
            closedon: currentDate.format(DATE_TIME_FORMAT),
            closedontrustcode: 1,
            numemployeesmin: 1,
            numemployeesmax: 1,
            totalfundingusd: 1,
            totalfundingvnd: 1,
            stockexchange: 'BBBBBB',
            stocksymbol: 'BBBBBB',
            numberofinvestments: 1,
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT),
            permalinkaliases: 'BBBBBB',
            investortype: 'BBBBBB',
            contactemail: 'BBBBBB',
            phonenumber: 'BBBBBB',
            rank: 1,
            primaryimageid: 1,
            ownedbyid: 1,
            headquartersid: 1,
            acquiredbyid: 1,
            ipoid: 1,
            domain: 'BBBBBB',
            homepageurl: 'BBBBBB',
            facebookurl: 'BBBBBB',
            twitterurl: 'BBBBBB',
            linkedinurl: 'BBBBBB',
            cityname: 'BBBBBB',
            regionname: 'BBBBBB',
            countrycode: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            foundedon: currentDate,
            closedon: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Organization', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
