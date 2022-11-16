package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.dto.CryptocurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrytocurrencyService implements ICrytocurrencyService {

    private final String hashReference = "Crytocurrency";

    @Resource(name="redisTemplate")
    private HashOperations<String, Integer, Cryptocurrency> hashOperations;

    @Autowired
    private MapperComponent modelMapper;

    @Override
    public List<CryptocurrencyDto> get() {
        List<CryptocurrencyDto> cryptocurrencies = this.modelMapper.ToList(this.hashOperations.values(this.hashReference),CryptocurrencyDto.class);
        return cryptocurrencies;
    }

    @Override
    public void save(CryptocurrencyDto cryptocurrencyDto) {
        var cryptocurrency = this.modelMapper.To(cryptocurrencyDto, Cryptocurrency.class);
        this.hashOperations.putIfAbsent(this.hashReference, cryptocurrency.getId(), cryptocurrency);
    }

    @Override
    public void update(CryptocurrencyDto cryptocurrencyDto) {
        var cryptocurrency = this.modelMapper.To(cryptocurrencyDto, Cryptocurrency.class);
        this.hashOperations.put(this.hashReference, cryptocurrency.getId(), cryptocurrency);
    }

    @Override
    public CryptocurrencyDto getById(Integer id) {
        return this.modelMapper.To(this.hashOperations.get(this.hashReference, id),CryptocurrencyDto.class);
    }

    @Override
    public void delete(Integer id) {
        this.hashOperations.delete(this.hashReference, id);
    }
}
