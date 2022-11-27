package com.unq.desa.criptoP2P.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.user.User;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
@Data
public class RequestTransferDto {
  @JsonProperty("intention_id")
  private Integer id;
  private String symbol;
  @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Mail no valido")
  @JsonProperty("email_to_transfer")
  private String email;
}
