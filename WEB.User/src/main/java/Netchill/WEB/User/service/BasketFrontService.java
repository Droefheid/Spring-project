package Netchill.WEB.User.service;

import Netchill.WEB.User.model.Basket;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BasketFrontService {

    private final BasketFrontService service;


    public BasketFrontService(BasketFrontService service) {
        this.service = service;
    }

    public List<Basket> findBasketsByIdUser(int idUser){
        return service.findBasketsByIdUser(idUser);
    }

    public Basket findByIdUser(int idUser){
        return service.findByIdUser(idUser);
    }

    public Basket createBasket(Basket basket){
        return service.createBasket(basket);
    }

    public Basket updateBasket(Basket basket,int id){
        return service.updateBasket(basket,id);
    }

    public Basket deleteBasket(int id,int idUser){ return service.deleteBasket(id,idUser);}

}
