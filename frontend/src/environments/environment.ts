// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  token_item: 'oidc_token_dev',
  host: 'http://127.0.0.1:8080',
  clientId: 'eb5d9c2a-b5aa-4467-b4b4-79ae82eee0d8',
  clientSecret: '05009f8d-97d7-4601-8852-b196ba7fdf41',
  scope: [
    'openid',
    'profile',
    'email',
    'create_authority',
    'read_authority',
    'update_authority',
    'delete_authority',
    'create_service_account',
    'read_service_account',
    'update_service_account',
    'delete_service_account',
    'create_user',
    'read_user',
    'update_user',
    'delete_user',
    'create_scope',
    'read_scope',
    'update_scope',
    'delete_scope',
  ].join(' '),
  redirectUri: 'http://127.0.0.1/oauth/callback'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
