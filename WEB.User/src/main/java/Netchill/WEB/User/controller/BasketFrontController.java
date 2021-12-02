package Netchill.WEB.User.controller;


import Netchill.WEB.User.model.Basket;
import Netchill.WEB.User.service.BasketFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/Mybasket")
public class BasketFrontController {

    private final BasketFrontService service;



    public BasketFrontController(BasketFrontService service){
        this.service = service;
    }

    @GetMapping
    public String home(Model model,@RequestParam(required = false)int idUser,@RequestParam(required = false)int idProduct){
        List<Basket> baskets = service.findBasketsByIdUser(idUser);
        model.addAttribute("baskets", baskets);
        model.addAttribute("basket", new Basket(-1, idUser, idProduct,1));
        return "MyBasket";


    }

    @GetMapping
    public String baskets(@RequestParam(required = false)int idUser){
        service.findBasketsByIdUser(idUser);
        return "/Mybasket";
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



