package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import com.unq.desa.criptoP2P.service.iservice.IIntentionService;
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
    private IIntentionService intentionService;
    @Autowired
    private IIntentionRepository intentionRepository;

    @Autowired
    private BinanceClient binanceClient;

    private LocalDateTime dateTime;
    private Cryptocurrency cryptoIntention1;
    private Quotation quotation1;
    private User user1;
    private Intention intention1U1;


    @BeforeEach
    public void setUp() {
        this.dateTime = LocalDateTime.now();
        this.cryptoIntention1 = this.binanceClient.getCryptocurrency("ALICEUSDT");
        this.quotation1 = new Quotation();
        this.user1 = new User();
        this.intention1U1 = new Intention();

    }

    @AfterEach
    public void tearDown() {
        this.intentionRepository.deleteAll();
    }

    @Test
    public void givenAnyTransactionOfPurchaseWhenItIsExpectedThatTheTransferOperationCanBeCarriedOut() throws Exception {
        Intention intention = anyIntention();

        intentionService.userExpressesHisIntentionToBuyOrSell(intention,user1.getId());

        Assertions.assertEquals(intention.getId(),this.intention1U1.getId());
        Assertions.assertEquals(intention.getOperacion(),this.intention1U1.getOperacion());
        Assertions.assertEquals(intention.getQuotation().getId(),this.intention1U1.getQuotation().getId());
        Assertions.assertEquals(intention.getAmountOfOperationInPesos(),this.intention1U1.getAmountOfOperationInPesos());
        Assertions.assertEquals(intention.getUserCripto().getId(),this.user1.getId());

    }


    private Intention anyIntention() {

        quotation1.setCryptocurrency(this.cryptoIntention1);
        quotation1.setDayAndTime(this.dateTime);

        this.user1.setName("u1");
        this.user1.setEmail("1@gmai.com");
        this.user1.setAddress("calle123");
        this.user1.setPassword("123");
        this.user1.setCvu("017020456000000878653");
        this.user1.setWalletAddress("3J98t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
        this.user1.setReputation(0);
        this.user1.setNumberOfOperations(0);
        this.user1.setSuccessfulOperation(0);

        this.intention1U1.setActive(true);
        this.intention1U1.setAmountOfOperationInPesos(Integer.valueOf("10000"));
        this.intention1U1.setOperacion(Operation.Purchase);
        this.intention1U1.setQuotation(quotation1);

        userRepository.save(user1);

        return this.intention1U1;

    }
}
