package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.quotation.Quotation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class QuotationService implements IQuotationService {

    private List<String> cryptocurrencies = new ArrayList<>();
    @Autowired
    private IQuotationService quotationService;

    public QuotationService() {
        this.cryptocurrencies.add("ALICEUSDT");
        this.cryptocurrencies.add("MATICUSDT");
        this.cryptocurrencies.add("AXSUSDT");
        this.cryptocurrencies.add("AAVEUSDT");
        this.cryptocurrencies.add("ATOMUSDT");
        this.cryptocurrencies.add("NEOUSDT");
        this.cryptocurrencies.add("DOTUSDT");
        this.cryptocurrencies.add("ETHUSDT");
        this.cryptocurrencies.add("CAKEUSDT");
        this.cryptocurrencies.add("BTCUSDT");
        this.cryptocurrencies.add("BNBUSDT");
        this.cryptocurrencies.add("ADAUSDT");
        this.cryptocurrencies.add("ADAUSDT");
        this.cryptocurrencies.add("TRXUSDT");
        this.cryptocurrencies.add("AUDIOUSDT");
    }

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
