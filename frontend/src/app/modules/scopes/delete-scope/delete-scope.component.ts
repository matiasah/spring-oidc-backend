import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Scope } from 'src/app/interfaces/scope';
import { ScopeService } from 'src/app/services/scope.service';

@Component({
    selector: 'app-delete-scope',
    templateUrl: './delete-scope.component.html',
    styleUrls: ['./delete-scope.component.scss']
})
export class DeleteScopeComponent implements OnInit {

    // Indicate if the scope is being deleted
    public deleting: boolean = false;

    public constructor(
        private dialogRef: MatDialogRef<DeleteScopeComponent>,
        private snackBar: MatSnackBar,
        private scopeService: ScopeService,
        @Inject(MAT_DIALOG_DATA) public scope: Scope
    ) {

    }

    public ngOnInit(): void {

    }

    public onSubmit(): void {
        // Indicate that the scope is being deleted
        this.deleting = true;

        // Delete
        this.scopeService.deleteById(this.scope.id).subscribe(
            () => {
                // Indicate that the scope is not being deleted
                this.deleting = false;

                // Notify success
                this.snackBar.open('The scope has been deleted', 'Ok', { duration: 2000 });

                // Close modal
                this.dialogRef.close();
            },
            () => {
                // Indicate that the scope is not being deleted
                this.deleting = false;

                // Notify error
                this.snackBar.open('Failed to delete scope, there was an error', 'Ok', { duration: 2000 });

                // Close modal
                this.dialogRef.close();
            }
        );
    }

}
