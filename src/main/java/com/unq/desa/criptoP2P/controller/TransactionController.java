package com.unq.desa.criptoP2P.controller;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.service.TransactionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    public void tranfer(Authentication authentication, @Valid @RequestBody RequestTransferDto requestTransferDto, Errors errors) throws Exception {
        this.transactionService.transferOperation(authentication.getName(),requestTransferDto);
    }
    @Operation(summary = "Confirm operation transaction")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/transaction/confirm")
    public ResponseEntity<Object> confirm(@NotNull @RequestParam("transaction_id") Integer transaction_id, Errors errors) throws Exception {
        this.transactionService.operationConfirm(transaction_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Cancel transaction")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/transaction/cancelled")
    public void cancelled(@NotNull @RequestParam("transaction_id") Integer transaction_id, Errors errors) throws Exception {
        this.transactionService.operationCancelled(transaction_id);
    }

}
