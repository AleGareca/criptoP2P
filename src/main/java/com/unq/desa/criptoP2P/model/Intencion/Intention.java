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
    @OneToOne(cascade=CascadeType.ALL)
    private Cryptocurrency activeCripto;
    @OneToOne(cascade=CascadeType.ALL)
    private Quotation quotation;

    private Integer amountOfOperationInPesos;

    @ManyToOne(fetch=FetchType.EAGER)
    private User userCripto;

    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    @Enumerated
    private Operation operacion;

    public void setUserCripto(User userCripto) {
        this.userCripto = userCripto;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public String toString() {
        return "Intention";
    }
}
