import { Authority } from "./authority";

export interface UserAccount {
    id: string;
    alias: string;
    authorities: Authority[];
    password: string;
    username: string;
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
    enabled: boolean;
    firstName: string;
    lastName: string;
}
