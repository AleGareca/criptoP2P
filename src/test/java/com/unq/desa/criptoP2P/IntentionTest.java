package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;
import com.unq.desa.criptoP2P.model.dto.RequestRegisterIntetionDto;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.ICrytoOcurrencyRepository;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.IQuotationRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import com.unq.desa.criptoP2P.service.IIntentionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class IntentionTest {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICrytoOcurrencyRepository crytoOcurrencyRepository;
    @Autowired
    private IQuotationRepository quotationRepository;
    @Autowired
    private IIntentionService intentionService;
    @Autowired
    private IIntentionRepository intentionRepository;

    private CryptoOcurrency cryptoOcurrency;
    private LocalDateTime dateTime;
    private Quotation quotation;
    private User user;
    private Intention intention1U1;
    private Double amountInPesos;
    private RequestRegisterIntetionDto intentionDto;

    @BeforeEach
    public void setUp() {
        this.dateTime = LocalDateTime.now();
        this.quotation = this.quotationRepository.getReferenceById(18);
        this.cryptoOcurrency = this.crytoOcurrencyRepository.findBySymbol(quotation.getSymbol());
        this.user = this.userRepository.getReferenceById(15);
        this.amountInPesos = this.cryptoOcurrency.getPrice();
        this.intentionDto = RequestRegisterIntetionDto.builder()
                .price(this.amountInPesos)
                .isActive(true)
                .state("Sale")
                .build();

    }

    @AfterEach
    public void tearDown() {
        this.intentionRepository.deleteAll();
    }

    @Test
    public void givenAnyTransactionOfPurchaseWhenItIsExpectedThatTheTransferOperationCanBeCarriedOut() throws Exception {

       var intention = intentionService.createIntention(intentionDto,this.user.getEmail());

        Assertions.assertEquals(intention.getOperacion(),this.intention1U1.getOperacion());
        Assertions.assertTrue(intention.isActive());
        Assertions.assertEquals(intention.getQuotation().getId(),this.quotation.getId());
        Assertions.assertEquals(intention.getAmountOfOperationInPesos(),this.amountInPesos);
        Assertions.assertEquals(intention.getUserCripto().getEmail(),this.user.getEmail());

    }



}
