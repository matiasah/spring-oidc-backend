export enum ClientAuthenticationMethod {
    CLIENT_SECRET_BASIC = "client_secret_basic",
    CLIENT_SECRET_POST = "client_secret_post",
    CLIENT_SECRET_JWT = "client_secret_jwt",
    PRIVATE_KEY_JWT = "private_key_jwt",
    NONE = "none",
}

export const clientAuthenticationMethodNames: {[key in ClientAuthenticationMethod]: string} = {
    [ClientAuthenticationMethod.CLIENT_SECRET_BASIC]: "Client Secret Basic",
    [ClientAuthenticationMethod.CLIENT_SECRET_POST]: "Client Secret POST",
    [ClientAuthenticationMethod.CLIENT_SECRET_JWT]: "Client Secret JWT",
    [ClientAuthenticationMethod.PRIVATE_KEY_JWT]: "Private Key JWT",
    [ClientAuthenticationMethod.NONE]: "None",
};
