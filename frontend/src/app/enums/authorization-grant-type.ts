export enum AuthorizationGrantType {
    AUTHORIZATION_CODE = "authorization_code",
    IMPLICIT = "implicit",
    REFRESH_TOKEN = "refresh_token",
    CLIENT_CREDENTIALS = "client_credentials",
    PASSWORD = "password",
    JWT_BEARER = "urn:ietf:params:oauth:grant-type:jwt-bearer",
}

export const authorizationGrantTypeNames: {[key in AuthorizationGrantType]: string} = {
    [AuthorizationGrantType.AUTHORIZATION_CODE]: "Authorization Code",
    [AuthorizationGrantType.IMPLICIT]: "Implicit",
    [AuthorizationGrantType.REFRESH_TOKEN]: "Refresh Token",
    [AuthorizationGrantType.CLIENT_CREDENTIALS]: "Client Credentials",
    [AuthorizationGrantType.PASSWORD]: "Password",
    [AuthorizationGrantType.JWT_BEARER]: "JWT Bearer",
};