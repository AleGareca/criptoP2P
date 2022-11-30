package com.unq.desa.criptoP2P.controller;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.dto.TransactionDto;
import com.unq.desa.criptoP2P.service.TransactionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MapperComponent modelMapper;

    @Operation(summary = "Generate transaction")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/transaction/tranfer")
    public  ResponseEntity<TransactionDto> tranfer(@Valid @RequestBody RequestTransferDto requestTransferDto, Authentication authentication, Errors errors) throws Exception {
        var result =this.transactionService.transferOperation(authentication.getName(),requestTransferDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @Operation(summary = "Confirm operation transaction")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/transaction/confirm")
    public ResponseEntity<TransactionDto> confirm(@RequestParam(value = "transaction_id") String transaction_id,
                                          Authentication authentication) throws Exception {
        var result = this.transactionService.operationConfirm(Integer.valueOf(transaction_id),authentication.getName());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @Operation(summary = "Cancel transaction")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/transaction/cancelled")
    public ResponseEntity<TransactionDto> cancelled(@RequestParam("transaction_id") Integer transaction_id, Authentication authentication, Errors errors) throws Exception {
        var result = this.transactionService.operationCancelled(transaction_id,authentication.getName());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
