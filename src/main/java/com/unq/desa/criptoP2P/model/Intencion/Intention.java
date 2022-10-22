package com.unq.desa.criptoP2P.model.Intencion;

import com.unq.desa.criptoP2P.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
