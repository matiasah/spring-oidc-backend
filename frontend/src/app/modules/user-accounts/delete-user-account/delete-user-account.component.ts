import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserAccount } from 'src/app/interfaces/user-account';
import { UserAccountService } from 'src/app/services/user-account.service';

@Component({
    selector: 'app-delete-user-account',
    templateUrl: './delete-user-account.component.html',
    styleUrls: ['./delete-user-account.component.scss'],
    standalone: false
})
export class DeleteUserAccountComponent implements OnInit {

    // Indicate if the authority is being deleted
    public deleting: boolean = false;

    public constructor(
        private dialogRef: MatDialogRef<DeleteUserAccountComponent>,
        private snackBar: MatSnackBar,
        private userAccountService: UserAccountService,
        @Inject(MAT_DIALOG_DATA) public userAccount: UserAccount
    ) {

    }

    public ngOnInit(): void {

    }

    public onSubmit(): void {
        // Indicate that the user account is being deleted
        this.deleting = true;

        // Delete
        this.userAccountService.deleteById(this.userAccount.id).subscribe(
            () => {
                // Indicate that the user account is not being deleted
                this.deleting = false;

                // Notify success
                this.snackBar.open('The user account has been deleted', 'Ok', { duration: 2000 });

                // Close modal
                this.dialogRef.close();
            },
            () => {
                // Indicate that the user account is not being deleted
                this.deleting = false;

                // Notify error
                this.snackBar.open('Failed to delete user account, there was an error', 'Ok', { duration: 2000 });

                // Close modal
                this.dialogRef.close();
            }
        );
    }

}
