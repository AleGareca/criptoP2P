package com.unq.desa.criptoP2P.service.iservice;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.dto.RequestRegisterIntetionDto;
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
