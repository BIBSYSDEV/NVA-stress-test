package no.sikt.nva.speedtest;

import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import no.sikt.nva.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class SpeedTest extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl(NVA_API_URI)
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
    .originHeader("https://swagger-ui-internal.dev.nva.aws.unit.no")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
  
private static final String NVA_API_URI = "https://api.test.nva.aws.unit.no";
private static final String TEST_ORGANIZATION = "20202";

  private final ScenarioBuilder scn = scenario("SpeedTest")
      .exec(session -> {
          var sessionToken = Aws.login("Dataporten_c924937b-f153-4836-bb7a-401893b27ba8");
          System.out.println(sessionToken);
          return session.set("accessToken", sessionToken);
      })
      .exec(session -> session.set("apiUri", NVA_API_URI))
      .exec(session -> session.set("organizationId", TEST_ORGANIZATION))
      .exec(Cristin.fundingSources)
      .exec(Cristin.picture)
      .exec(Person.query)
      .exec(Person.personsByOrganization)
      .exec(Person.get)
      .exec(Person.employments)
      .exec(Organization.query)
      .exec(Organization.get)
      .exec(Project.query)
      .exec(Project.get);

  {
      setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
//      setUp(scn.injectOpen(rampUsers(100).during(60))).protocols(httpProtocol);
  }
}
