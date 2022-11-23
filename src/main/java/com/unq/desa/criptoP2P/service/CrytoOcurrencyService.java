package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;
import com.unq.desa.criptoP2P.persistence.ICrytoOcurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrytoOcurrencyService implements ICrytoOcurrencyService {


    @Autowired
    private ICrytoOcurrencyRepository crytoOcurrencyRepository;

    @Autowired
    private MapperComponent modelMapper;

    @Override
    public List<CryptoOcurrencyDto> get() {
        return modelMapper.ToList(this.crytoOcurrencyRepository.findAll(), CryptoOcurrencyDto.class);
    }

    @Override
    public void update(CryptoOcurrencyDto cryptocurrencyDto) {
        this.crytoOcurrencyRepository.save(this.modelMapper.To(cryptocurrencyDto, CryptoOcurrency.class));
    }

    @Override
    public CryptoOcurrencyDto getById(Integer id) {
        return modelMapper.To(this.crytoOcurrencyRepository.getReferenceById(id), CryptoOcurrencyDto.class);
    }

    @Override
    public void delete(Integer id) {
        this.delete(id);
    }



}
