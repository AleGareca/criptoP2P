package com.unq.desa.criptoP2P.model.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Table(name = "UserCripto")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="usrId")
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;


    private String password;

    private String cvu;

    private String walletAddress;



}
