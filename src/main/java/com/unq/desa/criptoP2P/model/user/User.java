package com.unq.desa.criptoP2P.model.user;


import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserCripto")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Hidden
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="usrId")
    private int id;

    private String name;

    private String email;

    private String address;

    private String password;

    private String cvu;

    private String walletAddress;

    private Integer reputation;

    private Integer numberOfOperations;

    private Integer successfulOperation;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private  List<Intention> intentions;

    @Override
    public String toString() {
        return "User";
    }
}
