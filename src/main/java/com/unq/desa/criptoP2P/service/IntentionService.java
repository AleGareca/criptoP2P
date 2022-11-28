package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.dto.RequestRegisterIntetionDto;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.IQuotationRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Intention userExpressesHisIntentionToBuyOrSell(Intention intention,int userId) {
        var user = userRepository.getReferenceById(userId);
        intention.setUserCripto(user);
        this.intentionRepository.save(intention);
        return intention;
    }

    @Override
    public void createIntention(RequestRegisterIntetionDto intention, String mail) {
        var user = userRepository.findByEmail(mail);
        var quotation = quotationRepository.findBySymbol(intention.getSymbol());
        var newIntention= Intention.builder()
                .amountOfOperationInPesos(intention.getPrice())
                .userCripto(user)
                .quotation(quotation)
                .operacion(Operation.valueOf(intention.getState()))
                .isActive(intention.getIsActive())
                .build();
        intentionRepository.save(newIntention);

    }

}
