package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.quotation.Quotation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuotationRepository extends JpaRepository<Quotation,Integer> {
}
