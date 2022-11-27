package com.unq.desa.criptoP2P.controller;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.QuotationDto;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.service.QuotationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
@RestController
public class    QuotationController {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private MapperComponent modelMapper;
    @Operation(summary = "Return all quotations (internal use) ")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @Cacheable(value = "quotation")
    @GetMapping("/quotations")
    public List<QuotationDto> index()throws Exception {
        return modelMapper.ToList(this.quotationService.get(),QuotationDto.class);
    }
    @Operation(summary = "Get by Quotation Id")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/quotation")
    public Quotation show(@RequestParam("quotationId") Integer id) throws Exception {
        return this.quotationService.getById(id);
    }
    @Operation(summary = "Register quotation")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @CachePut(value="quotation", key = "#id")
    @PostMapping(value = "/quotation")
    public void register(@Valid @RequestBody QuotationDto quotationDto, @RequestParam("quotationId") Integer id, Errors errors) throws Exception {
        quotationDto.setDayAndTime(LocalDateTime.now());
        this.quotationService.save(quotationDto,id);
    }

    @Operation(summary = "Delete Quotation")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @DeleteMapping(value = "/quotation")
    public void delete(@PathVariable Integer id) throws Exception {
        this.quotationService.delete(id);
    }
    @Operation(summary = "Get all quotation")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping(value = "/quotation/cryptoAssetQuote")
    public List<QuotationDto> quotes() throws Exception {
        return modelMapper.ToList(this.quotationService.quotes(), QuotationDto.class);
    }

}
