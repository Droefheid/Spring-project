package Netchill.WEB.Product.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix ="netchill.api.product")
public class CustomProperties {
    private String api;
    public String getApi(){
        return api;
    }
    public void SetApi(String api){
        this.api=api;
    }
}
