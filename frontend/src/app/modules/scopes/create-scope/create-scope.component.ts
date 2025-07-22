import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Scope } from 'src/app/interfaces/scope';
import { ScopeService } from 'src/app/services/scope.service';

@Component({
    selector: 'app-create-scope',
    templateUrl: './create-scope.component.html',
    styleUrls: ['./create-scope.component.scss'],
    standalone: false
})
export class CreateScopeComponent implements OnInit {

    // Scope to create
    public scope: Scope = {} as Scope;

    // Indicate if the account is being created
    public creating = false;

    // Form reference
    @ViewChild('form', { static: true })
    public form!: NgForm;

    public constructor(
        private dialogRef: MatDialogRef<CreateScopeComponent>,
        private snackBar: MatSnackBar,
        private scopeService: ScopeService
    ) {

    }

    public ngOnInit() { }

    public onSubmit() {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that the account is being created
            this.creating = true;

            // Create
            this.scopeService.save(this.scope).subscribe(
                response => {
                    // Indicate that the scope is not being created
                    this.creating = false;

                    // Notify success
                    this.snackBar.open('The scope has been created', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                },
                error => {
                    // Indicate that the scope is not being created
                    this.creating = false;

                    // Notify error
                    this.snackBar.open('Failed to create scope, there was an error', 'Ok', { duration: 2000 });

                    // Close modal
                    this.dialogRef.close();
                }
            );
        }
    }

}
