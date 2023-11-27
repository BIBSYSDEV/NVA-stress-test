package no.sikt.nva;

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

import java.util.HashMap;

public final class Aws {

    private static final SsmClient ssmClient = SsmClient.builder().build();

    private static final String USER_POOL_ID =
            ssmClient.getParameter(GetParameterRequest.builder()
                    .name("/CognitoUserPoolId")
                    .build()).parameter().value();;
    private static final String CLIENT_ID =
            ssmClient.getParameter(GetParameterRequest.builder()
                    .name("/CognitoUserPoolAppClientId")
                    .build()).parameter().value();
    private static final String AUTH_FLOW = "ADMIN_USER_PASSWORD_AUTH";
    private static final String PASSWORD = "P_1234_abcd";


    public static String login(String userName) {

        System.out.println(USER_POOL_ID);

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

        System.out.println(accessToken);

        return accessToken;
    }
}
