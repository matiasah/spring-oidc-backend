import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Scope } from 'src/app/interfaces/scope';
import { ScopeService } from 'src/app/services/scope.service';

@Component({
    selector: 'app-edit-scope',
    templateUrl: './edit-scope.component.html',
    styleUrls: ['./edit-scope.component.scss'],
    standalone: false
})
export class EditScopeComponent implements OnInit {

    // Indicate if the scope is being edited
    public editing: boolean = false;

    // Form
    @ViewChild('form', { static: true })
    public form!: NgForm;

    public constructor(
        private dialogRef: MatDialogRef<EditScopeComponent>,
        private snackBar: MatSnackBar,
        private scopeService: ScopeService,
        @Inject(MAT_DIALOG_DATA) public scope: Scope
    ) {

    }

    public ngOnInit(): void { }

    public onSubmit(): void {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that the scope is being edited
            this.editing = true;

            // Update
            this.scopeService.update(this.scope).subscribe(
                () => {
                    // Indicate that the scope is not being edited
                    this.editing = false;

                    // Notify success
                    this.snackBar.open('The scope has been updated', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                },
                () => {
                    // Indicate that the scope is not being edited
                    this.editing = false;

                    // Notify error
                    this.snackBar.open('Failed to update scope, there was an error', 'Ok', { duration: 4000 });

                    // Close modal
                    this.dialogRef.close();
                }
            );
        }
    }

}
