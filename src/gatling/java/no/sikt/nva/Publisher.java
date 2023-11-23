package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;


public class Publisher {


    public static final ChainBuilder query =
    exec(http("QueryPublisher")
        .get("/publication-channels-v2/publisher?year=2023&query=institute")
            .check(jmesPath("hits[0].id").ofString().transform(uri ->
                            uri.split("/")[uri.split("/").length - 1])
                    .saveAs("publisherYear"))
            .check(jmesPath("hits[0].id").ofString().transform(uri ->
                            uri.split("/")[uri.split("/").length - 2])
                    .saveAs("publisherId")));

    public static final ChainBuilder get =
        query.exec(http("GetPublisher")
            .get("/publication-channels-v2/publisher/#{publisherId}/#{publisherYear}"));
}
