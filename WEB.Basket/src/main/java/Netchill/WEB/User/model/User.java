package Netchill.WEB.User.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String pseudo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private String email;
    private String password;
    private WEB.Connexion.model.User.USER_TYPES userType;

    public static enum USER_TYPES {
        CLIENT("client"),
        ADMIN("admin");
        String userType;

        USER_TYPES(String userType) {
            this.userType = userType;
        }
    }
}
