export interface Principal {
    authenticated: boolean;
    authorities: string[];
    principal: {
        id: string;
        username: string;
        authorities: string[];
        accountNonExpired: boolean;
        accountNonLocked: boolean;
        credentialsNonExpired: boolean;
        enabled: boolean;
        createdAt: string;
        updatedAt: string;
    };
}
