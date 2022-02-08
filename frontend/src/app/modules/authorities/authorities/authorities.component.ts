import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { Authority } from 'src/app/interfaces/authority';
import { AuthorityService } from 'src/app/services/authority.service';
import { Paginator } from 'src/app/util/paginator';
import { CreateAuthorityComponent } from '../create-authority/create-authority.component';
import { DeleteAuthorityComponent } from '../delete-authority/delete-authority.component';
import { EditAuthorityComponent } from '../edit-authority/edit-authority.component';

@Component({
    selector: 'app-authorities',
    templateUrl: './authorities.component.html',
    styleUrls: ['./authorities.component.scss']
})
export class AuthoritiesComponent implements OnInit {

    // Datatable columns
    public displayedColumns: string[] = [
        'id',
        'name',
        'options'
    ];

    // Paginator
    public paginator: Paginator<Authority>;

    // Data-source
    public dataSource: MatTableDataSource<Authority> = new MatTableDataSource();

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
        private authorityService: AuthorityService,
        private dialog: MatDialog
    ) {
        // Create paginator
        this.paginator = this.authorityService.paginator();

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
        const ref: MatDialogRef<CreateAuthorityComponent> = this.dialog.open(CreateAuthorityComponent, {
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

    public editEntry(authority: Authority): void {
        // Open edit dialog
        const ref: MatDialogRef<EditAuthorityComponent> = this.dialog.open(EditAuthorityComponent, {
            minWidth: '60vw',
            data: authority
        });

        // On close dialog
        ref.afterClosed().subscribe(
            () => {
                // Update paginator
                this.paginator.update();
            }
        );
    }

    public deleteEntry(authority: Authority) {
        // Open delete dialog
        const ref: MatDialogRef<DeleteAuthorityComponent> = this.dialog.open(DeleteAuthorityComponent, {
            minWidth: '60vw',
            data: authority
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
