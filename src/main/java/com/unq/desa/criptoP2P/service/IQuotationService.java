package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.quotation.Quotation;

import java.util.List;

public interface IQuotationService {

    public List<Quotation> get();

    public void save(Quotation quotation);

    public Quotation getById(Integer id);

    public void delete(Integer id);

    public List<Cryptocurrency> quotes();
}
