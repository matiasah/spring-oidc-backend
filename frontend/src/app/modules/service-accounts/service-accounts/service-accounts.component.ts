import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { ServiceAccount } from 'src/app/interfaces/service-account';
import { ServiceAccountService } from 'src/app/services/service-account.service';
import { Paginator } from 'src/app/util/paginator';
import { CreateServiceAccountComponent } from '../create-service-account/create-service-account.component';
import { DeleteServiceAccountComponent } from '../delete-service-account/delete-service-account.component';
import { EditServiceAccountComponent } from '../edit-service-account/edit-service-account.component';

@Component({
    selector: 'app-service-accounts',
    templateUrl: './service-accounts.component.html',
    styleUrls: ['./service-accounts.component.scss']
})
export class ServiceAccountsComponent implements OnInit {

    // Datatable columns
    public displayedColumns: string[] = [
        'id',
        'clientName',
        'clientDescription',
        'options'
    ];

    // Paginator
    public paginator: Paginator<ServiceAccount>;

    // Data-source
    public dataSource: MatTableDataSource<ServiceAccount> = new MatTableDataSource();

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
    public clientNameSearch: string = '';
    public clientDescriptionSearch: string = '';

    public constructor(
        private serviceAccountService: ServiceAccountService,
        private dialog: MatDialog
    ) {
        // Create paginator
        this.paginator = this.serviceAccountService.paginator();

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
        const ref: MatDialogRef<CreateServiceAccountComponent> = this.dialog.open(CreateServiceAccountComponent, {
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

    public editEntry(serviceAccount: ServiceAccount): void {
        // Open edit dialog
        const ref: MatDialogRef<EditServiceAccountComponent> = this.dialog.open(EditServiceAccountComponent, {
            minWidth: '60vw',
            data: serviceAccount
        });

        // On close dialog
        ref.afterClosed().subscribe(
            () => {
                // Update paginator
                this.paginator.update();
            }
        );
    }

    public deleteEntry(serviceAccount: ServiceAccount) {
        // Open delete dialog
        const ref: MatDialogRef<DeleteServiceAccountComponent> = this.dialog.open(DeleteServiceAccountComponent, {
            minWidth: '60vw',
            data: serviceAccount
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
