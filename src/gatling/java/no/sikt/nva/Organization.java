package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Organization {

    public static final ChainBuilder get =
        exec(http("Organization")
            .get("/cristin/organization/#{organizationId}.0.0.0"));

    public static final ChainBuilder query =
        exec(http("QueryOrganization")
            .get("/cristin/organization?query=ntnu")
                .check(jmesPath("hits[0].id")
                        .ofString()
                        .transform(uri -> uri.split("/")[uri.split("/").length - 1])
                        .transform(id -> id.replace(".0.0.0", ""))
                        .saveAs("organizationId")));
}
