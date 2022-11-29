package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.persistence.ICrytoOcurrencyRepository;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.ITransactionRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ICrytoOcurrencyRepository crytoOcurrencyRepository;
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
        var intention = intentionRepository.getById(requestTransferDto.getId());
        var systemPrice = binanceClient.getCryptocurrency(requestTransferDto.getSymbol());
        var price = crytoOcurrencyRepository.findBySymbol(intention.getQuotation().getSymbol()).getPrice();
        var transfer = Transaction.builder().
                stateTransaction(StateTransaction.Transferred)
                .user(user).intention(intention).build();
        transfer.transfer(systemPrice,price);
        return transactionRepository.save(transfer);
    }

    @Override
    public Transaction operationConfirm(Integer transaction_id) {
        var transaction = transactionRepository.getById(transaction_id);
        transaction.setStateTransaction(StateTransaction.Confirm);
        transaction.confirm();
        this.transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction operationCancelled(Integer transaction_id) {
        var transaction = transactionRepository.getById(transaction_id);
        transaction.setStateTransaction(StateTransaction.Cancelled);
        transaction.operationCanceledByUser();
        this.transactionRepository.save(transaction);
        return transaction;
    }





}
