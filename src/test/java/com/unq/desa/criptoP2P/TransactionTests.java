package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;
import com.unq.desa.criptoP2P.model.dto.RequestTransferDto;
import com.unq.desa.criptoP2P.model.dto.TransactionDto;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.ICrytoOcurrencyRepository;
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
    private ICrytoOcurrencyRepository crytoOcurrencyRepository;
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

    private User user1;
    private Intention intention1U1;
    private TransactionDto transactionDto;
    private RequestTransferDto transferDto;
    private CryptoOcurrency cryptoOcurrency;

    @BeforeEach
    public void setUp() {
        this.dateTime = LocalDateTime.now();
        this.user1 = this.userRepository.getReferenceById(15);
        this.intention1U1 = this.intentionRepository.getReferenceById(19);
        this.cryptoOcurrency = this.crytoOcurrencyRepository.findBySymbol("TRXUSDT");
        this.transferDto = RequestTransferDto.builder()
                .id(23)
                .email("1@gmai.com")
                .symbol("TRXUSDT")
                .amountOfOperationInPesos(this.cryptoOcurrency.getPrice())
                .amountOfOperation(Integer.valueOf("300"))
                .build();

    }


    @Test
    public void givenAnyTransactionOfPurchaseWhenTransferOperationThenItsStatusIsTransferred() throws Exception {
        this.transactionDto = this.transactionService.transferOperation(user1.getEmail(),transferDto);

        Assertions.assertEquals(this.transactionDto.getStateTransaction(), StateTransaction.Transferred);
        Assertions.assertEquals(this.transactionDto.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
    }

    @Test
    public void givenAnyTransactionOfPurchaseWhenConfirmOperationThenItsStatusIsConfirm() throws Exception {

        this.transactionDto = transactionService.operationConfirm(this.user1.getTransactions().get(0).getId(),this.user1.getEmail());

        //var report=userService.generateReport(user1.getEmail(),LocalDateTime.now().toLocalDate(),LocalDateTime.now().plusDays(10).toLocalDate());
        //Assertions.assertTrue(report!= null);

        Assertions.assertEquals(this.transactionDto.getStateTransaction(), StateTransaction.Confirm);
        Assertions.assertEquals(this.transactionDto.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());

    }

    @Test
    public void givenAnyTransactionOfPurchaseWhenCancelledOperationThenItsStatusIsCanceled() throws Exception {
        this.transactionDto = this.transactionService.transferOperation(user1.getEmail(),transferDto);

        this.transactionDto = transactionService.operationCancelled(this.user1.getTransactions().get(0).getId(),this.user1.getEmail());

        Assertions.assertEquals(this.transactionDto.getStateTransaction(), StateTransaction.Cancelled);
        Assertions.assertEquals(this.transactionDto.getDayAndTimeOfOperation().getDayOfMonth(),this.dateTime.getDayOfMonth());
    }

}
