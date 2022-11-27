package com.unq.desa.criptoP2P.service;


import com.unq.desa.criptoP2P.model.dto.RequestRegisterIntetionDto;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public interface IIntentionService {
    public List<Intention> get();

    public void save(Intention intention);

    public Intention getById(Integer id);

    public void delete(Integer id);
    @Lazy
    public List<Intention> listIntentionsActive();

    public Intention userExpressesHisIntentionToBuyOrSell(Intention intention,int userId);


    void createIntention(RequestRegisterIntetionDto intention, String mail);
}
