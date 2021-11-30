package Netchill.API.Product.repository;

import Netchill.API.Product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository
    extends CrudRepository<Product,Integer>{
    public Iterable<Product> findByCategory(String category);


}
