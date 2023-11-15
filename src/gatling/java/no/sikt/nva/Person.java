package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;

class Person {

    private static final Map<CharSequence, String> headers_5 = Map.ofEntries(
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    static ChainBuilder get =
    exec(http("GetPerson")
        .get("/cristin/person/#{cristinId}")
        .headers(headers_5));
}
