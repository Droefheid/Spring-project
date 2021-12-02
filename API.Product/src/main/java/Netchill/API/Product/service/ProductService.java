package Netchill.API.Product.service;

import Netchill.API.Product.model.Product;
import Netchill.API.Product.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository repo;




    public Iterable<Product>getProducts(){
        return repo.findAll();
    }
    public Iterable<Product>getProductsBy(String category, int priceMin,int priceMax, boolean asc){return repo.findByPriceAndCategory(category, priceMin,priceMax, asc);}

    public Product getProduct(int id){
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No object with id "+id));
    }

    public Iterable<Product> getProductsFromCategory(String category){
        return repo.findByCategory(category);
    }
    public Product saveProduct(Product product){
        return repo.save(product);
    }

    public Product updateProduct(Product product,int id){
        Product p = repo.findById(id).orElseThrow(InternalError::new);
        p.setName(product.getName());
        p.setShortDescription(product.getShortDescription());
        p.setLongDescription(product.getLongDescription());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        return repo.save(p);
    }
    public void deleteProduct(Product product){
        repo.delete(product);
    }

}
