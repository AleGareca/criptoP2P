package com.unq.desa.criptoP2P.model.quotation;

import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
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

    private CryptoOcurrency cryptocurrency;

    private LocalDateTime dayAndTime;

    @Override
    public String toString() {
        return "Quotation";
    }
}
