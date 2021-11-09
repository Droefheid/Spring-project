package Netchill.API.Product.controller;


import Netchill.API.Product.model.Product;
import Netchill.API.Product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService service;

    @GetMapping
    public List<Product> getProduct(@RequestParam(required = false) String category){
        return category == null?
                service.findAll() :
                service.findByCategory(category);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") int id){
        return service.findProductById(id);
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody Product product){
        service.saveProduct(product);
    }

    @PutMapping("/product/{id}")
    public void updateProduct(@RequestBody Product product, Product productUpd){
        int id = product.getId();
        service.updateProduct(productUpd,id);
    }

   @DeleteMapping("/product/{id}")
   public void deleteProduct(@PathVariable("id") int id){
        service.deleteProduct(id);
   }
}
