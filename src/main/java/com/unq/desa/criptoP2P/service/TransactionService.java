package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionService implements ITransactionService {

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IIntentionService intentionService;

    @Autowired
    private IUserService userService;

    @Override
    public List<Transaction> get() {
        return this.transactionService.get();
    }

    @Override
    public void save(Transaction transaction) {
        this.transactionService.save(transaction);
    }

    @Override
    public Transaction getById(Integer id) {
        return this.transactionService.getById(id);
    }

    @Override
    public void delete(Integer id) {
        this.transactionService.delete(id);
    }

    @Override
    public void saleOPurchaseOperation(Integer userId, Intention intention) {

       this.intentionService.userExpressesHisIntentionToBuyOrSell(intention,userId);

    }

    @Override
    public void purchaseOperation(Integer userId, Intention intention) {
        this.intentionService.userExpressesHisIntentionToBuyOrSell(intention,userId);
    }

    @Override
    public void operationCancelled(Integer userId, Intention intention) {

    }
}
