package Netchill.WEB.ListProduct.controller;
import Netchill.WEB.ListProduct.model.Product;
import Netchill.WEB.ListProduct.proxy.ListProductProxy;
import Netchill.WEB.ListProduct.service.ListProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
@Controller
@RequestMapping("/listProduct")
public class ListProductFront {

    private ListProductProxy proxy;
    private ListProductService service;

    public ListProductFront(ListProductProxy proxy, ListProductService service){
        this.proxy=proxy;
        this.service = service;
    }

    @GetMapping
    public String home(@RequestParam (required=false) String category,@RequestParam(required = false) int priceMin,@RequestParam(required = false) int priceMax, Model model){
        if((category==null)){
            if(priceMin<0 && priceMax>= Integer.MAX_VALUE) {
                model.addAttribute("products", service.findAll());
            }else{
                model.addAttribute("products", service.findProducts(null,priceMin,priceMax));
            }
        }else{
            if(priceMin<0 && priceMax>= Integer.MAX_VALUE) {
                model.addAttribute("products", service.findProducts(category,-1,Integer.MAX_VALUE));
            }else{
                model.addAttribute("products", service.findProducts(category,priceMin,priceMax));
            }

        }
        return "listProduct";
    }

    @PostMapping
    public ModelAndView createProduct(@ModelAttribute Product product){
        service.createProduct(product);
        return new ModelAndView("redirect:/");
    }

    @GetMapping
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        service.deleteProduct(id);
        return new ModelAndView("redirect:/");
    }
}
