package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.unq.desa.criptoP2P.model.enums.operation.Operation.Purchase;
import static com.unq.desa.criptoP2P.model.enums.operation.Operation.Sale;

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
    public void transferOperation(Transaction transaction) {
       var systemPrice = binanceClient.getCryptocurrency(transaction.getIntention().getActiveCripto().getSymbol()).getPrice();
        transaction.transfer(systemPrice);
        this.transactionRepository.save(transaction);

    }

    @Override
    public void operationConfirm(Transaction transaction) {
        transaction.confirm();
        this.transactionRepository.save(transaction);
    }

    @Override
    public void operationCancelled(Transaction transaction) {
        transaction.operationCanceledByUser();
        this.transactionRepository.save(transaction);
    }





}
