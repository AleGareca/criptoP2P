package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.dto.CryptocurrencyDto;
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
    private ICrytocurrencyService crytocurrencyService;

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
        for(CryptocurrencyDto cryptocurrencyDto : this.crytocurrencyService.get()) {
            quotation = new Quotation();
            //quotation.setCryptocurrencyDto(this.binanceClient.getCryptocurrency(cryptocurrencyDto.getSymbol()));
            quotation.setDayAndTime(LocalDateTime.now());
            quotes.add(quotation);
        }
        return quotes;
    }


}
