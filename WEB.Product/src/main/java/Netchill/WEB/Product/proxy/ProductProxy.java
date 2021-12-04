package Netchill.WEB.Product.proxy;

import Netchill.WEB.Product.config.CustomProperties;
import Netchill.WEB.Product.loadbalancer.LoadBalancerConfig;
import Netchill.WEB.Product.model.Comment;
import Netchill.WEB.Product.model.Product;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@FeignClient(name = "gateway")
@LoadBalancerClient(name = "gateway", configuration = LoadBalancerConfig.class)
public interface ProductProxy {

    @GetMapping
    List<Product> findAll();

    @GetMapping("/{id}")
    double averageByVehicleId(@PathVariable("id") int id);

   //table:  findByVehicleIdAndUser ensuite findByVehicleIdExceptUserId
    @GetMapping
    Iterable<Comment> findByUserId(@PathVariable("id") int id);

    @PutMapping("/comments/{id}")
    Comment saveComment (@RequestBody Comment comment);

    @PostMapping
    Product createBasket(@PathVariable("id") int idBasket, @PathVariable("id") int idProduct); //a verifier pour avec Axel

    @GetMapping
    Product getProductById (@PathVariable("id") int id);

    @PutMapping("/comments/{id}")
    Comment editComment(@RequestBody Comment comment, @PathVariable("id") int id) ;

    @DeleteMapping("/comments/{id}")
    void deleteById(@PathVariable("id") int id);

    @PostMapping
    Comment addComment (@RequestBody Comment comment);

    @PutMapping("/products/{id}")
    Product updateProduct(@RequestBody Product product,@PathVariable("id") int id);

    @DeleteMapping("/products/{id}")
    Product deleteProduct(@PathVariable("id") int id);

}






