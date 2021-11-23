package Netchill.API.User.service;

import Netchill.API.User.repository.UserRepository;
import Netchill.API.User.model.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repo;
    private static List<User> users = new ArrayList<>();
    private static int count =2;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User findById(int id) {
        return repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,
                "No object with id " + id));
    }

    public Iterable<User> findAll() {
        return repo.findAll();
    }

    public Iterable<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public void updateUser(int id, User user) {
        User updateUser = repo.findById(id).orElseThrow(InternalError::new);
        updateUser.setLast_name(user.getLast_name());
        updateUser.setFirst_name(user.getFirst_name());
        updateUser.setDate_of_birth(user.getDate_of_birth());
        updateUser.setAddress(user.getAddress());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setAdmin(user.isAdmin());
        repo.save(updateUser);
    }

    public void deleteUser(int id) {
        User deleteUser = repo.findById(id).orElseThrow(InternalError::new);
        repo.delete(deleteUser);
    }





}