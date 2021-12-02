package Netchill.API.Product.repository;

import Netchill.API.Product.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository
    extends CrudRepository<Product,Integer>{
    Iterable<Product> findByCategory(String category);
    //Iterable<Product> findByPrice(String category, int priceMin, int priceMax, boolean asc);

    @Query("select p.id,p.category,p.longDescription,p.shortDescription" +
            ",p.name,p.price from products p where p.category = ?1 AND (p.price>=?2 AND p.price<=?3) ORDER BY p.price asc ")
    Iterable<Product> findByPriceAndCategory(String category, int priceMin, int priceMax, boolean asc);




}
