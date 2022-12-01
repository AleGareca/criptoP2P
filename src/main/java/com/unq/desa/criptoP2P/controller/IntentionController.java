package com.unq.desa.criptoP2P.controller;

import org.springframework.security.core.Authentication;
import com.unq.desa.criptoP2P.config.MapperComponent;
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

    @Operation(summary = "Get by intention User")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/intention")
    public List<IntentionDto> getIntentionUser(Authentication authCredential) {
        var intentions = intentionService.getIntentionUser(authCredential.getName());
        var resultMap = modelMapper.toListIntentions(intentions);
        return resultMap;
    }
    @Operation(summary = "Register intention")

    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/registerIntention")
    public IntentionDto register(@Valid @RequestBody RequestRegisterIntetionDto intention,
                         Authentication authCredential, Errors errors) throws Exception {
        return modelMapper.toIntentionDto(this.intentionService.createIntention(intention,authCredential.getName()));


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

