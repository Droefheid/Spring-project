package Netchill.WEB.User.proxy;


import Netchill.WEB.User.config.CustomProperties;
import Netchill.WEB.User.model.Basket;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class check {

    private final String baseUrl;


    public check(CustomProperties props){
        baseUrl = props.getApi();
    }

    public List<Basket> findBaskets(int idUser){
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Basket>> response = template.exchange(
                baseUrl + "/basket",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Basket>>() {}
        );
        return response.getBody();
    }

    public void CreateBasket(Basket basket){
        RestTemplate template = new RestTemplate();
        HttpEntity<Basket> request = new HttpEntity<Basket>(basket);
        template.exchange(
                baseUrl + "/basket",
                HttpMethod.POST,
                request,
               Basket.class
        );

    }

}
