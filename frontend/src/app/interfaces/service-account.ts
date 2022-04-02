import { AuthorizationGrantType } from "../enums/authorization-grant-type";
import { ClientAuthenticationMethod } from "../enums/client-authentication-method";
import { Authority } from "./authority";
import { ClientSettings } from "./client-settings";
import { Scope } from "./scope";
import { TokenSettings } from "./token-settings";

export interface ServiceAccount {
    id: string;
    clientId: string;
    clientIdIssuedAt: string;
    clientSecret: string;
    clientSecretExpiresAt: string;
    clientName: string;
    clientDescription: string;
    clientAuthenticationMethods: ClientAuthenticationMethod[];
    authorizationGrantTypes: AuthorizationGrantType[];
    redirectUris: string[];
    scopes: Scope[];
    clientSettings: ClientSettings;
    tokenSettings: TokenSettings;
    authorities: Authority[];
    tags: string[];
}
