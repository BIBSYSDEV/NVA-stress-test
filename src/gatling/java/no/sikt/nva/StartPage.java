package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

final class StartPage {

    private static final Map<CharSequence, String> headers_0 = Map.ofEntries(
            Map.entry("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "document"),
            Map.entry("sec-fetch-mode", "navigate"),
            Map.entry("sec-fetch-site", "none"),
            Map.entry("sec-fetch-user", "?1"),
            Map.entry("upgrade-insecure-requests", "1")
    );

    private static final Map<CharSequence, String> headers_1 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("content-type", "application/x-amz-json-1.1"),
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "cross-site"),
            Map.entry("x-amz-target", "AWSCognitoIdentityProviderService.GetUser"),
            Map.entry("x-amz-user-agent", "aws-amplify/5.0.4 auth framework/0")
    );

    private static final Map<CharSequence, String> headers_2 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "manifest"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-origin")
    );

    private static final Map<CharSequence, String> headers_3 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("access-control-request-headers", "authorization"),
            Map.entry("access-control-request-method", "GET"),
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_4 = Map.ofEntries(
            Map.entry("authorization", "Bearer #{accessToken}"),
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_5 = Map.ofEntries(
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final String NVA_URI = "https://e2e.nva.aws.unit.no";
    private static final String COGNITO_URI = "https://cognito-idp.eu-west-1.amazonaws.com";
    private static final String NVA_API_URI = "https://api.e2e.nva.aws.unit.no";

    static final ChainBuilder startPage =
            feed(csv("data/users.csv").eager().circular())
                    .exec(session -> session.set("accessToken", Aws.login(session.get("userName"))))
                    .exec(http("Startpage")
                                    .get(NVA_URI + "/")
                                    .basicAuth("osteloff","osteloff")
                                    .resources(http("Manifest")
                                            .get(NVA_URI + "/manifest.json")
                                            .check(status().is(401)), http("Resources")
                                            .get("/search/resources?")));


    static final ChainBuilder startPageLogin =
            feed(csv("data/users.csv").eager().circular())
                    .exec(session -> session.set("accessToken", Aws.login(session.get("userName"))))
                    .exec(http("Startpage")
                            .get(NVA_URI + "/")
                            .headers(headers_0)
                            .basicAuth("osteloff","osteloff"))
                    .exec(http("Login")
                            .post(COGNITO_URI + "/")
                            .headers(headers_1)
                            .body(ElFileBody("no/sikt/nva/startregistration/token.dat"))
                            .check(jmesPath("*").ofList().saveAs("login")))
                    .exec(session -> session.set("customerUri", Aws.getUserMap(session).get("custom:customerId")))
                    .exec(http("Manifest")
                            .get("/manifest.json")
                            .headers(headers_2)
                            .check(status().is(401)))
                    .exec(http("Customer")
                            .options("#{customerUri}")
                            .headers(headers_3))
                    .exec(http("request_4")
                            .get("#{customerUri}")
                            .headers(headers_4))
                    .exec(http("Resources")
                            .get(NVA_API_URI + "/search/resources?")
                            .headers(headers_5));
}