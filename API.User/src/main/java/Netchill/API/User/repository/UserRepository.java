package Netchill.API.User.repository;

import Netchill.API.User.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository <User,Integer>{
    Iterable<User> findByEmail(String email);
}