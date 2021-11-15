package Netchill.API.Product.controller;


import Netchill.API.Product.model.Product;
import Netchill.API.Product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService service;
/*
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

           @PutMapping("/product/{id}")
    public void updateProduct(@RequestBody Product product, Product productUpd){
        int id = product.getId();
        service.updateProduct(productUpd,id);
    }

   @DeleteMapping("/product/{id}")
   public void deleteProduct(@PathVariable("id") int id){
        service.deleteProduct(id);
   }
    }*/

    @GetMapping
    public ResponseEntity<List<Product>> getProduct(@RequestBody(required = false)String category) {
        List<Product> products = new ArrayList<>();

        if(category == null){
            service.getProducts().forEach(products::add);
        }else{
            service.getProductsFromCategory(category).forEach(products::add);
        }
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);

    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        Product p = service.getProduct(id);
        if(p == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(p,HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product){
        Product p = service.saveProduct(product);
        if(p == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/product/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable("id")int id,@RequestBody Product product){
        Product p = service.getProduct(id);
        if (p==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            service.updateProduct(product,id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }



    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@RequestBody Product product){
        service.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
