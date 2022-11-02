package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.ITransactionRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import com.unq.desa.criptoP2P.service.UserService;
import com.unq.desa.criptoP2P.service.iservice.IIntentionService;
import com.unq.desa.criptoP2P.service.iservice.ITransactionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TransactionTests {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IIntentionService intentionService;
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private BinanceClient binanceClient;

    @Autowired
    private UserService userService;

    private LocalDateTime dateTime;
    private Cryptocurrency cryptoIntention1;
    private Cryptocurrency cryptoIntention2;

    private Quotation quotation1;
    private Quotation quotation2;
    private User user1;
    private User user2;
    private Intention intention1U1;
    private Intention intention2U2;
    private Transaction buyCrypto;

    @BeforeEach
    public void setUp() {
        this.dateTime = LocalDateTime.now();
        this.cryptoIntention1 = this.binanceClient.getCryptocurrency("ALICEUSDT");
        this.cryptoIntention2 = this.binanceClient.getCryptocurrency("BNBUSDT");
        this.quotation1 = new Quotation();
        this.quotation2 = new Quotation();
        this.buyCrypto = new Transaction();
        this.user1 = new User();
        this.user2 = new User();
        this.intention1U1 = new Intention();
        this.intention2U2 = new Intention();
    }

    @AfterEach
    public void tearDown() {
        this.transactionRepository.deleteAll();
    }

    @Test
    public void givenAnyTransactionOfPurchaseWhenItIsExpectedThatTheTransferOperationCanBeCarriedOut() throws Exception {
        Transaction buyCrypto = anyTransaction();
        //After
        Assertions.assertEquals(buyCrypto.getStateTransaction(), StateTransaction.UndefinedState);
        Assertions.assertEquals(buyCrypto.getAmountOfOperationInPesos(),Double.valueOf("270000"));
        Assertions.assertEquals(buyCrypto.getAmountOfOperation(),Integer.valueOf("300"));
        Assertions.assertEquals(buyCrypto.getUser().getId(),this.user1.getId());
        Assertions.assertEquals(buyCrypto.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
        Assertions.assertEquals(buyCrypto.getNumberOfOperations(),this.user1.getNumberOfOperations());
        Assertions.assertEquals(buyCrypto.getIntention(),this.intention1U1);
        Assertions.assertEquals(buyCrypto.getReputationOfUser(),this.user1.getReputation());
        Assertions.assertEquals(buyCrypto.getShippingAddress(),null);

        transactionService.transferOperation(buyCrypto);

        //Before
        Assertions.assertEquals(buyCrypto.getStateTransaction(), StateTransaction.Transferred);
        Assertions.assertEquals(buyCrypto.getAmountOfOperationInPesos(),Double.valueOf("270000"));
        Assertions.assertEquals(buyCrypto.getAmountOfOperation(),Integer.valueOf("300"));
        Assertions.assertEquals(buyCrypto.getUser().getId(),this.user1.getId());
        Assertions.assertEquals(buyCrypto.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
        Assertions.assertEquals(buyCrypto.getNumberOfOperations(),this.user1.getNumberOfOperations());
        Assertions.assertEquals(buyCrypto.getIntention().getId(),this.intention1U1.getId());
        Assertions.assertEquals(buyCrypto.getReputationOfUser(),this.user1.getReputation());
        Assertions.assertEquals(buyCrypto.getShippingAddress(),this.user1.getCvu());
    }

    @Test
    public void givenAnyTransactionOfSaleWhenItIsExpectedThatTheConfirmOperationCanBeCarriedOut() throws Exception {
        Transaction buyCrypto = anyTransaction();
        buyCrypto.setStateTransaction(StateTransaction.Transferred);
        buyCrypto.setUser(user2);
        buyCrypto.setIntention(intention2U2);
        buyCrypto.setNumberOfOperations(user2.getNumberOfOperations());
        buyCrypto.setDayAndTimeOfOperation(LocalDateTime.now());
        buyCrypto.setReputationOfUser(user2.getReputation());

        //After
        Assertions.assertEquals(buyCrypto.getStateTransaction(), StateTransaction.Transferred);
        Assertions.assertEquals(buyCrypto.getAmountOfOperationInPesos(),Double.valueOf("270000"));
        Assertions.assertEquals(buyCrypto.getAmountOfOperation(),Integer.valueOf("300"));
        Assertions.assertEquals(buyCrypto.getUser().getId(),this.user2.getId());
        Assertions.assertEquals(buyCrypto.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
        Assertions.assertEquals(buyCrypto.getNumberOfOperations(),this.user2.getNumberOfOperations());
        Assertions.assertEquals(buyCrypto.getIntention(),this.intention2U2);
        Assertions.assertEquals(buyCrypto.getReputationOfUser(),this.user2.getReputation());
        Assertions.assertEquals(buyCrypto.getShippingAddress(),null);

        transactionService.operationConfirm(buyCrypto);
        var report=userService.generateReport(user2.getId(),LocalDateTime.now().toLocalDate(),LocalDateTime.now().plusDays(10).toLocalDate());
        Assertions.assertTrue(report!= null);
        //Before
        Assertions.assertEquals(buyCrypto.getStateTransaction(), StateTransaction.Confirm);
        Assertions.assertEquals(buyCrypto.getAmountOfOperationInPesos(),Double.valueOf("270000"));
        Assertions.assertEquals(buyCrypto.getAmountOfOperation(),Integer.valueOf("300"));
        Assertions.assertEquals(buyCrypto.getUser().getId(),this.user2.getId());
        Assertions.assertEquals(buyCrypto.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
        Assertions.assertEquals(buyCrypto.getNumberOfOperations(),this.user2.getNumberOfOperations());
        Assertions.assertEquals(buyCrypto.getIntention().getId(),this.intention2U2.getId());
        Assertions.assertEquals(buyCrypto.getReputationOfUser(),this.user2.getReputation());
        //Assertions.assertEquals(buyCrypto.getShippingAddress(),this.user2.getWalletAddress());
    }

    @Test
    public void givsdfsdfriedOut() throws Exception {
        Transaction buyCrypto = anyTransaction();
        buyCrypto.setStateTransaction(StateTransaction.Cancelled);
        buyCrypto.setUser(user2);
        buyCrypto.setIntention(intention2U2);
        buyCrypto.setNumberOfOperations(user2.getNumberOfOperations());
        buyCrypto.setDayAndTimeOfOperation(LocalDateTime.now());
        buyCrypto.setReputationOfUser(user2.getReputation());

        transactionService.operationCancelled(buyCrypto);

        Assertions.assertEquals(buyCrypto.getStateTransaction(), StateTransaction.Cancelled);
        Assertions.assertEquals(buyCrypto.getAmountOfOperationInPesos(),Double.valueOf("270000"));
        Assertions.assertEquals(buyCrypto.getAmountOfOperation(),Integer.valueOf("300"));
        Assertions.assertEquals(buyCrypto.getUser().getId(),this.user2.getId());
        Assertions.assertEquals(buyCrypto.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
        Assertions.assertEquals(buyCrypto.getNumberOfOperations(),this.user2.getNumberOfOperations());
        Assertions.assertEquals(buyCrypto.getIntention().getId(),this.intention2U2.getId());
        Assertions.assertEquals(buyCrypto.getReputationOfUser(),this.user2.getReputation());
        Assertions.assertFalse(this.intention2U2.isActive());
    }


    private Transaction anyTransaction() {

        quotation1.setCryptocurrency(this.cryptoIntention1);
        quotation1.setDayAndTime(this.dateTime);

        quotation2.setCryptocurrency(this.cryptoIntention2);
        quotation2.setDayAndTime(this.dateTime);

        this.user1.setName("u1");
        this.user1.setEmail("1@gmai.com");
        this.user1.setAddress("calle123");
        this.user1.setPassword("123");
        this.user1.setCvu("017020456000000878653");
        this.user1.setWalletAddress("3J98t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
        this.user1.setReputation(0);
        this.user1.setNumberOfOperations(0);
        this.user1.setSuccessfulOperation(0);

        this.user2.setName("u2");
        this.user2.setEmail("2@gmai.com");
        this.user2.setAddress("calle864");
        this.user2.setPassword("123567");
        this.user2.setCvu("047020456008921878653");
        this.user2.setWalletAddress("3M58t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
        this.user2.setReputation(20);
        this.user2.setNumberOfOperations(2);
        this.user2.setSuccessfulOperation(1);

        this.intention1U1.setActive(true);
        this.intention1U1.setAmountOfOperationInPesos(Integer.valueOf("10000"));
        this.intention1U1.setOperacion(Operation.Purchase);
        this.intention1U1.setQuotation(quotation1);

        this.intention2U2.setActive(true);
        this.intention2U2.setAmountOfOperationInPesos(Integer.valueOf("10000"));
        this.intention2U2.setOperacion(Operation.Sale);
        this.intention2U2.setQuotation(quotation2);

        userRepository.save(user1);
        userRepository.save(user2);

        intentionService.userExpressesHisIntentionToBuyOrSell(intention1U1,user1.getId());
        intentionService.userExpressesHisIntentionToBuyOrSell(intention2U2,user2.getId());

        this.buyCrypto.setAmountOfOperationInPesos(Double.valueOf("270000"));
        this.buyCrypto.setAmountOfOperation(Integer.valueOf("300"));
        this.buyCrypto.setNumberOfOperations(user1.getNumberOfOperations());
        this.buyCrypto.setDayAndTimeOfOperation(LocalDateTime.now());
        this.buyCrypto.setReputationOfUser(user1.getReputation());
        this.buyCrypto.setUser(user1);
        this.buyCrypto.setIntention(this.intention1U1);
        this.buyCrypto.setStateTransaction(StateTransaction.UndefinedState);

        return this.buyCrypto;

    }

}
