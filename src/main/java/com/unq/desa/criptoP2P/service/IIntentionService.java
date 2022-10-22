package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IIntentionService {
    public List<Intention> get();

    public void save(Intention intention);

    public Intention getById(Integer id);

    public void delete(Integer id);

    public List<Intention> listIntentionsActiveOfAUser();

}
