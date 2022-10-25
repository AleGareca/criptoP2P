package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntentionService implements IIntentionService {
    @Autowired
    private IIntentionRepository intentionRepository;
    @Autowired
    @Lazy
    private IUserService UserService;

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
    public List<Intention> listIntentionsActiveOfAUser(Boolean value) {
        return intentionRepository.findIntentionByIsActive(value);
    }

    @Override
    public Intention userExpressesHisIntentionToBuyOrSell(Intention intention,Integer userId) {
        User user = this.UserService.getById(userId);
        user.setIntention(intention);
        intention.setUserCripto(user);
        this.UserService.save(user);
        this.intentionRepository.save(intention);
        return intention;
    }

    @Override
    public List<Intention> salesIntentionsByPriceAndReputation(Integer amountOfActiveCripto, Integer reputation) {
        return null;
    }

    @Override
    public List<Intention> findByIsActiveAndUserCripto_ReputationAndAmountOfActiveCripto(Boolean isActive, Integer reputation, int amountOfActiveCripto) {
        return this.intentionRepository.findByIsActiveAndUserCripto_ReputationAndAmountOfActiveCripto(isActive,reputation,amountOfActiveCripto);
    }
}
