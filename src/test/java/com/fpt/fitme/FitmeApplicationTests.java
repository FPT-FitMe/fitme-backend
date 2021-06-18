package com.fpt.fitme;

import com.fpt.fitme.repository.AppUserRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FitmeApplicationTests {

	@Autowired
	private AppUserRoleRepository appUserRoleRepository;

	@Test
	void contextLoads() {
	}

}
