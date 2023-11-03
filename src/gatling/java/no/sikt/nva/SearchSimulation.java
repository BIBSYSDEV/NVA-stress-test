package no.sikt.nva;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

//import software.amazon.awssdk.auth.credentials.AwsCredentials;
//import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;


public class SearchSimulation extends Simulation {

  private final HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://api.e2e.nva.aws.unit.no")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36");

  private static final String NVA_URI = "https://e2e.nva.aws.unit.no";
  private static final String QUERY = "test registration title";
  public static final String PAGING = "%22&results=10&from=0";
  private static final String QUERY_URI = "/search/resources?query=%22" + QUERY + PAGING;
  private static final String FILTER_URI = QUERY_URI + "%22+AND+%28entityDescription.contributors.identity.id%3A%22https%3A%2F%2Fapi.e2e.nva.aws.unit.no%2Fcristin%2Fperson%2F1137659%22%29" + PAGING;

  private static class Search {
    private static final ChainBuilder query =
      exec(http("Query")
        .get(QUERY_URI));

    private static final ChainBuilder filterPerson =
      exec( http("FilterPerson")
        .get(FILTER_URI)
          .check(jmesPath("hits[0].associatedArtifacts[0].identifier").saveAs("fileIdentifier"))
          .check(jmesPath("hits[0].identifier").saveAs("identifier")));
  }

  private static class LandingPage {
    private static final ChainBuilder landingPage =
      exec(http("LandingPage")
        .get("/publication/#{identifier}")
              .check(jmesPath("projects[0].id").saveAs("projectUri"))
              .check(jmesPath("entityDescription.reference.publicationContext.id").saveAs("journalUri"))
              .check(jmesPath("entityDescription.contributors[0].affiliations[0].id").saveAs("organizationUri"))
        .resources(
          http("Associated registrations")
            .get("/search/resources?query=%22#{identifier}%22%20AND%20NOT%20(identifier:%22#{identifier}%22)"),
          http("Organization")
            .get("#{organizationUri}"),
          http("Crawler")
            .get("/publication/#{identifier}"),
          http("Journal")
            .get("#{journalUri}"),
          http("Project")
            .get("#{projectUri}"),
          http("File")
            .get("/download/public/#{identifier}/files/#{fileIdentifier}")));

  }

  private final ScenarioBuilder scn = scenario("SearchSimulation")
    .pause(5)
    .exec(StartPage.startPage)
    .pause(5)
    .exec(Search.query)
    .pause(5)
    .exec(Search.filterPerson)
    .pause(2)
    .exec(LandingPage.landingPage);

  {
//	  setUp(scn.injectOpen(rampUsers(1000).during(60))).protocols(httpProtocol);
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
