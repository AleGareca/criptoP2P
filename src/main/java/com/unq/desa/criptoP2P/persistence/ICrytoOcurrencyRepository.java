package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICrytoOcurrencyRepository extends JpaRepository<CryptoOcurrency, Integer> {
    CryptoOcurrency findBySymbol(String symbol);
}
