package Netchill.API.Product.controller;


import Netchill.API.Product.model.Product;
import Netchill.API.Product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
