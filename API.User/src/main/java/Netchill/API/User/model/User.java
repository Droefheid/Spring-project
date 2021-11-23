package Netchill.API.User.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name="users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String last_name;

    private String first_name;

    private Date date_of_birth;

    private String address;

    private String email;

    private String password;

    private boolean isAdmin;


}