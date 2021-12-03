package Netchill.WEB.User.controller;


import Netchill.WEB.User.model.Basket;
import Netchill.WEB.User.service.BasketFrontService;
import WEB.Connexion.service.UsersFrontService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class BasketFrontController {

    private final BasketFrontService service;
    private UsersFrontService userservice;




    public BasketFrontController(BasketFrontService service){
        this.service = service;

    }

    @GetMapping
    public String home(Model model){
        List<Basket> baskets = service.findBasketsByIdUser(2);
        model.addAttribute("baskets", baskets);
        for (Basket b:baskets) {
           // Basket basket = service.findByIdUser(idUser,idProduct);
            model.addAttribute("basket",b);
        }

        return "myBasket";


    }

    @GetMapping(":/baskets")
    public String baskets(@RequestParam(required = false)int idUser){
        service.findBasketsByIdUser(idUser);
        return "myBasket";
    }

    @PostMapping(":/baskets")
    public ModelAndView createBasket(@ModelAttribute Basket basket){
        service.createBasket(basket);
        return new ModelAndView("redirect:/baskets");
    }

    @GetMapping(":/baskets/{id}")
    public ModelAndView deleteBasket(@PathVariable("id") int id,int idUser){
        service.deleteBasket(id,1);
        return new ModelAndView("redirect:/all");
    }
}



