package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.model.dto.UserDto;
import com.unq.desa.criptoP2P.webservice.UserController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
/*
public class UserControllerTest extends CriptoP2PApplicationTests {

    private static final String HTTP_LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private UserController userController;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void ItChecksThatTheUserListLength() throws Exception {

        assertThat(this.restTemplate.getForEntity(HTTP_LOCALHOST + port + "/userAll",
                UserDto[].class).getBody().length).isGreaterThan(0);
    }

    @Test
    public void givenAnIdUsuarioOneWhenTheUserIsSearchedAfterItIsVerifiedExists() throws Exception {
        assertThat(this.restTemplate.getForObject(HTTP_LOCALHOST + port + "/user/1",
                UserDto.class).getId()).isEqualTo(1);
    }

    @Test
    void givenAnIdUsuarioTwoWhenTheUserIsSearchedAfterItIsVerifyingThatItDoesNotExist() throws Exception {
        assertThat(this.restTemplate.getForObject(HTTP_LOCALHOST + port + "/user/1",
                UserDto.class).getId()).isNotEqualTo(2);
    }


}*/
