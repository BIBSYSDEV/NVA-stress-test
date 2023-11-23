package no.sikt.nva;

public class Config {

    public static final String ENV = System.getProperty("awsEnv", "e2e");
    public static final String API_DOMAIN = System.getProperty("apiDomain", "api.e2e.nva.aws.unit.no");
    public static final String USER_POOL_ID = System.getProperty("userPoolId", "");
    public static final String CLIENT_ID = System.getProperty("applicationId", "");
}
