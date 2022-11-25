import {
    Component,
    ElementRef,
    HostBinding,
    Input,
    OnChanges,
    OnDestroy,
    OnInit,
    Optional, Self, SimpleChanges,
    ViewChild
} from '@angular/core';
import { MatLegacyFormFieldControl as MatFormFieldControl } from "@angular/material/legacy-form-field";
import { ControlValueAccessor, UntypedFormControl, NgControl } from "@angular/forms";
import { Subject } from "rxjs";
import { coerceBooleanProperty } from "@angular/cdk/coercion";
import { COMMA, ENTER } from "@angular/cdk/keycodes";
import { FocusMonitor } from "@angular/cdk/a11y";
import { MatLegacyChipInputEvent as MatChipInputEvent } from "@angular/material/legacy-chips";

@Component({
    selector: 'app-input-tags',
    templateUrl: './input-tags.component.html',
    styleUrls: ['./input-tags.component.scss'],
    providers: [
        {
            provide: MatFormFieldControl,
            useExisting: InputTagsComponent
        }
    ]
})
export class InputTagsComponent implements OnInit, OnChanges, OnDestroy, MatFormFieldControl<string[]>, ControlValueAccessor {

    private static NEXT_ID: number = 0;

    // Tags
    public tags: string[] = [];

    // Tag form control
    public tagsFormControl: UntypedFormControl = new UntypedFormControl();

    @ViewChild('tagsInput', { read: ElementRef, static: true })
    public tagsInputElement!: ElementRef<HTMLInputElement>;

    // Observable
    public tagChanges: Subject<string> = new Subject<string>();

    @Input()
    public placeholder: string = '';

    @HostBinding('id')
    public id: string = `input-tags-${InputTagsComponent.NEXT_ID++}`;

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
        @Optional() @Self() public ngControl: NgControl
    ) {

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

    public get value(): string[] {
        return this.tags;
    }

    public get empty(): boolean {
        return this.tags.length == 0;
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

    public writeValue(value: string[] | undefined | null): void {
        // If value is undefined
        if (value === undefined || value === null) {

            // Set empty array
            this.tags = [];
        } else {

            // Set value
            this.tags = value;
        }
    }

    public removeTag(string: string): void {

        // Remove string from array
        const tags = this.tags.filter(
            (tagToRemove) => {

                // If the string is not the same
                return tagToRemove != string;
            }
        );

        // Focus input
        this.focused = true;

        // Set the tags
        this.tags = tags;

        // Update changes
        this.updateChanges();
    }

    public registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    public registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }

    public addChip(event: MatChipInputEvent): void {

        // If the input has a value
        if (event.value) {

            // Chip value
            const chipValue = event.value.toLowerCase();

            // If the chip value is not already in the array
            if (this.tags.find((tag) => tag == chipValue) == null) {

                // Add tag to array
                this.tags.push(chipValue);

            }

            // Clean input
            this.tagsFormControl.reset();
            this.tagsInputElement.nativeElement.value = '';

            // Update changes
            this.updateChanges();

        }

    }

}
