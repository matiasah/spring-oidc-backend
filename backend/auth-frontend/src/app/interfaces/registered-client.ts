export interface RegisteredClient {
    id: string;
    clientId: string;
    clientIdIssuedAt: number;
    clientSecret: string;
    clientSecretExpiresAt: number;
    clientName: string;
    clientAuthenticationMethods: any[];
    authorizationGrantTypes: any[];
    redirectUris: string[];
    scopes: string[];
    clientSettings: any;
    tokenSettings: any;
}
