package com.unq.desa.criptoP2P.model.Intencion;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.user.User;
import io.swagger.models.auth.In;
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
    @OneToOne()
    private Cryptocurrency activeCripto;
    @OneToOne()
    private Quotation quotation;

    private Integer amountOfOperationInPesos;

    @ManyToOne(cascade=CascadeType.ALL)
    private User userCripto;

    private Boolean isActive;
    @Enumerated
    private Operation operacion;

    public void setUserCripto(User userCripto) {
        this.userCripto = userCripto;
    }
}
