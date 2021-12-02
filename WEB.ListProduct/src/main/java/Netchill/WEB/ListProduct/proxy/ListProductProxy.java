package Netchill.WEB.ListProduct.proxy;

import Netchill.WEB.ListProduct.model.Product;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "http://localhost:9001/")
//@RequestMapping("/products")
public interface ListProductProxy {
    @GetMapping
    List<Product> findAll();


    @GetMapping
    List<Product> findProductsBy(@PathVariable(required = false) String category,
                                 @RequestParam(defaultValue="-1") int priceMin,
                               @RequestParam(defaultValue="9999999") int priceMax,
                               @RequestParam(defaultValue = "true") boolean asc);

    @PostMapping
    Product createProduct(@RequestBody Product product);

    @PutMapping("/{id}")
    Product updateProduct(@RequestBody Product product,@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    Product deleteProduct(@PathVariable("id") int id);

    @GetMapping("/{id}")
    Product findById(@PathVariable("id") int id);
}
