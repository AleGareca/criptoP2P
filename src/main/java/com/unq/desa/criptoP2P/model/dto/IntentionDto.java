package com.unq.desa.criptoP2P.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntentionDto {
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private QuotationDto quotation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double amountOfOperationInPesos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isActive;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Operation operacion;
}
