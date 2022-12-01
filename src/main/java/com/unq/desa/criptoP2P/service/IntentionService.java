package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.dto.RequestRegisterIntetionDto;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.IQuotationRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class IntentionService implements IIntentionService {
    @Autowired
    private IIntentionRepository intentionRepository;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IQuotationRepository quotationRepository;

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
    public List<Intention> listIntentionsActive() {
        return intentionRepository.findIntentionByIsActive(true);
    }

    @Override
    public Intention userExpressesHisIntentionToBuyOrSell(Intention intention, User user) {

        if(user.getIntentions() == null){
            var intentions = new ArrayList<Intention>();
            intentions.add(intention);
            user.setIntentions(intentions);
            intention.setUserCripto(user);
        }
        this.userRepository.save(user);
        return intention;

    }

    @Override
    public Intention createIntention(RequestRegisterIntetionDto intention, String mail) {
        var user = userRepository.findByEmail(mail);
        var quotation = quotationRepository.getBySymbol(intention.getSymbol());
        var intentions = new ArrayList<Intention>();
        var newIntention= Intention.builder()
                .amountOfOperationInPesos(intention.getPrice())
                .quotation(quotation)
                .operacion(Operation.valueOf(intention.getState()))
                .isActive(intention.getIsActive())
                .amountOfOperationInPesos(intention.getPrice())
                .userCripto(user)
                .build();
        intentions.add(newIntention);
        user.setIntentions(intentions);
        this.userRepository.save(user);
        return newIntention;

    }

    @Override
    public List<Intention> getIntentionUser(String mail) {
        return userRepository.findByEmail(mail).getIntentions();
    }

}
