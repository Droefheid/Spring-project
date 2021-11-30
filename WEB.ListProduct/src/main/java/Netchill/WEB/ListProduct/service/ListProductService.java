package Netchill.WEB.ListProduct.service;

import Netchill.WEB.ListProduct.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;

public class ListProductService {

    private final ListProductService proxy;


    public ListProductService(ListProductService proxy){
        this.proxy = proxy;
    }

    public Product findById(int id){return proxy.findById(id);}
    public List<Product> findAll(){ return proxy.findAll();}

    public List<Product> findProducts(String category, int priceMin,int priceMax){
        return proxy.findProducts(category, priceMin,priceMax);
    }


    public Product createProduct( Product product){ return proxy.createProduct(product);}


    public Product updateProduct(Product product, int id){
        return proxy.updateProduct(product,id);
    }


    public Product deleteProduct( int id){return proxy.deleteProduct(id);}
}
