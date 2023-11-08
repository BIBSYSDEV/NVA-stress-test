package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;

final class File {

    static ChainBuilder get =
            exec(http("File")
                    .get("/download/public/#{identifier}/files/#{fileIdentifier}"));
}
