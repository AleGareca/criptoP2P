package com.unq.desa.criptoP2P.model.Intencion;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
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
    @OneToOne
    private Cryptocurrency activeCripto;

    private int amountOfActiveCripto;
    @OneToOne
    private Quotation quotation;

    private Double amountOfOperationInPesos;

    @ManyToOne()
    private User userCripto;

    private Boolean isActive;
    @Enumerated
    private Operation operacion;

    public void setUserCripto(User userCripto) {
        this.userCripto = userCripto;
    }
}
