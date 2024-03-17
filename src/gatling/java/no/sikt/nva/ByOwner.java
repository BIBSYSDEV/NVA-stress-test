package no.sikt.nva;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class ByOwner extends Simulation {

  private static final String NVA_API_URI = "https://" + Config.API_DOMAIN;
  private static final String TEST_ORGANIZATION = "194";
  private static final HttpProtocolBuilder httpProtocol = http
          .baseUrl("https://api.e2e.nva.aws.unit.no")
          .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
          .acceptHeader("application/json")
          .acceptEncodingHeader("gzip, deflate, br")
          .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
          .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36");

  private static final ScenarioBuilder scn = scenario("FullRegistration")
          .exec(session -> session.set("accessToken", Aws.login("Dataporten_c924937b-f153-4836-bb7a-401893b27ba8")))
          .exec(session -> session.set("apiUri", NVA_API_URI))
          .exec(session -> session.set("organizationId", TEST_ORGANIZATION))
          .exec(StartPage.startPageLogin)
          .pause(5)
          .exec(Publication.byOwner)
          ;
  {
//    setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
	  setUp(scn.injectOpen(rampUsers(500).during(300))).protocols(httpProtocol);
  }
}
