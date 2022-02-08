import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserAccount } from 'src/app/interfaces/user-account';
import { UserAccountService } from 'src/app/services/user-account.service';

@Component({
    selector: 'app-edit-user-account',
    templateUrl: './edit-user-account.component.html',
    styleUrls: ['./edit-user-account.component.scss']
})
export class EditUserAccountComponent implements OnInit {

    // Indicate if the user account is being edited
    public editing: boolean = false;

    // Form
    @ViewChild('form', { static: true })
    public form!: NgForm;

    public constructor(
        private dialogRef: MatDialogRef<EditUserAccountComponent>,
        private snackBar: MatSnackBar,
        private userAccountService: UserAccountService,
        @Inject(MAT_DIALOG_DATA) public userAccount: UserAccount
    ) {

    }

    public ngOnInit(): void { }

    public onSubmit(): void {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that the user account is being edited
            this.editing = true;

            // Update
            this.userAccountService.update(this.userAccount).subscribe(
                () => {
                    // Indicate that the user account is not being edited
                    this.editing = false;

                    // Notify success
                    this.snackBar.open('The user account has been updated', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                },
                () => {
                    // Indicate that the user account is not being edited
                    this.editing = false;

                    // Notify error
                    this.snackBar.open('Failed to update user account, there was an error', 'Ok', { duration: 4000 });

                    // Close modal
                    this.dialogRef.close();
                }
            );
        }
    }

}
