package Netchill.WEB.ListProduct.proxy;

import Netchill.WEB.ListProduct.model.Product;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "gateway")
@RequestMapping("/users")
public interface ListProductProxy {
    @GetMapping
    List<Product> findAll();


    @GetMapping
    List<Product> findProducts(@PathVariable(required = false) String category, @PathVariable(required = false) int priceMin,@PathVariable(required = false) int priceMax);

    @PostMapping
    Product createProduct(@RequestBody Product product);

    @PutMapping("/{id}")
    Product updateProduct(@RequestBody Product product,@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    Product deleteProduct(@PathVariable("id") int id);
}
