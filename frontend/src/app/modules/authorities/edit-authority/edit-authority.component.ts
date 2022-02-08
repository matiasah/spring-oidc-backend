import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Authority } from 'src/app/interfaces/authority';
import { AuthorityService } from 'src/app/services/authority.service';

@Component({
    selector: 'app-edit-authority',
    templateUrl: './edit-authority.component.html',
    styleUrls: ['./edit-authority.component.scss']
})
export class EditAuthorityComponent implements OnInit {

    // Indicate if the authority is being edited
    public editing: boolean = false;

    // Form
    @ViewChild('form', { static: true })
    public form!: NgForm;

    public constructor(
        private dialogRef: MatDialogRef<EditAuthorityComponent>,
        private snackBar: MatSnackBar,
        private authorityService: AuthorityService,
        @Inject(MAT_DIALOG_DATA) public authority: Authority
    ) {

    }

    public ngOnInit(): void { }

    public onSubmit(): void {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that the authority is being edited
            this.editing = true;

            // Update
            this.authorityService.update(this.authority).subscribe(
                () => {
                    // Indicate that the authority is not being edited
                    this.editing = false;

                    // Notify success
                    this.snackBar.open('The authority has been updated', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                },
                () => {
                    // Indicate that the authority is not being edited
                    this.editing = false;

                    // Notify error
                    this.snackBar.open('Failed to update authority, there was an error', 'Ok', { duration: 4000 });

                    // Close modal
                    this.dialogRef.close();
                }
            );
        }
    }

}
