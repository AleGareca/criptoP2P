package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static com.unq.desa.criptoP2P.model.enums.operation.Operation.Purchase;
import static com.unq.desa.criptoP2P.model.enums.operation.Operation.Sale;

public class TransactionService implements ITransactionService {

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IIntentionService intentionService;

    @Autowired
    private IUserService userService;
    @Autowired
    private BinanceClient binanceClient;

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
    public void transferOperation(Transaction transaction) {
        Double systemPrice = binanceClient.getCryptocurrency(transaction.getIntention().getActiveCripto().getSymbol()).getPrice();
        transaction.transfer(systemPrice);
        this.transactionService.save(transaction);

    }

    @Override
    public void operationConfirm(Transaction transaction) {
        transaction.confirm();
        this.transactionService.save(transaction);
    }

    @Override
    public void operationCancelled(Transaction transaction) {
        transaction.operationCanceledByUser();
        this.transactionService.save(transaction);
    }





}
