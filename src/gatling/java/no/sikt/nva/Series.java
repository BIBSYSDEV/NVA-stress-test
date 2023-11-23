package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Series {

    public static final ChainBuilder query =
        exec(http("QuerySeries")
        .get("/publication-channels-v2/series?year=2023&query=physics")
                .check(jmesPath("hits[0].id").ofString().transform(uri ->
                                uri.split("/")[uri.split("/").length - 1])
                        .saveAs("seriesYear"))
                .check(jmesPath("hits[0].id").ofString().transform(uri ->
                                uri.split("/")[uri.split("/").length - 2])
                        .saveAs("seriesId")));

    public static final ChainBuilder get =
        query.exec(http("GetSeries")
            .get("/publication-channels-v2/series/#{seriesId}/#{seriesYear}"));
}
