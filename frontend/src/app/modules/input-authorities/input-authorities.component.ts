import { FocusMonitor } from '@angular/cdk/a11y';
import { coerceBooleanProperty } from '@angular/cdk/coercion';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { Component, ElementRef, HostBinding, Input, OnChanges, OnDestroy, OnInit, Optional, Self, SimpleChanges, ViewChild } from '@angular/core';
import { ControlValueAccessor, UntypedFormControl, NgControl } from '@angular/forms';
import { MatLegacyAutocomplete as MatAutocomplete } from '@angular/material/legacy-autocomplete';
import { MatLegacyChipInputEvent as MatChipInputEvent } from '@angular/material/legacy-chips';
import { MatLegacyFormFieldControl as MatFormFieldControl } from '@angular/material/legacy-form-field';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Authority } from 'src/app/interfaces/authority';
import { AuthorityService } from 'src/app/services/authority.service';

@Component({
    selector: 'app-input-authorities',
    templateUrl: './input-authorities.component.html',
    styleUrls: ['./input-authorities.component.scss'],
    providers: [
        {
            provide: MatFormFieldControl,
            useExisting: InputAuthoritiesComponent
        }
    ]
})
export class InputAuthoritiesComponent implements OnInit, OnChanges, OnDestroy, MatFormFieldControl<Authority[]>, ControlValueAccessor {

    private static NEXT_ID: number = 0;

    // Authorities
    public authorities: Authority[] = [];

    // Authority name form control
    public authorityNameFormControl: UntypedFormControl = new UntypedFormControl();

    @ViewChild('authoritiesInput', { read: ElementRef, static: true })
    public authoritiesInputElement!: ElementRef<HTMLInputElement>;

    // List of authorities to choose
    public selectableAuthorities: Authority[] = [];

    // Map of authorities
    private authoritiesMap: { [name: string]: Authority } = {};

    // Observable
    public authorityNameChanges: Subject<string | Authority> = new Subject<string | Authority>();

    @ViewChild(MatAutocomplete, { static: true })
    public autocomplete!: MatAutocomplete;

    @Input()
    public placeholder: string = '';
    
    @HostBinding('id')
    public id: string = `input-authorities-${InputAuthoritiesComponent.NEXT_ID++}`;

    @Input()
    public get required(): boolean { return this.requiredFlag; }
    public set required(value: boolean) {
        this.requiredFlag = coerceBooleanProperty(value);
        this.stateChanges.next();
    }
    private requiredFlag: boolean = false;

    @Input()
    public get disabled(): boolean { return this.disabledFlag; }
    public set disabled(value: boolean) {
        this.disabledFlag = coerceBooleanProperty(value);
        this.stateChanges.next();
    }
    private disabledFlag: boolean = false;

    public get errorState(): boolean {
        return this.ngControl && this.ngControl.errors !== null && !!this.ngControl.touched;
    }

    public stateChanges: Subject<void> = new Subject<void>();
    public focused: boolean = false;

    @HostBinding('attr.aria-describedby')
    public describedBy: string = '';

    public onChange: (_: any) => void = (_: any) => { };
    public onTouched: () => void = () => { };

    // Separator key codes
    public separatorKeysCodes: number[] = [ENTER, COMMA];

    public constructor(
        private focusMonitor: FocusMonitor,
        private elementRef: ElementRef<HTMLElement>,
        private authorityService: AuthorityService,
        @Optional() @Self() public ngControl: NgControl
    ) {

        // Subscribe to input events
        this.authorityNameChanges.pipe(debounceTime(500)).subscribe(
            (authorities) => {
            
                // Activate method
                this.onChangeAuthoritiesString(authorities);

            }
        );

        // Subscribe to input events
        this.authorityNameChanges.subscribe(
            (authorities) => {
            
                // Activate method
                this.onChangeAuthoritiesObject(authorities);

            }
        );
    }

    public ngOnInit(): void {
        this.focusMonitor.monitor(this.elementRef, true).subscribe(origin => {
            if (this.focused && !origin) {
                this.onTouched();
            }
            this.focused = !!origin;
            this.stateChanges.next();
        });

        if (this.ngControl != null) {
            this.ngControl.valueAccessor = this;
        }
    }

    public ngOnChanges(changes: SimpleChanges): void {

    }

    public ngOnDestroy(): void {
        this.stateChanges.complete();
        this.focusMonitor.stopMonitoring(this.elementRef);
    }

    @HostBinding('class.floating')
    public get shouldLabelFloat(): boolean {
        return this.focused || !this.empty;
    }

    public get value(): Authority[] {
        return this.authorities;
    }

    public get empty(): boolean {
        return this.authorities.length == 0;
    }

    public setDescribedByIds(ids: string[]): void {
        this.describedBy = ids.join(' ');
    }

    public onContainerClick(event: MouseEvent): void {
        if ((event.target as Element).tagName.toLowerCase() !== 'input') {
            this.elementRef.nativeElement.querySelector('input')?.focus();
        }
    }

    public updateChanges(): void {
        this.onChange(this.value);
    }

    public writeValue(value: Authority[] | undefined | null): void {
        // If value is undefined
        if (value === undefined || value === null) {

            // Set empty array
            this.authorities = [];
        } else {

            // Set value
            this.authorities = value;
        }
    }

    public onChangeAuthoritiesString(authority: Authority | string | undefined | null): void {
        
        // If the value is not in the hash map
        if (!authority || (typeof authority == 'string' && !this.authoritiesMap[authority])) {

            // Search authorities that contain the word
            this.authorityService.findTop10ByNameContaining(authority ? authority : '').subscribe(
                (authorities) => {

                    // Copy authorities
                    this.selectableAuthorities = [...authorities];

                    // Clean authorities map
                    this.authoritiesMap = {};

                    // Add authorities to map
                    for (const selectableAuthority of authorities) {

                        // If the authority has a name
                        if (selectableAuthority.name) {

                            // Add authority to map
                            this.authoritiesMap[selectableAuthority.name] = selectableAuthority;
                        }

                    }

                    // Update changes
                    this.updateChanges();
                }
            );

        }

    }

    public onChangeAuthoritiesObject(authority: Authority | string | undefined | null): void {

        if (authority !== null && authority !== undefined) {

            if (typeof authority === 'object') {

                // Id to remove
                const idToRemove: string = authority.id;

                // Authority was selected
                this.authorities = [
                    ...this.authorities.filter(
                        authorityToRemove => authorityToRemove.id !== idToRemove
                    ),
                    authority
                ];

                // Empty input
                this.authorityNameFormControl.reset();
                this.authoritiesInputElement.nativeElement.value = '';

                // Update changes
                this.updateChanges();

            }

        }

    }

    public removeAuthority(authority: Authority): void {

        // Remove authority from array
        this.authorities = this.authorities.filter(
            (authorityToRemove) => {

                // If the authority is not the same
                return authorityToRemove.id != authority.id;
            }
        );

        // If no authorities are left
        if (this.authorities.length == 0) {

            // Unfocus
            this.focused = false;

        }

        // Update changes
        this.updateChanges();
    }

    public registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    public registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }

    public displayAuthority(authority: Authority): string {
        return authority ? authority.name : '';
    }

    public addChip(event: MatChipInputEvent): void {
        
        // If autocomplete is not open
        if (!this.autocomplete.isOpen) {

            // If the input has a value
            if (event.value) {
                // Chip value
                const chipValue = event.value.toLowerCase();

                // For every selectable authority
                for (let authority of this.selectableAuthorities) {

                    // If the authority name is the same
                    if (authority.name.toLowerCase() == chipValue) {

                        // Add authority to array
                        this.authorities = [
                            ...this.authorities,
                            authority
                        ];

                        // Clean input
                        this.authorityNameFormControl.reset();
                        this.authoritiesInputElement.nativeElement.value = '';

                        // Update changes
                        this.updateChanges();

                        // Break
                        break;
                    }

                }

            }

        }
    }

}
