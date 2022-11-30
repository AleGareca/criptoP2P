package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;
import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.dto.TransactionDto;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.ICrytoOcurrencyRepository;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.ITransactionRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IIntentionRepository intentionRepository;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private BinanceClient binanceClient;
    @Autowired
    private ICrytoOcurrencyRepository crytoOcurrencyRepository;

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
    public TransactionDto transferOperation(String email, RequestTransferDto requestTransferDto) {
        var user = userRepository.findByEmail(email);
        var intention = user.getIntentions().stream().filter(i -> i.getId() == requestTransferDto.getId()).findFirst().get();
        var systemPrice = crytoOcurrencyRepository.findBySymbol(requestTransferDto.getSymbol());
        var result = initTransfer(user,systemPrice,intention,requestTransferDto.getAmountOfOperationInPesos(),requestTransferDto.getAmountOfOperation());
        return TransactionDto.builder().id(result.getId())
                .stateTransaction(result.getStateTransaction())
                .amountOfOperation(requestTransferDto.getAmountOfOperation())
                .dayAndTimeOfOperation(result.getDayAndTimeOfOperation())
                .symbolCripto(intention.getQuotation().getSymbol())
                .amountOfOperationInPesos(requestTransferDto.getAmountOfOperationInPesos())
                .shippingAddress(result.getShippingAddress())
                .build();
    }

    private Transaction initTransfer(User user, CryptoOcurrency systemPrice, Intention intention, Double amountInPesos, Integer amountOfOperation) {
        var transfer = Transaction.builder().
                stateTransaction(StateTransaction.Transferred)
                .user(user).intention(intention).amountOfOperationInPesos(amountInPesos)
                .amountOfOperation(amountOfOperation).symbolCripto(intention.getQuotation().getSymbol())
                .dayAndTimeOfOperation(LocalDateTime.now()).build();


        operationTransfer(systemPrice,intention,transfer,amountInPesos);

        if(user.getTransactions() == null){
            var transactions =  new ArrayList<Transaction>();
            transactions.add(transfer);
        }else{
            user.getTransactions().add(transfer);
        }
        var transaction = transactionRepository.save(transfer);
        userRepository.save(user);
        return transaction;
    }

    private void operationTransfer(CryptoOcurrency cryptoOcurrency,Intention intention,Transaction transaction,Double amountInPesos) {
        if (this.theTransferOfSaleIsValid(intention)) {
            this.transferOrCancel(cryptoOcurrency,intention,transaction,amountInPesos);
        }
    }

    private boolean theTransferOfSaleIsValid(Intention intention) {
        return intention.getOperacion() == Operation.Purchase;
    }

    private void transferOrCancel(CryptoOcurrency cryptoOcurrency, Intention intention,Transaction transfer,Double amountInPesos) {

        if ( amountInPesos < cryptoOcurrency.getPrice()) {
            transfer.setStateTransaction(StateTransaction.Cancelled);
            intention.setActive(false);
        } else {
            transfer.setStateTransaction(StateTransaction.Transferred);
            this.shippingAddress(transfer);
        }
    }

    private void shippingAddress(Transaction transaction) {
        if (transaction.getIntention().getOperacion() == Operation.Purchase){
            transaction.setShippingAddress(transaction.getUser().getCvu());
        } else {
            transaction.setShippingAddress(transaction.getUser().getWalletAddress());
        }
    }

    @Override
    public TransactionDto operationConfirm(Integer transaction_id, String emailUser) {
        var user = userRepository.findByEmail(emailUser);
        var transaction =user.getTransactions().stream().filter(i -> i.getId() == transaction_id).findFirst().get();
        transaction.setStateTransaction(StateTransaction.Confirm);
        shippingAddress(transaction);
        increaseUserReputationPoints(transaction,user);
        user.setSuccessfulOperation(user.getSuccessfulOperation() + 1);
        user.setNumberOfOperations(user.getSuccessfulOperation() + 1);
        transaction.setNumberOfOperations(user.getNumberOfOperations());
        transaction.getIntention().setActive(false);
        var result = transactionRepository.save(transaction);

        return TransactionDto.builder().id(result.getId())
                .stateTransaction(result.getStateTransaction())
                .amountOfOperation(result.getAmountOfOperation())
                .dayAndTimeOfOperation(result.getDayAndTimeOfOperation())
                .symbolCripto(transaction.getSymbolCripto())
                .amountOfOperationInPesos(result.getAmountOfOperationInPesos())
                .build();
    }

    private void increaseUserReputationPoints(Transaction transaction, User user) {
        LocalDateTime currentTime = LocalDateTime.now();
        if(transaction.getDayAndTimeOfOperation().getHour() - currentTime.getHour() < 30) {
            user.setReputation(user.getReputation() + 10);
        } else {
            user.setReputation(user.getReputation() + 5);
        }
    }

    @Override
    public TransactionDto operationCancelled(Integer transaction_id, String emailUser) {
        var user = userRepository.findByEmail(emailUser);
        var transaction =user.getTransactions().stream().filter(i -> i.getId() == transaction_id).findFirst().get();
        transaction.setStateTransaction(StateTransaction.Cancelled);
        user.setReputation(user.getReputation() - 20);
        transaction.setReputationOfUser(user.getReputation());
        transaction.getIntention().setActive(false);
        var result = transactionRepository.save(transaction);

        return TransactionDto.builder().id(result.getId())
                .stateTransaction(result.getStateTransaction())
                .amountOfOperation(result.getAmountOfOperation())
                .dayAndTimeOfOperation(result.getDayAndTimeOfOperation())
                .symbolCripto(transaction.getSymbolCripto())
                .amountOfOperationInPesos(result.getAmountOfOperationInPesos())
                .build();
    }

}
