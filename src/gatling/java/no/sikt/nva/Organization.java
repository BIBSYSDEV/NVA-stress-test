package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;

class Organization {

    static final ChainBuilder get =
        exec(http("Organization")
                .get("#{organizationUri}"));

}
