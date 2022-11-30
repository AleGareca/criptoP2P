package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.client.BinanceClient;
import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoOCurrency.CryptoOcurrency;
import com.unq.desa.criptoP2P.model.dto.CryptoOcurrencyDto;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.ICrytoOcurrencyRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import com.unq.desa.criptoP2P.service.CrytoOcurrencyService;
import com.unq.desa.criptoP2P.service.IIntentionService;
import com.unq.desa.criptoP2P.service.ICrytoOcurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Order(1)
@Component
public class FakeData implements CommandLineRunner {

     @Autowired
     private IUserRepository userRepository;
     @Autowired
     private IIntentionService intentionService;
     @Autowired
     private BinanceClient binanceClient;
     @Autowired
     private ICrytoOcurrencyService crytoOcurrencyService;
     @Autowired
     private ICrytoOcurrencyRepository crytocurrencyRepository;
     @Autowired
     private MapperComponent modelMapper;

     @Override
     public void run(String... args) throws Exception {
          LocalDateTime dateTime = LocalDateTime.now();
          var cryptoIntention1 = new CryptoOcurrency();
          var cryptoIntention2 = new CryptoOcurrency();
          var cryptoIntention3 = new CryptoOcurrency();
          var quotation1 = new Quotation();
          var quotation2 = new Quotation();
          var quotation3 = new Quotation();
          var user1 = new User();
          var user2 = new User();
          var user3 = new User();
          var intentionU1 = new Intention();
          var intentionU2 = new Intention();
          var intentionU3 = new Intention();


          crytoOcurrencyService.update(this.crytocurrency( "ALICEUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("MATICUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("AXSUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("AAVEUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("ATOMUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("NEOUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("DOTUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("ETHUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("CAKEUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("BTCUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("BNBUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("ADAUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("TRXUSDT"));
          crytoOcurrencyService.update(this.crytocurrency("AUDIOUSDT"));

          cryptoIntention1 = this.crytocurrencyRepository.findBySymbol("ALICEUSDT");
          cryptoIntention2 = this.crytocurrencyRepository.findBySymbol("BNBUSDT");
          cryptoIntention3 = this.crytocurrencyRepository.findBySymbol("TRXUSDT");

          quotation1.setSymbol(cryptoIntention1.getSymbol());
          quotation1.setDayAndTime(dateTime);

          quotation2.setSymbol(cryptoIntention2.getSymbol());
          quotation2.setDayAndTime(dateTime);

          quotation3.setSymbol(cryptoIntention3.getSymbol());
          quotation3.setDayAndTime(dateTime);

          user1.setName("Ale Gareca");
          user1.setEmail("1@gmai.com");
          user1.setAddress("calle123");
          user1.setPassword("$2a$10$83CGsv2Of7.8eh3LtwZbieoD0IZ8RxGi0WThzJLFOGowcMWwxafuC");
          user1.setCvu("017020456000000878653");
          user1.setWalletAddress("3J98t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
          user1.setReputation(0);
          user1.setNumberOfOperations(0);
          user1.setSuccessfulOperation(0);

          user2.setName("Lucas Gomez");
          user2.setEmail("2@gmai.com");
          user2.setAddress("calle583");
          user2.setPassword("123567");
          user2.setCvu("099020456008900878653");
          user2.setWalletAddress("3E38t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
          user2.setReputation(0);
          user2.setNumberOfOperations(0);
          user2.setSuccessfulOperation(0);

          user3.setName("Martin Martinez");
          user3.setEmail("3@gmai.com");
          user3.setAddress("calle864");
          user3.setPassword("123567");
          user3.setCvu("047020456008921878653");
          user3.setWalletAddress("3M58t1WpEZ73CNmQviecrdhyiWrnqRhWNLy");
          user3.setReputation(20);
          user3.setNumberOfOperations(2);
          user3.setSuccessfulOperation(1);

          intentionU1.setActive(true);
          intentionU1.setAmountOfOperationInPesos(Double.valueOf("100000"));
          intentionU1.setOperacion(Operation.Purchase);
          intentionU1.setQuotation(quotation1);

          intentionU2.setActive(false);
          intentionU2.setAmountOfOperationInPesos(Double.valueOf("200000"));
          intentionU2.setOperacion(Operation.Sale);
          intentionU2.setQuotation(quotation2);

          intentionU3.setActive(true);
          intentionU3.setAmountOfOperationInPesos(Double.valueOf("0.05423"));
          intentionU3.setOperacion(Operation.Purchase);
          intentionU3.setQuotation(quotation3);

          userRepository.save(user1);
          userRepository.save(user2);
          userRepository.save(user3);

          intentionService.userExpressesHisIntentionToBuyOrSell(intentionU1, user3.getId());
          intentionService.userExpressesHisIntentionToBuyOrSell(intentionU2, user2.getId());
          intentionService.userExpressesHisIntentionToBuyOrSell(intentionU3, user1.getId());

     }

     private CryptoOcurrencyDto crytocurrency(String symbol) {
          var cryptoMapper = this.modelMapper.To(this.binanceClient.getCryptocurrency(symbol), CryptoOcurrency.class);
          var cryptoDTO = new CryptoOcurrencyDto();
          cryptoDTO.setPrice(cryptoMapper.getPrice());
          cryptoDTO.setSymbol(cryptoMapper.getSymbol());
          return cryptoDTO;
     }
}

