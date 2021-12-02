package Netchill.WEB.ListProduct.service;

import Netchill.WEB.ListProduct.model.Product;
import Netchill.WEB.ListProduct.proxy.ListProductProxy;
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
    public List<Product> findAll(){ return proxy.findAll();}

    public List<Product> findProducts(String category, int priceMin,int priceMax, boolean asc){
        return proxy.findProductsBy(category, priceMin,priceMax, asc);
    }


    public Product createProduct( Product product){ return proxy.createProduct(product);}


    public Product updateProduct(Product product, int id){
        return proxy.updateProduct(product,id);
    }


    public Product deleteProduct( int id){return proxy.deleteProduct(id);}
}
