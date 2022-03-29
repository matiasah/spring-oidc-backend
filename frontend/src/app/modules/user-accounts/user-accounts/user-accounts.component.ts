import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { UserAccount } from 'src/app/interfaces/user-account';
import { UserAccountService } from 'src/app/services/user-account.service';
import { Paginator } from 'src/app/util/paginator';
import { CreateUserAccountComponent } from '../create-user-account/create-user-account.component';
import { DeleteUserAccountComponent } from '../delete-user-account/delete-user-account.component';
import { EditUserAccountComponent } from '../edit-user-account/edit-user-account.component';

@Component({
    selector: 'app-user-accounts',
    templateUrl: './user-accounts.component.html',
    styleUrls: ['./user-accounts.component.scss']
})
export class UserAccountsComponent implements OnInit {

    // Datatable columns
    public displayedColumns: string[] = [
        'id',
        'alias',
        'username',
        'enabled',
        'options'
    ];

    // Paginator
    public paginator: Paginator<UserAccount>;

    // Data-source
    public dataSource: MatTableDataSource<UserAccount> = new MatTableDataSource();

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
    public aliasSearch: string = '';

    public constructor(
        private userAccountService: UserAccountService,
        private dialog: MatDialog
    ) {
        // Create paginator
        this.paginator = this.userAccountService.paginator();

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
        const ref: MatDialogRef<CreateUserAccountComponent> = this.dialog.open(CreateUserAccountComponent, {
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

    public editEntry(userAccount: UserAccount): void {
        // Open edit dialog
        const ref: MatDialogRef<EditUserAccountComponent> = this.dialog.open(EditUserAccountComponent, {
            minWidth: '60vw',
            data: userAccount
        });

        // On close dialog
        ref.afterClosed().subscribe(
            () => {
                // Update paginator
                this.paginator.update();
            }
        );
    }

    public deleteEntry(userAccount: UserAccount) {
        // Open delete dialog
        const ref: MatDialogRef<DeleteUserAccountComponent> = this.dialog.open(DeleteUserAccountComponent, {
            minWidth: '60vw',
            data: userAccount
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
