package no.sikt.nva;

import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class StartRegistration extends Simulation {

  private final HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://e2e.nva.aws.unit.no")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36");
  
  private static final Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "document"),
    Map.entry("sec-fetch-mode", "navigate"),
    Map.entry("sec-fetch-site", "none"),
    Map.entry("sec-fetch-user", "?1"),
    Map.entry("upgrade-insecure-requests", "1")
  );
  
  private static final Map<CharSequence, String> headers_1 = Map.ofEntries(
    Map.entry("accept", "*/*"),
    Map.entry("content-type", "application/x-amz-json-1.1"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "cross-site"),
    Map.entry("x-amz-target", "AWSCognitoIdentityProviderService.GetUser"),
    Map.entry("x-amz-user-agent", "aws-amplify/5.0.4 auth framework/0")
  );
  
  private static final Map<CharSequence, String> headers_2 = Map.ofEntries(
    Map.entry("accept", "*/*"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "manifest"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-origin")
  );
  
  private static final Map<CharSequence, String> headers_3 = Map.ofEntries(
    Map.entry("accept", "*/*"),
    Map.entry("access-control-request-headers", "authorization"),
    Map.entry("access-control-request-method", "GET"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private static final Map<CharSequence, String> headers_4 = Map.ofEntries(
    Map.entry("authorization", "Bearer #{accessToken}"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );

  private static final Map<CharSequence, String> headers_5 = Map.ofEntries(
          Map.entry("origin", "https://e2e.nva.aws.unit.no"),
          Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
          Map.entry("sec-ch-ua-mobile", "?0"),
          Map.entry("sec-ch-ua-platform", "Windows"),
          Map.entry("sec-fetch-dest", "empty"),
          Map.entry("sec-fetch-mode", "cors"),
          Map.entry("sec-fetch-site", "same-site")
  );

  private static final String NVA_URI = "https://e2e.nva.aws.unit.no";
  private static final String NVA_API_URI = "https://api.e2e.nva.aws.unit.no";
  private static final String COGNITO_URI = "https://cognito-idp.eu-west-1.amazonaws.com";


  private final ScenarioBuilder scn = scenario("StartRegistration")
      .exec(StartPage.startPageLogin)
      .pause(7)
      .exec(Publication.create)
      .pause(5)
      .exec(Publication.get);
  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
//	  setUp(scn.injectOpen(rampUsers(400).during(60))).protocols(httpProtocol);
  }
}
