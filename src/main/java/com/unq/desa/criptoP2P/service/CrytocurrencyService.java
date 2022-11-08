package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.dto.CryptocurrencyDto;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.ICrytocurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CrytocurrencyService implements ICrytocurrencyService {

    @Autowired
    ICrytocurrencyRepository crytocurrencyRepository;

    @Autowired
    private MapperComponent modelMapper;

    @Override
    public List<CryptocurrencyDto> get() {
        return modelMapper.ToList(this.crytocurrencyRepository.findAll(),CryptocurrencyDto.class);
    }

    @Override
    public void update(CryptocurrencyDto cryptocurrencyDto) {
       this.crytocurrencyRepository.save(this.modelMapper.To(cryptocurrencyDto, Cryptocurrency.class));
    }

    @Override
    public CryptocurrencyDto getById(Integer id) {
        return modelMapper.To(this.crytocurrencyRepository.getReferenceById(id),CryptocurrencyDto.class);
    }

    @Override
    public void delete(Integer id) {
        this.delete(id);
    }
}
