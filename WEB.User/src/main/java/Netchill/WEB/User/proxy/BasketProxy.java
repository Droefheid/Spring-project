package Netchill.WEB.User.proxy;

import Netchill.WEB.User.model.Basket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="gateway")
@RequestMapping("/baskets")
public interface BasketProxy {

    @GetMapping
    List<Basket> findBasketsByIdUser(@PathVariable(required = false)int idUser);

    @GetMapping
    Basket findByIdUser(@PathVariable(required = false)int idUser);

    @PostMapping
    Basket createBasket(@RequestBody Basket basket);

    @PutMapping("/{id}")
    Basket updateBasket(Basket basket, int idUser,int idProduct);

    @DeleteMapping("/{id}")
    Basket deleteBasket(@PathVariable("id") int idUser,int idProduct);


}
