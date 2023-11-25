package no.sikt.nva;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class FileTest extends Simulation{

    public static final Filter.DenyList DENY_LIST = DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*");
    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://api.e2e.nva.aws.unit.no")
            .inferHtmlResources(AllowList(), DENY_LIST)
            .acceptHeader("application/json")
            .acceptEncodingHeader("gzip, deflate, br")
            .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36");

    private final ScenarioBuilder scn = scenario("SearchSimulation")
            .exec(File.create)
            .exec(File.prepare)
            .exec(File.put)
            .exec(File.complete)
            ;



    {
//        Aws.login("test-user-with-author@test.no");
        setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }
}
