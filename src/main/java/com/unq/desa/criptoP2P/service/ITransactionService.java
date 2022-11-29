package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.transaction.Transaction;

import java.util.List;

public interface ITransactionService {

     List<Transaction> get();

     void save(Transaction transaction);

     Transaction getById(Integer id);

     void delete(Integer id);

     Transaction transferOperation(String email, RequestTransferDto requestTransferDto);

     Transaction operationConfirm(Integer transaction_id, String emailUser);

     Transaction operationCancelled(Integer transaction_id, String emailUser);


}
