package no.sikt.nva;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class FullRegistration extends Simulation {

  private static final HttpProtocolBuilder httpProtocol = http
          .baseUrl("https://api.e2e.nva.aws.unit.no")
          .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
          .acceptHeader("application/json")
          .acceptEncodingHeader("gzip, deflate, br")
          .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
          .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36");

  private static final ScenarioBuilder scn = scenario("FullRegistration")
          .exec(StartPage.startPageLogin)
          .pause(5)
          .exec(Publication.create)
          .pause(7)
          .exec(Project.query)
          .exec(session -> {
              System.out.println(session.getString("projectUri"));
              return session;
          })
          .pause(5)
          .exec(Project.get)
          .pause(5)
          .exec(Journal.query)
          .pause(5)
          .exec(Journal.get)
          .pause(5)
          .exec(Person.get)
          .exec(Organization.get)
          .pause(9)
//          .exec(File.create)
//          .pause(5)
//          .exec(File.prepare)
//          .pause(5)
//          .exec(File.put)
//          .pause(5)
//          .exec(File.complete)
          .pause(5)
          .exec(Publication.put)
          .exec(Publication.ticketsForPublication)
          .exec(Publication.get)
//          .exec(File.get)
          .exec(Search.associatedRegistrations)
          .exec(Project.get)
          .exec(Journal.get);
  {
//    setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
	  setUp(scn.injectOpen(rampUsers(100).during(60))).protocols(httpProtocol);
  }
}
