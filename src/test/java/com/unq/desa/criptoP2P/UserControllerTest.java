package com.unq.desa.criptoP2P;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import com.unq.desa.criptoP2P.webservice.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



public class UserControllerTest extends CriptoP2PApplicationTests {

    private static final String HTTP_LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private UserController userController;

    @Autowired
    private TestRestTemplate restTemplate;

    private MockMvc mvc;

    @Mock
    private IUserRepository userRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void getUserTest() throws Exception {

        assertThat(this.restTemplate.getForObject(HTTP_LOCALHOST + port + "/users",
                String.class)).contains("users");

    }

}
