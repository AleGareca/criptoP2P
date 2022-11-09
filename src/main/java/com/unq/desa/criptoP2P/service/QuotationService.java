package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.persistence.ICrytocurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuotationService implements IQuotationService {

    @Autowired
    private BinanceClient binanceClient;
    @Autowired
    @Lazy
    private IQuotationService quotationService;

    @Autowired
    private ICrytocurrencyRepository crytocurrencyRepository;

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

    @Override
    public List<Quotation> quotes() {
        List<Quotation> quotes = new ArrayList<>();
        Quotation quotation;
        for(Cryptocurrency cryptocurrency : this.crytocurrencyRepository.findAll()) {
            quotation = new Quotation();
            quotation.setCryptocurrency(this.binanceClient.getCryptocurrency(cryptocurrency.getSymbol()));
            quotation.setDayAndTime(LocalDateTime.now());
            quotes.add(quotation);
        }
        return quotes;
    }


}
