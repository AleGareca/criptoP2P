package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.dto.TransactionDto;
import com.unq.desa.criptoP2P.model.transaction.Transaction;

import java.util.List;

public interface ITransactionService {

     List<Transaction> get();

     void save(Transaction transaction);

     Transaction getById(Integer id);

     void delete(Integer id);

     TransactionDto transferOperation(String email, RequestTransferDto requestTransferDto);

     TransactionDto operationConfirm(Integer transaction_id, String emailUser);

     TransactionDto operationCancelled(Integer transaction_id, String emailUser);


}
