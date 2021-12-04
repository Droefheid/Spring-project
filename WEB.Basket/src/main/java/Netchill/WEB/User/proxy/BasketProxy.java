package Netchill.WEB.User.proxy;

import Netchill.WEB.User.loadbalancer.LoadBalancerConfig;
import Netchill.WEB.User.model.Basket;
import Netchill.WEB.User.model.User;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="gateway")
@LoadBalancerClient(name = "gateway", configuration = LoadBalancerConfig.class)
public interface BasketProxy {

    @GetMapping
    List<Basket> findBasketsByIdUser(@PathVariable("id")int idUser);

    @GetMapping("/{id}")
    Basket findByIdUser(@PathVariable("id")int idUser,@PathVariable("id") int idProduct);

    @PostMapping
    Basket createBasket(@RequestBody Basket basket);

    @PutMapping("baskets/{id}")
    Basket updateBasket(@RequestBody Basket basket, @PathVariable("id") int idUser,@PathVariable("id")  int idProduct);

    @DeleteMapping("baskets/{id}")
    Basket deleteBasket(@PathVariable("id") int idUser,int idProduct);


}
