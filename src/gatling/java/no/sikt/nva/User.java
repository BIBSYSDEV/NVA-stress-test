package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

public class User {

    private static final Map<CharSequence, String> HEADERS = Map.ofEntries(
            Map.entry("accept", "application/json"),
            Map.entry("authorization", "Bearer #{accessToken}"),
            Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    public static final ChainBuilder role =
        exec(http("GetRoleOptions")
        .options("/users-roles/roles/Creator")
        .resources(http("GetRole")
            .get("/users-roles/roles/Creator")
            .headers(HEADERS)));

    public static final ChainBuilder query =
        Customer.list.exec(http("QueryUsersOptions")
        .options("/users-roles/institutions/users?institution=https%3A%2F%2Fapi.test.nva.aws.unit.no%2Fcustomer%2F#{customerId}")
        .resources(http("QueryUsers")
            .get("/users-roles/institutions/users?institution=https%3A%2F%2Fapi.test.nva.aws.unit.no%2Fcustomer%2F#{customerId}")
            .check(jmesPath("users[0].username").ofString().saveAs("username"))
            .headers(HEADERS)));

    public static final ChainBuilder get =
    query.exec(http("GetUserOptions")
        .options("/users-roles/users/#{username}")
        .resources(http("GetUser")
            .get("/users-roles/users/#{username}")
            .headers(HEADERS)));

    public static final ChainBuilder userInfo =
    exec(http("UserInfoOptions")
        .options("/users-roles/userinfo")
        .resources(http("UserInfo")
            .get("/users-roles/userinfo")
            .headers(HEADERS)));

}
