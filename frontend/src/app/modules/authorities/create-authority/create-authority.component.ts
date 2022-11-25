import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatLegacyDialogRef as MatDialogRef } from '@angular/material/legacy-dialog';
import { MatLegacySnackBar as MatSnackBar } from '@angular/material/legacy-snack-bar';
import { Authority } from 'src/app/interfaces/authority';
import { AuthorityService } from 'src/app/services/authority.service';

@Component({
    selector: 'app-create-authority',
    templateUrl: './create-authority.component.html',
    styleUrls: ['./create-authority.component.scss']
})
export class CreateAuthorityComponent implements OnInit {

    // Authority to create
    public authority: Authority = {} as Authority;

    // Indicate if the account is being created
    public creating = false;

    // Form reference
    @ViewChild('form', { static: true })
    public form!: NgForm;

    public constructor(
        private dialogRef: MatDialogRef<CreateAuthorityComponent>,
        private snackBar: MatSnackBar,
        private authorityService: AuthorityService
    ) {

    }

    public ngOnInit() { }

    public onSubmit() {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that the account is being created
            this.creating = true;

            // Create
            this.authorityService.save(this.authority).subscribe(
                response => {
                    // Indicate that the authority is not being created
                    this.creating = false;

                    // Notify success
                    this.snackBar.open('The authority has been created', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                },
                error => {
                    // Indicate that the authority is not being created
                    this.creating = false;

                    // Notify error
                    this.snackBar.open('Failed to create authority, there was an error', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                }
            );
        }
    }

}
