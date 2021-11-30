package Netchill.API.Basket.service;


import Netchill.API.Basket.model.Basket;
import Netchill.API.Basket.repositery.BasketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BasketService {

    @Autowired
    private BasketsRepository repository;


    public Iterable<Basket> getBaskets(int idUser){
        return repository.findBasketByIdUser(idUser);
    }

    public Basket getBasket(int idBasket){
        return repository.findById(idBasket)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Basket with id "+idBasket));
    }

    public Basket saveBasket(Basket basket){
        return repository.save(basket);
    }

    public Basket updateBasket(Basket basket,int idBasket){
        Basket b = repository.findById(idBasket).orElseThrow(InternalError::new);
        b.setQuantity(basket.getQuantity());
        return repository.save(b);
    }

    public void deleteBasket(Basket basket){
        repository.delete(basket);
    }


}
