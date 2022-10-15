import { Injectable, OnDestroy } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable()
export class TestMatDialogRef<T, R = any> implements OnDestroy {

    /*
    public _containerInstance: MatDialogContainer;
    public id: string;
    */
    public componentInstance!: T;
    /*
    public disableClose: boolean | undefined;
    */

    private obsBeforeClosed: Subject<R> = new Subject<R>();
    private obsAfterClosed: Subject<R> = new Subject<R>();

    public ngOnDestroy(): void {
        this.obsBeforeClosed.complete();
        this.obsAfterClosed.complete();
    }

    public close(dialogResult: R): void {
        this.obsBeforeClosed.next(dialogResult);
        this.obsAfterClosed.next(dialogResult);
    }

    /*
    public afterOpened(): Observable<void> {
        throw new Error('Method not implemented.');
    }
    */

    public afterClosed(): Observable<any> {
        return this.obsAfterClosed;
    }

    public beforeClosed(): Observable<any> {
        return this.obsBeforeClosed;
    }

    /*
    public backdropClick(): Observable<MouseEvent> {
        throw new Error('Method not implemented.');
    }

    public keydownEvents(): Observable<KeyboardEvent> {
        throw new Error('Method not implemented.');
    }

    public updatePosition(position?: DialogPosition | undefined): this {
        throw new Error('Method not implemented.');
    }

    public updateSize(width?: string | undefined, height?: string | undefined): this {
        throw new Error('Method not implemented.');
    }

    public addPanelClass(classes: string | string[]): this {
        throw new Error('Method not implemented.');
    }

    public removePanelClass(classes: string | string[]): this {
        throw new Error('Method not implemented.');
    }

    public getState(): MatDialogState {
        throw new Error('Method not implemented.');
    }
    */

}
