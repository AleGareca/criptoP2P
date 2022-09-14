package model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="usrId", nullable = false)
    private int id;
    //@NotBlank(message = "First Name is field requerid");
    private String firstName ;
    //@NotBlank(message = "Last Name is field requerid");
    private String lastName;
    //@NotBlank(message = "First Name is field requerid");
    private String email;
    private String address;
    private String password;
    private int cvu;
    private String walletAddress;

    public boolean isValidateFullName() {
        return firstName.length() < 30 || firstName.length()>3
                && lastName.length() < 30 || lastName.length()>3;
    }
}
