package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.quotation.Quotation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuotationService implements IQuotationService {

    @Autowired
    private IQuotationService quotationService;

    @Override
    public List<Quotation> get() {
        return this.quotationService.get();
    }

    @Override
    public void save(Quotation quotation) {
        this.quotationService.save(quotation);
    }

    @Override
    public Quotation getById(Integer id) {
        return this.quotationService.getById(id);
    }

    @Override
    public void delete(Integer id) {
        this.quotationService.delete(id);
    }


}
