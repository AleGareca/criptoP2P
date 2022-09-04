package model.user;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@NotBlank(message = "First Name is field requerid");
    private String firstName ;
    //@NotBlank(message = "Last Name is field requerid");
    private String lastName;
    //@NotBlank(message = "First Name is field requerid");
    @Email
    private String email;
    private String address;
    private String password;
    private int cvu;
    private String walletAddress;


    public User(int id, String firstName, String lastName, String email, String address, String password, int cvu, String walletAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.cvu = cvu;
        this.walletAddress = walletAddress;
    }
}
