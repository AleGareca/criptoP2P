package com.unq.desa.criptoP2P.controller;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.TransactionDto;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.service.TransactionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@Validated
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private MapperComponent modelMapper;

    @Operation(summary = "Generate transaction")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/transaction/tranfer")
    public void tranfer(@Valid @RequestBody TransactionDto transaction, Errors errors) throws Exception {
        this.transactionService.transferOperation(modelMapper.To(transaction, Transaction.class));
    }
    @Operation(summary = "Confirm operation transaction")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/transaction/confirm")
    public void confirm(@Valid @RequestBody TransactionDto transaction, Errors errors) throws Exception {
        this.transactionService.operationConfirm(modelMapper.To(transaction, Transaction.class));
    }
    @Operation(summary = "Cancel transaction")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/transaction/cancelled")
    public void cancelled(@RequestBody TransactionDto transaction, Errors errors) throws Exception {
        this.transactionService.operationCancelled(modelMapper.To(transaction, Transaction.class));
    }

}
