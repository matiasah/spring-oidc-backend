import { Principal } from "./principal";
import { RegisteredClient } from "./registered-client";
import {Scope} from "./scope";

export interface AuthorizationInfo {
    principal: Principal;
    client: RegisteredClient;
    scopes: Scope[];
    state: string;
}
