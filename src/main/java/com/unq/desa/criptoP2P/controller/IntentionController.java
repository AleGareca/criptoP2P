package com.unq.desa.criptoP2P.controller;

import com.unq.desa.criptoP2P.config.AuthCredential;
import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.dto.IntentionDto;
import com.unq.desa.criptoP2P.model.dto.RequestRegisterIntetionDto;
import com.unq.desa.criptoP2P.service.IntentionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@Validated
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
    public List<IntentionDto> getAllIntentions() {
        return modelMapper.ToList(intentionService.get(), IntentionDto.class);
    }
    @Operation(summary = "Get by intention Id")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/intention")
    public IntentionDto getIntentionById(@RequestParam("intentionId") Integer id) {
        return modelMapper.To(this.intentionService.getById(id), IntentionDto.class);
    }
    @Operation(summary = "Register intention")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/registerIntention")
    public void register(@Valid @RequestBody RequestRegisterIntetionDto intention,
                         AuthCredential authCredential, Errors errors) throws Exception {
        this.intentionService.createIntention(intention,authCredential.getMail());

    }
    @Operation(summary = "Delete intention user")
    @SecurityRequirement(name = "bearerAuth")
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

