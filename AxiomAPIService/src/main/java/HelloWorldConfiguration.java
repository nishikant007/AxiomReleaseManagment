import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.security.auth.login.AppConfigurationEntry;

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String template;

    @NotEmpty
    @JsonProperty
    private String defaultName = "Stranger";

    public String getTemplate() {
        return template;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        return new AppConfigurationEntry[0];
    }
}
