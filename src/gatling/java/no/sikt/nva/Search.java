package no.sikt.nva;

import java.util.Map;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

final class Search {

    private static final Map<CharSequence, String> headers_5 = Map.ofEntries(
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final String NVA_API_URI = "https://api.e2e.nva.aws.unit.no";
    private static final String QUERY = "test registration title";
    public static final String PAGING = "%22&results=10&from=0";
    private static final String QUERY_URI = NVA_API_URI + "/search/resources?query=%22" + QUERY + PAGING;
    private static final String FILTER_URI = QUERY_URI + "%22+AND+%28entityDescription.contributors.identity.id%3A%22https%3A%2F%2Fapi.e2e.nva.aws.unit.no%2Fcristin%2Fperson%2F1137659%22%29" + PAGING;


    static ChainBuilder listResources =
            exec(http("Resources")
                .get(NVA_API_URI + "/search/resources?")
                .headers(headers_5));

    static ChainBuilder query =
            exec(http("Query")
                .get(QUERY_URI));

    static ChainBuilder filterPerson =
            exec( http("FilterPerson")
                .get(FILTER_URI)
                .check(jmesPath("hits[0].associatedArtifacts[0].identifier").saveAs("fileIdentifier"))
                .check(jmesPath("hits[0].identifier").saveAs("identifier")));

    static ChainBuilder associatedRegistrations =
            exec(http("Associated registrations")
                .get(NVA_API_URI + "/search/resources?query=%22#{identifier}%22%20AND%20NOT%20(identifier:%22#{identifier}%22)"));
}
