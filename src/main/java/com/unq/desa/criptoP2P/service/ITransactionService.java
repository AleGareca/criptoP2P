package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;

import java.util.List;

public interface ITransactionService {

    public List<Transaction> get();

    public void save(Transaction transaction);

    public Transaction getById(Integer id);

    public void delete(Integer id);

    public void transferOperation(Integer userId, Integer intentionId,Transaction transaction);

    public void operationConfirm(Integer userId, Integer intentionId,Transaction transaction);

    public void operationCancelled(Integer userId, Integer intentionId,Transaction transaction);


}
