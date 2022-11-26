package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import com.unq.desa.criptoP2P.service.iservice.IIntentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Order(1)
@Component
public class FakeData implements CommandLineRunner {

     @Autowired
     private IUserRepository userRepository ;
     @Autowired
     private IIntentionService intentionService;

     @Autowired
     private BinanceClient binanceClient;

        @Override
        public void run(String... args) throws Exception {
             LocalDateTime dateTime = LocalDateTime.now();

             var cryptoIntention1 = this.binanceClient.getCryptocurrency("ALICEUSDT");
             var cryptoIntention2 = this.binanceClient.getCryptocurrency("BNBUSDT");
             var cryptoIntention3 = this.binanceClient.getCryptocurrency("TRXUSDT");
             var quotation1 = new Quotation();
             var quotation2 = new Quotation();
             var quotation3 = new Quotation();

             quotation1.setCryptocurrency(cryptoIntention1);
             quotation1.setDayAndTime(dateTime);

             quotation2.setCryptocurrency(cryptoIntention2);
             quotation2.setDayAndTime(dateTime);

             quotation3.setCryptocurrency(cryptoIntention3);
             quotation3.setDayAndTime(dateTime);

             var user1 = new User();
             user1.setName("u1");
             user1.setEmail("1@gmai.com");
             user1.setAddress("calle123");
             user1.setPassword("$2a$10$83CGsv2Of7.8eh3LtwZbieoD0IZ8RxGi0WThzJLFOGowcMWwxafuC");
             user1.setCvu("017020456000000878653");
             user1.setWalletAddress("3J98t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user1.setReputation(0);
             user1.setNumberOfOperations(0);
             user1.setSuccessfulOperation(0);

             var user2 = new User();
             user2.setName("u2");
             user2.setEmail("2@gmai.com");
             user2.setAddress("calle583");
             user2.setPassword("123567");
             user2.setCvu("099020456008900878653");
             user2.setWalletAddress("3E38t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user2.setReputation(0);
             user2.setNumberOfOperations(0);
             user2.setSuccessfulOperation(0);

             var user3 = new User();
             user3.setName("u3");
             user3.setEmail("3@gmai.com");
             user3.setAddress("calle864");
             user3.setPassword("123567");
             user3.setCvu("047020456008921878653");
             user3.setWalletAddress("3M58t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user3.setReputation(20);
             user3.setNumberOfOperations(2);
             user3.setSuccessfulOperation(1);

             var intention1U3 = new Intention();
             intention1U3.setActive(true);
             intention1U3.setAmountOfOperationInPesos(Integer.valueOf("100000"));
             intention1U3.setOperacion(Operation.Purchase);
             intention1U3.setQuotation(quotation1);

             var intention2U3 = new Intention();
             intention2U3.setActive(false);
             intention2U3.setAmountOfOperationInPesos(Integer.valueOf("200000"));
             intention2U3.setOperacion(Operation.Sale);
             intention2U3.setQuotation(quotation2);

             var intention3U3 = new Intention();
             intention3U3.setActive(true);
             intention3U3.setAmountOfOperationInPesos(Integer.valueOf("270000"));
             intention3U3.setOperacion(Operation.Sale);
             intention3U3.setQuotation(quotation3);

             userRepository.save(user1);
             userRepository.save(user2);
             userRepository.save(user3);

             intentionService.userExpressesHisIntentionToBuyOrSell(intention1U3,user3.getId());
             intentionService.userExpressesHisIntentionToBuyOrSell(intention2U3,user3.getId());
             intentionService.userExpressesHisIntentionToBuyOrSell(intention3U3,user2.getId());

            /* var intentions = intentionService.listIntentionsActiveOfAU();

             for ( Intention intention :  intentions ) {
                  System.out.println(intention.getId());
             }*/


        }
}

