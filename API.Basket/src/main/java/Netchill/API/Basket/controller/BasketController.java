package Netchill.API.Basket.controller;


import Netchill.API.Basket.model.Basket;
import Netchill.API.Basket.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private BasketService service;

    @GetMapping
    public ResponseEntity<List<Basket>> getBasket(@RequestBody(required = false)int idUser){
        List<Basket> baskets = new ArrayList<>();
        service.getBaskets(idUser).forEach(baskets::add);
        if(baskets.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(baskets, HttpStatus.OK);

    }

    @GetMapping("/basket/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable("id") int id){
        Basket basket = service.getBasket(id);
        if(basket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(basket,HttpStatus.OK);
        }
    }
}
