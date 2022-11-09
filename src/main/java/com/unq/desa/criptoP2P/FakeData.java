package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.ICrytocurrencyRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import com.unq.desa.criptoP2P.service.IIntentionService;
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
     @Autowired
     private ICrytocurrencyRepository crytocurrencyRepository;

        @Override
        public void run(String... args) throws Exception {
             LocalDateTime dateTime = LocalDateTime.now();

             var cryptoIntention1 = new Cryptocurrency();
             var cryptoIntention2 = new Cryptocurrency();
             var cryptoIntention3 = new Cryptocurrency();
             var quotation1 = new Quotation();
             var quotation2 = new Quotation();
             var quotation3 = new Quotation();
             var user1 = new User();
             var user2 = new User();
             var user3 = new User();
             var intentionU1 = new Intention();
             var intentionU2 = new Intention();
             var intentionU3 = new Intention();

             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("ALICEUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("MATICUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("AXSUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("AAVEUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("ATOMUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("NEOUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("DOTUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("ETHUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("CAKEUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("BTCUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("BNBUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("ADAUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("TRXUSDT"));
             this.crytocurrencyRepository.save(this.binanceClient.getCryptocurrency("AUDIOUSDT"));

             cryptoIntention1 = this.crytocurrencyRepository.findBySymbol("ALICEUSDT");
             cryptoIntention2 = this.crytocurrencyRepository.findBySymbol("BNBUSDT");
             cryptoIntention3 = this.crytocurrencyRepository.findBySymbol("TRXUSDT");

             quotation1.setCryptocurrency(cryptoIntention1);
             quotation1.setDayAndTime(dateTime);

             quotation2.setCryptocurrency(cryptoIntention2);
             quotation2.setDayAndTime(dateTime);

             quotation3.setCryptocurrency(cryptoIntention3);
             quotation3.setDayAndTime(dateTime);

             user1.setName("u1");
             user1.setEmail("1@gmai.com");
             user1.setAddress("calle123");
             user1.setPassword("123");
             user1.setCvu("017020456000000878653");
             user1.setWalletAddress("3J98t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user1.setReputation(0);
             user1.setNumberOfOperations(0);
             user1.setSuccessfulOperation(0);

             user2.setName("u2");
             user2.setEmail("2@gmai.com");
             user2.setAddress("calle583");
             user2.setPassword("123567");
             user2.setCvu("099020456008900878653");
             user2.setWalletAddress("3E38t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user2.setReputation(0);
             user2.setNumberOfOperations(0);
             user2.setSuccessfulOperation(0);

             user3.setName("u3");
             user3.setEmail("3@gmai.com");
             user3.setAddress("calle864");
             user3.setPassword("123567");
             user3.setCvu("047020456008921878653");
             user3.setWalletAddress("3M58t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
             user3.setReputation(20);
             user3.setNumberOfOperations(2);
             user3.setSuccessfulOperation(1);

             intentionU1.setActive(true);
             intentionU1.setAmountOfOperationInPesos(Integer.valueOf("100000"));
             intentionU1.setOperacion(Operation.Purchase);
             intentionU1.setQuotation(quotation1);

             intentionU2.setActive(false);
             intentionU2.setAmountOfOperationInPesos(Integer.valueOf("200000"));
             intentionU2.setOperacion(Operation.Sale);
             intentionU2.setQuotation(quotation2);

             intentionU3.setActive(true);
             intentionU3.setAmountOfOperationInPesos(Integer.valueOf("270000"));
             intentionU3.setOperacion(Operation.Sale);
             intentionU3.setQuotation(quotation3);

             userRepository.save(user1);
             userRepository.save(user2);
             userRepository.save(user3);

             intentionService.userExpressesHisIntentionToBuyOrSell(intentionU1,user1.getId());
             intentionService.userExpressesHisIntentionToBuyOrSell(intentionU2,user2.getId());
             intentionService.userExpressesHisIntentionToBuyOrSell(intentionU3,user3.getId());



        }
}

