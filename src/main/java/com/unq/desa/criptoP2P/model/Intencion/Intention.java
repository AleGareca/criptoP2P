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
    private User user;

    private Boolean isActive;

}
