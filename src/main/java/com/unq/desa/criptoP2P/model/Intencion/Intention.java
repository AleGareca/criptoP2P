package com.unq.desa.criptoP2P.model.Intencion;

import com.unq.desa.criptoP2P.model.user.User;

import javax.persistence.*;

@Entity
public class Intention {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String activeCripto;

    private int amountOfActiveCripto;

    private int quotation;

    private Double amountOfOperationInPesos;

    @ManyToOne()
    private User userCripto;

    private Boolean isActive;

    public Intention(String activeCripto, int amountOfActiveCripto, User userCripto, Boolean isActive) {
        this.activeCripto = activeCripto;
        this.amountOfActiveCripto = amountOfActiveCripto;
        this.userCripto = userCripto;
        this.isActive = isActive;
    }

    public void setUserCripto(User userCripto) {
        this.userCripto = userCripto;
    }
}
