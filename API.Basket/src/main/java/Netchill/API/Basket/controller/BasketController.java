package Netchill.API.Basket.controller;


import Netchill.API.Basket.model.Basket;
import Netchill.API.Basket.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private BasketService service;

    public BasketController(BasketService service) {
        this.service = service;
    }


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

    @PostMapping
    public ResponseEntity<Void> creatBasket(@RequestBody Basket basket,int idUser,int idProduct){
        Basket bask = service.createBasket(idUser,idProduct);

        if(bask == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/basket/{id}")
                .buildAndExpand(bask.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/basket/{id}")
    public ResponseEntity<HttpStatus> updateBasket(@PathVariable("id")int idBasket,@RequestBody Basket basket){
        Basket bask = service.getBasket(idBasket);
        if (bask==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            service.updateBasket(basket,idBasket);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/basket/{id}")
    public ResponseEntity<HttpStatus> deleteBasket(@RequestBody Basket basket){
        service.deleteBasket(basket);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
