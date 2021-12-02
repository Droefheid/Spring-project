package Netchill.WEB.User.service;

import Netchill.WEB.User.model.Basket;
import Netchill.WEB.User.proxy.BasketProxy;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BasketFrontService {

    private final BasketProxy proxy;


    public BasketFrontService(BasketProxy proxy) {
        this.proxy = proxy;
    }

    public List<Basket> findBasketsByIdUser(int idUser){
        return proxy.findBasketsByIdUser(idUser);
    }

    public Basket findByIdUser(int idUser){
        return proxy.findByIdUser(idUser);
    }

    public Basket createBasket(Basket basket){
        return proxy.createBasket(basket);
    }

    public Basket updateBasket(Basket basket,int idUser,int idProduct){
        return proxy.updateBasket(basket,idUser,idProduct);
    }

    public Basket deleteBasket(int id,int idUser){ return proxy.deleteBasket(id,idUser);}

}
