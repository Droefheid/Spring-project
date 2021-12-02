package Netchill.API.Basket.service;


import Netchill.API.Basket.model.Basket;

import Netchill.API.Basket.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BasketService {

    @Autowired
    private BasketRepository repository;



    public Iterable<Basket> getBaskets(int idUser){
        return repository.findBasketsByIdUser(idUser);
    }

    public Basket getBasket(int idBasket){
        return repository.findById(idBasket)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Basket with id "+idBasket));
    }

    public Basket saveBasket(Basket basket){
        return repository.save(basket);
    }

    public Basket createBasket(int idUser,int idProduct){
        return repository.createBasket(idUser,idProduct);
    }

    public Basket updateBasket(Basket basket,int idBasket){
        Basket b = repository.findById(idBasket).orElseThrow(InternalError::new);
        b.setQuantity(basket.getQuantity());
        return repository.save(b);
    }

   // public Basket createBasket(int idUser,int idProduct){
    //    return repository.createBasket(idUser,idProduct);
   // }

    public void deleteBasket(Basket basket){
        repository.delete(basket);
    }


}
