package no.sikt.nva;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class FullRegistration extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://api.e2e.nva.aws.unit.no")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5,sv;q=0.4")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
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
  
  private Map<CharSequence, String> headers_1 = Map.ofEntries(
    Map.entry("accept", "*/*"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "manifest"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-origin")
  );
  
  private Map<CharSequence, String> headers_2 = Map.ofEntries(
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
  
  static Map<CharSequence, String> headers_3 = Map.ofEntries(
    Map.entry("accept", "*/*"),
    Map.entry("access-control-request-headers", "authorization"),
    Map.entry("access-control-request-method", "GET"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private Map<CharSequence, String> headers_4 = Map.ofEntries(
    Map.entry("authorization", "Bearer #{accessToken}"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private Map<CharSequence, String> headers_5 = Map.ofEntries(
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private Map<CharSequence, String> headers_6 = Map.ofEntries(
    Map.entry("accept", "*/*"),
    Map.entry("access-control-request-headers", "authorization"),
    Map.entry("access-control-request-method", "POST"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private Map<CharSequence, String> headers_20 = Map.ofEntries(
    Map.entry("accept", "*/*"),
    Map.entry("access-control-request-headers", "authorization,content-type"),
    Map.entry("access-control-request-method", "POST"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private Map<CharSequence, String> headers_21 = Map.ofEntries(
    Map.entry("authorization", "Bearer #{accessToken}"),
    Map.entry("content-type", "application/json"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private Map<CharSequence, String> headers_24 = Map.ofEntries(
    Map.entry("Accept", "*/*"),
    Map.entry("Access-Control-Request-Headers", "content-type"),
    Map.entry("Access-Control-Request-Method", "PUT"),
    Map.entry("Origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "cross-site")
  );
  
  private Map<CharSequence, String> headers_25 = Map.ofEntries(
    Map.entry("Accept", "*/*"),
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "cross-site"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_28 = Map.ofEntries(
    Map.entry("accept", "*/*"),
    Map.entry("access-control-request-headers", "authorization,content-type"),
    Map.entry("access-control-request-method", "PUT"),
    Map.entry("origin", "https://e2e.nva.aws.unit.no"),
    Map.entry("sec-fetch-dest", "empty"),
    Map.entry("sec-fetch-mode", "cors"),
    Map.entry("sec-fetch-site", "same-site")
  );
  
  private static final String COGNITO_URI = "https://cognito-idp.eu-west-1.amazonaws.com";
  
  private static final String NVA_STORAGE_URI = "https://nva-resource-storage-282305091481.s3.eu-west-1.amazonaws.com/e945c267-8b04-4b38-aaef-8967278f7fb0";
  
  private final String NVA_URI = "https://e2e.nva.aws.unit.no";

  private ScenarioBuilder scn = scenario("FullRegistration")
    .exec(StartPage.startPageLogin)
    .pause(5)
//    .exec(Publication.create)
//    .pause(7)
//    .exec(Project.search)
//    .pause(5)
//    .exec(Project.get)
//    .pause(5)
//    .exec(Journal.search)
//    .pause(5)
//    .exec(Journal.get)
//    .pause(5)
//    .exec(http("request_15")
//        .get("/cristin/person/43419")
//        .headers(headers_5)
//        .resources(
//          http("request_16")
//            .get("/cristin/organization/20754.6.0.0")
//            .headers(headers_5),
//          http("request_17")
//            .get("/cristin/organization/5991.0.0.0")
//            .headers(headers_5),
//          http("request_18")
//            .get("/cristin/organization/20202.0.0.0")
//            .headers(headers_5),
//          http("request_19")
//            .get("/cristin/organization/20754.0.0.0")
//            .headers(headers_5)))
//    .pause(9)
//    .exec(http("UploadOptions")
//        .options("/upload/create")
//        .headers(headers_20)
//        .resources(
//          http("UploadCreate")
//            .post("/upload/create")
//            .headers(headers_21)
//            .body(RawFileBody("no/sikt/nva/fullregistration/0021_request.json")),
//          http("UploadPrepareOptions")
//            .options("/upload/prepare")
//            .headers(headers_20),
//          http("UploadPrepare")
//            .post("/upload/prepare")
//            .headers(headers_21)
//            .body(RawFileBody("no/sikt/nva/fullregistration/0023_request.json")),
//          http("request_24")
//            .options(NVA_STORAGE_URI + "?uploadId=s9XP_JCIcuTObg68EaXgVs7ihSfy23.r0Lwo3DjieIOOlHP2U3hNbGwar3lm4976bT_91z41faiBhFFByQ9pQmfxNy.npgzwzKIrQL6souCYjkQY8lZKq0w4tecLzdFVjQ1v5879zGHaNInzgssnrfOrbLk4Cs0ankzD4XSOkKphRKtHKdyrYQS1q4Eu6jCeQULQ3GonV_DU7nf76lxpuA--&partNumber=1&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEPb%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCWV1LXdlc3QtMSJIMEYCIQCzrNP7mZO7YHnf%2BMQn90gHNadleQhtOZptU3zBlve0ywIhANSqxWCC3qucyPTgasy1sri0gESS9MLdrZ5miwEK1atNKtMDCC4QARoMMjgyMzA1MDkxNDgxIgy7ITNwnZUGZqNPil0qsANIWCk4fdon2qSOwwVnHfT6d%2Fgvj2NdZVcosYUxtNnfTwdhaqZVJnGxJBz6iB3%2FFWhdvwP7Zuwq6D9lMmdXn6FhhvTl8v1xnx4RAMfYf1thRSRqd5sbOdaUg5Sqd8KksGwUoD6WCYsnRvOti1ifQVZFPL4SQXHuoOXI2oOgismONbSiCpyx9BaoH4QgecNxVSW7vF99fw1GbhjtV8vrEDtxsPcsugrN5vMrulEnoIjRGDKNYPxww4ScDWmYPrZOtR9301deZ2QxPqxlb9thX9kIfnNWBvPeCWDbEl6BsQBk6FSd0jbCyGFYUsIrRX%2B2NlLEK4NbjlVKjS%2Bs1RXVZAHz9g%2B97oCONRIj1uOa1IOsCcYr7jwDH1RX2Kc8MKGK%2BPAg%2FGi%2B1qjhc9lomw54PZX5zBOhMb9WQ3KJTsM5du%2F%2BUm8o2olIWbrPW001Yxn4Bavfbts25uKFdAGaSx67v%2Bv0emtMdPvU53OJfuwE3wct%2FZK%2BJRPz%2B5FgPu6wj%2FjLYeqz%2F%2FdO0C2YXEcKsOfVfwWc1E%2BsA05sF9oM5%2BAOjZONt24Sr6bV2ivLU9onkX76zJgwxsSOqgY6nQHQqdCb39%2BWDfIpdK0aIwXeIDCQFrunB%2FftCXknuH7jaEoStId8HWul2XdD6xEw6eQStjJOlDccTzOCntTdx1LrZNg6131SJbOp6lfINXFZbnfhlmBGYbgjXihl40PEigMHQMgmFDFODmsQxVg44hMPesrAEQBjYZn8UXI2NAF7Tpiofsfb0UNUTiXOJemZ54CIwjbGtsTkq6%2F7CmlI&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231102T132112Z&X-Amz-SignedHeaders=host&X-Amz-Expires=899&X-Amz-Credential=ASIAUDOVR26M2NVBVQMY%2F20231102%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Signature=90ee3ad53dd2c24f83eabfe18fbec90ad03c8704e1aff935aa55082536fd0744")
//            .headers(headers_24),
//          http("request_25")
//            .put(NVA_STORAGE_URI + "?uploadId=s9XP_JCIcuTObg68EaXgVs7ihSfy23.r0Lwo3DjieIOOlHP2U3hNbGwar3lm4976bT_91z41faiBhFFByQ9pQmfxNy.npgzwzKIrQL6souCYjkQY8lZKq0w4tecLzdFVjQ1v5879zGHaNInzgssnrfOrbLk4Cs0ankzD4XSOkKphRKtHKdyrYQS1q4Eu6jCeQULQ3GonV_DU7nf76lxpuA--&partNumber=1&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEPb%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCWV1LXdlc3QtMSJIMEYCIQCzrNP7mZO7YHnf%2BMQn90gHNadleQhtOZptU3zBlve0ywIhANSqxWCC3qucyPTgasy1sri0gESS9MLdrZ5miwEK1atNKtMDCC4QARoMMjgyMzA1MDkxNDgxIgy7ITNwnZUGZqNPil0qsANIWCk4fdon2qSOwwVnHfT6d%2Fgvj2NdZVcosYUxtNnfTwdhaqZVJnGxJBz6iB3%2FFWhdvwP7Zuwq6D9lMmdXn6FhhvTl8v1xnx4RAMfYf1thRSRqd5sbOdaUg5Sqd8KksGwUoD6WCYsnRvOti1ifQVZFPL4SQXHuoOXI2oOgismONbSiCpyx9BaoH4QgecNxVSW7vF99fw1GbhjtV8vrEDtxsPcsugrN5vMrulEnoIjRGDKNYPxww4ScDWmYPrZOtR9301deZ2QxPqxlb9thX9kIfnNWBvPeCWDbEl6BsQBk6FSd0jbCyGFYUsIrRX%2B2NlLEK4NbjlVKjS%2Bs1RXVZAHz9g%2B97oCONRIj1uOa1IOsCcYr7jwDH1RX2Kc8MKGK%2BPAg%2FGi%2B1qjhc9lomw54PZX5zBOhMb9WQ3KJTsM5du%2F%2BUm8o2olIWbrPW001Yxn4Bavfbts25uKFdAGaSx67v%2Bv0emtMdPvU53OJfuwE3wct%2FZK%2BJRPz%2B5FgPu6wj%2FjLYeqz%2F%2FdO0C2YXEcKsOfVfwWc1E%2BsA05sF9oM5%2BAOjZONt24Sr6bV2ivLU9onkX76zJgwxsSOqgY6nQHQqdCb39%2BWDfIpdK0aIwXeIDCQFrunB%2FftCXknuH7jaEoStId8HWul2XdD6xEw6eQStjJOlDccTzOCntTdx1LrZNg6131SJbOp6lfINXFZbnfhlmBGYbgjXihl40PEigMHQMgmFDFODmsQxVg44hMPesrAEQBjYZn8UXI2NAF7Tpiofsfb0UNUTiXOJemZ54CIwjbGtsTkq6%2F7CmlI&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231102T132112Z&X-Amz-SignedHeaders=host&X-Amz-Expires=899&X-Amz-Credential=ASIAUDOVR26M2NVBVQMY%2F20231102%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Signature=90ee3ad53dd2c24f83eabfe18fbec90ad03c8704e1aff935aa55082536fd0744")
//            .headers(headers_25),
//          http("request_26")
//            .options("/upload/complete")
//            .headers(headers_20),
//          http("request_27")
//            .post("/upload/complete")
//            .headers(headers_21)
//            .body(RawFileBody("no/sikt/nva/fullregistration/0027_request.json"))))
//    .pause(5)
//    .exec(http("request_28")
//        .options("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c")
//        .headers(headers_28)
//        .resources(
//          http("request_29")
//            .put("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c")
//            .headers(headers_21)
//            .body(RawFileBody("no/sikt/nva/fullregistration/0029_request.json")),
//          http("request_30")
//            .options("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c/tickets")
//            .headers(headers_3),
//          http("request_31")
//            .get("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c")
//            .headers(headers_5),
//          http("request_32")
//            .get("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c/tickets")
//            .headers(headers_4),
//          http("request_33")
//            .options("/download/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c/files/e945c267-8b04-4b38-aaef-8967278f7fb0")
//            .headers(headers_3),
//          http("request_34")
//            .get("/search/resources?query=%22018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c%22%20AND%20NOT%20(identifier:%22018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c%22)")
//            .headers(headers_5),
//          http("request_35")
//            .get("/cristin/project/7039549")
//            .headers(headers_5),
//          http("request_36")
//            .get("/publication-channels-v2/journal/C054AE8C-81F1-4FA8-859B-79A4635AA432/2023")
//            .headers(headers_5),
//          http("request_37")
//            .get("/download/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c/files/e945c267-8b04-4b38-aaef-8967278f7fb0")
//            .headers(headers_4)));
;
  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
