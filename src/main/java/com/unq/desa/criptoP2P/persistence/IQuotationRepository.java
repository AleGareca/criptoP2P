package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.quotation.Quotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuotationRepository extends JpaRepository<Quotation,Integer> {

    Quotation findQuotationByCryptocurrency_Symbol(String symbol);
}
