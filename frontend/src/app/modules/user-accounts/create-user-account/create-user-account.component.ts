import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserAccount } from 'src/app/interfaces/user-account';
import { UserAccountService } from 'src/app/services/user-account.service';

@Component({
    selector: 'app-create-user-account',
    templateUrl: './create-user-account.component.html',
    styleUrls: ['./create-user-account.component.scss'],
    standalone: false
})
export class CreateUserAccountComponent implements OnInit {

    // User account to create
    public userAccount: UserAccount = {
        authorities: [],
        enabled: true
    } as any as UserAccount;

    // Indicate if the account is being created
    public creating = false;

    // Form reference
    @ViewChild('form', { static: true })
    public form!: NgForm;

    public constructor(
        private dialogRef: MatDialogRef<CreateUserAccountComponent>,
        private snackBar: MatSnackBar,
        private userAccountService: UserAccountService
    ) {

    }

    public ngOnInit() { }

    public onSubmit() {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that the account is being created
            this.creating = true;

            // Create
            this.userAccountService.save(this.userAccount).subscribe(
                response => {
                    // Indicate that the user account is not being created
                    this.creating = false;

                    // Notify success
                    this.snackBar.open('The user account has been created', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                },
                error => {
                    // Indicate that the user account is not being created
                    this.creating = false;

                    // Notify error
                    this.snackBar.open('Failed to create user account, there was an error', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                }
            );
        }
    }

}
