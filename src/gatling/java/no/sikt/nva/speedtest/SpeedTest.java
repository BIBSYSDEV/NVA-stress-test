package no.sikt.nva.speedtest;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import no.sikt.nva.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class SpeedTest extends Simulation {

  private final HttpProtocolBuilder httpProtocol = http
    .baseUrl(NVA_API_URI)
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
    .originHeader("https://swagger-ui-internal.dev.nva.aws.unit.no")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
  
private static final String NVA_API_URI = "https://api.test.nva.aws.unit.no";
private static final String TEST_ORGANIZATION = "194";

  private final ScenarioBuilder scn = scenario("SpeedTest")
      .exec(session -> session.set("accessToken", Aws.login("Dataporten_c924937b-f153-4836-bb7a-401893b27ba8")))
      .exec(session -> session.set("apiUri", NVA_API_URI))
      .exec(session -> session.set("organizationId", TEST_ORGANIZATION))
//      .exec(Cristin.listFundingSources)
//      .exec(Cristin.getFundingSources)
//      .exec(Cristin.picture)
//      .exec(Cristin.keywords)
//      .exec(Cristin.positions)
//
//      .exec(Person.query)
//      .exec(Person.personsByOrganization)
//      .exec(Person.get)
//      .exec(Person.personInOrganization)
//
//      .exec(Person.employments)
//      .exec(Organization.query)
//      .exec(Organization.get)
//
//      .exec(Project.query)
//      .exec(Project.get)
//      .exec(Project.getProjectsByOrganization)
//
//      .exec(Customer.list)
//      .exec(Customer.get)
//      .exec(Customer.findCustomerByCristinId)
//      .exec(Customer.doiAgent)
//      .exec(Customer.vocabularies)

//      .exec(Publication.byOwner)
//      .exec(Publication.context)
//      .exec(Publication.get)
//      .exec(Publication.ticketsForPublication)
//      .exec(Publication.ticketsForUser)
//      .exec(Publication.getTicket)

//          .exec(Journal.query)
//          .exec(Journal.get)
//          .exec(Series.query)
//          .exec(Series.get)
//          .exec(Publisher.query)
//          .exec(Publisher.get)

//      .exec(User.query)
//      .exec(User.get)
//      .exec(User.role)
//      .exec(User.userInfo)

      .exec(Nvi.query)
      .exec(Nvi.get)
      .exec(Nvi.period)
      ;

  {
      setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
//      setUp(scn.injectOpen(rampUsers(100).during(60))).protocols(httpProtocol);
  }
}
