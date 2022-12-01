package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.config.CustomCacheEventLogger;
import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;
import com.unq.desa.criptoP2P.model.dto.QuotationDto;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.persistence.ICrytoOcurrencyRepository;
import com.unq.desa.criptoP2P.persistence.IQuotationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class QuotationService implements IQuotationService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomCacheEventLogger.class);

    @Autowired
    private BinanceClient binanceClient;

   @Autowired
    private ICrytoOcurrencyRepository crytoOcurrencyRepository;

    @Autowired
    private MapperComponent modelMapper;

    @Autowired
    private IQuotationRepository quotationRepository;


    @Override
    public List<QuotationDto> get() {
        LOG.info("Returning customer information for get quotations ");
        return this.modelMapper.ToList(this.quotationRepository.findAll(), QuotationDto.class);
    }

    @Override
    public void save(QuotationDto quotationDto, Integer id) {
        LOG.info("Returning customer information for save quotation id {} ",id);
        var quotation = this.modelMapper.To(quotationDto, Quotation.class);
        quotation.setSymbol(quotationDto.getSymbolCryptocurrency());
        this.quotationRepository.save(quotation);
    }

    @Override
    public Quotation getById(Integer id) {
        LOG.info("Returning customer information for get quotation id {} ",id);
        var quotation = this.quotationRepository.getReferenceById(id);
        return this.quotationRepository.getReferenceById(id);
    }


    @Override
    public void delete(Integer id) {
        LOG.info("Returning customer information for delete quotation id {} ",id);
        this.quotationRepository.deleteById(id);
    }

    @Override
    @Cacheable(value="quotation")
    public void quotesUpdate() {
        List<CryptoOcurrency> cryptoOcurrencies = new ArrayList<>();
        for(CryptoOcurrency cryptocurrency : this.crytoOcurrencyRepository.findAll()) {
            var quotations = this.quotationRepository.findBySymbol(cryptocurrency.getSymbol());
            var cryto = modelMapper.To(this.binanceClient.getCryptocurrency(cryptocurrency.getSymbol()),CryptoOcurrency.class);
            quotations.stream().forEach(q -> {
                    if (q != null) {
                        q.setDayAndTime(LocalDateTime.now());
                        this.quotationRepository.save(q);
                        LOG.info("Returning customer information for update quotations  {} ", q);
                    }
               }
            );
            this.crytoOcurrencyRepository.save(cryto);
        }
    }


}
