import { HttpClient, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { HttpTestingController, TestRequest, provideHttpClientTesting } from '@angular/common/http/testing';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { BrowserModule } from '@angular/platform-browser';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { Observable } from 'rxjs';
import { Paginator } from './paginator';
import { environment } from "../../environments/environment";
import { MaterialModule } from "../modules/material/material.module";
import { provideRouter } from '@angular/router';

@Component({
    selector: 'app-paginator-test',
    templateUrl: './paginator.spec.html'
})
class PaginatorTestComponent implements OnInit, OnDestroy {

    // Datatable columns
    public displayedColumns: string[] = ['nombre'];

    // Paginator
    public paginator!: Paginator<any>;

    // Data-source
    public dataSource: MatTableDataSource<any> = new MatTableDataSource();

    // Indicate if the component is still loading results
    public isLoading!: Observable<boolean>;

    // Sort
    @ViewChild(MatSort, { static: true })
    public matSort!: MatSort;

    // Paginator
    @ViewChild(MatPaginator, { static: true })
    public matPaginator!: MatPaginator;

    public constructor(
        private http: HttpClient
    ) {

    }

    public ngOnInit(): void {
        // Create paginator
        this.paginator = new Paginator(this.http, `${environment.host}/test`);

        // Observables
        this.isLoading = this.paginator.isLoadingSubject;

        // Initialize paginator
        this.paginator.init(this.dataSource, this.matPaginator, this.matSort);
    }

    public ngOnDestroy(): void {
        this.paginator.complete();
    }

}

describe('Paginator', () => {
    let component: PaginatorTestComponent;
    let fixture: ComponentFixture<PaginatorTestComponent>;
    let controller: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
    declarations: [
        PaginatorTestComponent
    ],
    imports: [BrowserModule,
        NoopAnimationsModule,
        MaterialModule],
    providers: [
        provideRouter([]),
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
    ]
}).compileComponents();
        controller = TestBed.inject(HttpTestingController);
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(PaginatorTestComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create component', () => {
        expect(component).toBeTruthy();
    });

    it('should not allow going to the next page', (done) => {
        const paginaA = {
            content: [
                { nombre: 'A' },
                { nombre: 'B' },
                { nombre: 'C' },
                { nombre: 'D' },
                { nombre: 'E' }
            ],
            pageable: {
                sort: {
                    sorted: false,
                    unsorted: true,
                    empty: true
                },
                pageNumber: 0,
                pageSize: 5,
                offset: 0,
                paged: true,
                unpaged: false
            },
            totalPages: 1,
            totalElements: 5,
            last: true,
            first: true,
            sort: {
                sorted: false,
                unsorted: true,
                empty: true
            },
            number: 0,
            numberOfElements: 5,
            size: 5,
            empty: false
        };

        fixture.whenStable().then(() => {
            // Aceptar petición a primera página
            const request: TestRequest = controller.expectOne(`${environment.host}/test?page=0&size=5`);
            request.flush(paginaA);
            expect(request.request).toBeTruthy();

            fixture.detectChanges();

            fixture.whenStable().then(() => {
                // Intentar cambiar a la siguiente página
                component.matPaginator.nextPage();

                // Rechazar petición
                controller.expectNone(`${environment.host}/test?page=1&size=5`);

                done();
            });
        });
    });

    it('should go to the next page', (done) => {
        const paginaA = {
            content: [
                { nombre: 'A' },
                { nombre: 'B' },
                { nombre: 'C' },
                { nombre: 'D' },
                { nombre: 'E' }
            ],
            pageable: {
                sort: {
                    sorted: false,
                    unsorted: true,
                    empty: true
                },
                pageNumber: 0,
                pageSize: 5,
                offset: 0,
                paged: true,
                unpaged: false
            },
            totalPages: 2,
            totalElements: 6,
            last: true,
            first: true,
            sort: {
                sorted: false,
                unsorted: true,
                empty: true
            },
            number: 0,
            numberOfElements: 5,
            size: 5,
            empty: false
        };

        const paginaB = {
            content: [
                { nombre: 'F' }
            ],
            pageable: {
                sort: {
                    sorted: false,
                    unsorted: true,
                    empty: true
                },
                pageNumber: 1,
                pageSize: 5,
                offset: 0,
                paged: true,
                unpaged: false
            },
            totalPages: 2,
            totalElements: 6,
            last: true,
            first: true,
            sort: {
                sorted: false,
                unsorted: true,
                empty: true
            },
            number: 1,
            numberOfElements: 1,
            size: 5,
            empty: false
        };

        fixture.whenStable().then(() => {
            // Aceptar petición a primera página
            const request: TestRequest = controller.expectOne(`${environment.host}/test?page=0&size=5`);
            request.flush(paginaA);
            expect(request.request).toBeTruthy();

            fixture.detectChanges();

            fixture.whenStable().then(() => {
                // Intentar cambiar a la siguiente página
                component.matPaginator.nextPage();

                // Aceptar petición
                const request2: TestRequest = controller.expectOne(`${environment.host}/test?page=1&size=5`);
                request2.flush(paginaB);
                expect(request2.request).toBeTruthy();

                done();
            });
        });
    });

    it('should go to the next page and go back to the previous one', (done) => {
        const paginaA = {
            content: [
                { nombre: 'A' },
                { nombre: 'B' },
                { nombre: 'C' },
                { nombre: 'D' },
                { nombre: 'E' }
            ],
            pageable: {
                sort: {
                    sorted: false,
                    unsorted: true,
                    empty: true
                },
                pageNumber: 0,
                pageSize: 5,
                offset: 0,
                paged: true,
                unpaged: false
            },
            totalPages: 2,
            totalElements: 6,
            last: true,
            first: true,
            sort: {
                sorted: false,
                unsorted: true,
                empty: true
            },
            number: 0,
            numberOfElements: 5,
            size: 5,
            empty: false
        };

        const paginaB = {
            content: [
                { nombre: 'F' }
            ],
            pageable: {
                sort: {
                    sorted: false,
                    unsorted: true,
                    empty: true
                },
                pageNumber: 1,
                pageSize: 5,
                offset: 0,
                paged: true,
                unpaged: false
            },
            totalPages: 2,
            totalElements: 6,
            last: true,
            first: true,
            sort: {
                sorted: false,
                unsorted: true,
                empty: true
            },
            number: 1,
            numberOfElements: 1,
            size: 5,
            empty: false
        };

        fixture.whenStable().then(() => {
            // Aceptar petición a primera página
            const request: TestRequest = controller.expectOne(`${environment.host}/test?page=0&size=5`);
            request.flush(paginaA);
            expect(request.request).toBeTruthy();

            fixture.detectChanges();

            fixture.whenStable().then(() => {
                // Intentar cambiar a la siguiente página
                component.matPaginator.nextPage();

                // Aceptar petición
                const request2: TestRequest = controller.expectOne(`${environment.host}/test?page=1&size=5`);
                request2.flush(paginaB);
                expect(request2.request).toBeTruthy();

                fixture.detectChanges();

                fixture.whenStable().then(() => {
                    // Intentar cambiar a la página anterior
                    component.matPaginator.previousPage();

                    // Aceptar petición
                    const request3: TestRequest = controller.expectOne(`${environment.host}/test?page=0&size=5`);
                    request3.flush(paginaA);
                    expect(request3.request).toBeTruthy();

                    fixture.detectChanges();

                    done();
                });
            });
        });
    });

    it('should prevent going back to the previous page', (done) => {
        const paginaA = {
            content: [
                { nombre: 'A' },
                { nombre: 'B' },
                { nombre: 'C' },
                { nombre: 'D' },
                { nombre: 'E' }
            ],
            pageable: {
                sort: {
                    sorted: false,
                    unsorted: true,
                    empty: true
                },
                pageNumber: 0,
                pageSize: 5,
                offset: 0,
                paged: true,
                unpaged: false
            },
            totalPages: 2,
            totalElements: 6,
            last: true,
            first: true,
            sort: {
                sorted: false,
                unsorted: true,
                empty: true
            },
            number: 0,
            numberOfElements: 5,
            size: 5,
            empty: false
        };

        fixture.whenStable().then(() => {
            // Aceptar petición a primera página
            const request: TestRequest = controller.expectOne(`${environment.host}/test?page=0&size=5`);
            request.flush(paginaA);
            expect(request.request).toBeTruthy();

            fixture.detectChanges();

            fixture.whenStable().then(() => {
                // Intentar cambiar a la página anterior
                component.matPaginator.previousPage();

                // Rechazar petición a página anterior
                controller.expectNone(`${environment.host}/test?page=-1&size=5`);

                // Rechazar petición a página actual
                controller.expectNone(`${environment.host}/test?page=0&size=5`);

                done();
            });
        });
    });

});
