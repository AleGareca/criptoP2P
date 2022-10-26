import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.transaction.Transaction;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.service.IIntentionService;
import com.unq.desa.criptoP2P.service.ITransactionService;
import com.unq.desa.criptoP2P.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Order(1)
@Component
public class FakeData implements CommandLineRunner {
        @Autowired
        private IUserService userService;
        @Autowired
        private IIntentionService intentionService;
        @Autowired
        private ITransactionService transactionService;
        @Autowired
        private BinanceClient binanceClient;

        @Override
        public void run(String... args) throws Exception {
             LocalDateTime dateTime = LocalDateTime.now();
             List<Intention> intentionsU1 = new ArrayList<>();
             List<Transaction> transactionsU1 = new ArrayList<>();
             List<Intention> intentionsU2 = new ArrayList<>();
             List<Transaction> transactionsU2 = new ArrayList<>();
             List<Intention> intentionsU3 = new ArrayList<>();
             List<Transaction> transactionsU3 = new ArrayList<>();
             Cryptocurrency crypto1Intention1 = this.binanceClient.getCryptocurrency("ALICEUSDT");
             Cryptocurrency crypto1Intention2 = this.binanceClient.getCryptocurrency("BNBUSDT");
             Quotation quotation1 = new Quotation();
             quotation1.setCryptocurrency(crypto1Intention1);
             quotation1.setDayAndTime(dateTime);

             Quotation quotation2 = new Quotation();
             quotation2.setCryptocurrency(crypto1Intention2);
             quotation2.setDayAndTime(dateTime);


             User user1 = new User();
             user1.setFirstName("u1");
             user1.setLastName("lastName1");
             user1.setEmail("1@gmai.com");
             user1.setAddress("calle123");
             user1.setPassword("123");
             user1.setCvu("017020456000000878653");
             user1.setWalletAddress("3J98t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user1.setReputation(0);
             user1.setNumberOfOperations(0);
             user1.setSuccessfulOperation(0);
             user1.setIntentions(intentionsU1);
             user1.setTransactions(transactionsU1);

             User user2 = new User();
             user2.setFirstName("u2");
             user2.setLastName("lastName2");
             user2.setEmail("2@gmai.com");
             user2.setAddress("calle583");
             user2.setPassword("123567");
             user2.setCvu("099020456008900878653");
             user2.setWalletAddress("3E38t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user2.setReputation(0);
             user2.setNumberOfOperations(0);
             user2.setSuccessfulOperation(0);
             user2.setIntentions(intentionsU2);
             user2.setTransactions(transactionsU2);

             User user3 = new User();
             user2.setFirstName("u3");
             user2.setLastName("lastName3");
             user2.setEmail("3@gmai.com");
             user2.setAddress("calle864");
             user2.setPassword("123567");
             user2.setCvu("047020456008921878653");
             user2.setWalletAddress("3M58t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user2.setReputation(20);
             user2.setNumberOfOperations(2);
             user2.setSuccessfulOperation(1);
             user2.setIntentions(intentionsU3);
             user2.setTransactions(transactionsU3);

             userService.save(user1);
             userService.save(user2);
             userService.save(user3);

             Intention intention1U3 = new Intention();
             intention1U3.setActiveCripto(crypto1Intention1);
             intention1U3.setUserCripto(user3);
             intention1U3.setIsActive(true);
             intention1U3.setAmountOfOperationInPesos(new Integer("100000");
             intention1U3.setOperacion(Operation.Purchase);
             intention1U3.setQuotation(quotation1);

             Intention intention2U3 = new Intention();
             intention2U3.setActiveCripto(crypto1Intention2);
             intention2U3.setUserCripto(user3);
             intention2U3.setIsActive(false);
             intention2U3.setAmountOfOperationInPesos(new Integer(200000));
             intention2U3.setOperacion(Operation.Sale);
             intention2U3.setQuotation(quotation2);

             intentionService.save(intention1U3);
             intentionService.save(intention2U3);
        }
}

