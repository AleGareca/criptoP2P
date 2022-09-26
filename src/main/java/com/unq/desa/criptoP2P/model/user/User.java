package com.unq.desa.criptoP2P.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;


@Entity
@Table(name = "UserCripto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="usrId")
    private int id;
    //@NotBlank(message = "First Name is field requerid")
    private String firstName ;
    //@NotBlank(message = "Last name is field requerid")
    private String lastName;
    //@Email
    private String email;
    private String address;
    //@NotBlank(message = "Password is field requerid")
    private String password;
    //@NotNull
    private BigInteger cvu;
    //@NotBlank(message = "walletAddress is field requerid")
    private String walletAddress;

    public User(String firstName, String lastName, String email, String address, String password, BigInteger cvu, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.cvu = cvu;
        this.walletAddress = walletAddress;
    }

    public boolean isValidateFullName() {
        return firstName.length() < 30 || firstName.length()>3
                && lastName.length() < 30 || lastName.length()>3;
    }
}
