package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.quotation.Quotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IQuotationRepository extends JpaRepository<Quotation,Integer> {
    Quotation getBySymbol(String symbol);

    List<Quotation> findBySymbol(String symbol);

    @Query("from Quotation q where q.symbol = ?1 and q.dayAndTime BETWEEN ?2 and ?3")
    List<Quotation> quoteOfCryptoOfTheLast24Hs(LocalDateTime dateFrom, LocalDateTime dateTo, String symbol);
}
