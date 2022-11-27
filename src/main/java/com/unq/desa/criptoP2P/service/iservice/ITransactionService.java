package com.unq.desa.criptoP2P.service.iservice;

import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.transaction.Transaction;

import java.util.List;

public interface ITransactionService {

    public List<Transaction> get();

    public void save(Transaction transaction);

    public Transaction getById(Integer id);

    public void delete(Integer id);

    public Transaction transferOperation(String email, RequestTransferDto requestTransferDto);

    public Transaction operationConfirm(Integer transaction_id);

    public Transaction operationCancelled(Integer transaction);


}
