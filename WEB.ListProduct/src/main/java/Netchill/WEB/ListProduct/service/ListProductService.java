package Netchill.WEB.ListProduct.service;

import Netchill.WEB.ListProduct.model.Product;
import Netchill.WEB.ListProduct.proxy.ListProductProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;
@Service
public class ListProductService {

    private final ListProductProxy proxy;


    public ListProductService(ListProductProxy proxy){
        this.proxy = proxy;
    }

    public Product findById(int id){return proxy.findById(id);}


    public ResponseEntity<List<Product>> findAll(){ return proxy.findAll();}

    public List<Product> findProducts(String category, int priceMin,int priceMax, boolean asc){
        return proxy.findProductsBy(category, priceMin,priceMax, asc);
    }


    public ResponseEntity createProduct( Product product){ return proxy.createProduct(product);}


    public ResponseEntity updateProduct(Product product, int id){
        return proxy.updateProduct(product,id);
    }


    public ResponseEntity deleteProduct( int id){return proxy.deleteProduct(id);}
}
