package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;
import com.unq.desa.criptoP2P.service.CrytoOcurrencyService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CrytoOcurrencyController {

    @Autowired
    private CrytoOcurrencyService crytocurrencyService;

    @Operation(summary = "Return all crytocurrencies (internal crytocurrency)")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/crytocurrencies")
    public List<CryptoOcurrencyDto> index() {
        return this.crytocurrencyService.get();
    }
    @Operation(summary = "Get a Crytocurrency by its id")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=404,message = "Crytocurrency not found")})
    @GetMapping("/user")
    public CryptoOcurrencyDto show(@RequestParam("crytocurrencyId") Integer id) {
        return this.crytocurrencyService.getById(id);
    }
    @Operation(summary = "Modify some crytocurrency data")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/crytocurrency")
    public void update(@RequestBody CryptoOcurrencyDto cryptocurrencyDto, Errors errors) throws Exception{
        this.crytocurrencyService.update(cryptocurrencyDto);
    }
    @Operation(summary = "Permanently delete a crytocurrency")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @DeleteMapping(value = "/crytocurrency")
    public void delete(@PathVariable Integer id) {
        this.crytocurrencyService.delete(id);
    }

}
