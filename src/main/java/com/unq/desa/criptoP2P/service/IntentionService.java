package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IntentionService implements IIntentionService {
    @Autowired
    private IIntentionRepository intentionRepository;

    public List<Intention> get(){
        return this.intentionRepository.findAll();
    }

    public void save(Intention intention) {
        this.intentionRepository.save(intention);
    }

    public Intention getById(Integer id) {
        return this.intentionRepository.findById(id).get();
    }

    public void delete(Integer id) {
        this.intentionRepository.deleteById(id);
    }

    @Override
    public List<Intention> listOfPurchaseIntentionsOrActiveSalesOfAUser() {
        return null;
    }
}
