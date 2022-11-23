package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;

import java.util.List;

public interface ICrytoOcurrencyService {

    public List<CryptoOcurrencyDto> get();

    public void update(CryptoOcurrencyDto cryptocurrencyDto);

    public CryptoOcurrencyDto getById(Integer id);

    public void delete(Integer id);

}
