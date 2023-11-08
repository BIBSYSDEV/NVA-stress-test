package no.sikt.nva;

import io.gatling.javaapi.core.Session;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Aws {

    private static final String USER_POOL_ID = "eu-west-1_lfd37eQxM";
    private static final String CLIENT_ID = "4ekg7vjqp0upin62bp3ikj00ts";
    private static final String AUTH_FLOW = "ADMIN_USER_PASSWORD_AUTH";
    private static final String USER = "test-user-with-author@test.no";
    private static final String PASSWORD = "P_1234_abcd";


    static String login(String userName) {

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

    static String getUserData(Session session, String key) {
        return getUserMap(session).get(key);
    }

    private static Map<String, String> getUserMap(Session session) {
        Map<String, String> userMap = new TreeMap<>();
        List<Map<String, String>> userData = (List<Map<String, String>>) session.getList("login").get(0);
        userData.forEach(object -> {
            userMap.put(object.get("Name"), object.get("Value"));
        });
        return userMap;
    }

}
