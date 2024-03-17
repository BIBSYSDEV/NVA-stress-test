package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

public final class Journal {

    private static final Map<CharSequence, String> headers_3 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("access-control-request-headers", "authorization"),
            Map.entry("access-control-request-method", "GET"),
            Map.entry("origin", "https://" + Config.DOMAIN),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_4 = Map.ofEntries(
            Map.entry("origin", "https://" + Config.DOMAIN),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    public static final String JOURNAL_QUERY = "/publication-channels-v2/journal?query=chemical&year=2023&offset=0&size=10";

    public static final ChainBuilder query =
        exec(http("QueryJournal")
            .get(JOURNAL_QUERY)
                .check(jmesPath("hits[0].id").ofString().transform(uri ->
                                uri.split("/")[uri.split("/").length - 1])
                        .saveAs("journalYear"))
                .check(jmesPath("hits[0].id").ofString().transform(uri ->
                                uri.split("/")[uri.split("/").length - 2])
                        .saveAs("journalId"))
                .check(jmesPath("hits[0].id").ofString().saveAs("journalUri"))
                .check(jmesPath("*").ofString().saveAs("journalResponse"))
            .headers(headers_4))
        .exec(session -> {
            System.out.println(session.getString("journalResponse"));
            return session;
        })
            ;

    public static final ChainBuilder get =
        query.exec(http("GetJournal")
            .get("/publication-channels-v2/journal/#{journalId}/#{journalYear}")
            .headers(headers_4));
}
