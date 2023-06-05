package com.nanmol.sbr.restPractice;

import com.nanmol.sbr.restPractice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestPracticeApplicationTests {

	@Autowired
	private CustomerService customerRepo;

	@Test
	void contextLoads() {
	}


}
