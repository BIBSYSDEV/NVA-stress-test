package no.sikt.nva;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.header;
import static io.gatling.javaapi.http.HttpDsl.http;

final class File {

    private static final Map<CharSequence, String> headers_20 = Map.ofEntries(
            Map.entry("accept", "*/*"),
            Map.entry("access-control-request-headers", "authorization,content-type"),
            Map.entry("access-control-request-method", "POST"),
            Map.entry("origin", "https://" + Config.DOMAIN),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_21 = Map.ofEntries(
            Map.entry("authorization", "Bearer #{accessToken}"),
            Map.entry("content-type", "application/json"),
            Map.entry("origin", "https://" + Config.DOMAIN),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows"),
            Map.entry("sec-fetch-dest", "empty"),
            Map.entry("sec-fetch-mode", "cors"),
            Map.entry("sec-fetch-site", "same-site")
    );

    private static final Map<CharSequence, String> headers_24 = Map.ofEntries(
            Map.entry("Accept", "*/*"),
            Map.entry("Access-Control-Request-Headers", "content-type"),
            Map.entry("Access-Control-Request-Method", "PUT"),
            Map.entry("Origin", "https://" + Config.DOMAIN),
            Map.entry("Sec-Fetch-Dest", "empty"),
            Map.entry("Sec-Fetch-Mode", "cors"),
            Map.entry("Sec-Fetch-Site", "cross-site")
    );

    private static final Map<CharSequence, String> headers_25 = Map.ofEntries(
            Map.entry("Accept", "*/*"),
            Map.entry("Content-Type", "application/json"),
            Map.entry("Origin", "https://" + Config.DOMAIN),
            Map.entry("Sec-Fetch-Dest", "empty"),
            Map.entry("Sec-Fetch-Mode", "cors"),
            Map.entry("Sec-Fetch-Site", "cross-site"),
            Map.entry("sec-ch-ua", "Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99"),
            Map.entry("sec-ch-ua-mobile", "?0"),
            Map.entry("sec-ch-ua-platform", "Windows")
    );

    private static final String NVA_STORAGE_URI = "https://nva-resource-storage-282305091481.s3.eu-west-1.amazonaws.com/#{fileKey}";

    static ChainBuilder get =
            exec(http("File")
                    .get("/download/public/#{identifier}/files/#{fileIdentifier}"));

    static ChainBuilder create =
        exec(http("UploadCreateOptions")
            .options("/upload/create")
            .headers(headers_20))
        .exec(http("UploadCreate")
            .post("/upload/create")
                .check(jmesPath("key").ofString().saveAs("fileKey"))
                .check(jmesPath("uploadId").ofString().saveAs("fileUploadId"))
            .headers(headers_21)
            .body(RawFileBody("no/sikt/nva/fullregistration/0021_request.json")));

    static ChainBuilder prepare =
        exec(session -> session.set("filePayload", "test file".getBytes()))
        .exec(http("UploadPrepareOptions")
            .options("/upload/prepare")
            .headers(headers_20))
        .exec(http("UploadPrepare")
            .post("/upload/prepare")
            .check(jmesPath("*").ofList().transform(response -> response.get(0)).saveAs("presignedUri"))
            .headers(headers_21)
            .body(ElFileBody("no/sikt/nva/fullregistration/0023_request.json")));

    static ChainBuilder put =
        exec(http("UploadPutOptions")
            .options("#{presignedUri}")
            .headers(headers_24))
        .exec(http("UploadPut")
            .put( "#{presignedUri}")
        .check(header("ETag").saveAs("ETag"))
            .headers(headers_25))
        .exec(session -> session.set("ETag", session.getString("ETag").replace("\"", "")));

    static ChainBuilder complete =
        exec(http("UploadCompleteOptions")
            .options("/upload/complete")
            .headers(headers_20))
        .exec(http("UploadComplete")
            .post("/upload/complete")
            .headers(headers_21)
            .body(ElFileBody("no/sikt/nva/fullregistration/0027_request.json")));
}
