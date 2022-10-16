import {TestBed} from '@angular/core/testing';
import {SystemGuard} from './system.guard';
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {TestLocalStorage} from "../util/test-local-storage.spec";

describe('SystemGuard', () => {
    let guard: SystemGuard;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                HttpClientTestingModule
            ],
            providers: [
                {
                    provide: Storage,
                    useValue: new TestLocalStorage()
                },
                {
                    provide: Window,
                    useValue: {}
                }
            ]
        });
        guard = TestBed.inject(SystemGuard);
    });

    it('should be created', () => {
        expect(guard).toBeTruthy();
    });

});
