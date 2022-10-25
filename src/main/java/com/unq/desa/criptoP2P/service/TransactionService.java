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
    public void transferOperation(Integer userId, Integer intentionId,Transaction transaction) {

        Transaction updateTransaction = this.transferValidation(intentionId,transaction);


    }

    @Override
    public void operationConfirm(Integer userId, Integer intentionId,Transaction transaction) {

    }

    @Override
    public void operationCancelled(Integer userId, Integer intentionId,Transaction transaction) {

    }

    public Transaction transferValidation(Integer intentionId,Transaction transaction) {
        Intention intention = this.intentionService.getById(intentionId);
        Double systemPrice = binanceClient.getCryptocurrency(intention.getActiveCripto().getSymbol()).getPrice();

        if(intention.getAmountOfActiveCripto() > systemPrice || intention.getAmountOfActiveCripto() < systemPrice) {
            transaction.setTransaction(StateTransaction.Cancelled);
        } else {
            transaction.setTransaction(StateTransaction.Transferring);
        }

        return transaction;

    }

    private void operationCanceledByUser() {
            /*if() {
                this.operationPerformedWithin30Minutes();
            }

            if() {
                this.operationPerformedAfter30Minutes();
            }*/
    }

    private void operationCanceledByTheSystem() {

    }

    private void operationPerformedWithin30Minutes() {
        // suman 10 puntos
    }

    private void operationPerformedAfter30Minutes() {
        // minutos suman 5
    }

}
