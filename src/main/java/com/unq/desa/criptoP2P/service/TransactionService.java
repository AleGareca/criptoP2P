package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;
import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
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
    public Transaction transferOperation(String email, RequestTransferDto requestTransferDto) {
        var user = userRepository.findByEmail(requestTransferDto.getEmail());
        var intention = user.getIntentions().stream().filter(i -> i.getId() == requestTransferDto.getId()).findFirst().get();
        var systemPrice = binanceClient.getCryptocurrency(requestTransferDto.getSymbol());
        return initTransfer(user,systemPrice,intention);
    }

    private Transaction initTransfer(User user, CryptoOcurrencyDto systemPrice, Intention intention) {
        var transfer = Transaction.builder().
                stateTransaction(StateTransaction.Transferred)
                .user(user).intention(intention).build();
        transfer.transfer(systemPrice);

        if (transfer.getIntention().getOperacion() == Operation.Purchase) {
            transferOrCancel(systemPrice);
        }

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

    private void transferOrCancel(CryptoOcurrencyDto systemPrice) {
       /* if (this.intention.getQuotation().getCryptocurrencyDto().getPrice() > systemPrice.getPrice()
                || this.intention.getQuotation().getCryptocurrencyDto().getPrice() < systemPrice.getPrice()) {
            this.setStateTransaction(StateTransaction.Cancelled);
            this.intention.setActive(false);
        } else {
            this.setStateTransaction(StateTransaction.Transferred);
            this.shippingAddress();
        }*/
    }

    public void shippingAddress(Transaction transaction, User user) {
        if (transaction.getIntention().getOperacion() == Operation.Purchase){
            transaction.setShippingAddress(user.getCvu());
        } else {
            transaction.setShippingAddress(user.getWalletAddress());
        }
    }

    @Override
    public Transaction operationConfirm(Integer transaction_id, String emailUser) {
        var user = userRepository.findByEmail(emailUser);
        var transaction =user.getTransactions().stream().filter(i -> i.getId() == transaction_id).findFirst().get();
        transaction.setStateTransaction(StateTransaction.Confirm);
        shippingAddress(transaction,user);
        increaseUserReputationPoints(transaction,user);
        user.setSuccessfulOperation(1);
        user.setNumberOfOperations(1);
        transaction.setNumberOfOperations(user.getNumberOfOperations());
        transaction.getIntention().setActive(false);

        return this.transactionRepository.save(transaction);
    }

    public void increaseUserReputationPoints(Transaction transaction, User user) {
        LocalDateTime currentTime = LocalDateTime.now();
        if(transaction.getDayAndTimeOfOperation().getHour() - currentTime.getHour() < 30) {
            user.setReputation(user.getReputation() + 10);
        } else {
            user.setReputation(user.getReputation() + 5);
        }
    }

    @Override
    public Transaction operationCancelled(Integer transaction_id, String emailUser) {
        var user = userRepository.findByEmail(emailUser);
        var transaction =user.getTransactions().stream().filter(i -> i.getId() == transaction_id).findFirst().get();
        transaction.setStateTransaction(StateTransaction.Cancelled);
        user.setReputation(user.getReputation() - 20);
        transaction.setReputationOfUser(user.getReputation());
        transaction.getIntention().setActive(false);

        return this.transactionRepository.save(transaction);
    }

}
