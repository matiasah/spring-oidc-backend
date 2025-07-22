import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ServiceAccount} from "../../../interfaces/service-account";
import {ServiceAccountService} from "../../../services/service-account.service";

@Component({
    selector: 'app-delete-service-account',
    templateUrl: './delete-service-account.component.html',
    styleUrls: ['./delete-service-account.component.scss'],
    standalone: false
})
export class DeleteServiceAccountComponent implements OnInit {

    // Indicate if the service account is being deleted
    public deleting: boolean = false;

    public constructor(
        private dialogRef: MatDialogRef<DeleteServiceAccountComponent>,
        private snackBar: MatSnackBar,
        private serviceAccountService: ServiceAccountService,
        @Inject(MAT_DIALOG_DATA) public serviceAccount: ServiceAccount
    ) {

    }

    public ngOnInit(): void {

    }

    public onSubmit(): void {
        // Indicate that the service account is being deleted
        this.deleting = true;

        // Delete
        this.serviceAccountService.deleteById(this.serviceAccount.id).subscribe(
            () => {
                // Indicate that the service account is not being deleted
                this.deleting = false;

                // Notify success
                this.snackBar.open('The service account has been deleted', 'Ok', {duration: 2000});

                // Close modal
                this.dialogRef.close();
            },
            () => {
                // Indicate that the service account is not being deleted
                this.deleting = false;

                // Notify error
                this.snackBar.open('Failed to delete service account, there was an error', 'Ok', {duration: 2000});

                // Close modal
                this.dialogRef.close();
            }
        );
    }

}
