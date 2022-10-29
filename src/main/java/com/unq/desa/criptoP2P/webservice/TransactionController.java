package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping(value = "/transaction/tranfer")
    public void tranfer(@Valid @RequestBody Transaction transaction, Errors errors) throws Exception {
        this.transactionService.transferOperation(transaction);
    }

    @PostMapping(value = "/transaction/confirm")
    public void confirm(@Valid @RequestBody Transaction transaction, Errors errors) throws Exception {
        this.transactionService.operationConfirm(transaction);
    }

    @PostMapping(value = "/transaction/cancelled")
    public void cancelled(@Valid @RequestBody Transaction transaction, Errors errors) throws Exception {
        this.transactionService.operationCancelled(transaction);
    }

}
