package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.model.dto.UserDto;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;


class CriptoP2PApplicationTests {
	private User user;
	private String cvu;
	@Autowired
	private UserService userService;
	private UserDto retrievedUser;

	@Before("")
	public void setUp() {
		this.user = new User();
		this.cvu = "017020466000000878652";
		this.retrievedUser = new UserDto();
	}

	@Test
	void givenAnyUserWhenIsRecordedThenAllYourDataIsChecked() {
		this.user = this.anyUser();
		Assertions.assertEquals("Alan",this.user.getFirstName());
		Assertions.assertEquals("Martinez",this.user.getLastName());
		Assertions.assertEquals("alan@gmail.com",this.user.getEmail());
		Assertions.assertEquals("calle falsa 123",this.user.getAddress());
		Assertions.assertEquals(this.cvu,this.user.getCvu());
		Assertions.assertEquals("1GjDMGrvdw15uTRbBQNA2ExCxL8GepkM32",this.user.getWalletAddress());

	}


	public void givenAnyUserWhenTheUserIsRegisteredThenReturnItIsVerifiedThatItIsCorrect() {
		// given
		this.user = this.anyUser();
		this.userService.save(this.user);

		// when
		this.retrievedUser = this.userService.getById(1);

		// then
		Assertions.assertEquals(retrievedUser.getFirstName(),this.user.getFirstName());
		Assertions.assertEquals(retrievedUser.getLastName(),this.user.getLastName());
		Assertions.assertEquals(retrievedUser.getEmail(),this.user.getEmail());
		Assertions.assertEquals(retrievedUser.getAddress(),this.user.getAddress());
		Assertions.assertEquals(retrievedUser.getCvu(),this.user.getCvu());
		Assertions.assertEquals(retrievedUser.getWalletAddress(),this.user.getWalletAddress());
	}

	/*	@Test
        public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
                throws Exception {

            List<User> users = userService.get();

            userService.get();
            mvc.perform(get("/api/users")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    */
	private User anyUser() {
		return this.user =  User.builder().firstName("Alan").lastName("Martinez").email("alan@gmail.com")
				.address("calle falsa 123").password("#A123#").cvu(this.cvu).walletAddress("1GjDMGrvdw15uTRbBQNA2ExCxL8GepkM32").build();
	}



}
