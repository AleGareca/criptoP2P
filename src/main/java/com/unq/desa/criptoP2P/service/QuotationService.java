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

    @CachePut(value="quotation")
    @Override
    public void save(QuotationDto quotationDto, Integer id) {
        LOG.info("Returning customer information for save quotation id {} ",id);
        var quotation = this.modelMapper.To(quotationDto, Quotation.class);
        quotation.setSymbol(quotationDto.getSymbolCryptocurrency());
        this.quotationRepository.save(quotation);
    }

    @Cacheable(value="quotation",key="#id")
    @Override
    public Quotation getById(Integer id) {
        LOG.info("Returning customer information for get quotation id {} ",id);
        var quotation = this.quotationRepository.getReferenceById(id);
        return this.quotationRepository.getReferenceById(id);
    }

    @CacheEvict(value="quotation")
    @Override
    public void delete(Integer id) {
        LOG.info("Returning customer information for delete quotation id {} ",id);
        this.quotationRepository.deleteById(id);
    }

    @Override
    public void quotesUpdate() {
        List<CryptoOcurrency> cryptoOcurrencies = new ArrayList<>();
        for(CryptoOcurrency cryptocurrency : this.crytoOcurrencyRepository.findAll()) {
            var quotation = this.quotationRepository.findBySymbol(cryptocurrency.getSymbol());
            var cryto = modelMapper.To(this.binanceClient.getCryptocurrency(cryptocurrency.getSymbol()),CryptoOcurrency.class);
            if(quotation != null) {
                quotation.setDayAndTime(LocalDateTime.now());
                this.quotationRepository.save(quotation);
            }
            this.crytoOcurrencyRepository.save(cryto);
        }
    }


}
