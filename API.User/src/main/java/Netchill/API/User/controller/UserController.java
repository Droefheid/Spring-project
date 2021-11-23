package Netchill.API.User.controller;

import Netchill.API.User.model.User;
import Netchill.API.User.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    public UserController(UserService service)
    {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        User saveUser =  service.saveUser(user);
        if(saveUser == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") int id) {
        User idr =  service.findById(id);
        if(idr == null)
            return ResponseEntity.noContent().build();
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(idr.getId()).toUri();
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") int id,@RequestBody User user) {
        service.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        service.deleteUser(id);
    }


    @GetMapping
    public Iterable<User> getUsers(@RequestParam (required = false ) String email) {
        if (email == null ) return service.findAll();
        return service.findByEmail(email);
    }



}