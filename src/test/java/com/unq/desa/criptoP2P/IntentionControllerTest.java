package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.webservice.IntentionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
/*
public class IntentionControllerTest extends CriptoP2PApplicationTests {

    private static final String HTTP_LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private IntentionController intentionController;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void contextLoads() throws Exception {
        assertThat(intentionController).isNotNull();
    }

    @Test
    public void ItChecksThatTheIntentionListLength() throws Exception {

        assertThat(this.restTemplate.getForEntity(HTTP_LOCALHOST + port + "/intentions",
                Intention[].class).getBody().length).isGreaterThan(0);
    }

    @Test
    public void givenAnIdIntentionoOneWhenTheUserIsSearchedAfterItIsVerifiedExists() throws Exception {
        assertThat(this.restTemplate.getForObject(HTTP_LOCALHOST + port + "/intention/1",
                Intention.class).getId()).isEqualTo(1);
    }

    @Test
    void givenAnIdUsuarioTwoWhenTheIntentionIsSearchedAfterItIsVerifyingThatItDoesNotExist() throws Exception {
        assertThat(this.restTemplate.getForObject(HTTP_LOCALHOST + port + "/intention/1",
                Intention.class).getId()).isNotEqualTo(2);
    }

    @Test
    public void ItChecksThatTheActiveIntentionListLength() throws Exception {

        assertThat(this.restTemplate.getForEntity(HTTP_LOCALHOST + port + "/intentionToBuyAndSell",
                Intention[].class).getBody().length).isGreaterThan(0);
    }
}
*/