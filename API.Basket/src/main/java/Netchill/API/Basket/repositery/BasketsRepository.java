package Netchill.API.Basket.repositery;

import Netchill.API.Basket.model.Basket;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface BasketsRepository extends CrudRepository<Basket,Integer>  {

        public Iterable<Basket> findBasketByIdUser(int idUser);



}
