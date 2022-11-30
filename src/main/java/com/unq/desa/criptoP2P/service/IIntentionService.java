package com.unq.desa.criptoP2P.service;


import com.unq.desa.criptoP2P.model.dto.RequestRegisterIntetionDto;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public interface IIntentionService {
    public List<Intention> get();

    public void save(Intention intention);

    public Intention getById(Integer id);

    public void delete(Integer id);
    @Lazy
    public List<Intention> listIntentionsActive();

    public Intention userExpressesHisIntentionToBuyOrSell(Intention intention, User user);


    Intention createIntention(RequestRegisterIntetionDto intention, String mail);

    Object getIntentionUser(String mail);
}
