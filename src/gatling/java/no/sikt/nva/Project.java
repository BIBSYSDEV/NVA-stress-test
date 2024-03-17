package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.List;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public final class Project {

    private static final Map<CharSequence, String> headers_5 = Map.ofEntries(
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    public static final ChainBuilder get =
            exec(http("GetProject")
                .get("#{projectUri}"));

    public static final ChainBuilder query =
            exec(http("QueryProject")
                .get("/cristin/project?query=test&results=10&page=1")
                .check(jmesPath("*").ofList().saveAs("project"))
                .headers(headers_5))
            .exec(session -> session.set("projectUri", ((Map<?, ?>)((List<?>)session.getList("project").get(8)).get(0)).get("id")));

    public static final ChainBuilder getProjectsByOrganization =
            exec(http("ProjectByOrganization")
                .get("/cristin/organization/20202.0.0.0/projects"));

}
