package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.dto.QuotationDto;
import com.unq.desa.criptoP2P.model.quotation.Quotation;

import java.util.List;

public interface IQuotationService {

    public List<QuotationDto> get();

    public void save(Quotation quotation);

    public QuotationDto getById(Integer id);

    public void delete(Integer id);

    public List<QuotationDto> quotes();
}
