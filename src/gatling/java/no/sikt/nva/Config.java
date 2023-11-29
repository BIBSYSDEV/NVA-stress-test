package no.sikt.nva;

import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

public class Config {

    private static final SsmClient ssmClient = SsmClient.builder().build();

    public static final String API_DOMAIN =
            ssmClient.getParameter(GetParameterRequest.builder()
                    .name("/NVA/ApiDomain")
                    .build()).parameter().value();;

    public static final String DOMAIN = API_DOMAIN.replace("api.", "");
}
