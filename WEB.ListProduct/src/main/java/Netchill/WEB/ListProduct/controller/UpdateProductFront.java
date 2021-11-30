package Netchill.WEB.ListProduct.controller;

import Netchill.WEB.ListProduct.model.Product;
import Netchill.WEB.ListProduct.proxy.ListProductProxy;
import Netchill.WEB.ListProduct.service.ListProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/updateProduct")
public class UpdateProductFront {

    private ListProductService service;

    public UpdateProductFront(ListProductService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("product", service.findById(id));
        System.out.println("update get"+ service.findById(id));
        return "updateProduct";
    }

    @PostMapping("/{id}")
    public ModelAndView updateProduct(@ModelAttribute Product product, @PathVariable("id") int id){
        System.out.println("update form" + product);
        service.updateProduct(product,id);
        return new ModelAndView("redirect:/");
    }
}
