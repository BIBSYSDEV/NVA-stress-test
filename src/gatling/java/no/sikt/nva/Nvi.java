package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;


public class Nvi {

    private static final Map<CharSequence, String> headers_1 = Map.ofEntries(
            Map.entry("authorization", "Bearer #{accessToken}"),
            Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    public static final ChainBuilder query =
        exec(http("QueryCandidatesOptions")
        .options("/scientific-index/candidate")
        .resources(http("QueryCandidates")
            .get("/scientific-index/candidate")
                .check(jmesPath("*").ofString().saveAs("queryResponse"))
                .check(jmesPath("hits[0].identifier").ofString().saveAs("candidateId"))
            .headers(headers_1)))
                .exec(session -> {
                    System.out.println(session.getString("queryRespons"));
                    return session;
                });

    public static final ChainBuilder get =
    query.exec(http("GetCandidateOptions")
        .options("/scientific-index/candidate/#{candidateId}")
        .resources(http("GetCandidate")
            .get("/scientific-index/candidate/#{candidateId}")
            .headers(headers_1)));

    public static final ChainBuilder period =
    exec(http("PeriodOptions")
        .options("/scientific-index/period")
        .resources(http("Period")
            .get("/scientific-index/period")
            .headers(headers_1)));
}
