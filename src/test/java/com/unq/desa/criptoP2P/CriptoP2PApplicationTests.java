package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.model.user.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;


class CriptoP2PApplicationTests {
	User user ;
	BigInteger cvu;

	@Before("")
	public void setUp() {
		this.user = new User();
        this.cvu = new BigInteger("017020466000000878652");
	}

	@Test
	void givenARegisteredUserTheirDataIsChecked() {

		this.user = new User("Alan","Martinez","alan@gmail.com","calle falsa 123", "#A123#",this.cvu, "1GjDMGrvdw15uTRbBQNA2ExCxL8GepkM32");

		Assertions.assertEquals(this.user.getFirstName(), "Alan");
		Assertions.assertEquals(this.user.getLastName(),"Martinez");
		Assertions.assertEquals(this.user.getEmail(),"alan@gmail.com");
		Assertions.assertEquals(this.user.getAddress(),"calle falsa 123");
		Assertions.assertEquals(this.user.getCvu(),this.cvu);
		Assertions.assertEquals(this.user.getWalletAddress(),"1GjDMGrvdw15uTRbBQNA2ExCxL8GepkM32");

	}




}
