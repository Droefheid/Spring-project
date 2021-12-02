package Netchill.API.Basket.repository;

import Netchill.API.Basket.model.Basket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {

    Iterable<Basket> findBasketsByIdUser(int idUser);

    @Modifying
    @Query(value = "insert into baskets(id,iduser,idProduct,quantity) " +
            "values (:id,:idUser,:idProduct,:quantity)",nativeQuery = true)
    Basket createBasket(int idUser,int idProduct);

}

