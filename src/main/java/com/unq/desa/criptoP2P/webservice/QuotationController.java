package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.QuotationDto;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.service.QuotationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class QuotationController {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private MapperComponent modelMapper;
    @Operation(summary = "Return all quotations (internal use) ")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/quotations")
    public List<QuotationDto> index()throws Exception {
        return modelMapper.ToList(this.quotationService.get(),QuotationDto.class);
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/quotation")
    public QuotationDto show(@RequestParam("quotationId") Integer id) throws Exception {
        return modelMapper.To(this.quotationService.getById(id),QuotationDto.class);
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/quotation")
    public void register(@Valid @RequestBody Quotation quotation, Errors errors) throws Exception {
        this.quotationService.save(quotation);
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/quotationUpdate")
    public void update(@RequestBody Quotation quotationDto) throws Exception {
        this.quotationService.save(modelMapper.To(quotationDto, Quotation.class));
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @DeleteMapping(value = "/quotationDelete")
    public void delete(@PathVariable Integer id) throws Exception {
        this.quotationService.delete(id);
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping(value = "/quotation/cryptoAssetQuote")
    public List<QuotationDto> quotes() throws Exception {
        return modelMapper.ToList(this.quotationService.quotes(), QuotationDto.class);
    }

}
