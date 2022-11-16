package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.dto.CryptocurrencyDto;

import java.util.List;

public interface ICrytocurrencyService {

    public List<CryptocurrencyDto> get();

    public void save(CryptocurrencyDto cryptocurrencyDto);

    public void update(CryptocurrencyDto cryptocurrencyDto);

    public CryptocurrencyDto getById(Integer id);

    public void delete(Integer id);

}
