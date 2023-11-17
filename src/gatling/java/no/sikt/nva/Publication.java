package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.List;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Publication {

    private static final Map<CharSequence, String> headers_3 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("access-control-request-headers", "authorization"),
            Map.entry("access-control-request-method", "GET"),
            Map.entry("origin", "https://test.nva.aws.unit.no"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_4 = Map.ofEntries(
            Map.entry("authorization", "Bearer #{accessToken}"),
            Map.entry("origin", "https://test.nva.aws.unit.no"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_5 = Map.ofEntries(
            Map.entry("origin", "https://test.nva.aws.unit.no"),
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
            Map.entry("origin", "https://test.nva.aws.unit.no"),
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
            Map.entry("origin", "https://test.nva.aws.unit.no"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );


    static final ChainBuilder create =
        exec(http("CreatePublicationOptions")
            .options("#{apiUri}/publication")
            .headers(headers_6))
        .exec(http("CreatePublication")
            .post( "#{apiUri}/publication")
            .headers(headers_4)
            .check(jmesPath("identifier").saveAs("identifier")));

    public static final ChainBuilder get =
        exec(http("GetPublication")
            .get("/publication/#{identifier}")
            .headers(headers_5));

    static final ChainBuilder put =
        exec(http("PutPublicationOptions")
            .options("/publication/#{identifier}")
            .headers(headers_28))
        .exec(http("PutPublication")
            .put("/publication/#{identifier}")
            .headers(headers_21)
            .body(ElFileBody("no/sikt/nva/fullregistration/0029_request.json")));
    public static ChainBuilder ticketsForPublication =
        exec(http("GetTicketsOptions")
            .options("/publication/#{identifier}/tickets")
            .headers(headers_3))
        .exec(http("GetTickets")
            .get("/publication/#{identifier}/tickets")
            .headers(headers_4));

    public static final ChainBuilder byOwner =
        exec(http("ByOwnerOptions")
            .options("/publication/by-owner")
            .headers(headers_3))
        .exec(http("ByOwner")
            .get("/publication/by-owner")
                .check(jmesPath("publications[0].identifier").ofString().saveAs("identifier"))
            .headers(headers_4));

    public static final ChainBuilder context =
        exec(http("PublicationContext")
            .get("/publication/context"));

    public static final ChainBuilder ticketsForUser =
        exec(http("GetTicketsForUserOptions")
            .options("/publication/tickets")
            .headers(headers_3))
        .exec(http("GetTicketsForUser")
            .get("/publication/tickets")
            .check(jmesPath("*").ofList().saveAs("tickets"))
            .headers(headers_4))
        .exec(session -> session.set("ticketId",
            ((Map<?, ?>)((List<?>)session.getList("tickets").get(1)).get(0)).get("identifier")))
        .exec(session -> session.set("publicationIdentifier",
            ((Map<?, ?>)((List<?>)session.getList("tickets").get(1)).get(0)).get("publicationIdentifier")));

    public static final ChainBuilder getTicket =
        exec(http("GetTicketOptions")
            .options("/publication/#{publicationIdentifier}/ticket/#{ticketId}")
            .headers(headers_3)
            .resources(http("GetTicket")
                .get("/publication/#{publicationIdentifier}/ticket/#{ticketId}")
                .headers(headers_4)));

}
