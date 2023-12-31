package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Session;

import java.util.Map;
import java.util.stream.Collectors;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

public final class Customer {
    private static final Map<CharSequence, String> headers_0 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("access-control-request-headers", "authorization"),
            Map.entry("access-control-request-method", "GET"),
            Map.entry("origin", "https://" + Config.DOMAIN),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_1 = Map.ofEntries(
            Map.entry("authorization", "Bearer #{accessToken}"),
            Map.entry("origin", "https://" + Config.DOMAIN),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    public static final ChainBuilder list =
        exec(http("ListCustomersOptions")
        .options("/customer/")
        .headers(headers_0)
        .resources(http("ListCustomers")
            .get("/customer/")
            .check(jmesPath("customers")
                .ofList()
                .saveAs("customerList"))
            .headers(headers_1)))
            .exec(session ->  session.set("customerId", getCustomerId(session)));

    private static String getCustomerId(Session session) {
        return ((String) ((Map<?, ?>) session.getList("customerList")
                .stream()
                .filter(customer -> "Norges teknisk-naturvitenskapelige universitet"
                        .equals(((Map<?, ?>) customer)
                                .get("displayName")))
                .collect(Collectors.toList())
                .get(0))
                .get("id"))
                .split("/")[4];
    }


    public static final ChainBuilder get =
        exec(http("CustomerOptions")
            .options("/customer/#{customerId}")
            .headers(headers_0))
        .exec(http("GetCustomer")
            .get("/customer/#{customerId}")
            .check(jmesPath("cristinId")
                    .ofString()
                    .transform(uri -> uri.split("/")[uri.split("/").length - 1])
                    .saveAs("customerCristinId"))
            .headers(headers_1));

    public static final ChainBuilder vocabularies =
        exec(http("VocabulariesOptions")
        .options("/customer/#{customerId}/vocabularies")
        .headers(headers_0)
        .resources(http("Vocabularies")
            .get("/customer/#{customerId}/vocabularies")
            .headers(headers_1)));

    public static final ChainBuilder doiAgent =
        exec(http("DoiAgentOptions")
        .options("/customer/#{customerId}/doiagent")
        .headers(headers_0)
        .resources(
            http("DoiAgent")
            .get("/customer/#{customerId}/doiagent")
            .headers(headers_1)));

    public static final ChainBuilder findCustomerByCristinId =
        exec(http("CustomerByCristinIdOptions")
        .options("/customer/cristinId/https%3A%2F%2F" + Config.DOMAIN + "%2Fcristin%2Forganization%2F#{customerCristinId}")
        .headers(headers_0)
        .resources(http("CustomerByCristinId")
            .get("/customer/cristinId/https%3A%2F%2F" + Config.API_DOMAIN + "%2Fcristin%2Forganization%2F#{customerCristinId}")
            .headers(headers_1)));
}
