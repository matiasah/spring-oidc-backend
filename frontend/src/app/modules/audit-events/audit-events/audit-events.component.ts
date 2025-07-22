import {Component, OnInit, ViewChild} from '@angular/core';
import {Paginator} from "../../../util/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {Observable} from "rxjs";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {AuditEvent} from "../../../interfaces/audit-event";
import {AuditEventService} from "../../../services/audit-event.service";

@Component({
    selector: 'app-audit-events',
    templateUrl: './audit-events.component.html',
    styleUrls: ['./audit-events.component.scss'],
    standalone: false
})
export class AuditEventsComponent implements OnInit {

    // Datatable columns
    public displayedColumns: string[] = [
        'id',
        'description',
        'createdAt'
    ];

    // Paginator
    public paginator: Paginator<AuditEvent>;

    // Data-source
    public dataSource: MatTableDataSource<AuditEvent> = new MatTableDataSource();

    // Indicate if still loading the table data
    public isLoading: Observable<boolean>;

    // Sort
    @ViewChild(MatSort, { static: true })
    public matSort!: MatSort;

    // Material paginator
    @ViewChild(MatPaginator, { static: true })
    public matPaginator!: MatPaginator;

    public constructor(
        private auditEventService: AuditEventService
    ) {
        // Create paginator
        this.paginator = this.auditEventService.paginator();

        // Observables
        this.isLoading = this.paginator.isLoadingSubject;
    }

    public ngOnInit() {
        // Initialize paginator
        this.paginator.init(this.dataSource, this.matPaginator, this.matSort);
    }

    public ngOnDestroy() {
        // Destroy paginator
        this.paginator.complete();
    }

}
