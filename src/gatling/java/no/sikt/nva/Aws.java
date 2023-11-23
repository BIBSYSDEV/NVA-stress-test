package no.sikt.nva;

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;

import java.util.HashMap;

public final class Aws {

    private static final String USER_POOL_ID = Config.USER_POOL_ID;
    private static final String CLIENT_ID = Config.CLIENT_ID;
    private static final String AUTH_FLOW = "ADMIN_USER_PASSWORD_AUTH";
    private static final String PASSWORD = "P_1234_abcd";


    public static String login(String userName) {

        var accessToken = "";
        try (var identityProvider = CognitoIdentityProviderClient.builder().build()) {
            var authParameters = new HashMap<String, String>();
            authParameters.put("USERNAME", userName);
            authParameters.put("PASSWORD", PASSWORD);
            var adminSetUserPasswordResponse = identityProvider.adminSetUserPassword(
                    AdminSetUserPasswordRequest.builder()
                            .userPoolId(USER_POOL_ID)
                            .username(userName)
                            .password(PASSWORD)
                            .permanent(true)
                            .build()
            );
            var authResponse = identityProvider.adminInitiateAuth(
                    AdminInitiateAuthRequest.builder()
                            .userPoolId(USER_POOL_ID)
                            .clientId(CLIENT_ID)
                            .authFlow(AUTH_FLOW)
                            .authParameters(authParameters)
                            .build());
            accessToken = authResponse.authenticationResult().accessToken();
        }

        return accessToken;
    }
}
