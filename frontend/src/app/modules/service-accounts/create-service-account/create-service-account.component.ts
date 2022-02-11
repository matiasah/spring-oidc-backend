import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServiceAccount } from 'src/app/interfaces/service-account';
import { ServiceAccountService } from 'src/app/services/service-account.service';

@Component({
    selector: 'app-create-service-account',
    templateUrl: './create-service-account.component.html',
    styleUrls: ['./create-service-account.component.scss']
})
export class CreateServiceAccountComponent implements OnInit {

    // Service account to create
    public serviceAccount: ServiceAccount = {} as ServiceAccount;

    // Indicate if the account is being created
    public creating = false;

    // Form reference
    @ViewChild('form', { static: true })
    public form!: NgForm;

    public constructor(
        private dialogRef: MatDialogRef<CreateServiceAccountComponent>,
        private snackBar: MatSnackBar,
        private serviceAccountService: ServiceAccountService
    ) {

    }

    public ngOnInit() { }

    public onSubmit() {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that the account is being created
            this.creating = true;

            // Create
            this.serviceAccountService.save(this.serviceAccount).subscribe(
                response => {
                    // Indicate that the service account is not being created
                    this.creating = false;

                    // Notify success
                    this.snackBar.open('The service account has been created', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                },
                error => {
                    // Indicate that the service account is not being created
                    this.creating = false;

                    // Notify error
                    this.snackBar.open('Failed to create service account, there was an error', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                }
            );
        }
    }

}
