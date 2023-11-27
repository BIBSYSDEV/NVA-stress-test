
import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;

public class SpeedRunner {

    public static void main(String[] args) {
        GatlingPropertiesBuilder props = new GatlingPropertiesBuilder()
                .simulationClass("no.sikt.nva.speedtest.SpeedTest")
                .runDescription("Lambda warmup run")
                .resourcesDirectory(IDEPathHelper.gradleResourcesDirectory.toString())
                .resultsDirectory(IDEPathHelper.resultsDirectory.toString())
                .binariesDirectory(IDEPathHelper.gradleBinariesDirectory.toString());

        Gatling.fromMap(props.build());
    }
}
