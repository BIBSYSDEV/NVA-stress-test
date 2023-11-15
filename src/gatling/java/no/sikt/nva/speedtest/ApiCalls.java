package no.sikt.nva.speedtest;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class ApiCalls extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://api.dev.nva.aws.unit.no")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
    .originHeader("https://swagger-ui-internal.dev.nva.aws.unit.no")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("accept", "application/json; version=2023-05-26"),
    Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private Map<CharSequence, String> headers_1 = Map.ofEntries(
    Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private Map<CharSequence, String> headers_3 = Map.ofEntries(
    Map.entry("accept", "application/ld+json; version=2023-11-03-aggregations"),
    Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );


  private ScenarioBuilder scn = scenario("ApiCalls")
//    .exec(
//      http("QueryOrganization")
//        .get("/cristin/organization/20202.0.0.0?depth=top")
//        .headers(headers_0)
//    )
//    .pause(51)
//    .exec(
//      http("QueryProjects")
//        .get("/cristin/project?query=physics")
//        .headers(headers_1)
//    )
//    .pause(42)
//    .exec(
//      http("GetProject")
//        .get("/cristin/project/303870")
//        .headers(headers_1)
//    )
//    .pause(58)
    .exec(
      http("QueryPersons")
        .get("/cristin/person?name=eirik%20nilsen")
        .headers(headers_3)
    )
    .pause(50)
    .exec(
      http("GetPerson")
        .get("/cristin/person/43419")
        .headers(headers_1)
    )
    .pause(15)
    .exec(
      http("QueryOrganization")
        .get("/cristin/organization?query=ntnu")
        .headers(headers_0)
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
