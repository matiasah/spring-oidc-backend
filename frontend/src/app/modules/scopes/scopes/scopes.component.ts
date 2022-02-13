import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { Scope } from 'src/app/interfaces/scope';
import { ScopeService } from 'src/app/services/scope.service';
import { Paginator } from 'src/app/util/paginator';
import { CreateScopeComponent } from '../create-scope/create-scope.component';
import { DeleteScopeComponent } from '../delete-scope/delete-scope.component';
import { EditScopeComponent } from '../edit-scope/edit-scope.component';

@Component({
    selector: 'app-scopes',
    templateUrl: './scopes.component.html',
    styleUrls: ['./scopes.component.scss']
})
export class ScopesComponent implements OnInit {

    // Datatable columns
    public displayedColumns: string[] = [
        'id',
        'name',
        'description',
        'options'
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

    public createEntry(): void {
        // Open create dialog
        const ref: MatDialogRef<CreateScopeComponent> = this.dialog.open(CreateScopeComponent, {
            minWidth: '60vw'
        });

        // On close dialog
        ref.afterClosed().subscribe(
            () => {
                // Update paginator
                this.paginator.update();
            }
        );
    }

    public editEntry(scope: Scope): void {
        // Open edit dialog
        const ref: MatDialogRef<EditScopeComponent> = this.dialog.open(EditScopeComponent, {
            minWidth: '60vw',
            data: scope
        });

        // On close dialog
        ref.afterClosed().subscribe(
            () => {
                // Update paginator
                this.paginator.update();
            }
        );
    }

    public deleteEntry(scope: Scope) {
        // Open delete dialog
        const ref: MatDialogRef<DeleteScopeComponent> = this.dialog.open(DeleteScopeComponent, {
            minWidth: '60vw',
            data: scope
        });

        // On close dialog
        ref.afterClosed().subscribe(
            response => {
                // Update paginator
                this.paginator.update();
            }
        );
    }

}
