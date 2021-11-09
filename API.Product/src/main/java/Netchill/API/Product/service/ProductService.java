package Netchill.API.Product.service;

import Netchill.API.Product.model.Product;
import Netchill.API.Product.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Product getProduct(int id){
        return repo.findById(id).orElseThrow(InternalError::new);
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

/*


    public List<Product> findByCategory(String category){
        return products.stream().filter(k -> k.getCategory().equals(category)).collect(Collectors.toList());
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
*/
}
