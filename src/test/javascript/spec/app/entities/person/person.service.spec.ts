/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PersonService } from 'app/entities/person/person.service';
import { IPerson, Person } from 'app/shared/model/person.model';

describe('Service Tests', () => {
  describe('Person Service', () => {
    let injector: TestBed;
    let service: PersonService;
    let httpMock: HttpTestingController;
    let elemDefault: IPerson;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PersonService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Person(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        currentDate,
        0,
        currentDate,
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
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
            bornon: currentDate.format(DATE_TIME_FORMAT),
            diedon: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a Person', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            bornon: currentDate.format(DATE_TIME_FORMAT),
            diedon: currentDate.format(DATE_TIME_FORMAT),
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            bornon: currentDate,
            diedon: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );
        service
          .create(new Person(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Person', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            permalink: 'BBBBBB',
            firstname: 'BBBBBB',
            lastname: 'BBBBBB',
            alsoknownas: 'BBBBBB',
            bio: 'BBBBBB',
            profileimageid: 1,
            roleinvestor: true,
            bornon: currentDate.format(DATE_TIME_FORMAT),
            bornontrustcode: 1,
            diedon: currentDate.format(DATE_TIME_FORMAT),
            diedontrustcode: 1,
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT),
            permalinkaliases: 'BBBBBB',
            gender: 'BBBBBB',
            rank: 1,
            primaryaffiliationid: 1,
            primarylocationid: 1,
            primaryimageid: 1,
            title: 'BBBBBB',
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
            bornon: currentDate,
            diedon: currentDate,
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

      it('should return a list of Person', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            permalink: 'BBBBBB',
            firstname: 'BBBBBB',
            lastname: 'BBBBBB',
            alsoknownas: 'BBBBBB',
            bio: 'BBBBBB',
            profileimageid: 1,
            roleinvestor: true,
            bornon: currentDate.format(DATE_TIME_FORMAT),
            bornontrustcode: 1,
            diedon: currentDate.format(DATE_TIME_FORMAT),
            diedontrustcode: 1,
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT),
            permalinkaliases: 'BBBBBB',
            gender: 'BBBBBB',
            rank: 1,
            primaryaffiliationid: 1,
            primarylocationid: 1,
            primaryimageid: 1,
            title: 'BBBBBB',
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
            bornon: currentDate,
            diedon: currentDate,
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

      it('should delete a Person', async () => {
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
