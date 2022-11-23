package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.transaction.Transaction;

import java.util.List;

public interface ITransactionService {

    public List<Transaction> get();

    public void save(Transaction transaction);

    public Transaction getById(Integer id);

    public void delete(Integer id);

    public Transaction transferOperation(Transaction transaction);

    public Transaction operationConfirm(Transaction transaction);

    public Transaction operationCancelled(Transaction transaction);


}
