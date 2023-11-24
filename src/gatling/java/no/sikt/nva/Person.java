package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Person {

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

    private static final Map<CharSequence, String> headers_5 = Map.ofEntries(
            Map.entry("origin", "https://test.nva.aws.unit.no"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    public static final ChainBuilder query =
        exec(http("QueryPersons")
        .get("/cristin/person?name=eirik%20nilsen"));


    public static ChainBuilder get =
    exec(http("GetPerson")
        .get("/cristin/person/#{personId}")
        .headers(headers_5));

    public static final ChainBuilder personsByOrganization =
        exec(session -> {
                System.out.println(session.getString("organizationId"));
                return session;
            })
        .exec(http("PersonsByOrganization")
            .get("/cristin/organization/#{organizationId}.0.0.0/persons")
            .check(jmesPath("hits[0].identifiers[0].value").ofString().saveAs("personId")));

    public static final ChainBuilder employments =
        exec(http("EmploymentsOptions")
            .options("#{apiUri}/cristin/person/#{personId}/employment")
            .resources(http("Employments")
                .get("#{apiUri}/cristin/person/#{personId}/employment")
                .headers(headers_1)));

    public static final ChainBuilder personInOrganization =
                exec(
        http("PersonOrganizationOptions")
        .options("/cristin/person/#{personId}/organization/#{organizationId}.0.0.0")
        .resources(http("PersonOrganization")
            .get("/cristin/person/#{personId}/organization/#{organizationId}.0.0.0")
            .headers(headers_1)));
}
