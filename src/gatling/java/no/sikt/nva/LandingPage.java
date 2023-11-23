package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Arrays;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

final class LandingPage {
    static ChainBuilder landingPage =
            exec(http("LandingPage")
                .get("/publication/#{identifier}")
                .check(jmesPath("projects[0].id").saveAs("projectUri"))
                .check(jmesPath("entityDescription.reference.publicationContext.id").saveAs("journalUri"))
                .check(jmesPath("entityDescription.contributors[0].affiliations[0].id")
                        .ofString()
                        .transform(uri ->uri.split("/")[uri.split("/").length - 1])
                        .saveAs("organizationId")))
            .exec(Search.associatedRegistrations)
            .exec(Organization.get)
            .exec(Publication.get)
            .exec(Journal.get)
            .exec(Project.get)
            .exec(File.get);

}
