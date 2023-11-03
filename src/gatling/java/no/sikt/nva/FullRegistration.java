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
    Map.entry("authorization", "Bearer eyJraWQiOiJcL1NaUm4rNUp1RUZVK0R4TThBNk9RTk5BMDlrUEN4a2pGdEs0NmlOOFR5QT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhMGI1ZjYzZC03ZDdkLTQ5ZWEtOTk2OC1hMTY1YzY1M2FmZTAiLCJjb2duaXRvOmdyb3VwcyI6WyJNQU5BR0VfT1dOX1BST0pFQ1RTQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcLzM0ZTM2YzFiLWNmMDMtNDEwYy04YjQ2LWIxNDk4NGY4MGRiNSIsIlBVQkxJU0hfREVHUkVFQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIlJFQURfRE9JX1JFUVVFU1RAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiTUFOQUdFX05WSV9QRVJJT0RTQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIk1BTkFHRV9OVklfQ0FORElEQVRFQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIk1BTkFHRV9PV05fUFJPSkVDVFNAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiUFJPQ0VTU19JTVBPUlRfQ0FORElEQVRFQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIkVESVRfT1dOX0lOU1RJVFVUSU9OX1JFU09VUkNFU0BodHRwczpcL1wvYXBpLmUyZS5udmEuYXdzLnVuaXQubm9cL2N1c3RvbWVyXC9iYjNkMGMwYy01MDY1LTQ2MjMtOWI5OC01ODEwOTgzYzI0NzgiLCJFRElUX09XTl9JTlNUSVRVVElPTl9VU0VSU0BodHRwczpcL1wvYXBpLmUyZS5udmEuYXdzLnVuaXQubm9cL2N1c3RvbWVyXC9iYjNkMGMwYy01MDY1LTQ2MjMtOWI5OC01ODEwOTgzYzI0NzgiLCJFRElUX09XTl9JTlNUSVRVVElPTl9QVUJMSUNBVElPTl9XT1JLRkxPV0BodHRwczpcL1wvYXBpLmUyZS5udmEuYXdzLnVuaXQubm9cL2N1c3RvbWVyXC9iYjNkMGMwYy01MDY1LTQ2MjMtOWI5OC01ODEwOTgzYzI0NzgiLCJSRUpFQ1RfRE9JX1JFUVVFU1RAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiQURNSU5JU1RSQVRFX0FQUExJQ0FUSU9OQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIkFQUFJPVkVfRE9JX1JFUVVFU1RAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiQVBQUk9WRV9QVUJMSVNIX1JFUVVFU1RAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiRURJVF9BTExfTk9OX0RFR1JFRV9SRVNPVVJDRVNAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiTUFOQUdFX05WSV9QRVJJT0RTQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcLzgyM2NkZjgxLTkwZTgtNDZmYi05Y2I1LWQzYjEyYTIwYTA1MyIsIk1BTkFHRV9PV05fUFJPSkVDVFNAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvODIzY2RmODEtOTBlOC00NmZiLTljYjUtZDNiMTJhMjBhMDUzIiwiUFJPQ0VTU19JTVBPUlRfQ0FORElEQVRFQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcLzgyM2NkZjgxLTkwZTgtNDZmYi05Y2I1LWQzYjEyYTIwYTA1MyIsIkFETUlOSVNUUkFURV9BUFBMSUNBVElPTkBodHRwczpcL1wvYXBpLmUyZS5udmEuYXdzLnVuaXQubm9cL2N1c3RvbWVyXC84MjNjZGY4MS05MGU4LTQ2ZmItOWNiNS1kM2IxMmEyMGEwNTMiXSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmV1LXdlc3QtMS5hbWF6b25hd3MuY29tXC9ldS13ZXN0LTFfbGZkMzdlUXhNIiwidmVyc2lvbiI6MiwiY2xpZW50X2lkIjoiNGVrZzd2anFwMHVwaW42MmJwM2lrajAwdHMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIG9wZW5pZCBodHRwczpcL1wvYXBpLm52YS51bml0Lm5vXC9zY29wZXNcL2Zyb250ZW5kIiwiYXV0aF90aW1lIjoxNjk4OTMxMTc5LCJleHAiOjE2OTg5MzQ3NzksImlhdCI6MTY5ODkzMTE4NCwianRpIjoiMThlYTJjNjUtMmM2Ni00N2U0LWI3ZmUtZGRlMmU2NjI0NjE0IiwidXNlcm5hbWUiOiJEYXRhcG9ydGVuX2M5MjQ5MzdiLWYxNTMtNDgzNi1iYjdhLTQwMTg5M2IyN2JhOCJ9.rEdyDYtH86SYGUZ2FaxYEbeyHNGmZY-43edjfQPFiC_oNXUsJgyfKEnE2ciqI7pBwa9bwfvL8ZE9S5bojtYOOi2QhE9sY0dScHyBEtClpOaO-ZeSuSx-YebRz18QHUl1sdQC7Fk_RFTN-YTxzLvVCD_6mRQcjAjy26wuLZMgZODy7OV4n6uuaOEwS3jhcUv2elvQmjt1bMipDs7llEGVV4yBdyClc2m2nOTxvlwJMTW3UumYNVF_pETO3eoD7kpZiWyaDrZI1ZYhyiDvvIJSSxDZAnb2rUPcct4_DJY19MYPpiP2WpipSB7D71YdfCdFuboTDGnUTxTL686M_AyReQ"),
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
    Map.entry("authorization", "Bearer eyJraWQiOiJcL1NaUm4rNUp1RUZVK0R4TThBNk9RTk5BMDlrUEN4a2pGdEs0NmlOOFR5QT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhMGI1ZjYzZC03ZDdkLTQ5ZWEtOTk2OC1hMTY1YzY1M2FmZTAiLCJjb2duaXRvOmdyb3VwcyI6WyJNQU5BR0VfT1dOX1BST0pFQ1RTQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcLzM0ZTM2YzFiLWNmMDMtNDEwYy04YjQ2LWIxNDk4NGY4MGRiNSIsIlBVQkxJU0hfREVHUkVFQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIlJFQURfRE9JX1JFUVVFU1RAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiTUFOQUdFX05WSV9QRVJJT0RTQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIk1BTkFHRV9OVklfQ0FORElEQVRFQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIk1BTkFHRV9PV05fUFJPSkVDVFNAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiUFJPQ0VTU19JTVBPUlRfQ0FORElEQVRFQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIkVESVRfT1dOX0lOU1RJVFVUSU9OX1JFU09VUkNFU0BodHRwczpcL1wvYXBpLmUyZS5udmEuYXdzLnVuaXQubm9cL2N1c3RvbWVyXC9iYjNkMGMwYy01MDY1LTQ2MjMtOWI5OC01ODEwOTgzYzI0NzgiLCJFRElUX09XTl9JTlNUSVRVVElPTl9VU0VSU0BodHRwczpcL1wvYXBpLmUyZS5udmEuYXdzLnVuaXQubm9cL2N1c3RvbWVyXC9iYjNkMGMwYy01MDY1LTQ2MjMtOWI5OC01ODEwOTgzYzI0NzgiLCJFRElUX09XTl9JTlNUSVRVVElPTl9QVUJMSUNBVElPTl9XT1JLRkxPV0BodHRwczpcL1wvYXBpLmUyZS5udmEuYXdzLnVuaXQubm9cL2N1c3RvbWVyXC9iYjNkMGMwYy01MDY1LTQ2MjMtOWI5OC01ODEwOTgzYzI0NzgiLCJSRUpFQ1RfRE9JX1JFUVVFU1RAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiQURNSU5JU1RSQVRFX0FQUExJQ0FUSU9OQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcL2JiM2QwYzBjLTUwNjUtNDYyMy05Yjk4LTU4MTA5ODNjMjQ3OCIsIkFQUFJPVkVfRE9JX1JFUVVFU1RAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiQVBQUk9WRV9QVUJMSVNIX1JFUVVFU1RAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiRURJVF9BTExfTk9OX0RFR1JFRV9SRVNPVVJDRVNAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvYmIzZDBjMGMtNTA2NS00NjIzLTliOTgtNTgxMDk4M2MyNDc4IiwiTUFOQUdFX05WSV9QRVJJT0RTQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcLzgyM2NkZjgxLTkwZTgtNDZmYi05Y2I1LWQzYjEyYTIwYTA1MyIsIk1BTkFHRV9PV05fUFJPSkVDVFNAaHR0cHM6XC9cL2FwaS5lMmUubnZhLmF3cy51bml0Lm5vXC9jdXN0b21lclwvODIzY2RmODEtOTBlOC00NmZiLTljYjUtZDNiMTJhMjBhMDUzIiwiUFJPQ0VTU19JTVBPUlRfQ0FORElEQVRFQGh0dHBzOlwvXC9hcGkuZTJlLm52YS5hd3MudW5pdC5ub1wvY3VzdG9tZXJcLzgyM2NkZjgxLTkwZTgtNDZmYi05Y2I1LWQzYjEyYTIwYTA1MyIsIkFETUlOSVNUUkFURV9BUFBMSUNBVElPTkBodHRwczpcL1wvYXBpLmUyZS5udmEuYXdzLnVuaXQubm9cL2N1c3RvbWVyXC84MjNjZGY4MS05MGU4LTQ2ZmItOWNiNS1kM2IxMmEyMGEwNTMiXSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmV1LXdlc3QtMS5hbWF6b25hd3MuY29tXC9ldS13ZXN0LTFfbGZkMzdlUXhNIiwidmVyc2lvbiI6MiwiY2xpZW50X2lkIjoiNGVrZzd2anFwMHVwaW42MmJwM2lrajAwdHMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIG9wZW5pZCBodHRwczpcL1wvYXBpLm52YS51bml0Lm5vXC9zY29wZXNcL2Zyb250ZW5kIiwiYXV0aF90aW1lIjoxNjk4OTMxMTc5LCJleHAiOjE2OTg5MzQ3NzksImlhdCI6MTY5ODkzMTE4NCwianRpIjoiMThlYTJjNjUtMmM2Ni00N2U0LWI3ZmUtZGRlMmU2NjI0NjE0IiwidXNlcm5hbWUiOiJEYXRhcG9ydGVuX2M5MjQ5MzdiLWYxNTMtNDgzNi1iYjdhLTQwMTg5M2IyN2JhOCJ9.rEdyDYtH86SYGUZ2FaxYEbeyHNGmZY-43edjfQPFiC_oNXUsJgyfKEnE2ciqI7pBwa9bwfvL8ZE9S5bojtYOOi2QhE9sY0dScHyBEtClpOaO-ZeSuSx-YebRz18QHUl1sdQC7Fk_RFTN-YTxzLvVCD_6mRQcjAjy26wuLZMgZODy7OV4n6uuaOEwS3jhcUv2elvQmjt1bMipDs7llEGVV4yBdyClc2m2nOTxvlwJMTW3UumYNVF_pETO3eoD7kpZiWyaDrZI1ZYhyiDvvIJSSxDZAnb2rUPcct4_DJY19MYPpiP2WpipSB7D71YdfCdFuboTDGnUTxTL686M_AyReQ"),
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
    .exec(http("Startpage")
        .get(NVA_URI + "/")
        .headers(headers_0)
        .basicAuth("osteloff","osteloff"))
    .exec(http("Manifest")
        .get(NVA_URI + "/manifest.json")
        .headers(headers_1)
        .check(status().is(401)))
    .exec(http("Login")
        .post(COGNITO_URI + "/")
        .headers(headers_2)
        .body(RawFileBody("no/sikt/nva/fullregistration/accessToken.dat")))
    .exec(http("request_3")
        .options("/customer/bb3d0c0c-5065-4623-9b98-5810983c2478")
        .headers(headers_3))
    .exec(http("GetCustomer")
        .get("/customer/bb3d0c0c-5065-4623-9b98-5810983c2478")
        .headers(headers_4))
    .exec(http("request_5")
        .get("/search/resources?")
        .headers(headers_5))
    .pause(5)
    .exec(http("request_6")
        .options("/publication")
        .headers(headers_6)
        .resources(http("request_7")
            .post("/publication")
            .headers(headers_4),
          http("request_8")
            .get("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c")
            .headers(headers_5)))
    .pause(7)
    .exec(http("request_9")
        .get("/cristin/project?query=test&results=10&page=1")
        .headers(headers_5))
    .pause(1)
    .exec(http("request_10")
        .get("/cristin/project/7039549")
        .headers(headers_5))
    .pause(7)
    .exec(http("request_11")
        .options("/publication-channels-v2/journal?query=physics&year=2023")
        .headers(headers_3)
        .resources(
          http("request_12")
            .get("/publication-channels-v2/journal?query=physics&year=2023")
            .headers(headers_4)))
    .pause(5)
    .exec(http("request_13")
        .options("/publication-channels-v2/journal/C054AE8C-81F1-4FA8-859B-79A4635AA432/2023")
        .headers(headers_3)
        .resources(
          http("request_14")
            .get("/publication-channels-v2/journal/C054AE8C-81F1-4FA8-859B-79A4635AA432/2023")
            .headers(headers_4)))
    .pause(5)
    .exec(http("request_15")
        .get("/cristin/person/43419")
        .headers(headers_5)
        .resources(
          http("request_16")
            .get("/cristin/organization/20754.6.0.0")
            .headers(headers_5),
          http("request_17")
            .get("/cristin/organization/5991.0.0.0")
            .headers(headers_5),
          http("request_18")
            .get("/cristin/organization/20202.0.0.0")
            .headers(headers_5),
          http("request_19")
            .get("/cristin/organization/20754.0.0.0")
            .headers(headers_5)))
    .pause(9)
    .exec(http("request_20")
        .options("/upload/create")
        .headers(headers_20)
        .resources(
          http("request_21")
            .post("/upload/create")
            .headers(headers_21)
            .body(RawFileBody("no/sikt/nva/fullregistration/0021_request.json")),
          http("request_22")
            .options("/upload/prepare")
            .headers(headers_20),
          http("request_23")
            .post("/upload/prepare")
            .headers(headers_21)
            .body(RawFileBody("no/sikt/nva/fullregistration/0023_request.json")),
          http("request_24")
            .options(NVA_STORAGE_URI + "?uploadId=s9XP_JCIcuTObg68EaXgVs7ihSfy23.r0Lwo3DjieIOOlHP2U3hNbGwar3lm4976bT_91z41faiBhFFByQ9pQmfxNy.npgzwzKIrQL6souCYjkQY8lZKq0w4tecLzdFVjQ1v5879zGHaNInzgssnrfOrbLk4Cs0ankzD4XSOkKphRKtHKdyrYQS1q4Eu6jCeQULQ3GonV_DU7nf76lxpuA--&partNumber=1&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEPb%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCWV1LXdlc3QtMSJIMEYCIQCzrNP7mZO7YHnf%2BMQn90gHNadleQhtOZptU3zBlve0ywIhANSqxWCC3qucyPTgasy1sri0gESS9MLdrZ5miwEK1atNKtMDCC4QARoMMjgyMzA1MDkxNDgxIgy7ITNwnZUGZqNPil0qsANIWCk4fdon2qSOwwVnHfT6d%2Fgvj2NdZVcosYUxtNnfTwdhaqZVJnGxJBz6iB3%2FFWhdvwP7Zuwq6D9lMmdXn6FhhvTl8v1xnx4RAMfYf1thRSRqd5sbOdaUg5Sqd8KksGwUoD6WCYsnRvOti1ifQVZFPL4SQXHuoOXI2oOgismONbSiCpyx9BaoH4QgecNxVSW7vF99fw1GbhjtV8vrEDtxsPcsugrN5vMrulEnoIjRGDKNYPxww4ScDWmYPrZOtR9301deZ2QxPqxlb9thX9kIfnNWBvPeCWDbEl6BsQBk6FSd0jbCyGFYUsIrRX%2B2NlLEK4NbjlVKjS%2Bs1RXVZAHz9g%2B97oCONRIj1uOa1IOsCcYr7jwDH1RX2Kc8MKGK%2BPAg%2FGi%2B1qjhc9lomw54PZX5zBOhMb9WQ3KJTsM5du%2F%2BUm8o2olIWbrPW001Yxn4Bavfbts25uKFdAGaSx67v%2Bv0emtMdPvU53OJfuwE3wct%2FZK%2BJRPz%2B5FgPu6wj%2FjLYeqz%2F%2FdO0C2YXEcKsOfVfwWc1E%2BsA05sF9oM5%2BAOjZONt24Sr6bV2ivLU9onkX76zJgwxsSOqgY6nQHQqdCb39%2BWDfIpdK0aIwXeIDCQFrunB%2FftCXknuH7jaEoStId8HWul2XdD6xEw6eQStjJOlDccTzOCntTdx1LrZNg6131SJbOp6lfINXFZbnfhlmBGYbgjXihl40PEigMHQMgmFDFODmsQxVg44hMPesrAEQBjYZn8UXI2NAF7Tpiofsfb0UNUTiXOJemZ54CIwjbGtsTkq6%2F7CmlI&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231102T132112Z&X-Amz-SignedHeaders=host&X-Amz-Expires=899&X-Amz-Credential=ASIAUDOVR26M2NVBVQMY%2F20231102%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Signature=90ee3ad53dd2c24f83eabfe18fbec90ad03c8704e1aff935aa55082536fd0744")
            .headers(headers_24),
          http("request_25")
            .put(NVA_STORAGE_URI + "?uploadId=s9XP_JCIcuTObg68EaXgVs7ihSfy23.r0Lwo3DjieIOOlHP2U3hNbGwar3lm4976bT_91z41faiBhFFByQ9pQmfxNy.npgzwzKIrQL6souCYjkQY8lZKq0w4tecLzdFVjQ1v5879zGHaNInzgssnrfOrbLk4Cs0ankzD4XSOkKphRKtHKdyrYQS1q4Eu6jCeQULQ3GonV_DU7nf76lxpuA--&partNumber=1&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEPb%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCWV1LXdlc3QtMSJIMEYCIQCzrNP7mZO7YHnf%2BMQn90gHNadleQhtOZptU3zBlve0ywIhANSqxWCC3qucyPTgasy1sri0gESS9MLdrZ5miwEK1atNKtMDCC4QARoMMjgyMzA1MDkxNDgxIgy7ITNwnZUGZqNPil0qsANIWCk4fdon2qSOwwVnHfT6d%2Fgvj2NdZVcosYUxtNnfTwdhaqZVJnGxJBz6iB3%2FFWhdvwP7Zuwq6D9lMmdXn6FhhvTl8v1xnx4RAMfYf1thRSRqd5sbOdaUg5Sqd8KksGwUoD6WCYsnRvOti1ifQVZFPL4SQXHuoOXI2oOgismONbSiCpyx9BaoH4QgecNxVSW7vF99fw1GbhjtV8vrEDtxsPcsugrN5vMrulEnoIjRGDKNYPxww4ScDWmYPrZOtR9301deZ2QxPqxlb9thX9kIfnNWBvPeCWDbEl6BsQBk6FSd0jbCyGFYUsIrRX%2B2NlLEK4NbjlVKjS%2Bs1RXVZAHz9g%2B97oCONRIj1uOa1IOsCcYr7jwDH1RX2Kc8MKGK%2BPAg%2FGi%2B1qjhc9lomw54PZX5zBOhMb9WQ3KJTsM5du%2F%2BUm8o2olIWbrPW001Yxn4Bavfbts25uKFdAGaSx67v%2Bv0emtMdPvU53OJfuwE3wct%2FZK%2BJRPz%2B5FgPu6wj%2FjLYeqz%2F%2FdO0C2YXEcKsOfVfwWc1E%2BsA05sF9oM5%2BAOjZONt24Sr6bV2ivLU9onkX76zJgwxsSOqgY6nQHQqdCb39%2BWDfIpdK0aIwXeIDCQFrunB%2FftCXknuH7jaEoStId8HWul2XdD6xEw6eQStjJOlDccTzOCntTdx1LrZNg6131SJbOp6lfINXFZbnfhlmBGYbgjXihl40PEigMHQMgmFDFODmsQxVg44hMPesrAEQBjYZn8UXI2NAF7Tpiofsfb0UNUTiXOJemZ54CIwjbGtsTkq6%2F7CmlI&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231102T132112Z&X-Amz-SignedHeaders=host&X-Amz-Expires=899&X-Amz-Credential=ASIAUDOVR26M2NVBVQMY%2F20231102%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Signature=90ee3ad53dd2c24f83eabfe18fbec90ad03c8704e1aff935aa55082536fd0744")
            .headers(headers_25),
          http("request_26")
            .options("/upload/complete")
            .headers(headers_20),
          http("request_27")
            .post("/upload/complete")
            .headers(headers_21)
            .body(RawFileBody("no/sikt/nva/fullregistration/0027_request.json"))))
    .pause(5)
    .exec(http("request_28")
        .options("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c")
        .headers(headers_28)
        .resources(
          http("request_29")
            .put("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c")
            .headers(headers_21)
            .body(RawFileBody("no/sikt/nva/fullregistration/0029_request.json")),
          http("request_30")
            .options("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c/tickets")
            .headers(headers_3),
          http("request_31")
            .get("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c")
            .headers(headers_5),
          http("request_32")
            .get("/publication/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c/tickets")
            .headers(headers_4),
          http("request_33")
            .options("/download/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c/files/e945c267-8b04-4b38-aaef-8967278f7fb0")
            .headers(headers_3),
          http("request_34")
            .get("/search/resources?query=%22018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c%22%20AND%20NOT%20(identifier:%22018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c%22)")
            .headers(headers_5),
          http("request_35")
            .get("/cristin/project/7039549")
            .headers(headers_5),
          http("request_36")
            .get("/publication-channels-v2/journal/C054AE8C-81F1-4FA8-859B-79A4635AA432/2023")
            .headers(headers_5),
          http("request_37")
            .get("/download/018b90310bb9-aa0e2c7e-42f4-4ea4-935b-ee4a28cb8a0c/files/e945c267-8b04-4b38-aaef-8967278f7fb0")
            .headers(headers_4)));

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
