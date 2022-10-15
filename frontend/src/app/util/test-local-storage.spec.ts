export class TestLocalStorage {

    private store: any;

    public constructor() {
        this.store = {};
    }

    public getItem(key: string): string {
        return key in this.store ? this.store[key] : null;
    }

    public setItem(key: string, value: string): void {
        this.store[key] = `${value}`;
    }

    public removeItem(key: string): void {
        delete this.store[key];
    }

    public clear() {
        this.store = {};
    }

    public toString() {
        return `TestLocalStorage{${ JSON.stringify(this.store) }}`;
    }

}
