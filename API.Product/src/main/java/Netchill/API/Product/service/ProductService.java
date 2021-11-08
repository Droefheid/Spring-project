package Netchill.API.Product.service;

import Netchill.API.Product.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class ProductService {

    private static List<Product> products = new ArrayList<>();
    private static int count =0;


    public List<Product> findAll(){
        return products;
    }

    public List<Product> findByCategory(String category){
        return products.stream().filter(k -> k.getCategory().equals(category)).collect(Collectors.toList());
    }

    public Product findProductById(int id){
        return products.stream().filter(k -> k.getId() == id).collect(toSingleton());

    }
    // stackOverflow : https://stackoverflow.com/questions/22694884/filter-java-stream-to-1-and-only-1-element
    private static <T> Collector<T, ?, T> toSingleton(){
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if(list.size() != 1){
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

    public void saveProduct(Product product){
        product.setId(++count);
        products.add(product);
    }

    public void updateProduct(int id, Product product){
        Product productTemp = findProductById(id);
        productTemp.setCategory(product.getCategory());
        productTemp.setPrice(product.getPrice());
        productTemp.setName(product.getName());
        productTemp.setLongDescription(product.getLongDescription());
        productTemp.setShortDescription(product.getShortDescription());
    }

    public void deleteProduct(int id){
        Product productTemp = findProductById(id);
        products.remove(productTemp);
    }

}
