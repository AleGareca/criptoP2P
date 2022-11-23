package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.dto.IntentionDto;
import com.unq.desa.criptoP2P.service.IntentionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class IntentionController {

    @Autowired
    private IntentionService intentionService;
    @Autowired
    private MapperComponent modelMapper;
    @Operation(summary = "Return all intentions")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/intentions")
    public List<IntentionDto> index() {
        return modelMapper.ToList(intentionService.get(), IntentionDto.class);
    }
    @Operation(summary = "Get by intention Id")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/intention")
    public IntentionDto show(@RequestParam("intentionId") Integer id) {
        return modelMapper.To(this.intentionService.getById(id), IntentionDto.class);
    }
    @Operation(summary = "Register intention user")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/registerIntention")
    public void register(@Valid @RequestBody IntentionDto intention, Errors errors) throws Exception {
        this.intentionService.save(modelMapper.To(intention,Intention.class));
    }
    @Operation(summary = "Delete intention user")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @DeleteMapping(value = "/intention")
    public void delete(@PathVariable Integer id) {
        this.intentionService.delete(id);
    }

    @Operation(summary = "Return all active intents")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/intentionToBuyAndSell")
    public List<IntentionDto> listTntentionsActiveOfAUser() {
        return modelMapper.ToList(this.intentionService.listIntentionsActive(),IntentionDto.class);
    }

}

