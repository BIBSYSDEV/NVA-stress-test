package no.sikt.nva.speedtest;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import no.sikt.nva.Journal;
import no.sikt.nva.Publisher;
import no.sikt.nva.Series;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class ChannelsTest extends Simulation {

    private static final String NVA_API_URI = "https://api.nva.unit.no";
  private final HttpProtocolBuilder httpProtocol = http
    .baseUrl(NVA_API_URI)
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
    .originHeader("https://swagger-ui-internal.dev.nva.aws.unit.no")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
  
private static final String TEST_ORGANIZATION = "194";


    private static final ChainBuilder journal =
//        exec(Journal.query)
        exec(Journal.get);

    private static final ChainBuilder series =
//        exec(Series.query)
        exec(Series.get);

    private static final ChainBuilder publisher =
//        exec(Publisher.query)
        exec(Publisher.get);

    private final ScenarioBuilder scn = scenario("SpeedTest")
//        .exec(session -> session.set("accessToken", Aws.login("Dataporten_c924937b-f153-4836-bb7a-401893b27ba8")))
//        .exec(session -> session.set("apiUri", NVA_API_URI))
//        .exec(session -> session.set("organizationId", TEST_ORGANIZATION))
        .exec(journal)
        .exec(series)
        .exec(publisher);

  {
//      setUp(scn.injectOpen(rampUsersPerSec(4).to(20).during(10).randomized())).protocols(httpProtocol);

      setUp(scn.injectOpen(atOnceUsers(10))).protocols(httpProtocol);
  }
}
