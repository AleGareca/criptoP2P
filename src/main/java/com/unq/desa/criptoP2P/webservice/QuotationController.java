package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @GetMapping("/quotation")
    public List<Quotation> index()throws Exception {
        return this.quotationService.get();
    }

    @GetMapping("/quotation")
    public Quotation show(@RequestParam("quotationId") Integer id) throws Exception {
        return this.quotationService.getById(id);
    }

    @PostMapping(value = "/quotation")
    public void register(@Valid @RequestBody Quotation quotation, Errors errors) throws Exception {
        this.quotationService.save(quotation);
    }

    @PutMapping(value = "/quotation")
    public void update(@RequestBody Quotation quotation) throws Exception {
        this.quotationService.save(quotation);
    }

    @DeleteMapping(value = "/quotation")
    public void delete(@PathVariable Integer id) throws Exception {
        this.quotationService.delete(id);
    }

    @GetMapping(value = "/quotation/cryptoAssetQuote")
    public List<Cryptocurrency> cotizacion() throws Exception {
        return this.quotationService.cotizacion();
    }

}
