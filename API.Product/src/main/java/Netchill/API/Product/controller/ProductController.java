package Netchill.API.Product.controller;


import Netchill.API.Product.model.Product;
import Netchill.API.Product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@RestController
public class ProductController  {


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

    private ProductService service;


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
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = new ArrayList<>();
        service.getProducts().forEach(products::add);

        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>>findProductsBy(@PathVariable(required = false)String category,
                                               @PathVariable(required = false)int priceMin,
                                               @PathVariable(required = false)int priceMax,
                                                     @PathVariable(required = true)boolean asc){

        List<Product> products = findProductsByP(category, priceMin, priceMax,asc);
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);


    }

    public List<Product> findProductsByP(String category, int priceMin,int priceMax,boolean asc){
        List<Product> products = new ArrayList<>();
        if(category == null){
            if(priceMin <0 && priceMax > Integer.MAX_VALUE){
                service.getProducts().forEach(products::add);
            }else {
                for (Product p : service.getProductsBy(null,priceMin,priceMax,asc)) {
                    if (p.getPrice() >= priceMin && p.getPrice() <= priceMax) {
                        products.add(p);
                    }
                }
            }
        }else{

            if(priceMin <0 && priceMax > Integer.MAX_VALUE){
                service.getProductsFromCategory(category).forEach(products::add);
            }else{
                for (Product p :service.getProductsBy(category,priceMin,priceMax,asc)) {
                    if((p.getCategory()==category) && (p.getPrice() >= priceMin) && (p.getPrice() <= priceMax)){
                        products.add(p);
                    }
                }
            }

        }

        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if(asc==true){
                    return Integer.compare(o1.getPrice(), o2.getPrice());
                }else{
                    return Integer.compare(o2.getPrice(), o1.getPrice());
                }
            }
        });

        return products;
    }






    @PostMapping("/product/{id}")
    public ResponseEntity<Void> createProduct(@RequestBody Product product){
        Product p = service.saveProduct(product);
        if(p == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/products/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable("id")int id,@RequestBody Product product){
        Product p = service.getProduct(id);
        if (p==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            service.updateProduct(product,id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }



    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@RequestBody Product product){
        service.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
