import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.service.IIntentionService;
import com.unq.desa.criptoP2P.service.ITransactionService;
import com.unq.desa.criptoP2P.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class FakeDate  implements CommandLineRunner {
    @Autowired
    private ICriptoMonedaService criptoMonedaService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IIntentionService intentionService;
    @Autowired
    private ITransactionService transactionService;

    @Override
    public void run(String... args) throws Exception {

        criptoMonedaService.save(new CriptoMonedaDTO("ALICEUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("MATICUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("AXSUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("AAVEUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("ATOMUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("NEOUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("DOTUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("ETHUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("CAKEUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("BTCUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("BNBUSDT"));
        criptoMonedaService.save(new CriptoMonedaDTO("ADAUSDT"));


        //setUp
        User usuario1 = new User("user1@gmail.com", "calle 10 esquina 77", "1234",
                "1234567891234567891234", "12345678");
        User usuario2 = new User("user2@hotmail.com", "Bransen 805", "4321",
                "1234567891234567894321", "12340000");
        User usuario3 = new User("user3@yahoo.com", "Zuviria 1024", "MedialunasDeJamon",
                "1234567891234560000000", "10101109");

        usuario1 = userService.save(usuario1);
        usuario2 = userService.save(usuario2);
        usuario3 = userService.save(usuario3);
    }
}