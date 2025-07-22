import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Authority } from 'src/app/interfaces/authority';
import { AuthorityService } from 'src/app/services/authority.service';

@Component({
    selector: 'app-delete-authority',
    templateUrl: './delete-authority.component.html',
    styleUrls: ['./delete-authority.component.scss'],
    standalone: false
})
export class DeleteAuthorityComponent implements OnInit {

    // Indicate if the authority is being deleted
    public deleting: boolean = false;

    public constructor(
        private dialogRef: MatDialogRef<DeleteAuthorityComponent>,
        private snackBar: MatSnackBar,
        private authorityService: AuthorityService,
        @Inject(MAT_DIALOG_DATA) public authority: Authority
    ) {

    }

    public ngOnInit(): void {

    }

    public onSubmit(): void {
        // Indicate that the authority is being deleted
        this.deleting = true;

        // Delete
        this.authorityService.deleteById(this.authority.id).subscribe(
            () => {
                // Indicate that the authority is not being deleted
                this.deleting = false;

                // Notify success
                this.snackBar.open('The authority has been deleted', 'Ok', { duration: 2000 });

                // Close modal
                this.dialogRef.close();
            },
            () => {
                // Indicate that the authority is not being deleted
                this.deleting = false;

                // Notify error
                this.snackBar.open('Failed to delete authority, there was an error', 'Ok', { duration: 2000 });

                // Close modal
                this.dialogRef.close();
            }
        );
    }

}
