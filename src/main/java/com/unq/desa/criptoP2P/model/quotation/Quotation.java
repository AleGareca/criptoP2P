package com.unq.desa.criptoP2P.model.quotation;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Cryptocurrency cryptocurrency;

    private LocalDateTime dayAndTime;

    private int quotationOfActiveCripto;

}
