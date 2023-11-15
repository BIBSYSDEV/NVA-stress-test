package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Cristin {

    private static final Map<CharSequence, String> headers_0 = Map.ofEntries(
            Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_1 = Map.ofEntries(
            Map.entry("authorization", "Bearer #{accessToken}"),
            Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );


    public static final ChainBuilder fundingSources =
        exec(http("FundingSources")
            .get("/cristin/funding-sources")
            .headers(headers_0)
    );

    public static final ChainBuilder personsByOrganization =
        exec(http("PersonsByOrganization")
            .get("/cristin/organization/#{organizationId}.0.0.0/persons")
                .check(jmesPath("hits[0].identifiers[0].value").ofString().saveAs("personId"))
            .headers(headers_0)
    );

    public static final ChainBuilder employments =
        exec(http("EmploymentsOptions")
        .options("#{apiUri}/cristin/person/#{personId}/employment")
        .headers(headers_0)
        .resources(http("Employments")
            .get("#{apiUri}/cristin/person/#{personId}/employment")
            .headers(headers_1)));

    public static final ChainBuilder picture =
        exec(http("Picture")
        .get("/cristin/person/43419/picture")
        .headers(headers_0)
    );

}
