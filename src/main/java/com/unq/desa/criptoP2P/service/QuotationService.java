package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.QuotationDto;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.persistence.ICrytoOcurrencyRepository;
import com.unq.desa.criptoP2P.persistence.IQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuotationService implements IQuotationService {

    @Resource(name="redisTemplate")
    private HashOperations<String, Integer, QuotationDto> hashOperations;

    @Autowired
    private BinanceClient binanceClient;

   @Autowired
    private ICrytoOcurrencyRepository crytocurrencyService;

    @Autowired
    private MapperComponent modelMapper;

    @Autowired
    private IQuotationRepository quotationRepository;

    private final String hashReference = "Quotation";

    @Override
    public List<QuotationDto> get() {
        List<QuotationDto> quotationDtos = this.modelMapper.ToList(this.hashOperations.values(this.hashReference), QuotationDto.class);
        return quotationDtos;
    }

    @Override
    public void save(Quotation quotation) {
        this.quotationRepository.save(quotation);
        var quotationDto = this.modelMapper.To(quotation, QuotationDto.class);
        this.hashOperations.put(this.hashReference, quotation.getId(), quotationDto);
    }

    @Override
    public QuotationDto getById(Integer id) {
        return this.modelMapper.To(this.hashOperations.get(this.hashReference, id),QuotationDto.class);
    }

    @Override
    public void delete(Integer id) {
        this.quotationRepository.deleteById(id);
        this.hashOperations.delete(this.hashReference, id);
    }

    @Override
    public List<QuotationDto> quotes() {
        /*List<QuotationDto> quotes = new ArrayList<>();
        QuotationDto quotation;
        or(CryptocurrencyDto cryptocurrencyDto : this.get()) {
            quotation = new QuotationDto();
            var cryptoDto = this.modelMapper.To(this.binanceClient.getCryptocurrency(cryptocurrencyDto.getSymbol()),CryptocurrencyDto.class);
            quotation.setCryptocurrency(cryptoDto);
            quotation.setDayAndTime(LocalDateTime.now());
            quotes.add(quotation);
        }*/
        //return quotes;
        return this.get();
    }


}
