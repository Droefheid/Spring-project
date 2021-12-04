package Netchill.WEB.Product.controller;

import Netchill.WEB.Product.model.Comment;
import Netchill.WEB.Product.model.Product;
import Netchill.WEB.Product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductFront {

    private final ProductService service;


    public ProductFront(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String home(Model model){
        List<Product> products = service.findAll();
        model.addAttribute("products", products);
        for (Product p:products) {
            Product product = service.getProductById(p.getId());
            model.addAttribute("product",product);
        }

        return "myProducts";

    }
    @GetMapping(":/products")
    public String products(@RequestParam(required = false)int idUser){
        service.getProductById(idUser);
        return "myProducts";
    }
    @GetMapping(":/comments")
    public String comments(@RequestParam(required = false)int idUser){
        service.findByUserId(idUser);
        return "myProducts";
    }

    @GetMapping(":/comments")
    public String averageByVehicleId(@RequestParam(required = false)int idUser){
        service.averageByVehicleId(idUser);
        return "myProducts";
    }

    @PostMapping(":/baskets")
    public ModelAndView createBasket(@RequestParam(required = false)int idBasket , @RequestParam(required = false)int idProduct){
        service.createBasket(idBasket,idProduct);
        return new ModelAndView("redirect:/all");
    }

    @GetMapping(":/comments/{id}")
    public ModelAndView deleteById(@PathVariable("id") int id){
        service.deleteById(id);
        return new ModelAndView("redirect:/all");
    }

    @GetMapping(":/comments/{id}")
    public ModelAndView editComment(@ModelAttribute Comment comment, @PathVariable("id") int id){
        service.editComment(comment,id);
        return new ModelAndView("redirect:/all");
    }

    @DeleteMapping("/products/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        service.deleteProduct(id);
        return new ModelAndView("redirect:/all");
    }

    @GetMapping(":/comments/{id}")
    public ModelAndView updateProduct(@ModelAttribute Product product,@PathVariable("id") int id){
        service.updateProduct(product,id);
        return new ModelAndView("redirect:/all");
    }


}
