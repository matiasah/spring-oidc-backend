import { Principal } from "./principal";
import { RegisteredClient } from "./registered-client";

export interface AuthorizationInfo {
    principal: Principal;
    client: RegisteredClient;
    scopes: string[];
    state: string;
}
