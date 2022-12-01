package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.quotation.Quotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuotationRepository extends JpaRepository<Quotation,Integer> {
    Quotation getBySymbol(String symbol);

    List<Quotation> findBySymbol(String symbol);




}
