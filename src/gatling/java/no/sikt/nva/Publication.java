package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.List;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

class Publication {

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

    private static final Map<CharSequence, String> headers_6 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("access-control-request-headers", "authorization"),
            Map.entry("access-control-request-method", "POST"),
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_21 = Map.ofEntries(
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

    private static final Map<CharSequence, String> headers_28 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("access-control-request-headers", "authorization,content-type"),
            Map.entry("access-control-request-method", "PUT"),
            Map.entry("origin", "https://e2e.nva.aws.unit.no"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );


    private static final String NVA_API_URI = "https://api.e2e.nva.aws.unit.no";

    static final ChainBuilder create =
            exec(http("CreatePublicationOptions")
                    .options(NVA_API_URI + "/publication")
                    .headers(headers_6))
            .exec(http("CreatePublication")
                    .post(NVA_API_URI + "/publication")
                    .headers(headers_4)
                    .check(jmesPath("identifier").saveAs("identifier")));

    static final ChainBuilder get =
            exec(http("GetPublication")
                    .get(NVA_API_URI + "/publication/#{identifier}")
                    .headers(headers_5));

    static final ChainBuilder put =
            exec(http("PutPublicationOptions")
                .options("/publication/#{identifier}")
                .headers(headers_28))
            .exec(http("PutPublication")
                .put("/publication/#{identifier}")
                .headers(headers_21)
                .body(ElFileBody("no/sikt/nva/fullregistration/0029_request.json")));
    public static ChainBuilder getTickets =
            exec(http("GetTicketsOptions")
                  .options("/publication/#{identifier}/tickets")
                  .headers(headers_3))
            .exec(http("GetTickets")
                  .get("/publication/#{identifier}/tickets")
                  .headers(headers_4))
    ;
}
