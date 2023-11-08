package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;

final class Customer {
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


    static final ChainBuilder get =
            exec(http("CustomerOptions")
                    .options("#{customerUri}")
                    .headers(headers_3))
            .exec(http("GetCustomer")
                    .get("#{customerUri}")
                    .headers(headers_4));
}
