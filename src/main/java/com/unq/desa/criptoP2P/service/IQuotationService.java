package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.dto.QuotationDto;
import com.unq.desa.criptoP2P.model.quotation.Quotation;

import java.util.List;

public interface IQuotationService {

    public List<QuotationDto> get();

    public void save(QuotationDto quotationDto, Integer id);

    public Quotation getById(Integer id);

    public void delete(Integer id);

    public void quotesUpdate();
}
