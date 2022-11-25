import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { ServiceAccount } from "../../../interfaces/service-account";
import { NgForm } from "@angular/forms";
import { ClientAuthenticationMethod, clientAuthenticationMethodNames } from "../../../enums/client-authentication-method";
import { AuthorizationGrantType, authorizationGrantTypeNames } from "../../../enums/authorization-grant-type";
import { MAT_LEGACY_DIALOG_DATA as MAT_DIALOG_DATA, MatLegacyDialogRef as MatDialogRef } from "@angular/material/legacy-dialog";
import { MatLegacySnackBar as MatSnackBar } from "@angular/material/legacy-snack-bar";
import { ServiceAccountService } from "../../../services/service-account.service";

@Component({
    selector: 'app-edit-service-account',
    templateUrl: './edit-service-account.component.html',
    styleUrls: ['./edit-service-account.component.scss']
})
export class EditServiceAccountComponent implements OnInit {

    // Indicate if the service account is being edited
    public editing: boolean = false;

    // Form reference
    @ViewChild('scopeForm', { static: true })
    public form!: NgForm;

    // Client authentication methods
    public clientAuthenticationMethods: ClientAuthenticationMethod[] = Object.values(ClientAuthenticationMethod);

    // Client authentication methods names
    public clientAuthenticationMethodsNames = clientAuthenticationMethodNames;

    // Authorization grant types
    public authorizationGrantTypes: AuthorizationGrantType[] = Object.values(AuthorizationGrantType);

    // Authorization grant types names
    public authorizationGrantTypesNames = authorizationGrantTypeNames;

    public constructor(
        private dialogRef: MatDialogRef<EditServiceAccountComponent>,
        private snackBar: MatSnackBar,
        private serviceAccountService: ServiceAccountService,
        @Inject(MAT_DIALOG_DATA) public serviceAccount: ServiceAccount
    ) {

    }

    public ngOnInit() { }

    public onSubmit(): void {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that the service account is being edited
            this.editing = true;

            // Update
            this.serviceAccountService.update(this.serviceAccount).subscribe(
                () => {
                    // Indicate that the service account is not being edited
                    this.editing = false;

                    // Notify success
                    this.snackBar.open('The service account has been updated', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                },
                () => {
                    // Indicate that the service account is not being edited
                    this.editing = false;

                    // Notify error
                    this.snackBar.open('Failed to update service account, there was an error', 'Ok', { duration: 4000 });

                    // Close modal
                    this.dialogRef.close();
                }
            );
        }
    }

    public addRedirectUri(): void {
        this.serviceAccount.redirectUris.push('');
    }

    public removeRedirectUri(index: number): void {
        this.serviceAccount.redirectUris.splice(index, 1);
    }

    public trackByIdx(index: number, obj: any): any {
        return index;
    }

}
