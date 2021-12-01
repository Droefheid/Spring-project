package Netchill.WEB.User.controller;


import Netchill.WEB.User.model.Basket;
import Netchill.WEB.User.service.BasketFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/baskets")
public class BasketFrontController {

    private final BasketFrontService service;


    public BasketFrontController(BasketFrontService service){
        this.service = service;
    }

    @GetMapping
    public String baskets(@RequestParam(required = false)int idUser){
        service.findBasketsByIdUser(idUser);
        return "/baskets";
    }

    @PostMapping
    public ModelAndView createBasket(@ModelAttribute Basket basket){
        service.createBasket(basket);
        return new ModelAndView("redirect:/");
    }

    @GetMapping
    public ModelAndView deleteBasket(@PathVariable("id") int id,int idUser){
        service.deleteBasket(id,idUser);
        return new ModelAndView("redirect:/");
    }
}



