package Netchill.API.Product.repository;

import Netchill.API.Product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository
    extends CrudRepository<Product,Integer>{
    public Iterable<Product> findByCategory(String category);
    public Iterable<Product> findProducts(String category, int priceMin, int priceMax, boolean asc);




}
