package com.unq.desa.criptoP2P.model.intencion;

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
    private Quotation quotation;

    private Integer amountOfOperationInPesos;

    @ManyToOne(fetch=FetchType.EAGER)
    private User userCripto;

    private boolean isActive;

    @Enumerated
    private Operation operacion;

    @Override
    public String toString() {
        return "Intention";
    }
}
