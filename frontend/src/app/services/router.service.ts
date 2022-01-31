import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class RouterService {

    public readonly backUrl: BehaviorSubject<undefined | string> = new BehaviorSubject(undefined) as BehaviorSubject<undefined | string>;

}
