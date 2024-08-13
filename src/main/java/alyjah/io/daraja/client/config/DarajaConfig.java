package alyjah.io.daraja.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties("daraja")
@Configuration
public class DarajaConfig {

    private String consumerKey;

    private String consumerSecret;

    private String grantType;

    private String oauthEndpoint;

    private String registerUrlEndpoint;

   private String simulateTransactionEndpoint;

   private String shortCode;
}
