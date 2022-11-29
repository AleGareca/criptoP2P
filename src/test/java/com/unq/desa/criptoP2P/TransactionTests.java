package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.ITransactionRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import com.unq.desa.criptoP2P.service.UserService;
import com.unq.desa.criptoP2P.service.IIntentionService;
import com.unq.desa.criptoP2P.service.ITransactionService;
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
    private IIntentionRepository intentionRepository;
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private BinanceClient binanceClient;

    @Autowired
    private UserService userService;

    private LocalDateTime dateTime;
    private CryptoOcurrency cryptoIntention1;
    private CryptoOcurrency cryptoIntention2;

    private Quotation quotation1;
    private Quotation quotation2;
    private User user1;
    private User user2;
    private Intention intention1U1;
    private Intention intention2U2;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        this.dateTime = LocalDateTime.now();
        this.transaction = new Transaction();
        transaction = anyTransaction();
    }

    @AfterEach
    public void tearDown() {
        this.transactionRepository.deleteAll();
    }

    @Test
    public void givenAnyTransactionOfPurchaseWhenTransferOperationThenItsStatusIsTransferred() throws Exception {

        var transferDto = RequestTransferDto.builder()
                .id(20)
                .email("1@gmai.com")
                .symbol("MATICUSDT")
                .build();

        this.transaction = this.transactionService.transferOperation(user1.getEmail(),transferDto);

        Assertions.assertEquals(this.transaction.getStateTransaction(), StateTransaction.Transferred);
        Assertions.assertEquals(this.transaction.getAmountOfOperationInPesos(),Double.valueOf("270000"));
        Assertions.assertEquals(this.transaction.getAmountOfOperation(),Integer.valueOf("300"));
        Assertions.assertEquals(this.transaction.getUser().getId(),this.user1.getId());
        Assertions.assertEquals(this.transaction.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
        Assertions.assertEquals(this.transaction.getNumberOfOperations(),this.user1.getNumberOfOperations());
        Assertions.assertEquals(this.transaction.getIntention().getId(),this.intention1U1.getId());
        Assertions.assertEquals(this.transaction.getReputationOfUser(),this.user1.getReputation());
        Assertions.assertEquals(this.transaction.getShippingAddress(),this.user1.getCvu());
    }

    @Test
    public void givenAnyTransactionOfPurchaseWhenConfirmOperationThenItsStatusIsConfirm() throws Exception {

        this.transaction = transactionService.operationConfirm(transaction.getId());

        var report=userService.generateReport(user1.getEmail(),LocalDateTime.now().toLocalDate(),LocalDateTime.now().plusDays(10).toLocalDate());
        Assertions.assertTrue(report!= null);

        Assertions.assertEquals(this.transaction.getStateTransaction(), StateTransaction.Confirm);
        Assertions.assertEquals(this.transaction.getAmountOfOperationInPesos(),Double.valueOf("270000"));
        Assertions.assertEquals(this.transaction.getAmountOfOperation(),Integer.valueOf("300"));
        Assertions.assertEquals(this.transaction.getUser().getId(),this.user1.getId());
        Assertions.assertEquals(this.transaction.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
        Assertions.assertEquals(this.transaction.getNumberOfOperations(),this.user1.getNumberOfOperations());
        Assertions.assertEquals(this.transaction.getIntention().getId(),this.intention1U1.getId());
        Assertions.assertEquals(this.transaction.getReputationOfUser(),this.user1.getReputation());
    }

    @Test
    public void givenAnyTransactionOfPurchaseWhenCancelledOperationThenItsStatusIsCanceled() throws Exception {

        this.transaction = transactionService.operationCancelled(transaction.getId());

        Assertions.assertEquals(this.transaction.getStateTransaction(), StateTransaction.Cancelled);
        Assertions.assertEquals(this.transaction.getAmountOfOperationInPesos(),Double.valueOf("270000"));
        Assertions.assertEquals(this.transaction.getAmountOfOperation(),Integer.valueOf("300"));
        Assertions.assertEquals(this.transaction.getUser().getEmail(),this.user1.getEmail());
        Assertions.assertEquals(this.transaction.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
        Assertions.assertEquals(this.transaction.getNumberOfOperations(),this.user1.getNumberOfOperations());
        Assertions.assertEquals(this.transaction.getIntention().getId(),this.intention1U1.getId());
        Assertions.assertEquals(this.transaction.getReputationOfUser(),this.user1.getReputation());
        Assertions.assertFalse(this.transaction.getIntention().isActive());
    }


    private Transaction anyTransaction() {
        this.user1 = this.userRepository.getReferenceById(15);
        this.intention1U1 = this.intentionRepository.getReferenceById(20);
        this.transaction.setAmountOfOperationInPesos(Double.valueOf("270000"));
        this.transaction.setAmountOfOperation(Integer.valueOf("300"));
        this.transaction.setNumberOfOperations(user1.getNumberOfOperations());
        this.transaction.setDayAndTimeOfOperation(LocalDateTime.now());
        this.transaction.setReputationOfUser(user1.getReputation());
        this.transaction.setUser(user1);
        this.transaction.setIntention(this.intention1U1);
        this.transaction.setStateTransaction(StateTransaction.UndefinedState);

        return this.transactionRepository.save(this.transaction);

    }

}
