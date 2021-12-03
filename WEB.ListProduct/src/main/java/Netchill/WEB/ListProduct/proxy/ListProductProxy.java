package Netchill.WEB.ListProduct.proxy;

import Netchill.WEB.ListProduct.model.Product;
import WEB.Connexion.loadbalancer.LoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "gateway")
//@RequestMapping("/products")
@LoadBalancerClient(name = "gateway", configuration = LoadBalancerConfig.class)
public interface ListProductProxy {
    @GetMapping("/products")
    List<Product> findAll();


    @GetMapping("/products")
    List<Product> findProductsBy(@PathVariable(required = false) String category,
                                 @RequestParam(defaultValue="-1") int priceMin,
                               @RequestParam(defaultValue="9999999") int priceMax,
                               @RequestParam(defaultValue = "true") boolean asc);

    @PostMapping
    Product createProduct(@RequestBody Product product);

    @PutMapping("/products/{id}")
    Product updateProduct(@RequestBody Product product,@PathVariable("id") int id);

    @DeleteMapping("products/{id}")
    Product deleteProduct(@PathVariable("id") int id);

    @GetMapping("products/{id}")
    Product findById(@PathVariable("id") int id);
}
