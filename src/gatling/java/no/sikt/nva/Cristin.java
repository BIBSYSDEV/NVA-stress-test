package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Cristin {

    private static final Map<CharSequence, String> headers_0 = Map.ofEntries(
            Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_1 = Map.ofEntries(
            Map.entry("authorization", "Bearer #{accessToken}"),
            Map.entry("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );


    public static final ChainBuilder listFundingSources =
        exec(http("FundingSources")
            .get("/cristin/funding-sources")
            .check(jmesPath("sources[0].id")
                    .ofString()
                    .transform(uri -> uri.split("/")[uri.split("/").length -1])
                    .saveAs("fundingSourceId")));

    public static final ChainBuilder getFundingSources =
        listFundingSources.exec(http("GetFundingSource")
        .get("/cristin/funding-sources/#{fundingSourceId}"));

    public static final ChainBuilder keywords =
        exec(http("Keywords")
        .get("/cristin/keyword"));

    public static final ChainBuilder picture =
        exec(http("Picture")
        .get("/cristin/person/43419/picture"));

    public static final ChainBuilder positions =
        exec(http("Positions")
        .get("/cristin/position"));
}
