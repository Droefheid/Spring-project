package Netchill.API.Product.service;

import Netchill.API.Product.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static List<Product> products = new ArrayList<>();


    public List<Product> findAll(){
        return products;
    }

    public List<Product> findByCategory(String category){
        return products.stream().filter(k -> k.getCategory().equals(category)).collect(Collectors.toList());
    }
}
