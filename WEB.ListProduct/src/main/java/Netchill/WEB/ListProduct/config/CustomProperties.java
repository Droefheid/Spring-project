package Netchill.WEB.ListProduct.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix= "netchill")
public class CustomProperties {

    private String api;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
