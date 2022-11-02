package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.persistence.ITransactionRepository;
import com.unq.desa.criptoP2P.service.iservice.IIntentionService;
import com.unq.desa.criptoP2P.service.iservice.ITransactionService;
import com.unq.desa.criptoP2P.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IIntentionService intentionService;

    @Autowired
    private IUserService userService;
    @Autowired
    private BinanceClient binanceClient;

    @Override
    public List<Transaction> get() {
        return this.transactionRepository.findAll();
    }

    @Override
    public void save(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

    @Override
    public Transaction getById(Integer id) {
        return this.transactionRepository.getReferenceById(id);
    }

    @Override
    public void delete(Integer id) {
        this.transactionRepository.deleteById(id);
    }

    @Override
    public Transaction transferOperation(Transaction transaction) {
        var systemPrice = binanceClient.getCryptocurrency(transaction.getIntention().getQuotation().getCryptocurrency().getSymbol());
        transaction.setStateTransaction(StateTransaction.Transferred);
        transaction.transfer(systemPrice);
        this.transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction operationConfirm(Transaction transaction) {
        transaction.setStateTransaction(StateTransaction.Confirm);
        transaction.confirm();
        this.transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction operationCancelled(Transaction transaction) {
        transaction.setStateTransaction(StateTransaction.Cancelled);
        transaction.operationCanceledByUser();
        this.transactionRepository.save(transaction);
        return transaction;
    }





}
