import { HttpClient, HttpParams } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { MatLegacyPaginator as MatPaginator, LegacyPageEvent as PageEvent } from '@angular/material/legacy-paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatLegacyTableDataSource as MatTableDataSource } from '@angular/material/legacy-table';
import moment from 'moment';
import { BehaviorSubject, Subject, Subscription } from 'rxjs';
import { debounceTime, skip } from 'rxjs/operators';

export interface SpringPage<T> {
    content: T[];
    pageable: {
        sort: {
            unsorted: boolean;
            sorted: boolean;
            empty: boolean;
        },
        pageSize: number;
        pageNumber: number;
        offset: number;
        paged: boolean;
        unpaged: boolean;
    };
    last: boolean;
    totalPages: number;
    totalElements: number;
    first: boolean;
    sort: {
        unsorted: boolean;
        sorted: boolean;
        empty: boolean;
    };
    number: number;
    numberOfElements: number;
    size: number;
    empty: boolean;
}

export class Paginator<T> {

    public constructor(
        private http: HttpClient,
        private path: string
    ) {

    }

    // Ordenamiento
    private sort: { [key: string]: string } = {};

    // Columnas adicionales a ordenar
    private sortWith: { [key: string]: string } = {};

    // Página actual
    private page: PageEvent = {
        pageIndex: 0,
        pageSize: 5,
        length: 0
    };

    // Parámetros adicionales
    private params: { [key: string]: string | Date | moment.Moment } = {};

    public pageSubject: BehaviorSubject<PageEvent> = new BehaviorSubject(this.page);
    public isLoadingSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    public onEntry: Subject<T> = new Subject<T>();

    private loadSubscription?: Subscription;
    private pageSubscription!: Subscription;
    private sortSubscription!: Subscription;
    private formSubscription!: Subscription;

    // Datos
    private dataSource!: MatTableDataSource<T>;

    // Paginator
    private paginator!: MatPaginator;

    public init(dataSource: MatTableDataSource<T>, paginator: MatPaginator, sort: MatSort, form?: NgForm, omitUpdate?: boolean): void {
        // Fijar MatTableDataSource
        this.dataSource = dataSource;

        // Fijar paginador
        this.paginator = paginator;

        // Al cambiar la pagina actual
        this.pageSubscription = paginator.page.subscribe((pageEvent: PageEvent) => {
            this.page = pageEvent;
            this.update();
        });

        // Al cambiar el orden de alguna columna
        this.sortSubscription = sort.sortChange.subscribe((event: Sort) => {
            if (event.direction === '') {
                this.sort = {};
            } else {
                this.sort = { [event.active]: event.direction };
            }
            this.update();
        });

        // Si hay formulario
        if (form && form.valueChanges) {
            // Al modificar el contenido del formulario
            form.valueChanges
                // Despues de 500ms de la última modificación
                .pipe(debounceTime(500))
                // Saltar primer valor
                .pipe(skip(1))
                // Suscribirse
                .subscribe(
                    params => {
                        // Actualizar respuesta
                        Object.assign(this.params, params);

                        // Volver a página inicial
                        this.page.pageIndex = 0;

                        // Actualizar datos del formulario
                        this.update();
                    }
                );
        }

        if (!omitUpdate) {
            this.update();
        }
    }

    public complete(): void {
        this.pageSubject.complete();
        this.isLoadingSubject.complete();
        this.onEntry.complete();

        if (this.loadSubscription) {
            this.loadSubscription.unsubscribe();
        }
        if (this.pageSubscription) {
            this.pageSubscription.unsubscribe();
        }
        if (this.sortSubscription) {
            this.sortSubscription.unsubscribe();
        }
        if (this.formSubscription) {
            this.formSubscription.unsubscribe();
        }
    }

    public setPath(path: string) {
        this.path = path;
    }

    public getSortWith(): { [key: string]: string } {
        return this.sortWith;
    }

    public getParams(): { [key: string]: string | Date | moment.Moment } {
        return this.params;
    }

    public update(): void {
        // Establecer parámetros
        let params: HttpParams = new HttpParams();

        // Establecer página
        params = params.append('page', this.page.pageIndex.toString());

        // Establecer tamaño
        params = params.append('size', this.page.pageSize.toString());

        // Ordenamiento
        for (const [column, direction] of Object.entries(this.sort)) {
            params = params.append('sort', column + ',' + direction);

            // Ordenamiento de columnas adicionales
            if (this.sortWith[column]) {
                params = params.append('sort', this.sortWith[column] + ',' + direction);
            }
        }

        // Parámetros adicionales
        for (const [key, value] of Object.entries(this.params)) {
            // Si el valor no es nulo o indefinido
            if (value !== null && value !== undefined) {
                if (typeof value === 'string') {
                    params = params.append(key, value);
                } else if (value instanceof Date || moment.isMoment(value)) {
                    params = params.append(key, value.toISOString());
                } else {
                    params = params.append(key, value);
                }
            }
        }

        // Indicar que se encuentra cargando resultados
        this.isLoadingSubject.next(true);

        // Cancelar subscripción anterior
        if (this.loadSubscription) {
            this.loadSubscription.unsubscribe();
        }

        this.loadSubscription = this.http.get<SpringPage<T>>(this.path, { params }).subscribe(
            page => {
                // Obtener datos
                this.dataSource.data = page.content;

                // Por cada entrada
                for (const entry of page.content) {
                    this.onEntry.next(entry);
                }

                this.paginator.pageIndex = page.number;
                this.paginator.pageSize = page.size;
                this.paginator.length = page.totalElements;

                // Eliminar subscripción
                this.loadSubscription = undefined;

                // Indicar que no se encuentra cargando resultados
                this.isLoadingSubject.next(false);
            },
            error => {
                // Eliminar subscripción
                this.loadSubscription = undefined;

                // Indicar que no se encuentra cargando resultados
                this.isLoadingSubject.next(false);
            }
        );
    }

}
