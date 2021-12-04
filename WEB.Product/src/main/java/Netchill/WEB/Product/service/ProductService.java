package Netchill.WEB.Product.service;

import Netchill.WEB.Product.proxy.ProductProxy;
import Netchill.WEB.Product.model.Comment;
import Netchill.WEB.Product.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;

@Service
public class ProductService {

    private final ProductService proxy;

    public ProductService(ProductService proxy){
        this.proxy = proxy;
    }

    public List<Product> findAll(){return proxy.findAll();}

    public double averageByVehicleId(int id){
        return proxy.averageByVehicleId(id);
    }


    public Iterable<Comment> findByUserId(int id){
        return proxy.findByUserId(id);
    }

    //table:  findByVehicleIdAndUser ensuite findByVehicleIdExceptUserId

    public Comment saveComment(Comment comment){
        return proxy.saveComment(comment);
    }
    public Product createBasket(int idBasket,int idProduct){
        return proxy.createBasket(idBasket,idProduct);
    }

   public Product getProductById (int id){
        return proxy.getProductById(id);
   }


   public Comment editComment( Comment comment,int id){
        return proxy.editComment(comment,id);
   }


    public void deleteById(int id){
        proxy.deleteById(id);
    }


    public Comment addComment (Comment comment){
        return proxy.addComment(comment);
    }


    public Product updateProduct(Product product,int id){
        return proxy.updateProduct(product,id);
    }


    public Product deleteProduct(int id){
        return proxy.deleteProduct(id);
    }
}
