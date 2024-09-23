package com.skysoft.krd.uber;

import com.skysoft.krd.uber.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;
	@Test
	void contextLoads() {
		emailSenderService.sendEmail(
				new String[]{"raniarhr8@gmail.com","muhamadnajh98@gmail.com","tixiyi7286@nastyx.com","rovowal974@ofionk.com"},
				"Spring Security Test Subject BULK SMS 09",
				"Hello Mohammed this is test email BULK"
		);
	}

}
