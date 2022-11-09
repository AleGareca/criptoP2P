package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICrytocurrencyRepository extends JpaRepository<Cryptocurrency,Integer> {
    Cryptocurrency findBySymbol(String symbol);
}
