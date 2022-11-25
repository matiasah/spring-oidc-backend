import { Component, forwardRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ControlValueAccessor, NgForm, NG_VALUE_ACCESSOR } from '@angular/forms';
import { MatLegacyDialog as MatDialog } from '@angular/material/legacy-dialog';
import { MatLegacyPaginator as MatPaginator } from '@angular/material/legacy-paginator';
import { MatSort } from '@angular/material/sort';
import { MatLegacyTableDataSource as MatTableDataSource } from '@angular/material/legacy-table';
import { Observable } from 'rxjs';
import { Scope } from 'src/app/interfaces/scope';
import { ScopeService } from 'src/app/services/scope.service';
import { Paginator } from 'src/app/util/paginator';

@Component({
    selector: 'app-input-scopes-table',
    templateUrl: './input-scopes-table.component.html',
    styleUrls: ['./input-scopes-table.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => InputScopesTableComponent),
            multi: true
        }
    ]
})
export class InputScopesTableComponent implements OnInit, OnDestroy, ControlValueAccessor {

    // Datatable columns
    public displayedColumns: string[] = [
        'select',
        'name',
        'description'
    ];

    // Paginator
    public paginator: Paginator<Scope>;

    // Data-source
    public dataSource: MatTableDataSource<Scope> = new MatTableDataSource();

    // Indicate if still loading the table data
    public isLoading: Observable<boolean>;

    // Sort
    @ViewChild(MatSort, { static: true })
    public matSort!: MatSort;

    // Material paginator
    @ViewChild(MatPaginator, { static: true })
    public matPaginator!: MatPaginator;

    // Form
    @ViewChild('form', { static: true })
    public form!: NgForm;

    // Search input
    public nameSearch: string = '';

    // Selected scopes
    public scopes: Scope[] = [];

    public onChange: any;
    public onTouched: any;

    public constructor(
        private scopeService: ScopeService,
        private dialog: MatDialog
    ) {
        // Create paginator
        this.paginator = this.scopeService.paginator();

        // Observables
        this.isLoading = this.paginator.isLoadingSubject;
    }

    public ngOnInit() {
        // Initialize paginator
        this.paginator.init(this.dataSource, this.matPaginator, this.matSort, this.form);
    }

    public ngOnDestroy() {
        // Destroy paginator
        this.paginator.complete();
    }

    public select(scope: Scope): void {
        // Find scope in selected scopes
        const index = this.scopes.findIndex(s => s.id === scope.id);

        // If the index is different than -1, remove it
        if (index !== -1) {

            // Remove scope from selected scopes
            this.scopes.splice(index, 1);

        } else {

            // Add scope to selected scopes
            this.scopes.push(scope);
        }

        if (this.onChange) {
            this.onChange(this.scopes);
        }
        if (this.onTouched) {
            this.onTouched();
        }
    }

    public isSelected(scope: Scope): boolean {
        // Find scope in selected scopes
        const index = this.scopes.findIndex(s => s.id === scope.id);

        // If the index is different than -1
        if (index !== -1) {

            // Return true
            return true;

        }

        // Return false
        return false;
    }

    public writeValue(scope: Scope[]): void {

        // If scopes is defined
        if (scope) {

            // Set scopes
            this.scopes = scope;

        } else {

            // Set scopes to empty array
            this.scopes = [];

        }
        
        if (this.onChange) {
            this.onChange(this.scopes);
        }
    }

    public registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    public registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }

}
