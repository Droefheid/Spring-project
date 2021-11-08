package Netchill.WEB.Product.proxy;

import Netchill.WEB.Product.config.CustomProperties;
import Netchill.WEB.Product.model.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class ProductProxy {
    private final String baseUrl;

    public ProductProxy(CustomProperties props){
        baseUrl = props.getApi();
    }

    public List<Product> findAll(){
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Product>> response = template.exchange(
                baseUrl + "/product",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        return response.getBody();
    }

    public void createProduct(Product product){
        RestTemplate template = new RestTemplate();
        HttpEntity<Product> request = new HttpEntity<Product>(product);
        template.exchange(
                baseUrl + "/product",
                HttpMethod.POST,
                request,
                Product.class
        );
    }



}
